package zzw;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static final int MAXNODENUM = 20;//定义图的最大节点数
    public static final int INFINITY = 32767;  //无穷远
    static CreateGraph graph = new CreateGraph();
    static Scanner scanner = new Scanner(System.in);
    static CreateGraph.ALGraph g1 = new CreateGraph.ALGraph();//图
    public static int a[][] = new int[8][9];
    public static void main(String[] args) throws Exception {
        CreateGraph.ALGraph g = new CreateGraph.ALGraph();//图
        g.nodeList = new CreateGraph.VNode[MAXNODENUM];
        CarGuild carGuild = new CarGuild();
        boolean createdG = true;//过滤
        int n;//输入
        boolean IsDetail = false;
        //path用来存储经过的路径，D用来存储两个顶点之间的距离
        int path[][] = new int[MAXNODENUM][MAXNODENUM];
        int[][] D = new int[MAXNODENUM][MAXNODENUM];
        graph.showMenu();//菜单
        while (true) {
            System.out.println("请输入您要选择的菜单项：");
            n = scanner.nextInt();
            //判断是否已经创建了图
            n = isCreate(createdG, n);
            createdG = false;
            switch (n) {
                case 0:
                    exit(0);
                case 1:
                    System.out.println(g.nodeList[0] == null);
                    graph.CreateGraph(g);
                    OutGraph.OutputGraph(g, false);//创建数组
                    break;
                case 2:
                    OutGraph.OutputGraph(g, true);//输出矩阵
                    break;
                case 3:
                    OutGuild.OutTheTour(g, g1);//输出导游路线图
                    OutGraph.OutputGraph(g1,true);
                    break;
                case 4:
                    FindCycleRoad.findCycleRoad(g1);//输出导游路中的回路
                    break;
                case 5:
                    Distance.getMiniDistanse(g, path, D);//求最短路径
                    break;
                case 6:
                    Road.OutRoadPlan(g, g.nodeList[0].name);//输出道路修建规划图
                    break;
                case 7:
                    Search.searchNode(g);//搜索某个景点的具体信息
                    break;
                case 8:
                    Search.sortByPopular(g);//将景点进行排序
                    break;
                case 9:
                    CarGuild.showMenu2();//停车场车辆进出记录信息
                    break;
                default:
                    exit(0);
            }
        }
    }

    static int isCreate(boolean Is, int n) {
        while (Is && !graph.IsZeroOrOne(n)) {
            System.out.println("您还不能进行此操作，请先创建图。");
            n = scanner.nextInt();
        }
        return n;
    }
}
