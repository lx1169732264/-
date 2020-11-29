package algorithm.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: 杨德石
 * @Date: 2020/8/1 14:35
 * @Version 1.0
 */
public class Graph {

    /**
     * 顶点数量
     */
    private int v;
    /**
     * 边数量
     */
    private int e;

    /**
     * 邻接表
     */
    private Queue<Integer>[] adj;

    /**
     * 创建一个包含V个订单但不包含边的图
     * @param v
     */
    public Graph(int v) {
        // 初始化顶点数量
        this.v = v;
        // 初始化边的数量
        this.e = 0;
        // 初始化邻接表
        this.adj = new Queue[v];
        // 初始化邻接表中的空队列
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayBlockingQueue<>(5);
        }
    }

    /**
     * 获取图中定点的数量
     * @return
     */
    public int V() {
        return v;
    }

    /**
     * 获取图中边的数量
     * @return
     */
    public int E() {
        return e;
    }

    /**
     * 向图中添加一条边v-w
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        // 把w添加到v的链表中，这样顶点v就多了一个相邻点w
        adj[v].add(w);
        // 把v添加到w的链表中，这样顶点w就多了一个相邻的点v
        adj[w].add(v);
        // 边的数量自增1
        e++;
    }

    /**
     * 获取和顶点v相邻的所有顶点
     * @param v
     * @return
     */
    public Queue<Integer> adj(int v) {
        return adj[v];
    }

}
class Test16{
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 4);
        graph.addEdge(0, 1);
        graph.addEdge(3, 4);
        graph.addEdge(2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
//        DepthFirstSearch search = new DepthFirstSearch(algorithm.graph, 0);
        BreadthFirstSearch search = new BreadthFirstSearch(graph, 0);
    }
}
