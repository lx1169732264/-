package algorithm.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BreadthFirstSearch {

    /**
     * 索引代表顶点，值代表当前订单是否已经被搜索
     */
    private boolean[] marked;

    /**
     * 记录有多少个顶点与s顶点想通
     */
    private int count;

    /**
     * 存储待搜索邻接表的点
     */
    private Queue<Integer> waitSearch;

    /**
     * 使用广度优先搜索找出G图中v顶点的所有相邻顶点
     *
     * @param g
     * @param v
     */
    public BreadthFirstSearch(Graph g, int v) {
        // 构造一个和图大小一样的布尔数组
        marked = new boolean[g.V()];
        // 初始化待搜索顶点的队列
        waitSearch = new ArrayBlockingQueue<>(g.V());
        // 构造g图中与顶点s相通的所有顶点
        dfs(g, v);
    }

    /**
     * 使用广度优先搜索找出fg图中v顶点的所有相邻顶点
     *
     * @param g
     * @param v
     */
    private void dfs(Graph g, int v) {
        // 把当前顶点v放入到队列中，等待搜索它的邻接表
        waitSearch.offer(v);
        // 使用while循环从队列中取出待搜索的顶点wait，进行搜索邻接表
        while (!waitSearch.isEmpty()) {
            Integer wait = waitSearch.poll();
            // 把wait设为已搜索
            marked[wait] = true;
            System.out.println(wait);
            // 遍历wait顶点的邻接表，得到每一个顶点
            for (Integer w : g.adj(wait)) {
                // 如果当前顶点w没有被搜索，则递归搜索与w想通的其他顶点
                if (!marked[w] && !waitSearch.contains(w)) {
                    waitSearch.offer(w);
                }
            }
        }
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
