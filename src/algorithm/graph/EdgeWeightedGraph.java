package algorithm.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: 杨德石
 * @Date: 2020/8/1 19:39
 * @Version 1.0
 */
public class EdgeWeightedGraph {

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
    private Queue<Edge>[] adj;

    /**
     * 创建一个包含V个订单但不包含边的图
     *
     * @param v
     */
    public EdgeWeightedGraph(int v) {
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
     *
     * @return
     */
    public int V() {
        return v;
    }

    /**
     * 获取图中边的数量
     *
     * @return
     */
    public int E() {
        return e;
    }

    /**
     * 向图中添加一条边e
     *
     * @param edge
     */
    public void addEdge(Edge edge) {
        // 取出来两个顶点
        int v = edge.either();
        int w = edge.other(v);
        // 把w添加到v的链表中，这样顶点v就多了一个相邻点w
        adj[v].add(edge);
        // 把v添加到w的链表中，这样顶点w就多了一个相邻的点v
        adj[w].add(edge);
        // 边的数量自增1
        e++;
    }

    /**
     * 获取和顶点v相邻的所有顶点
     *
     * @param v
     * @return
     */
    public Queue<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * 获取加权无向图的所有的边
     *
     * @return
     */
    public Queue<Edge> edges() {
        // 创建一个队列，存储所有的边
        Queue<Edge> edges = new ArrayBlockingQueue<>(adj.length);
        // 遍历顶点，拿到每个定点的邻接表
        for (int i = 0; i < this.v; i++) {
            // 拿到邻接表，拿到邻接表中的每条边
            for (Edge edge : adj(v)) {
                // 因为无向图中，每条边对象Edge都会在两个定点的邻接表中各出现一次，为了不重复获取，暂定一条规则
                // 除了当前顶点v，再获取边e的另外一个顶点w，如果v<w则添加，这样就可以保证同一条边，只会被统计一次
                if (edge.other(v) < v) {
                    edges.offer(edge);
                }
            }
        }
        return edges;
    }

}
