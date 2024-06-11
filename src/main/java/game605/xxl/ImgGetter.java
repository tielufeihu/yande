package game605.xxl;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import game605.util.PythonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ImgGetter
 * @description TODO
 * @since 2024/6/7 18:02
 */
@Component
@Slf4j
public class ImgGetter {

    @Value("${python-script-path}")
    private String pythonPath;

    /**
     * 调用python脚本 --- 进程模式
     * @param start
     * @param offset
     * @param thCount
     * @throws IOException
     * @throws InterruptedException
     */
    public void run(int start, int offset, int thCount) throws IOException, InterruptedException {
        PythonUtil.invokePythonWithProcess(pythonPath,
                String.valueOf(start), String.valueOf(offset), String.valueOf(thCount));
    }


    @XxlJob("imgGetter")
    public void imgGetter() {
        log.info("imgGetter");
        // 获取参数
        String param = XxlJobHelper.getJobParam();
        String[] methodParams = param.split(",");
        log.info("起始页..[{}]", methodParams[0]);
        log.info("偏移量...[{}]", methodParams[1]);
        log.info("线程数 ...[{}]", methodParams[2]);
        try {
            run(Integer.parseInt(methodParams[0])
                    , Integer.parseInt(methodParams[1])
                    , Integer.parseInt(methodParams[2]));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("imgGetter success");
    }

}
