package game605.test.qs;

import java.util.PriorityQueue;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3102
 * @description 最小化曼哈顿距离
 * @since 2024/7/9 16:52
 */
public class Q3102 {

    class Node{
        public int point1;
        public int point2;
        public int distance;
        public Node(int point1, int point2, int distance) {
               this.point1 = point1;
               this.point2 = point2;
               this.distance = distance;
        }
    }

    // 堆排序+枚举超时
    public int minimumDistance(int[][] points) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.distance - o1.distance);
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i+1; j < points.length; j++) {
                queue.add(new Node(i, j, computeDistance(points[i], points[j])));
            }
        }
        Node max = queue.poll();
        int point1 = max.point1;
        int point2 = max.point2;
        int max1 = 0,max2 = 0;
        PriorityQueue<Node> temp = new PriorityQueue<>((o1, o2) -> o2.distance - o1.distance);
        temp.addAll(queue);
        while (!temp.isEmpty()){
            Node node = temp.poll();
            if(node.point1 != point1 && node.point2 != point1){
                max1 = node.distance;
                break;
            }
        }
        temp.clear();
        temp.addAll(queue);
        while (!temp.isEmpty()){
            Node node = temp.poll();
            if(node.point1 != point2 && node.point2 != point2){
                max2 = node.distance;
                break;
            }
        }

        return Math.min(max1, max2);
    }

    public int computeDistance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }

    public static void main(String[] args) {
        Q3102 q3102 = new Q3102();
        int[][] points = {{3,10},{5,15},{10,2},{4,4}};
        System.out.println(q3102.minimumDistance(points));
    }


}
