package game605.test.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Koyou
 * @version 1.0.0
 * @className UnsafeUtil
 * @description TODO
 * @since 2024/6/24 11:31
 */
public class UnsafeUtil {

    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        long p1 = unsafe.allocateMemory(32 * 100);
        Integer[] arr = new Integer[100];
        unsafe.putObject(p1, 0, arr);
        unsafe.freeMemory(p1);
    }



}
