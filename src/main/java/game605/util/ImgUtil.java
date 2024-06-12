package game605.util;

import java.awt.image.BufferedImage;
import java.io.*;

public class ImgUtil {

    //将图片（路径）转换成字节流
    public static byte[] getImgByte(String path) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (FileInputStream fis = new FileInputStream(path)) {
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                out.write(b, 0, len);
            }
        } catch (IOException e) {
            throw e;
        }
        byte[] imgByte = out.toByteArray();
        return imgByte;
    }

    //将图片转（File类）换成字节流
    public static byte[] getImgByte(File img){
        byte[] fileBytes = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(img);
            fileBytes = new byte[(int) img.length()];
            fis.read(fileBytes);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileBytes;
    }

    //byte转换成File类
    public static File bytesToFile(byte[] bytes, String outPath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(outPath);
            if (!dir.exists() && dir.isDirectory()) { //判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(outPath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    //重置大小
    public static byte[] resizeImg(byte[] img) throws Exception {
        int x,y;
        double nn,n,nx,ny;
        BufferedImage b_img = SLTUtil.bytesToBufferedImage(img);
        x = b_img.getWidth();
        y = b_img.getHeight();
        n = Math.sqrt(160000.0 / x / y);
        nx = x * n;
        ny = y * n;
        BufferedImage b_img_new = SLTUtil.resizeImageOne(b_img, (int) nx, (int) ny);
        return SLTUtil.imageToBytes(b_img_new);
    }



}
