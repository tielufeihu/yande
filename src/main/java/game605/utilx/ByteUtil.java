package game605.utilx;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ByteUtil {

    public static byte[] intToBytes(int x){
        return ByteBuffer.allocate(Integer.SIZE / Byte.SIZE).putInt(x).array();
    }

    public static int bytesToInt(byte[] x){
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(x, 0, x.length);
        buffer.flip();
        return buffer.getInt();
    }


    public static String bytesToString(byte[] bytes) {
        return new String(bytes);
    }


    public static byte[] stringToBytes(String s) {
        return s.getBytes();
    }

    public static List<Integer> intByteListToList(List<byte[]> bytes_list){
        List<Integer> re = new ArrayList<>();
        for (byte[] bytes: bytes_list) {
            re.add(bytesToInt(bytes));
        }
        return re;
    }

    public static List<byte[]> intListToByteList(List<Integer> int_list){
        List<byte[]> re = new ArrayList<>();
        for (Integer i: int_list) {
            re.add(intToBytes(i));
        }
        return re;
    }

    public static byte[][] intListToByteArrArr(List<Integer> list){
        byte[][] re = new byte[list.size()][4];
        int step = 0;
        for (Integer i: list) {
            re[step++] = ByteUtil.intToBytes(i);
        }
        return re;
    }

    // 调用系统api 速度更快
    public static byte[] longToBytes(Long x){
        return ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(x).array();
    }

    public static Long bytesToLong(byte[] x){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(x, 0, x.length);
        buffer.flip();
        return buffer.getLong();
    }

}
