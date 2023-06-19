from bs4 import BeautifulSoup
import requests
import time

headers = {
    'method': 'GET',
    'sec-ch-ua': '".Not/A)Brand";v = "99", "Google Chrome";v = "103", "Chromium";v = "103"',
    'user-agent': 'Mozilla / 5.0(Linux;Android6.0;Nexus5Build / MRA58N) AppleWebKit / 537.36(KHTML, likeGecko) '
                  'Chrome / 103.0.0.0Mobile Safari / 537.36 '
}


def print_html(target):
    req = requests.get(url=target)
    html = req.text
    print(html)


def reBfObj(target):
    # req.add_header('user-agent', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)
    # Chrome/65.0.3325.181 Safari/537.36')
    req = requests.session()
    req.headers.update()
    # 添加请求头 伪装成浏览器
    while True:
        try:
            req = requests.get(url=target, headers=headers)
            html = req.text
        except Exception as e:
            print(e)
            print('等待30秒重试')
            time.sleep(30)
        if req.__class__ == requests.models.Response:
            break

    # 设置访问间隔防止被断开
    time.sleep(0.5)
    bf = BeautifulSoup(html, features="html.parser")
    return bf


def gdk_to_utd8(str_t):
    return str_t.encode('latin-1', "ignore").decode('cp936', "ignore").encode('utf-8').decode('utf-8')


# 网络图片路径为url
def download_img(url_t, img_name=None, path='E://lolita_fashion//'):
    # 指定图片保存位置在 path 目录下，设置图片文件名为 img_name
    # 若 未输入名称 则使用时间戳作为名称
    if img_name is None:
        img_name = str(int(time.time() * 1000000000))
        img_name = img_name + '.jpg'

    # 获取url上的图片
    sign = -1
    while sign == -1:
        try:
            req = requests.get(url=url_t, headers=headers)
            if req.__class__ == requests.models.Response:
                break
        except Exception as e:
            print(e)
            print('等待30秒重试')
            time.sleep(30)

    time.sleep(0.5)
    # 创建并打开abc.jpg文件，通过r.content写入url上的图片
    # 这和 try ... finally是一样的，但是代码更佳简洁，并且不必调用f.close()方法
    with open(path + img_name, 'wb') as file:
        file.write(req.content)

    print(img_name + '已保存')

# download_img('https://i2.hdslb.com/bfs/archive/24c11dbe8171bd625960db4987ff0fc10eee62f4.jpg')
