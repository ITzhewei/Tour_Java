package zzw;
import zzw.CreateGraph.ALGraph;
import zzw.CreateGraph.ENode;
/**
 * Created by john on 2016/11/24.
 */
public class FindCycleRoad {

    /**
     * 找回路
     **/
    static int findCycleRoad(ALGraph G1) {
        String a[] = new String[G1.vNodeNum];//将不在回路中的顶点暂存在字符数组a中
        int indegree[] = new int[G1.vNodeNum];//为顶点入度数组开辟空间
        int top = 0, base = 0;
        int S[] = new int[G1.vNodeNum];//为顶点栈开辟空间
        int i = 0;
        for (; i < G1.vNodeNum; i++)//初始化顶点入读数组
            indegree[i] = 0;
        getInDegree(G1, indegree);//调用求入度函数求出各个顶点的入度
        for (i = 0; i < G1.vNodeNum; i++)
            if (indegree[i] == 0)//将入度减为0的顶点入栈
                S[top++] = i;
        int count = 0;//记录已经进入拓扑序列的顶点的个数
        int k;
        ENode p = new ENode();
        while (top != base)//当栈不空是继续循环
        {
            k = S[--top];
            a[count++] = G1.nodeList[k].name;
            for (p = G1.nodeList[k].firstNode; p != null; p = p.nextENode)
                if ((--indegree[p.location]) == 0)//如果入度减为0，则入栈
                    S[top++] = p.location;
        }
        if (count < G1.vNodeNum) {
            System.out.println("图中有回路,回路为：");
            for (i = 0; i < G1.vNodeNum; i++) {
                for (k = 0; k < count; k++)
                    if (G1.nodeList[i].name == a[k])//如果顶点与数组a中的顶点相同就跳出循环，说明不在循环中
                        break;
                if (k >= count)
                    System.out.println(G1.nodeList[i].name);
            }
            return 0;
        } else {
            System.out.println("图中没有回路");
            return 1;
        }

    }

    static void getInDegree(ALGraph g1, int indegree[])//求各个顶点的入度
    {
        ENode p = new ENode();
        for (int i = 0; i < g1.vNodeNum; i++)
            for (p = g1.nodeList[i].firstNode; p != null; p = p.nextENode)//搜索完所有的边
                indegree[p.location]++;
    }
}
