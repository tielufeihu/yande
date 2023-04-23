package game605.servicelmpl;

import game605.utilx.SpringUtil;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class PythonService {
    public String getLList(String dirPath){
        //获取Python文件
        //String path = this.getClass().getClassLoader().getResource("").getPath() + "static/python/test.py";
        String path = "F:\\python\\img_utli.py";
        //System.out.println(path);
        PythonInterpreter interpreter = SpringUtil.getPythonInterpreter();
        interpreter.execfile(path);
        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("dealDirToLList", PyFunction.class);
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyString(dirPath));
        //PyObject 的返回结果可以通过toString获取
        String re =  pyobj.asString();
        //String re = pyobj.toString();
        //int ret = pyobj.asInt();
        //System.out.println("结果！");
        //System.out.println(pyobj.toString());
        return re;
    }

    public static void main(String[] args) {
        PythonService ps = new PythonService();
        String res = ps.getLList("F:\\珈百璃的堕落");
    }

}
