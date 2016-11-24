package zzw;

import zzw.CreateGraph.ALGraph;

import java.util.Scanner;

import static zzw.CreateGraph.locateNode;

/**
 * Created by john on 2016/11/21.
 */
public class Distance {

    /**
     * 求两个景点之间的最短路径,和最短距离
     */
    static void ShortestPath(ALGraph G, int path[][], double D[][])//求最短路径
    {
        int u, v, w;
        for (v = 0; v < G.vNodeNum; v++)
            for (w = 0; w < G.vNodeNum; w++) {
                D[v][w] = Main.a[v][w];//对最短距离初始化为任意两点之间的权值
                if (Main.a[v][w] < Main.INFINITY)
                    path[v][w] = v;//对最短路径初始化为自身的前一个结点的序号
            }
        for (u = 0; u < G.vNodeNum; u++)
            for (v = 0; v < G.vNodeNum; v++)
                for (w = 0; w < G.vNodeNum; w++)
                    if (D[v][u] + D[u][w] < D[v][w])//如果新加入的结点导致最短路径变短了，就更改他，同时记录增加的路径的编号
                    {
                        D[v][w] = D[v][u] + D[u][w];
                        path[v][w] = u;
                    }
    }

    static void getMiniDistanse(ALGraph G1, int path[][], double D[][])//输出最短路径
    {
        ShortestPath(G1, path, D);
        String A, B;
        System.out.println("请输入要查询距离的两个景点的名称：");
        Scanner scanner = new Scanner(System.in);
        A = scanner.nextLine();
        B = scanner.nextLine();
        int i = locateNode(G1, A);
        int j = locateNode(G1, B);
        System.out.println("最短路径为：");
        OutPutShortestPath(G1, path, D, i, j);
        System.out.println("最短距离为：" + D[i][j]);
    }

    static void OutPutShortestPath(ALGraph G, int path[][], double D[][], int i,
                                   int j) {
        if (path[i][j] == i)
            System.out.println(G.nodeList[i].name + "--" + G.nodeList[j].name);//输出经过的最短路径上的边的两端顶点
        else {
            OutPutShortestPath(G, path, D, i, path[i][j]);//依次找经过的中间路径
            OutPutShortestPath(G, path, D, path[i][j], j);
        }
    }
}
