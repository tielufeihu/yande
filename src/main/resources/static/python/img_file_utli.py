import os
import shutil
import traceback

import out_log


# -*- coding: utf-8 -*-
# coding=utf-8

def getDirAllFile(file_dir) -> list:
    for root, dirs, files in os.walk(file_dir):
        # print("root", root)  # 当前目录路径
        # print("dirs", dirs)  # 当前路径下所有子目录
        # print("files", files)  # 当前路径下所有非目录子文件
        # t = files[0]
        # print(t)
        # print(type(t))
        # print(type(files))
        return files


def dealImgName(imgName: str) -> list:
    strlist = []
    try:
        # s = imgName.split(".")
        st = imgName.replace(".jpg", "")
        strlist = st.split("_20")
    except:
        out_log.log_error("文件名转换时出错！ 文件名：" + imgName)

    return strlist


def dealDirToLList(file_dir) -> list:
    llist = []
    name_list = getDirAllFile(file_dir)
    for name in name_list:
        tag_list = dealImgName(name)
        llist.append(tag_list)
    return llist


# 文件从一个目录移动到另一个目录
def img_to_target(source_src, file_name, target_src='F:\\yande_imgdb\\'):
    # print('from : ' + source_src)
    # print('to : ' + target_src)
    try:
        # cmd = 'chmod -R +x ' + src_path
        # os.popen(cmd)
        f_src = os.path.join(source_src, file_name)
        if not os.path.exists(target_src):
            os.mkdir(target_src)
        f_dst = os.path.join(target_src, file_name)
        shutil.move(f_src, f_dst)
    except Exception as e:
        print('move_file ERROR: ' + e)
        traceback.print_exc()


# 删除
def img_remove(source_src, file_name):
    try:
        os.remove(source_src + "\\" + file_name)
    except:
        out_log.log_error("删除文件失败！{}".format(source_src + "\\" + file_name))


# 转换为blog
def convertToBinaryData(filepath, filename):
    # Convert digital data to binary format
    with open(filepath + "\\" + filename, 'rb') as file:
        blob_data = file.read()
    return blob_data
