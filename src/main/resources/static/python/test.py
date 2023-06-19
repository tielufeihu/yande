import time
import random

import img_file_utli
import dateutil
import out_log
import dao_mysql
from tqdm import tqdm
import opencv_img_tosql

tags = ["ocean","sky","person","car","pen","flower","python",
        "c++","java","asus","dell","hp","intel","FuGuang","YangZhangGui",
        "drop","table","from","database","MilkTea","Milk","tea","software","engineer",
        "bookshelf","trashbin","computer","laptop","macbook","pycharm","man","woman",
        "helicopter","armed","strong","stranger","relationship","teapot","hair","bubble","pants",
        "shoes","teeth","bottle","WASP","asian","orientian","headphone","earphone","glasses",
        "tissue","random","import","tags","important","book","door","lock","keys","shampoo","watch",
        "petak","amazon","USA","gun","raffle","pistol","snipper","cup","keyboard","linear_algebra",
        "eignevalue","eignevector","Son_ChenRanHan","doom","dman","switch","lamp","hang","drug","hospital",
        "building_site","hall","television","telephone","determinent","vector","tensor","trivial","ring",
        "group","column","rank","space","nullspace","transform","linear","non-linear","algebra",
        "abstract","beautiful","soup","youbad","no","ok","one","two","three","row","echolon","form",
        "matrix","orthogonal","normal","fortainpen","traffic_light","subway","track","Vehicles","plane",
        "bomber","train","coach","mouse","cursor","smart","wise","knowledgeable","disabled","bi-polar",
        "air-condiction","heater","money","cell_phone","buck","bicycle","bike","egg","millet","rice"]


# 注释版
def dealDirAllAll(path, number=0, target="G:\\yande_imgdb"):
    all_file_name = img_file_utli.getDirAllFile(path)
    if number <= 0:
        tqdm_iterable = tqdm(all_file_name)
    else:
        tqdm_iterable = tqdm(all_file_name[:number])
        tqdm_iterable.set_description("当前进度：")

    for i in tqdm_iterable:  # 测试3条
        i_list = img_file_utli.dealImgName(i)
        if len(i_list) >= 1:
            i_id = i_list[0]
            i_tag = i_list[1:]
            i_path = path + "\\" + i
            i_target_path = target + "\\" + i
        else:
            print("未知原因获取i_list失败！ i：{}".format(i))
            out_log.log_error("未知原因获取i_list失败！ i：{}".format(i))
            continue

        # 插入imginfo
        # print("i-id: {},i_target_path:{}".format(i_id, i_target_path))
        # 这个函数返回影响行数   mysql 的\ 同样是转移字符，因此这样要有四个
        ishave = dao_mysql.add_imginfo(i_id, i_target_path.replace("\\", "\\\\"))
        # 如果插入失败则是重复
        if ishave != 1:
            # 检测这条记录是否有缩略图
            out_log.log_warn("插入id：{} 失败，已存在".format(i_id))
            tqdm_iterable.set_description("插入id：{} 失败，已存在".format(i_id))
            is_hava_slt = dao_mysql.get_img_blob(i_id)
            if is_hava_slt is None:
                # 生成
                create_thumbnails_to_db(i_path, i_id)
            img_file_utli.img_remove(path, i)  # 最后再删除
            continue  # 直接执行下一个

        # 插入tag_img
        for tag in i_tag:
            # 把这个tag存数据库
            # 先查看 tag表是否有这个 tag
            tag_id = dao_mysql.tag_name_to_tag_id(tag)
            # print("tag_id: "+str(tag_id))
            if tag_id == -1:
                # 如果没有 则添加这个tag
                dao_mysql.add_tag(tag, "", "", "", "0")
                tag_id = dao_mysql.tag_name_to_tag_id(tag)
            # 添加这个imgtag
            # print("添加这个imgtag :{}  ,  {}  ,  {}".format(i_id, tag_id, tag))
            # 错误原因 ： imgtag表是两个外键 这里传递的 参数 有误！  add_img_tag(img_id, tag_id, tag_name)
            dao_mysql.add_img_tag(i_id, tag_id)


        # 缩略图
        re = create_thumbnails_to_db(i_path, i_id)
        if re is False:
            print("id:{}生成缩略图失败！".format(i_id))
            out_log.log_error("id:{}生成缩略图失败！".format(i_id))
            # 回滚
            dao_mysql.del_imginfo(i_id)  # 删数据库
            img_file_utli.img_remove(path, i)  # 删文件
            # break
        else:
            out_log.log_info("插入成功！id{}".format(i_id))  #
            print("插入成功！id{}".format(i_id))
            img_file_utli.img_to_target(path + "\\", i, target_src=target)
            # 该图片所有tag加一
            for tag in i_tag:
                tag_id = dao_mysql.tag_name_to_tag_id(tag)
                dao_mysql.tag_add1_from_id(tag_id)



def reCreateToDirNoMove(path, number=0):
    all_file_name = img_file_utli.getDirAllFile(path)
    if number <= 0:
        tqdm_iterable = tqdm(all_file_name)
    else:
        tqdm_iterable = tqdm(all_file_name[:number])
        tqdm_iterable.set_description("当前进度：")

    for i in tqdm_iterable:
        i_list = img_file_utli.dealImgName(i)
        if len(i_list) >= 1:
            i_id = i_list[0]
            i_tag = i_list[1:]
            i_path = path + "\\" + i   # target="G:\\yande_imgdb"
        else:
            print("未知原因获取i_list失败！ i：{}".format(i))
            out_log.log_error("未知原因获取i_list失败！ i：{}".format(i))
            continue


        ishave = dao_mysql.add_imginfo(i_id, i_path.replace("\\", "\\\\"))
        # 如果插入失败则是重复
        if ishave != 1:
            # 检测这条记录是否有缩略图
            # tqdm_iterable.set_description("插入id：{} 失败，已存在".format(i_id))
            print("插入id：{} 失败，已存在".format(i_id))
            #is_hava_slt = dao_mysql.get_img_blob(i_id)
            #if is_hava_slt is None:
            #   # 生成
            #    create_thumbnails_to_db(i_path, i_id)
            # img_file_utli.img_remove(path, i)  # 最后再删除
            continue  # 直接执行下一个

        # 插入tag_img
        for tag in i_tag:
            # 把这个tag存数据库
            # 先查看 tag表是否有这个 tag
            tag_id = dao_mysql.tag_name_to_tag_id(tag)
            # print("tag_id: "+str(tag_id))
            if tag_id == -1:
                # 如果没有 则添加这个tag
                dao_mysql.add_tag(tag, "", "", "", "0")
                tag_id = dao_mysql.tag_name_to_tag_id(tag)
            # 添加这个imgtag
            dao_mysql.add_img_tag(i_id, tag_id)

        # 缩略图
        re = create_thumbnails_to_db(i_path, i_id)
        if re is False:
            print("id:{}生成缩略图失败！".format(i_id))
            out_log.log_error("id:{}生成缩略图失败！".format(i_id))
            # 回滚
            dao_mysql.del_imginfo(i_id)  # 删数据库
            # img_file_utli.img_remove(path, i)  # 删文件
            # break
        else:
            out_log.log_info("插入成功！id{}".format(i_id))  #
            print("插入成功！id{}".format(i_id))
            # 该图片所有tag加一
            for tag in i_tag:
                tag_id = dao_mysql.tag_name_to_tag_id(tag)
                dao_mysql.tag_add1_from_id(tag_id)


# 不改变原位置
def randomTag(count=10):
    rTags = []
    for i in range(count):
        rTags.append(random.choice(tags))
    return rTags


def dealDirAllAllNoMove(all_file_name,path,startIoc=1100):
    tqdm_iterable = tqdm(all_file_name)
    tqdm_iterable.set_description("当前进度：")
    iox = startIoc
    for i in tqdm_iterable:  # 测试3条
        # i_list = img_file_utli.dealImgName(i)
        i_id = iox
        iox = iox + 1
        i_tag = randomTag(random.randint(5,20))
        i_path = path + "\\" + i
        i_target_path = path + "\\" + i

        # 插入imginfo
        # print("i-id: {},i_target_path:{}".format(i_id, i_target_path))
        # 这个函数返回影响行数   mysql 的\ 同样是转移字符，因此这样要有四个
        ishave = dao_mysql.add_imginfo(i_id, i_target_path.replace("\\", "\\\\"))
        # 如果插入失败则是重复
        if ishave != 1:
            # 检测这条记录是否有缩略图
            out_log.log_warn("插入id：{} 失败，已存在".format(i_id))
            tqdm_iterable.set_description("插入id：{} 失败，已存在".format(i_id))
            is_hava_slt = dao_mysql.get_img_blob(i_id)
            if is_hava_slt is None:
                # 生成
                create_thumbnails_to_db(i_path, i_id)
            # img_file_utli.img_remove(path, i)  # 最后再删除
            continue  # 直接执行下一个

        # 插入tag_img
        for tag in i_tag:
            # 把这个tag存数据库
            # 先查看 tag表是否有这个 tag
            tag_id = dao_mysql.tag_name_to_tag_id(tag)
            # print("tag_id: "+str(tag_id))
            if tag_id == -1:
                # 如果没有 则添加这个tag
                dao_mysql.add_tag(tag, "", "", "", "0")
                tag_id = dao_mysql.tag_name_to_tag_id(tag)
            # 添加这个imgtag
            # print("添加这个imgtag :{}  ,  {}  ,  {}".format(i_id, tag_id, tag))
            # 错误原因 ： imgtag表是两个外键 这里传递的 参数 有误！  add_img_tag(img_id, tag_id, tag_name)
            dao_mysql.add_img_tag(i_id, tag_id)


        # 缩略图
        re = create_thumbnails_to_db(i_path, i_id)
        if re is False:
            print("id:{}生成缩略图失败！".format(i_id))
            out_log.log_error("id:{}生成缩略图失败！".format(i_id))
            # 回滚
            dao_mysql.del_imginfo(i_id)  # 删数据库
            img_file_utli.img_remove(path, i)  # 删文件
            # break
        else:
            out_log.log_info("插入成功！id{}".format(i_id))  #
            print("插入成功！id{}".format(i_id))
            # 该图片所有tag加一
            for tag in i_tag:
                tag_id = dao_mysql.tag_name_to_tag_id(tag)
                dao_mysql.tag_add1_from_id(tag_id)


def reAddImgTag(path):
    all_file_name = img_file_utli.getDirAllFile(path)
    tqdm_iterable = tqdm(all_file_name)
    # tqdm_iterable.set_description("当前进度：")
    for i in tqdm_iterable:  # 测试3条
        issign = 1
        tqdm_iterable.set_description("当前处理的item： {}  \n".format(i))
        tag_list = img_file_utli.dealImgName(i)
        if len(tag_list) >= 1:
            tid = tag_list[0]
            for tag in tag_list[1:]:
                t_tag_id = dao_mysql.tag_name_to_tag_id(tag)
                if t_tag_id == -1:
                    dao_mysql.add_tag(tag, "", "", "")
                    t_tag_id = dao_mysql.tag_name_to_tag_id(tag)
                # 添加这个imgtag
                if t_tag_id != -1:
                    dao_mysql.add_img_tag(tid, t_tag_id, tag)
                else:
                    out_log.log_warn("生成tag时出错，跳过该图片{}，：tag：{}".format(tag_list[0], tag))
                    issign = -1
                    continue
        if issign == 1:
            out_log.log_info("插入成功！id{}".format(tid))
            img_file_utli.img_to_target(path + "\\", i)


# 执行一次 生成缩略图+存数据库  成功
def create_thumbnails_to_db(path, tid):
    img = opencv_img_tosql.get_thumbnails(path)
    if img is not None:
        imgstr = opencv_img_tosql.img_to_blog(img)
        dao_mysql.insert_img_blob(imgstr, tid)
        return True
    else:
        return False


# 对数据库全部内容执行
def all_create_thumbnails_to_db():
    all_data = dao_mysql.select_all_imginfo()
    tqdm_iterable = tqdm(all_data)
    for i in tqdm_iterable:
        is_have_thumbnails = dao_mysql.get_img_blob(i[0])
        # print("i[0] {} , i[1] {}".format(i[0],i[1]))
        if is_have_thumbnails is not None:
            re = create_thumbnails_to_db(i[1], i[0])
            if re != -1:
                print("id:{} 生成插入成功！".format(i[0]))
                return 1
            else:
                print("id{} 缩略图生成失败！！".format(i[0]))
                return -1
        else:
            print("id{} 已有缩略图！".format(i[0]))
            return 0


# 计算所有tag的图片数量
def count_all_tag_img_count():
    all_tags = dao_mysql.get_all_tag()
    tqdm_iterable = tqdm(all_tags)

    for tag_item in tqdm_iterable:
        tag_count = dao_mysql.countTag(tag_item[0])
        tag_id = tag_item[0]
        re = dao_mysql.set_tag_count(tag_id, tag_count)
        if re == 1:
            print("tag:{} set count :{}".format(tag_id,tag_count))



