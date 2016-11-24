package zzw;

import zzw.CreateGraph.ALGraph;

import java.util.Scanner;
/**
 * Created by john on 2016/11/21.
 */
public class Search {
    static void sortByPopular(ALGraph g) {
        int a[] = new int[Main.MAXNODENUM];
        for (int i = 0; i < g.vNodeNum; ++i) {
            a[i] = g.nodeList[i].popularValue;
        }
        quickSort(a, 0, 7);
        System.out.println("最受欢迎的景点依次为:");
        for (int j = 0; j < g.vNodeNum; ++j) {
            for (int i = 0; i < g.vNodeNum; ++i) {
                if (g.nodeList[i].popularValue == a[j]) {
                    System.out.println(g.nodeList[i].name + g.nodeList[i].popularValue);
                }
            }
        }
    }
    static void quickSort(int s[], int l, int r) {
        if (l < r) {
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if (i < j)
                    s[i++] = s[j];
                while (i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if (i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quickSort(s, l, i - 1); // 递归调用
            quickSort(s, i + 1, r);
        }
    }

    //查找
    static void searchNode(ALGraph g) {
        System.out.println("请输入景点的名字");
        String name;
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
        for (int j = 0; j < g.vNodeNum; ++j) {
            boolean exites = g.nodeList[j].name.equals(name);
            if (exites) {
                System.out.println("景点的详细信息为:" + g.nodeList[j].desc);
                System.out.println("景点的受欢迎程度为:" + g.nodeList[j].popularValue);
            }
        }
    }

}
