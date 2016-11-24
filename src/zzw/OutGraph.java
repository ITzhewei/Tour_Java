package zzw;

/**
 * Created by john on 2016/11/21.
 */
public class OutGraph {
    /**
     * 输出为矩阵图
     */
    static void OutputGraph(CreateGraph.ALGraph g, boolean isprint) {
        int i, j;

        //初始化数组数据
        for (int k = 0; k < g.vNodeNum; k++)
            for (int l = 0; l < g.vNodeNum; l++)
                if (k == l)

                    Main.a[k][l] = 0;
                else
                    Main.a[k][l] = Main.INFINITY;

        //遍历链表进行赋值
        CreateGraph.ENode node;
        for (int m = 0; m < g.vNodeNum; m++) {
            for (node = g.nodeList[m].firstNode; node != null; node = node.nextENode) {
                int location = node.location;
                Main.a[m][location] = node.w;
            }
        }
        if (isprint) {
            //遍历数组并输出
            for (i = 0; i < g.vNodeNum; i++)
                System.out.print("\t\t" + g.nodeList[i].name);
            System.out.println();
            for (i = 0; i < g.vNodeNum; i++) {
                System.out.print(g.nodeList[i].name + "\t\t");
                for (j = 0; j < g.vNodeNum; j++)
                    System.out.print(Main.a[i][j] + "\t\t");
                System.out.println();
            }
        }
    }

}
