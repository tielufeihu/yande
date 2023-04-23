package game605.utilx;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

@Component
public class OpenCVUtil {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);   //加载库,否则会出错
    }


    public static byte[] mat2Byte(Mat matrix, String fileExtension) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(fileExtension, matrix, mob);
        byte[] byteArray = mob.toArray();
        return byteArray;
    }



    public static byte[] resize(byte[] big_img){

        Mat mat = Imgcodecs.imread("D:\\1.png");//原始图片
        Mat m = new Mat();//缩放之后的图片
        int w = 100;//新图片的宽度
        int h = 100;//新图片的高度
        int i = Imgproc.INTER_NEAREST;//图片的效果,放大图片时,这个值会让图片略有不同,INTER_NEAREST是默认值,除此之外还有INTER_NEAREST,INTER_AREA,INTER_CUBIC,INTER_LANCZOS4
        Imgproc.resize(mat, m, new Size(w, h), 0, 0, i);//缩放图片
        Imgcodecs.imwrite("D:\\2.png", m);//写入图片



        return null;
    }

}
