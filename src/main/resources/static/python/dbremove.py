import dao_mysql
import img_file_utli
import out_log
from test import create_thumbnails_to_db
from tqdm import tqdm

def remove(path, number=0, target="G:\\yande_imgdb"):
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
            i_path = path + "\\" + i
            i_target_path = target + "\\" + i
        else:
            print("未知原因获取i_list失败！ i：{}".format(i))
            out_log.log_error("未知原因获取i_list失败！ i：{}".format(i))
            continue

        # 插入imginfo
        # print("i-id: {},i_target_path:{}".format(i_id, i_target_path))
        # 这个函数返回影响行数   mysql 的\ 同样是转移字符，因此这样要有四个
        ishave = dao_mysql.set_imginfo_url(i_target_path.replace("\\", "\\\\"),i_id)  # 重置url值

        # 如果插入失败则是重复
        if ishave != 1:
            # 检测这条记录是否有缩略图
            out_log.log_warn("更新url id：{} 失败，id不存在存在".format(i_id))
            tqdm_iterable.set_description("更新url id：{} 失败，id不存在存在".format(i_id))
            # img_file_utli.img_remove(path, i)  # 最后再删除
            img_file_utli.img_to_target(path + "\\", i, target_src="H:\\yande_imgdbNo")  # 移动到指定文件夹统一处理
            continue  # 直接执行下一个

        img_file_utli.img_to_target(path + "\\", i, target_src=target)  # 移动到目标文件夹


# remove("F:\\yande_imgdb",  target="H:\\yande_imgdb")
# remove("E:\\yande_imgdb",  target="H:\\yande_imgdb1")
# remove("D:\\yande_imgdb",  target="H:\\yande_imgdb2")