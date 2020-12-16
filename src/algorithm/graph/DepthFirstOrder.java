package algorithm.graph;

import java.util.Stack;

public class DepthFirstOrder {

    /**
     * 索引代表顶点，值表示当前顶点是否已经被搜索
     */
    private boolean[] marked;

    /**
     * 使用栈存储顶点序列
     */
    private Stack<Integer> reversePost;

    /**
     * 创建一个顶点排序对象，生成顶点线性序列
     *
     * @param g
     */
    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.V()];
        reversePost = new Stack<>();
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    /**
     * 基于深度优先搜索，生成顶点线性序列
     *
     * @param g
     * @param v
     */
    public void dfs(Digraph g, int v) {
        // 把当前节点标记为已搜索
        marked[v] = true;
        // 遍历v顶点的邻接表，得到每一个顶点w
        for (Integer w : g.adj(v)) {
            // 如果当前顶点w没有被搜索，递归搜索
            if(!marked[w]) {
                dfs(g, w);
            }
        }
        // 当前顶点已经搜索完毕，让当前顶点入栈
        reversePost.push(v);
    }

    /**
     * 获取顶点线性序列
     *
     * @return
     */
    public Stack<Integer> reversePost() {
        return reversePost;
    }

}
class Test18 {
    public static void main(String[] args) {
        Digraph g = new Digraph(6);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(1, 3);
        DepthFirstOrder order = new DepthFirstOrder(g);
        Stack<Integer> stack = order.reversePost();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
