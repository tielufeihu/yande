package game605.test.qs;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 奖励最顶尖的N名学生
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/11 10:48
 **/
public class Q2512 {

    // 暴力hash
    public List<Integer> topStudents(String[] positive_feedback
            , String[] negative_feedback
            , String[] report
            , int[] student_id
            , int k) {
        // 初始化map
        Map<String, Integer> wordMap = new HashMap<>();
        for (String s : positive_feedback) {
            wordMap.put(s, 3);
        }
        for (String s : negative_feedback) {
            wordMap.put(s, -1);
        }
        int len = student_id.length;
        // 使用优先队列（堆排序 + 自定义比较器）
        Queue<StudentInfo> pq = new PriorityQueue<>(((o1, o2) -> {
            if(o1.points != o2.points)
                return o2.points - o1.points;
            return o1.studentId - o2.studentId;
        }));
        for (int i = 0; i < len; i++) {
            // 逐个将学生加入到优先队列中（有序插入）
            String reportStu = report[i];
            String[] reportStuWords = reportStu.split(" ");
            int pointsTmp = 0;
            for (int j = 0; j < reportStuWords.length; j++)
                if(wordMap.containsKey(reportStuWords[j]))
                    pointsTmp += wordMap.get(reportStuWords[j]);
            pq.add(new StudentInfo(student_id[i], pointsTmp));
        }
        // 出队前k个即是结果
        List<Integer> retList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            retList.add(pq.poll().studentId);
        }
        return retList;
    }

}

class StudentInfo{
    int studentId;
    int points;

    StudentInfo(int id, int points){
        this.studentId = id;
        this.points = points;
    }
}

