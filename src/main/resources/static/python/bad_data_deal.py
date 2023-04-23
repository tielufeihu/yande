import opencv_img_tosql
import dao_mysql
import os
from tqdm import tqdm

def text1():
    # print("text1")
    # re = have_shrink_img("2067")
    # print(re)
    # re = have_file_img(
    #     "F:\\yande_imgdb\\2070585_20bra_20breasts_20censored_20crease_20cum_20gangbang_20nipples_20nopan_20penis_20pubic_hair_20pussy_20sex_20shirt_lift_20urushihara_satoshi.jpg")
    # print(re)

    # re = bad_img_deal("20916857", "F:\\yande_imgdb\\20916857_20bondage_20cum_20daria_leonova_20lactation_20megaten_20naked_20nipples_20penis_20persona_20persona_5_20pussy_20sex_20takamaki_anne_20thighhighs_20uncensored.jpg")
    # print(re)

    pass


def have_shrink_img(img_id):
    """
    是否有 正常的 缩略图
    :return: bool类型 若有正常的缩略图返回 ture 否则 False
    """
    shrink_img = dao_mysql.get_img_blob(img_id)
    if shrink_img is None:
        return False
    lenn = len(shrink_img)
    if lenn <= 512:
        return False
    else:
        return True


def have_file_img(path):
    """
    是否有 正常的  img文件
    :return: bool类型 若有正常的img文件返回 ture 否则 False
    """
    new_img = None
    try:
        lenn = os.path.getsize(path)
    except:
        # print("无法打开文件"+path)
        return False
    # print(lenn)
    if lenn <= 90000:
        return False
    else:
        return True


def del_img_file(path):
    try:
        os.remove(path)
    except:
        print(path + "删除文件失败！")


def del_imgInfo_fromId(img_id, path):
    """
    :param path:
    :param img_id:
    :return:
    """
    # 1.删除所有有关这个img_id 的 tag
    dao_mysql.del_img_tags(img_id)
    # 2.删除此img_id imginfo
    dao_mysql.del_imginfo(img_id)
    # 3.删除此文件
    del_img_file(path)


def reCreate_shrink_img_to_db(path, tid):
    """
    重新生成 缩略图 到数据库
    :param path: 图片在文件中的路径
    :param tid:  图片id
    :return:    成功返回True 失败返回False
    """
    img = opencv_img_tosql.get_thumbnails(path)
    if img is not None:
        imgstr = opencv_img_tosql.img_to_blog(img)
        dao_mysql.insert_img_blob(imgstr, tid)
        return True
    else:
        return False


def bad_img_deal(img_id: str, img_path: str) -> int:
    """
    判断单个 img 是否存在异常 并且解决异常
    :param img_path:
    :param img_id:
    :return:  如果无异常 返回0，如果有异常且解决异常返回1， 如果无法解决则删除这条记录及其外键依赖返回 -1
    """
    if not have_file_img(img_path):
        del_imgInfo_fromId(img_id, img_path)
        return -1

    if not have_shrink_img(img_id):
        # 1.尝试生成缩略图
        isCreate = reCreate_shrink_img_to_db(img_path, img_id)
        if isCreate:
            # 成功后返回 1
            return 1
        else:
            # 删除这条记录！
            del_imgInfo_fromId(img_id, img_path)
            return -1

    return 0


# 清理数据库 失效记录 或者不完整记录
def clear_imgdb():
    sql = dao_mysql.select_all_imginfo_sql
    print(sql)
    re = dao_mysql.execute_sql(sql, mode="select")
    # print(re)
    tqdm_itor = tqdm(re)
    for i in tqdm_itor:
        if len(i) >= 2:
            id_i = i[0]
            path_i = i[1]
            re = bad_img_deal(id_i, path_i)
            if re == 0:
                # print(str(id_i) + ": 为正常图片")
                pass
            elif re == 1:
                print(str(id_i) + ": 缺少缩略图已经重新生成")
            elif re == -1:
                print(str(id_i) + ": 找不到文件已删除相关记录")


# text1()
# clear_imgdb()
bag_arr = []

for i in bag_arr:
    re = del_imgInfo_fromId(i,dao_mysql.get_imgPath_from_imgId(i))