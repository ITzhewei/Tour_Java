package zzw;
import zzw.CreateGraph.ALGraph;

import static zzw.CreateGraph.locateNode;

/**
 * Created by john on 2016/11/21.
 */
public class Road {


    /**
     * 输出道路修建规划图
     */
    static int Mininum(ALGraph G, CreateGraph.Edge[] a) {
        int min, i, j, mark;
        for (i = 0; a[i].lowcost == 0; i++) ;//找出还没有加入最小生成树的边
        min = a[i].lowcost;
        mark = i;
        for (j = i + 1; j < G.vNodeNum; j++)//找出权值最小的
            if (a[j].lowcost > 0 && a[j].lowcost < min) {
                min = a[j].lowcost;
                mark = j;
            }
        return mark;
    }

    static void MiniSpanTree(ALGraph G, String u) {
        CreateGraph.Edge[] closedge = new CreateGraph.Edge[Main.MAXNODENUM];
        int i = locateNode(G, u);
        for (int j = 0; j < G.vNodeNum; j++)//初始化各个边的信息
            if (j != i) {
                closedge[j] = new CreateGraph.Edge();
                closedge[j].vNodeName = u;
                closedge[j].lowcost = Main.a[i][j];
            }
        closedge[i] = new CreateGraph.Edge();
        closedge[i].lowcost = 0;//初始化u已经在最小生成树中
        int k;
        System.out.println("道路修建规划图为");
        for (i = 1; i < G.vNodeNum; i++) {
            k = Mininum(G, closedge);
            System.out.println("从" + closedge[k].vNodeName + "到" + G.nodeList[k].name + "修一条路");
            closedge[k].lowcost = 0;//将k顶点加入生成树中
            int j = 0;
            for (j = 0; j < G.vNodeNum; j++)
                if (Main.a[k][j] < closedge[j].lowcost)//当新加入的顶点使距离更小了，就更改他
                {
                    closedge[j].lowcost = Main.a[k][j];
                    closedge[j].vNodeName = G.nodeList[k].name;
                }
        }
    }

    static void OutRoadPlan(ALGraph g, String basic_String) {
        MiniSpanTree(g, basic_String);
    }



}
