package algorithm.graph;

/**
 * @Author: 杨德石
 * @Date: 2020/8/2 14:04
 * @Version 1.0
 */
public class DirectedEdge {

    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return w;
    }

    /**
     * 获取有向边的起点
     *
     * @return
     */
    public int from() {
        return v;
    }

    /**
     * 获取有向边的终点
     *
     * @return
     */
    public int to() {
        return w;
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
