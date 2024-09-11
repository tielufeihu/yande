package game605.test.qs;

import java.util.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q133
 * @description 图的深拷贝
 * @since 2024/9/11 13:58
 */
class GraphNode {
    public int val;
    public List<GraphNode> neighbors;
    public GraphNode() {
        val = 0;
        neighbors = new ArrayList<GraphNode>();
    }
    public GraphNode(int _val) {
        val = _val;
        neighbors = new ArrayList<GraphNode>();
    }
    public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Q133 {

    public GraphNode cloneGraph(GraphNode node) {
        if(node == null) return null;
        Map<GraphNode, GraphNode> map = new HashMap<>();
        // 根节点加入map
        map.put(node, new GraphNode(node.val));
        // bfs队列（里面放的都是未遍历的）
        Queue<GraphNode> queue = new ArrayDeque<>();
        // 根加入队列
        queue.add(node);
        // 遍历
        while(!queue.isEmpty()){
            // 出队
            GraphNode cur = queue.poll();
            // 遍历邻居
            for(GraphNode neighbor : cur.neighbors){
                // 如果没有被访问（节点的邻居节点不一定不被访问）
                if(!map.containsKey(neighbor)){
                    // 加入map中
                    map.put(neighbor, new GraphNode(neighbor.val));
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
}
