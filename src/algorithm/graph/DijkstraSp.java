package algorithm.graph;


import algorithm.tree.IndexMinPriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: 杨德石
 * @Date: 2020/8/2 14:26
 * @Version 1.0
 */
public class DijkstraSp {

    /**
     * 索引代表顶点，值表示从顶点s到当前顶点的最短路径上的最后一条边
     */
    private DirectedEdge[] edgeTo;

    /**
     * 索引代表顶点，值从顶点s到当前顶点的最短路径的总权重
     */
    private double[] distTo;

    /**
     * 存放树中顶点与非树中顶点之间的有效横切边
     */
    private IndexMinPriorityQueue pq;

    /**
     * 根据一副加权有向图G和顶点s，创建一个计算顶点为s的最短路径树对象
     *
     * @param G
     * @param s
     */
    public DijkstraSp(EdgeWeightedDigraph G, int s) {
        // 创建一个和图的顶点数一样大小的DirectedEdge数组，表示边
        this.edgeTo = new DirectedEdge[G.V()];
        // 创建一个和图的顶点数一样大小的double数组，表示权重，并且初始化数组中的内容为无穷大。
        this.distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        // 创建一个和图的顶点数一样大小的索引优先队列，存储有效地横切边
        this.pq = new IndexMinPriorityQueue(G.V());
        // 默认让顶点s进入树中，但顶点s目前没有与树中其他的顶点相连接，因此初始化distTo[s] = 0.0
        distTo[s] = 0.0;
        // 使用顶点s和权重0.0初始化pq
        pq.insert(s, 0.0);
        // 遍历有效边队列
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    /**
     * 松弛图G中的顶点v
     *
     * @param G
     * @param v
     */
    private void relax(EdgeWeightedDigraph G, int v) {
        // 松弛顶点v就是松弛顶点v邻接表中的每一条边
        for (DirectedEdge e : G.adj(v)) {
            // 获取edge的终点
            int w = e.to();
            // 起点s到w的权重是否大于起点s到顶点v的权重+边e的权重，如果大于，则修改s->w的路径 edgeTo[w] = e
            // 并修改distTo[v] = distTo[v] + e.weight()，如果不大于，则忽略
            if (distTo(w) > distTo(v) + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                // 如果顶点w已经存在于优先队列pq中，则重置顶点w的权重
                if (pq.contains(w)) {
                    pq.changeItem(w, distTo(w));
                } else {
                    // 如果不存在，则添加
                    pq.insert(w, distTo(w));
                }
            }
        }
    }

    /**
     * 获取从顶点s到顶点v的最短路径的总权重
     *
     * @param v
     * @return
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * 判断从顶点s到顶点v是否可达
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * 查询从起点s到顶点v的最短路径中所有的边
     *
     * @param v
     * @return
     */
    public Queue<DirectedEdge> pathTo(int v) {
        // 如果顶点s到v不可达，则返回null
        if (!hasPathTo(v)) {
            return null;
        }
        // 创建队列Queue保存最短路径的边
        Queue<DirectedEdge> edges = new ArrayBlockingQueue<>(distTo.length);
        // 从顶点v开始，你想寻找，一直找到顶点s位置，而起点s为最短路径树的根节点，所以edgeTo[s] = null
        DirectedEdge e = null;
        while (true) {
            e = edgeTo[v];
            if (e == null) {
                break;
            }
            edges.offer(e);
            v = e.from();
        }
        return edges;
    }

}

class Test20 {
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(8);
        g.addEdge(new DirectedEdge(4, 5, 0.35));
        g.addEdge(new DirectedEdge(5, 4, 0.35));
        g.addEdge(new DirectedEdge(4, 7, 0.37));
        g.addEdge(new DirectedEdge(5, 7, 0.28));
        g.addEdge(new DirectedEdge(7, 5, 0.28));
        g.addEdge(new DirectedEdge(5, 1, 0.32));
        g.addEdge(new DirectedEdge(0, 4, 0.38));
        g.addEdge(new DirectedEdge(0, 2, 0.26));
        g.addEdge(new DirectedEdge(7, 3, 0.39));
        g.addEdge(new DirectedEdge(1, 3, 0.29));
        g.addEdge(new DirectedEdge(2, 7, 0.34));
        g.addEdge(new DirectedEdge(6, 2, 0.40));
        g.addEdge(new DirectedEdge(3, 6, 0.52));
        g.addEdge(new DirectedEdge(6, 0, 0.58));
        g.addEdge(new DirectedEdge(6, 4, 0.93));
        DijkstraSp sp = new DijkstraSp(g, 0);
        Queue<DirectedEdge> edges = sp.pathTo(6);
        System.out.println(edges.poll());
        System.out.println(edges.poll());
        System.out.println(edges.poll());
        System.out.println(edges.poll());
    }
}
