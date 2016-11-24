package zzw;

import utils.FileUtils;

import java.util.List;


/**
 * Created by john on 2016/11/21.
 */
public class CreateGraph {

//    private static final int zzw.Main.MAXNODENUM = 20;//定义图的最大节点数
//    private static final int INFINITY = 32767;  //无穷远

    public void showMenu() {
        System.out.println("                  ");
        System.out.println("=====================");
        System.out.println("                        ");
        System.out.println("欢迎使用景区旅游信息管理系统");
        System.out.println("  **请选择菜单**");
        System.out.println("=====================");
        System.out.println("0、退出系统。");
        System.out.println("1、创建景区景点分布图。");
        System.out.println("2、输出景区景点分布图。");
        System.out.println("3、输出导游线路图。");
        System.out.println("4、输出导游线路图中的回路。");
        System.out.println("5、求两个景点间的最短路径和最短距离。");
        System.out.println("6、输出道路修建规划图。");
        System.out.println("7、搜索某个景点的具体信息。");
        System.out.println("8、把景点按照受欢迎程度进行排序");
        System.out.println("9、停车场车辆进出记录信息。");
    }

    boolean IsZeroOrOne(int n) {
        if (n == 0 || n == 1)
            return true;
        return false;
    }

    void CreateGraph(ALGraph g) throws Exception {
        List<String> vNodes = FileUtils.readNames();
        List<String> v1s = FileUtils.readV1s();
        List<String> v2s = FileUtils.readV2s();
        List<Integer> ws = FileUtils.readWs();
        List<String> details = FileUtils.readStringList("detail.txt");
        List<Integer> populars = FileUtils.readintList("popular.txt");
        g.vNodeNum = vNodes.size();
        g.eNodeNum = ws.size();
        for (int i = 0; i < g.vNodeNum; ++i) {
            g.nodeList[i] = new VNode();
            g.nodeList[i].name = vNodes.get(i);
            g.nodeList[i].desc = details.get(i);
            g.nodeList[i].popularValue = populars.get(i);
            g.nodeList[i].firstNode = null;
        }
        String v1, v2;//两个顶点名
        int w;//顶点之间的距离
        for (int j = 0; j < g.eNodeNum; ++j) {
            v1 = v1s.get(j);
            v2 = v2s.get(j);
            w = ws.get(j);
            //得到两个节点的位置
            int index1 = locateNode(g, v1);
            int index2 = locateNode(g, v2);
            //将边结点插入
            insert(g, w, index1, index2);
        }
    }

    public static int locateNode(ALGraph g, String nodeName) {
        for (int i = 0; i < g.vNodeNum; ++i) {
            if (nodeName.equals(g.nodeList[i].name)) {
                return i;
            }
        }
        return -1;//定位失败
    }

    void insert(ALGraph g, int w, int index1, int index2) {
        ENode node1 = new ENode();
        ENode node2 = new ENode();
        //设置第一个节点
        node1.w = w;
        node1.location = index2;//他的相邻节点
        node1.nextENode = g.nodeList[index1].firstNode;
        g.nodeList[index1].firstNode = node1;
        //设置第二个节点
        node2.w = w;
        node2.location = index1;
        node2.nextENode = g.nodeList[index2].firstNode;
        g.nodeList[index2].firstNode = node2;
    }

    public static class ENode {
        int location;//数组中位置
        ENode nextENode;//下一个节点
        int w;//顶点之间距离
    }

    public static class VNode {
        String name;//景点名字
        String desc;//景点的详细介绍
        int popularValue;//受欢迎程度
        ENode firstNode;//所有的边
    }

    public static class ALGraph {
        VNode[] nodeList = new VNode[Main.MAXNODENUM];
        int vNodeNum, eNodeNum;
    }

    public static class Edge {
        String vNodeName;
        int lowcost;
    }


}
