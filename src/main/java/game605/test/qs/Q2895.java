package game605.test.qs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 最小处理时间
 * 周赛原题
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/9 10:49
 **/
public class Q2895 {

    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        List<Integer> sortedProcessorTime = processorTime.stream().sorted().collect(Collectors.toList());
        List<Integer> sortedTasks = tasks.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Integer> maxBlock = new ArrayList<>();
        for (int i = 0; i < sortedTasks.size()/4; i++) {
            maxBlock.add(sortedTasks.get(i*4));
        }
        int minTime = Integer.MIN_VALUE;
        for (int i = 0; i < sortedProcessorTime.size(); i++) {
            int t = sortedProcessorTime.get(i) + maxBlock.get(i);
            if(t > minTime){
                minTime = t;
            }
        }
        return minTime;
    }

    @Test
    public void t1(){
    }

}
