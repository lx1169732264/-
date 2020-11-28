package algorithm.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: 杨德石
 * @Date: 2020/8/1 16:20
 * @Version 1.0
 */
public class Digraph {

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
    private Queue<Integer>[] adj;

    public Digraph(int v) {
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
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].offer(w);
        e++;
    }

    /**
     * 获取由v指出的边所连接的所有顶点
     *
     * @param v
     * @return
     */
    public Queue<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 获取该图的反向图
     *
     * @return
     */
    private Digraph reverse() {
        Digraph digraph = new Digraph(v);
        // 遍历0-v-1所有顶点，拿到每一个顶点v
        for (int i = 0; i < v; i++) {
            // 得到原图中的v顶点对应的邻接表，原图中边为v->w，则反向图中为w->v
            for (Integer w : adj(v)) {
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }
}
