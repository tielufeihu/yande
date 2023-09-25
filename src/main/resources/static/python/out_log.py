# from datetime import time
import time

import dateutil.utils
# -*- coding: utf-8 -*-

log_path = r"logfile3.log"
bag_log = r"bag_log.log"


def get_time_str() -> str:
    return time.strftime('%Y-%m-%d  %H:%M:%S', time.localtime())

def log_info(info):
    pass
    # file = open(log_path, mode='a', encoding= 'utf-8')
    # file.write("[info] " + info + "datatime:  " + get_time_str())
    # file.write("\n")


def log_warn(info):
    file = open(bag_log, mode='a', encoding= 'utf-8')
    file.write("[warn] " + info + "datatime:  " + get_time_str())
    file.write("\n")


def log_error(info):
    file = open(bag_log, mode='a', encoding= 'utf-8')
    file.write("[error] " + info + "datatime:  " + get_time_str())
    file.write("\n")

