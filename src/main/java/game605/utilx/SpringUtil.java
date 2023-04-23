package game605.utilx;


import org.python.util.PythonInterpreter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
* 这个类继承ApplicationContextAware
 * 可以在别的类内实现ApplicationContext类的功能
* */

@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz){
        return (T)applicationContext.getBean(clazz);
    }

    private static PythonInterpreter pyInterpreter = null;

    public static PythonInterpreter getPythonInterpreter() {
        if (pyInterpreter == null) {
            Properties props = new Properties();
            props.put("python.home", "../jython-2.5.2");
            props.put("python.console.encoding", "UTF-8");
            props.put("python.security.respectJavaAccessibility", "false");
            props.put("python.import.site", "false");
            Properties preprops = System.getProperties();
            PythonInterpreter.initialize(preprops, props, new String[0]);
            pyInterpreter = new PythonInterpreter();
            pyInterpreter.exec("import sys");
            pyInterpreter.exec("print 'prefix', sys.prefix");
            pyInterpreter.exec("print sys.path");
            System.out.println("python的jar包引用正确");
            pyInterpreter = new PythonInterpreter();
        }
        return pyInterpreter;
    }

}