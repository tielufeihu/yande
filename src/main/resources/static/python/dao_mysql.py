import pymysql
import out_log
import img_file_utli
import img_util
from tqdm import tqdm


def get_conn():
    conn = pymysql.connect(host='127.0.0.1'  # 连接名称，默认127.0.0.1
                           , user='root'  # 用户名
                           , passwd='986977'  # 密码
                           , port=3306  # 端口，默认为3306
                           , db='yande'  # 数据库名称
                           , charset='utf8'  # 字符编码
                           )
    return conn


# 查重插入 INSERT INTO `yande`.`tag` (`name`, `message`, `cn_name`, `class`) SELECT '{name}', '{message}','{cn_name}',
# '{clazz}' FROM DUAL WHERE NOT EXISTS(SELECT name FROM imginfo WHERE name = '{name}')
# 字段 clsaa 改名 clazz   避免保留字冲突
add_tag_sql = "INSERT INTO `yande`.`tag` (`name`, `message`, `cn_name`, `clazz`, `img_count`) SELECT '{name}', '{message}','{cn_name}'" \
              ", '{clazz}', '{img_count}' FROM DUAL WHERE NOT EXISTS(SELECT name FROM tag WHERE name = '{name}');"
add_imginfo_sql = "INSERT INTO `yande`.`imginfo` (`id`, `path`) SELECT '{tid}', '{path}' FROM DUAL WHERE NOT EXISTS(" \
                  "SELECT id FROM imginfo WHERE id = '{tid}'); "
add_img_tag_sql = "INSERT INTO `yande`.`img_tag` (`img_id`, `tag_id`) VALUES ('{}', '{}');"
tag_name_to_tag_id_sql = "SELECT `id` FROM `yande`.`tag` WHERE `name` = '{}';"
select_imginfo_sql = "SELECT id FROM `yande`.`imginfo` WHERE `id` = '{}';"
insert_blob_img_sql = """ UPDATE `yande`.imginfo SET `img` = %s WHERE `id` = %s"""
select_all_imginfo_sql = "SELECT `id`,`path`  FROM  `yande`.`imginfo`;"
get_img_blob_sql = "SELECT img FROM `yande`.`imginfo` WHERE `id` = '{}';"
select_all_imginfo_path_sql = "SELECT `id`,`path`  FROM  `yande`.`imginfo`;"
del_imginfo_sql = "DELETE FROM `yande`.`imginfo` WHERE `id` = '{}'; "
del_img_tags_sql = "DELETE FROM `yande`.`img_tag` WHERE `img_id` = '{}'"
get_tag_img_count_sql = "SELECT COUNT(*) FROM `yande`.`img_tag` WHERE `tag_id` = '{}'"
get_all_tag_sql = "SELECT * FROM `yande`.`tag`"
set_tag_count_sql = "UPDATE `yande`.`tag` SET `img_count` = '{}' WHERE `id` = '{}'"
tag_add1_sql = "UPDATE `yande`.`tag` SET `img_count` = `img_count` + 1 WHERE `id` = '{}'"
set_imginfo_url_sql = "UPDATE `yande`.`imginfo` SET `path` = '{}' WHERE `id` = '{}'"
select_all_imgtag_sql = "SELECT * FROM `yande`.`img_tag`"
tag_id_to_tag_name_sql = "SELECT NAME FROM `yande`.`tag` WHERE `id` = '{}'"
set_imgtag_tagname_sql = "UPDATE `yande`.`img_tag` SET `tag_name` = '{}' WHERE `img_id` = '{}' AND `tag_id` = '{}'"
get_imgPath_from_imgId_sql = "SELECT PATH FROM `yande`.`imginfo` WHERE `id` = '{}'"

# UPDATE `yande`.`tag` SET `message` = '或许是jk吧' WHERE `id` = '26';

def insert_img_blob(img_blog, imginfo_id):
    conn = get_conn()
    try:
        cursor = conn.cursor()
        sql_insert_blob_query = insert_blob_img_sql
        # file = convertToBinaryData(biodataFile)
        # Convert data into tuple format

        insert_blob_tuple = (img_blog, imginfo_id)
        result = cursor.execute(sql_insert_blob_query, insert_blob_tuple)
        conn.commit()
        out_log.log_info("{tid} 生成缩略图存数据库成功！".format(tid=imginfo_id))
        # print("Image and file inserted successfully as a BLOB into python_employee table", result)
        cursor.close()
        return 1
    except:
        # print("Failed inserting BLOB data into MySQL table {}")
        out_log.log_error("{tid} 生成缩略图存数据库失败！！".format(tid=imginfo_id))
        return -1
    finally:
        conn.close()
        # print("MySQL connection is closed")


def execute_sql(tsql, mode='insert'):
    conn = get_conn()
    data = -1
    try:
        cur = conn.cursor()  # for i in data:  # 打印数据
        #     print(i)
        cur.execute(tsql)  # 执行SQL语句
        conn.commit()  # 提交
        if mode == 'insert' or mode == 'update' or mode == 'delete':
            data = cur.rowcount
        elif mode == 'select':
            data = cur.fetchall()  # 通过fetchall方法获得数据
        cur.close()  # 关闭游标
        out_log.log_info("执行语句" + tsql + "：成功！  ")
    except Exception as e:
        print("读取数据库出错！ 错误信息：" + str(e))
        out_log.log_error("执行语句" + tsql + "时出错")
        conn.rollback()
    finally:
        conn.close()  # 关闭连接
    return data


def add_tag(name, message, cn_name, clazz, count):
    sql = add_tag_sql.format(name=name, message=message, cn_name=cn_name, clazz=clazz, img_count=count)
    # print(sql)
    re = execute_sql(sql)
    return re


def add_imginfo(tid, path):
    sql = add_imginfo_sql.format(tid=tid, path=path)
    # print(sql)
    re = execute_sql(sql)
    # print("add_imginfo" + str(re))
    return re


def add_img_tag(img_id, tag_id):
    sql = add_img_tag_sql.format(img_id, tag_id)
    # print("add_img_tag sql：" + str(sql))
    re = execute_sql(sql, mode='insert')
    return re


def set_imgtag_tagname(tagname, imgid, imgtagid):
    sql = set_imgtag_tagname_sql.format(tagname, imgid, imgtagid)
    re = execute_sql(sql, mode="update")
    return re


def tag_name_to_tag_id(tagname):
    sql = tag_name_to_tag_id_sql.format(tagname)
    re = execute_sql(sql, mode='select')

    if len(re) != 0:
        return (re[0])[0]
    else:
        return -1


def tag_id_to_tag_name(tagid):
    sql = tag_id_to_tag_name_sql.format(tagid)
    re = execute_sql(sql, mode='select')

    if len(re) != 0:
        return (re[0])[0]
    else:
        return -1


def is_have_imginfo(tid):
    sql = select_imginfo_sql.format(tid)
    re = execute_sql(sql, mode='select')
    # print("is_have_imginfo" + str(re))
    if len(re) > 0:
        return (re[0])[0]
    else:
        return -1


def select_all_imginfo():
    re = execute_sql(select_all_imginfo_sql, mode='select')
    return re


def get_img_blob(tid):
    sql = get_img_blob_sql.format(tid)
    re = execute_sql(sql, mode='select')
    return (re[0])[0]


def del_imginfo(tid):
    sql = del_imginfo_sql.format(tid)
    re = execute_sql(sql, mode="delete")
    return re


def del_img_tags(tid):
    sql = "DELETE FROM `yande`.`img_tag` WHERE `img_id` = '{}'".format(tid)
    re = execute_sql(sql, mode="delete")
    return re


def countTag(tag_id):
    sqlcountTag = get_tag_img_count_sql.format(tag_id)
    re = execute_sql(sqlcountTag, mode="select")
    return (re[0])[0]


def get_all_tag():
    sqlget_all_tag = get_all_tag_sql
    re = execute_sql(sqlget_all_tag, mode="select")
    return re


def select_all_imgtag():
    sqlget_all_tag = select_all_imgtag_sql
    re = execute_sql(sqlget_all_tag, mode="select")
    return re


def get_imgPath_from_imgId(tid):
    sql = get_imgPath_from_imgId_sql.format(tid)
    re = execute_sql(sql, mode="select")
    return (re[0])[0]

def tag_add1_from_id(tag_id):
    sql = tag_add1_sql.format(tag_id)
    re = execute_sql(sql, mode="update")
    return re


def set_tag_count(tag_id, tag_count):
    sql = set_tag_count_sql.format(tag_count, tag_id)
    re = execute_sql(sql, mode="update")
    return re


def set_imginfo_url(new_url, img_id):
    sql = set_imginfo_url_sql.format(new_url, img_id)
    re = execute_sql(sql, mode="delete")
    return re