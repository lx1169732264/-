package algorithm.graph;

import com.jg.tree.IndexMinPriorityQueue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: 杨德石
 * @Date: 2020/8/1 21:15
 * @Version 1.0
 */
public class PrimMst {

    /**
     * 索引代表顶点，值表示当前顶点和最小生成树之间的最短边
     */
    private Edge[] edgeTo;

    /**
     * 索引代表顶点，值表示当前顶点和最小生成树之间的最短边的权重
     */
    private double[] distTo;

    /**
     * 索引代表顶点，如果当前顶点已经在树中，则值为true，否则为false
     */
    private boolean[] marked;

    /**
     * 存放树中顶点与非树中顶点之间的有效横切边
     */
    private IndexMinPriorityQueue pq;

    /**
     * 根据一副加权无向图，创建最小生成树计算对象
     *
     * @param g
     */
    public PrimMst(EdgeWeightedGraph g) {
        // 创建一个和图的定点数大小一样的Edge数组，表示边
        this.edgeTo = new Edge[g.V()];
        // 创建一个和图的定点数一样大小的double数组，表示权重，并且初始化数组中的内容为无穷大
        this.distTo = new double[g.V()];
        for (int i = 0; i < distTo.length; i++) {
            this.distTo[i] = Double.POSITIVE_INFINITY;
        }
        // 创建一个和图的顶点数一样大小的boolean数组，表示当前顶点是否已经在树中
        this.marked = new boolean[g.V()];
        // 默认让顶点0进入树中，但0顶点目前没有与树中的其他顶点相连接，因此初始化dist[0] = 0.0
        distTo[0] = 0.0;
        this.pq = new IndexMinPriorityQueue(g.V());
        // 使用顶点0的权重初始化pq
        pq.insert(0, 0.0);
        // 遍历有效边队列
        while (!pq.isEmpty()) {
            // 找到权重最小的横切变对应的顶点，加入到最小生成树中
            visit(g, pq.delMin());
        }
    }

    /**
     * 将顶点v添加到最小生成树中，并且更新数据
     *
     * @param g
     * @param v
     */
    private void visit(EdgeWeightedGraph g, int v) {
        // 把顶点添加到树中
        marked[v] = true;
        // 遍历顶点v的邻接表，得到每一条边Edge edge
        Queue<Edge> queue = g.adj(v);
        for (Edge edge : queue) {
            // 边e的一个顶点是v，找到另一个顶点w
            int w = edge.other(v);
            // 检测是否存在于树中，如果存在，则继续下一次循环，
            // 如果不在，则需要修正当前顶点w距离最小生成树的最小边edgeTo[w]以及它的权重distTo[w]，还有有效横切边也需要修正
            if (marked[w]) {
                continue;
            }
            // 如果v-w边的权重比目前distTo[w]的权重小，则需要修正数据
            if (edge.weight() < distTo[w]) {
                // 把顶点w距离最小生成树的边修改为edge
                edgeTo[w] = edge;
                // 把顶点w距离最小生成树的边的权重改为edge.weight()
                distTo[w] = edge.weight();
                // 如果pq中存储的有效横切边已经包含了w顶点，则需要修正最小索引优先队列w索引关联的权重
                if (pq.contains(w)) {
                    pq.changeItem(w, edge.weight());
                } else {
                    // 如果pq中存储的有效横切边不包含w顶点，则需要向最小索引优先队列中添加v-w和其权重值
                    pq.insert(w, edge.weight());
                }
            }
        }
    }

    /**
     * 获取最小生成树的所有边
     *
     * @return
     */
    public Queue<Edge> edges() {
        Queue<Edge> edges = new ArrayBlockingQueue<>(marked.length);
        for (int i = 0; i < marked.length; i++) {
            if (edgeTo[i] != null) {
                edges.offer(edgeTo[i]);
            }
        }
        return edges;
    }

}

class Test19 {
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(8);
        g.addEdge(new Edge(4, 5, 0.35));
        g.addEdge(new Edge(4, 7, 0.37));
        g.addEdge(new Edge(5, 7, 0.28));
        g.addEdge(new Edge(0, 7, 0.16));
        g.addEdge(new Edge(1, 5, 0.32));
        g.addEdge(new Edge(0, 4, 0.38));
        g.addEdge(new Edge(2, 3, 0.17));
        g.addEdge(new Edge(1, 7, 0.19));
        g.addEdge(new Edge(0, 2, 0.26));
        g.addEdge(new Edge(1, 2, 0.36));
        g.addEdge(new Edge(1, 3, 0.29));
        g.addEdge(new Edge(2, 7, 0.34));
        g.addEdge(new Edge(6, 2, 0.40));
        g.addEdge(new Edge(3, 6, 0.52));
        g.addEdge(new Edge(6, 0, 0.58));
        g.addEdge(new Edge(6, 4, 0.93));

        PrimMst mst = new PrimMst(g);
        Queue<Edge> edges = mst.edges();
        System.out.println(edges.poll());
        System.out.println(edges.poll());
        System.out.println(edges.poll());
        System.out.println(edges.poll());
        System.out.println(edges.poll());
        System.out.println(edges.poll());
        System.out.println(edges.poll());
    }
}
