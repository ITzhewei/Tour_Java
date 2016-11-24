package zzw;

import zzw.CreateGraph.*;

import static zzw.CreateGraph.locateNode;

/**
 * Created by john on 2016/11/21.
 */
public class OutGuild {
    /**
     * 导游路线图
     */
    static boolean[] visited = new boolean[Main.MAXNODENUM];//定义一个数组保存图的节点是否遍历过
    static String[] node1List = new String[Main.MAXNODENUM];//深度优先序列
    static int k;
    static ENode p = new ENode();

    static void dfs(ALGraph G, int v) {
        visited[v] = true;
        node1List[k++] = G.nodeList[v].name;

        for (p = G.nodeList[v].firstNode; p != null; p = p.nextENode)
            if (!visited[p.location]) {
                dfs(G, p.location);
                p = G.nodeList[v].firstNode;
            }
    }

    static void DFSTraverse(ALGraph G) {
        int v;
        for (v = 0; v < G.vNodeNum; v++)
            visited[v] = false;//初始化遍历顶点为没有被访问
        for (v = 0; v < G.vNodeNum; v++)
            if (!visited[v])//如果没有被访问就对其进行深度优先遍历
                dfs(G, v);
    }

    static boolean IsEdge(ALGraph G, String v1, String v2)//判断两个顶点之间是否有边
    {
        int i = locateNode(G, v1);
        int j = locateNode(G, v2);
        if (Main.a[i][j] < Main.INFINITY)//如果这两个顶点之间有边就返回为真，否则返回假
            return true;
        return false;
    }

    static String[] node2List = new String[2 * Main.MAXNODENUM];//存储导游线路图的顶点遍历序列

     static void OutTheTour(ALGraph g, ALGraph g1) {
        DFSTraverse(g);
        int i = 0, j = 0, n = 0;
        boolean Is;
        for (i = 0; i < g.vNodeNum - 1; i++) {
           int k = 0;
            Is = true;
            while (Is) {
                node2List[n++] = node1List[i + k];
                if (IsEdge(g, node1List[i + k], node1List[i + 1]))//如果他们之间有边就直接连接上这条边
                    Is = false;
                else
                    k--;//如果没有就回溯，直到找到和vex[i+1]有边的
            }
        }
        node2List[n] = node1List[i];//将最后一个顶点放进vex数组中
        //生成路线图
        createGraph2(g, g1, i, j, n);
        System.out.println("导游路线为：");
        for (i = 0; i <= n; i++)
            System.out.print(node2List[i] + "->");
    }

    static void createGraph2(ALGraph g, ALGraph g1, int i, int j, int n) {
        for (i = 0; i <= n; i++)
            visited[i] = false;
        for (i = 0; i < g.vNodeNum; i++)//初始化导游图
        {
            g1.nodeList[i] = new VNode();
            g1.nodeList[i].name = g.nodeList[i].name;
            g1.nodeList[i].firstNode = null;
        }

        int edgeNum = 0;//记录导游线路图中的边的个数
        for (int k = 0; k < n; k++) {
            i = locateNode(g, node2List[k]);
            j = locateNode(g, node2List[k + 1]);
            if (visited[j])//如果该点已经在导游线路图中，就寻找它是否与已经在导游线路图中的点形成回路
            {
                int m = k + 2;//跳过他的直接前驱
                if (m <= n)//判断是否超出存储导游线路图的数组
                {
                    while (visited[locateNode(g, node2List[m])])//直到出现新的节点为止
                    {
                    /*判断这两个点在原来的景区分布图中是否有边，如果有就连接上这条边，否者继续向后找*/
                        if (IsEdge(g, node2List[k], node2List[m])) {
                            j = locateNode(g, node2List[m]);
                            ENode P = new ENode();
                            ENode Q;
                            P.location = j;
                            P.w = Main.a[i][j];

                            Q = g1.nodeList[i].firstNode;
                            g1.nodeList[i].firstNode = P;
                            P.nextENode = Q;

                            edgeNum++;
                        }
                        if (++m >= n)//如果在查找的过程中到了数组末尾就强行退出
                            break;
                    }
                }
            } else//如果没有在导游线路图中就连接上它
            {
                visited[i] = visited[j] = true;

                ENode P = new ENode();
                ENode Q = new ENode();
                P.location = j;
                P.w = Main.a[i][j];

                Q = g1.nodeList[i].firstNode;
                g1.nodeList[i].firstNode = P;
                P.nextENode = Q;

                edgeNum++;
            }

        }
        g1.vNodeNum = g.vNodeNum;//赋值导游线路图中顶点的个数
        g1.eNodeNum = edgeNum;//赋值导游线路图中边的个数
    }
}
