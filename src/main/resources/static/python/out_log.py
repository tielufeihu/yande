# from datetime import time
import time

import dateutil.utils
# -*- coding: utf-8 -*-

log_path = r"D:\学习\projects\JavaProjects\yande\src\main\resources\static\python\logfile3.log"
bag_log = r"D:\学习\projects\JavaProjects\yande\src\main\resources\static\python\bag_log.log"


def get_time_str() -> str:
    return time.strftime('%Y-%m-%d  %H:%M:%S', time.localtime())

def log_info(info):
    pass
    # file = open(log_path, mode='a')
    # file.write("[info] " + info + "datatime:  " + get_time_str())
    # file.write("\n")


def log_warn(info):
    file = open(bag_log, mode='a')
    file.write("[warn] " + info + "datatime:  " + get_time_str())
    file.write("\n")


def log_error(info):
    file = open(bag_log, mode='a')
    file.write("[error] " + info + "datatime:  " + get_time_str())
    file.write("\n")

