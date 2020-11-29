package algorithm.graph;

/**
 * @Author: 杨德石
 * @Date: 2020/8/1 17:10
 * @Version 1.0
 */
public class DirectedCycle {

    /**
     * 索引代表顶点，值表示当前顶点是否已经被搜索
     */
    private boolean[] marked;

    /**
     * 记录图中是否有环
     */
    private boolean hasCycle;

    /**
     * 索引代表顶点，使用栈的思想
     * 记录当前顶点有没有已经处于正在搜索的有向路径上
     */
    private boolean[] onStack;

    /**
     * 创建一个检测环对象，检测图G中是否有环
     *
     * @param g
     */
    public DirectedCycle(Digraph g) {
        // 创建一个和图的顶点树一样大小的marked数组
        marked = new boolean[g.V()];
        // 创建一个和图顶点数一样大小的onStack数组
        onStack = new boolean[g.V()];
        // 默认没有环
        this.hasCycle = false;
        // 遍历搜索图中的每一个顶点
        for (int i = 0; i < g.V(); i++) {
            // 如果当前顶点没有被搜索过，则搜索
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    /**
     * 基于深度优先搜索，检测图G中是否有环
     *
     * @param g
     * @param v
     */
    private void dfs(Digraph g, int v) {
        // 当前顶点标记为已搜索
        marked[v] = true;
        // 让当前顶点进栈
        onStack[v] = true;
        // 遍历v顶点的邻接表，得到每一个顶点
        for (Integer w : g.adj(v)) {
            // 如果当前顶点w没有被搜索过，则递归搜索与w顶点想通的其它顶点
            if(!marked[w]) {
                dfs(g, w);
            }
            // 如果顶点w已经被搜索过，则查看w是否在栈中。如果在，则证明有环，修改hasCycle标记，并结束循环
            if(onStack[w]) {
                hasCycle = true;
                return;
            }
        }
        // 搜索完之后，让当前顶点出栈
        onStack[v] = false;
    }

    /**
     * 判断图中是否有环
     *
     * @return
     */
    public boolean hasCycle() {
        return hasCycle;
    }

}

class Test17 {
    public static void main(String[] args) {
        Digraph g = new Digraph(5);
        g.addEdge(3, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 0);
        g.addEdge(1, 4);
        // 检测是否有环
        DirectedCycle cycle = new DirectedCycle(g);
        System.out.println(cycle.hasCycle());
    }
}
