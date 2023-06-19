import time
import dateutil.utils
import img_file_utli
import out_log
import pc_util
import dao_mysql
from test import create_thumbnails_to_db
import threading

class myThread (threading.Thread):   #继承父类threading.Thread
    def __init__(self, threadID, name, counter,savePath,tagName="null",start=1,end=9999):
        threading.Thread.__init__(self)
        self.endPage = end
        self.startPage = start
        self.threadID = threadID
        self.name = name
        self.counter = counter
        self.savePath = savePath
        self.tagName = tagName
    def run(self):                   #把要执行的代码写到run函数里面 线程在创建后会直接运行run函数
        print("开始线程" + str(self.threadID))
        dealTag(savePath=self.savePath,tagName=self.tagName,start=self.startPage,end=self.endPage)
        print("线程{tid}、TagName（{tagN}）：处理完成！".format(tid=self.threadID,tagN=self.tagName))


def dealPage(url):
    bf = pc_util.reBfObj(url)
    a_s = bf.find_all('a', class_='directlink largeimg')
    re_s = []
    for i in a_s:
        # print(i.get('href'))
        try:
            url_img = i.get('href')
        except Exception as e:
            print('错误' + str(e))
            print('错误发生对象' + str(i))
            continue

        name_img = url_img.split('/').pop().replace('yande.re%', '').replace('%', '_')
        re_s.append([url_img, name_img])

    return re_s


def dealImgUrlArr(img_arr,pageNum, savePath):
    for i in range(0, len(img_arr)):
        # print(img_arr[i])
        # download_img 没问题  在这之前 先进行数据库有无判断
        i_list = img_file_utli.dealImgName(img_arr[i][1])
        # print(i_list)
        if len(i_list) >= 1:
            # print(i_list[0])  # 201041340
            ishave = dao_mysql.is_have_imginfo(i_list[0])
        else:
            return

        if ishave == -1:   # 若无此图片
            i_url = img_arr[i][0]
            i_name = img_arr[i][1]
            i_id = i_list[0]
            i_tag = i_list[1:]
            i_path = savePath + i_name
            print(i_url)
            print(i_name)
            print("{}, page:{},index:{}".format(i_id,pageNum,i+1))
            print(i_tag)
            print(i_path)
            time.sleep(1)
            # 无这个照片 开始保存和生成数据
            # 1.下载到本地
            pc_util.download_img(i_url, i_name, path=savePath)
            # 2.插入 imginfo 表数据
            dao_mysql.add_imginfo(i_id, i_path)
            # 3.插入 imgtag 表数据
            for tag_name in i_tag:
                # 把这个tag存数据库
                # 先查看 tag表是否有这个 tag
                tag_id = dao_mysql.tag_name_to_tag_id(tag_name)
                if tag_id == -1:
                    # 如果没有 则添加这个tag
                    dao_mysql.add_tag(tag_name, "", "", "", "0")
                    tag_id = dao_mysql.tag_name_to_tag_id(tag_name)
                # 添加这个imgtag
                dao_mysql.add_img_tag(i_id, tag_id)

            # 4. 生成和和存储缩略图
            re = create_thumbnails_to_db(i_path, i_id)
            if re is False:
                print("id:{}生成缩略图失败！".format(i_id))
                out_log.log_error("id:{}生成缩略图失败！".format(i_id))
                # 回滚
                dao_mysql.del_imginfo(i_id)  # 删数据库
                img_file_utli.img_remove(savePath, i_name)  # 删文件
                # break
            else:
                out_log.log_info("插入成功！id{}".format(i_id))  #
                print("插入成功！id{}".format(i_id))
                # 该图片所有tag加一
                for tag in i_tag:
                    tag_id = dao_mysql.tag_name_to_tag_id(tag)
                    dao_mysql.tag_add1_from_id(tag_id)
        else:
            pass
            # 更新url
            # print("更新一个图片url")
            # dao_mysql.set_imginfo_url(savePath + img_arr[i][1], i_list[0])


def dealTag(savePath, tagName="null", start=1, end=99999):
    # 循环抓取 100 344, tagName="null"
    for i in range(start, end):
        print('--------------------------------------------' + str(i) + '--------------------------------------------')
        out_log.log_info("--------------------------------------------" + str(i) +
                         "--------------------------------------------")
        if tagName == "null":
            target = 'https://yande.re/post?page=' + str(i)
        else:
            target = 'https://yande.re/post?page=' + str(i) + '&tags=' + tagName

        sign = -1
        imgUrl_arr_t = []
        while sign == -1:
            try:
                imgUrl_arr_t = dealPage(target)
                sign = 1
            except Exception as e:
                print(e)
                print('等待30秒重试')
                time.sleep(30)
                sign = -1
            if len(imgUrl_arr_t) == 0:
                sign = -1
                # print('等待30秒重试')
                # time.sleep(30)
                break
        dealImgUrlArr(imgUrl_arr_t, i, savePath)


def getNextPage():
    pass


def multithreadingDownLoad(savePath,tagName='null',start=1,offset=100,th_count=5):
    # 计算 th_count 个线程分别的开始结束位置
    th_offset = offset // th_count
    th_start = start
    th_end = start + th_offset

    threads = []
    for i in range(th_count):
        threads.append(myThread(i, "Thread-{}".format(i), i, savePath=savePath,tagName=tagName, start=th_start, end=th_end))
        th_start += th_offset
        th_end += th_offset

    for th in threads:
        th.start()

    for th in threads:
        th.join()

    print("任务(tagName={},start={},offset={})已完成！".format(tagName,start,offset))




multithreadingDownLoad("H:/yande_imgdb4/",start=1,offset=15,th_count=5)

