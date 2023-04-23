package game605.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class ControllerT {

    @RequestMapping("/tokenTest")
    public int ssss(){

        //操作
        System.out.println("tokenTest:执行了操作！");

        return 1;
    }

}
