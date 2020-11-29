package algorithm.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: 杨德石
 * @Date: 2020/8/2 14:07
 * @Version 1.0
 */
public class EdgeWeightedDigraph {

    /**
     * 记录顶点数量
     */
    private int v;
    /**
     * 记录边数量
     */
    private int e;
    /**
     * 邻接表
     */
    private Queue<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v) {
        this.v = v;
        this.e = 0;
        this.adj = new ArrayBlockingQueue[v];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayBlockingQueue<>(v);
        }
    }

    public int V() {
        return v;
    }

    public int E() {
        return e;
    }

    /**
     * 向有向图中添加一条边v->w
     *
     * @param edge
     */
    public void addEdge(DirectedEdge edge) {
        // 获取起点
        int v = edge.from();
        adj[v].offer(edge);
        e++;
    }

    /**
     * 获取由v指出的边所连接的所有顶点
     *
     * @param v
     * @return
     */
    public Queue<DirectedEdge> adj(int v) {
        return adj[v];
    }

    /**
     * 获取加权有向图的所有边
     *
     * @return
     */
    public Queue<DirectedEdge> edges() {
        Queue<DirectedEdge> edges = new ArrayBlockingQueue<>(v);
        // 遍历顶点
        for (int i = 0; i < this.v; i++) {
            // 遍历邻接表，拿到邻接表中每条边存储到队列中
            for (DirectedEdge edge : adj(v)) {
                edges.offer(edge);
            }
        }
        return edges;
    }
}
