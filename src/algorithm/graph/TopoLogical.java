package algorithm.graph;

import java.util.Stack;

/**
 * @Author: 杨德石
 * @Date: 2020/8/1 17:49
 * @Version 1.0
 */
public class TopoLogical {

    /**
     * 顶点的拓扑排序
     */
    private Stack<Integer> order;

    public TopoLogical(Digraph g) {
        // 检测是否有环
        DirectedCycle directedCycle = new DirectedCycle(g);
        if (!directedCycle.hasCycle()) {
            // 没有环，进行顶点排序
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g);
            order = depthFirstOrder.reversePost();
        }
    }

    /**
     * 判断图G中是否有环
     *
     * @return
     */
    public boolean isCycle() {
        return order == null;
    }

    /**
     * 获取拓扑排序的所有顶点
     *
     * @return
     */
    public Stack<Integer> order() {
        return order;
    }
}
