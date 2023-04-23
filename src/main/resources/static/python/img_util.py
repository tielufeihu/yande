import math

import cv2


def open_img(path):
    return cv2.imread(path)


def save_img(img):
    cv2.imwrite("F:\\" + "保存成功.jpg", img)


def img_to_byte(img):
    pass