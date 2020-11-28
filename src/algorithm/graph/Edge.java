package algorithm.graph;

/**
 * @Author: 杨德石
 * @Date: 2020/8/1 19:34
 * @Version 1.0
 */
public class Edge {

    /**
     * 两个顶点
     */
    private int v;
    private int w;

    /**
     * 当前边的权重
     */
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    /**
     * 获取边上的一个点
     *
     * @return
     */
    public int either() {
        return v;
    }

    /**
     * 获取边上除了顶点vertex外的另外的一个顶点
     *
     * @param vertex
     * @return
     */
    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else {
            return v;
        }
    }

    /**
     * 比较当前边和参数that边的权重
     * 如果当前边权重大，返回1，
     * 如果一样大，返回0，如果当前权重小，返回-1
     *
     * @param that
     * @return
     */
    public int compareTo(Edge that) {
        if (this.weight() > that.weight()) {
            return 1;
        } else if (this.weight() == that.weight) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
