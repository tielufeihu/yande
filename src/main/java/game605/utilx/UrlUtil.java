package game605.utilx;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtil {

    public static JSONObject getHttpJson(String url, int state) throws Exception {
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            //请求成功
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //10MB的缓存
                byte[] buffer = new byte[10485760];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String jsonString = baos.toString();

                if(jsonString.startsWith("\ufeff")){
                    jsonString = jsonString.substring(1);
                }

                //System.out.println(jsonString);
                baos.close();
                is.close();
                //转换成json数据处理
                // getHttpJson函数的后面的参数1，表示返回的是json数据，2表示http接口的数据在一个（）中的数据
                JSONObject jsonArray = getJsonString(jsonString, state);
                return jsonArray;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJsonString(String str, int comefrom) throws Exception{
        JSONObject jo = null;
        if(comefrom==1){
            jo = new JSONObject(str);
            return jo;
        }else if(comefrom==2){
            int indexStart = 0;
            //字符处理
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='('){
                    indexStart = i;
                    break;
                }
            }
            String strNew = "";
            //分割字符串
            for(int i=indexStart+1;i<str.length()-1;i++){
                strNew += str.charAt(i);
            }
            return new JSONObject(strNew);
        }
        return jo;
    }

    public static JSONArray getJsonArray(String str){
        JSONArray ja = null;
        //System.out.println(str);
        ja = new JSONArray(str);
        new JSONArray();
        System.out.println(ja);
        return ja;
    }

    public static String getUrlStr(String url){
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            //请求成功
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //10MB的缓存
                byte[] buffer = new byte[10485760];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String jsonString = baos.toString();
                baos.close();
                is.close();
                return jsonString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
