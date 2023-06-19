import math
import numpy as np
import cv2


def open_img(path):
    try:
        cv2.imread(path)
    except:
        return None
    return cv2.imread(path)


def save_img(img, name, path="F:\\target\\"):
    cv2.imwrite(path + name, img)


def resize(img, area=160000):
    height, width = img.shape[:2]
    # 根据基准重置大小
    nn = area / width / height
    n = math.sqrt(nn)
    new_width = width * n
    new_height = height * n
    new_size = (int(new_width), int(new_height))
    new_img = cv2.resize(img, new_size, interpolation=cv2.INTER_AREA)
    return new_img


def img_to_blog(img):
    byte = cv2.imencode('.jpg', img)[1]
    imgstring = np.array(byte).tobytes()
    # print(imgstring)
    # 显示
    # cv2.imshow("new_img", new_img)
    # save_img(new_img, "good.jpg")
    # cv2.waitKey(0)
    # img = cv2.imread("F:\\201023117_20anus_20censored_20cum_20ken__28coffee_michikusa_29_20naked_20nipples_20pussy_20pussy_juice_20reiuji_utsuho_20thighhighs_20touhou.jpg",-1)
    return imgstring


# 获取该图片的缩率图
def get_thumbnails(path):
    new_img = None
    img = open_img(path)
    if img is not None:
        new_img = resize(img)

    return new_img

#
#
# img = open_img("E:\\20999734_20animal_ears_20azur_lane_20horns_20kashino__28azur_lane_29_20lactation_20nipples_20ogre_craft_20topless.jpg")
#
# cv2.imshow("img", img)