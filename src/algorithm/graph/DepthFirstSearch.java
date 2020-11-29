package algorithm.graph;

import java.util.Queue;

/**
 * @Author: 杨德石
 * @Date: 2020/8/1 15:00
 * @Version 1.0
 */
public class DepthFirstSearch {

    /**
     * 记录有多少个顶点与S顶点相通
     */
    private int count;

    /**
     * 索引代表顶点，值代表当前顶点是否已经被搜索
     */
    private boolean[] marked;

    /**
     * 构造深度优先搜索对象，使用深度优先搜索出图中s顶点的所有相通顶点
     *
     * @param g
     * @param s
     */
    public DepthFirstSearch(Graph g, int s) {
        // 构造一个和图大小一样的布尔数组
        marked = new boolean[g.V()];
        // 构造g图中与顶点s相通的所有顶点
        dfs(g, s);
    }

    /**
     * 使用深度优先搜索找出g图中v定点的所有想通顶点
     *
     * @param g
     * @param v
     */
    private void dfs(Graph g, int v) {
        // 当前节点标记为已搜索
        System.out.println(v);
        marked[v] = true;
        // 遍历v定点的邻接表，找到每一个顶点w
        Queue<Integer> queue = g.adj(v);
        for (Integer w : queue) {
            // 如果当前顶点w没有被搜索过，则递归搜索与w顶点想通的其他顶点
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        // 想通的顶点数量+1
        count++;
    }

    /**
     * 判断w顶点与s顶点是否相通
     *
     * @param w
     * @return
     */
    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

}
