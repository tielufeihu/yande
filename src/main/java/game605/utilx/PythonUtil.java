package game605.utilx;

import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
//映射test.py
public class PythonUtil {

    //映射test.py add方法
    public int pyAdd(int a,int b){
        //获取Python文件
        //String path = this.getClass().getClassLoader().getResource("").getPath() + "static/python/test.py";
        String path = "D:\\学习\\java\\springboot\\WaterTestSystem\\src\\main\\resources\\static\\python\\test.py";
        //System.out.println(path);
        PythonInterpreter interpreter = SpringUtil.getPythonInterpreter();
        interpreter.execfile(path);
        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyInteger(a),new PyInteger(b));
        //PyObject 的返回结果可以通过toString获取
        String re = pyobj.toString();
        int ret = pyobj.asInt();
        //System.out.println("结果！");
        //System.out.println(pyobj.toString());
        return ret;
    }

    //映射test.py getRe方法
    public int getRe(){
        String path = "D:\\学习\\java\\springboot\\WaterTestSystem\\src\\main\\resources\\static\\python\\eff_train.py";
        PythonInterpreter interpreter = SpringUtil.getPythonInterpreter();
        interpreter.execfile(path);
        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("getRes", PyFunction.class);
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__();
        //PyObject 的返回结果可以通过toString获取
        String re = pyobj.toString();
        int ret = pyobj.asInt();
        return ret;
    }

    public int runGetRe(String img_path, String img_name) throws IOException, InterruptedException {


        //通过原生方式调用，解决python文件引入第三方库的问题
        //第一个参数默认是python,第二个参数python脚本路径，第三和第四个参数是python要接收的参数
        String[] argg = new String[] { "python", "D:\\学习\\java\\springboot\\WaterTestSystem\\src\\main\\resources\\static\\python\\test.py", img_path, img_name};

        Process pr = Runtime.getRuntime().exec(argg);

        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        String result = "";
        //接收返回结果
        while ((line = in.readLine()) != null) {
            result += line;
        }
        //System.out.println("------结果-------");
        //System.out.println(result);
        in.close();
        pr.waitFor();
        return Integer.parseInt(result);
    }

//    }


}
