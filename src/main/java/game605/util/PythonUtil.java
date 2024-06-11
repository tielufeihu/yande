package game605.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Koyou
 * @version 1.0.0
 * @className PythonUtil
 * @description python调用工具类
 * @since 2024/6/11 15:51
 */
public class PythonUtil {

    /**
     * 原生方式调用python脚本
     * @param scriptPath
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void invokePythonWithProcess(String scriptPath, String... args) throws IOException, InterruptedException {
        // 原生方式调用
        String[] argg = new String[] { "python", scriptPath};
        argg = Stream.of(argg, args).flatMap(Arrays::stream).toArray(String[]::new);
        // 启动进程
        Process pr = Runtime.getRuntime().exec(argg);
        // 获取输出流
        BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        // 读取输出流
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        pr.waitFor();
    }


}
