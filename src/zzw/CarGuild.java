package zzw;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by john on 2016/11/21.
 */
public class CarGuild {

    private static final int MAXCAR = 2;

    private static Stack<zanlind> stack1 = new Stack<>();
    private static Stack<zanlind> stack2 = new Stack<>();
    private static Queue<zanlind> queue = new Queue<zanlind>();
    private static int count = 0;
    private static int count2 = 0;


    public static void showMenu2() {

        System.out.println("       *** 停车场管理程序 ***   ");
        System.out.println("================================");
        System.out.println("**                               ");
        System.out.println("**   1--- 汽车进车场 2--- 汽车出车场  ");
        System.out.println("**                               ");
        System.out.println("**     3 --- 退出 程序            ");
        System.out.println("**                                ");
        System.out.println("================================  ");

        while (true) {
            System.out.println(" 请选择 <1 2 3 > : ");
            int in;
            Scanner scanner = new Scanner(System.in);
            in = scanner.nextInt();
            switch (in) {
                case 1:
                    inCar();
                    break;
                case 2:
                    outCar();
                    break;
                case 3:
                    exit(0);
                default:
                    exit(0);
            }
        }
    }

    private static void outCar() {
        int card;
        int out_time;
        System.out.println("请输入车牌和" + "汽车离去的时间");
        Scanner scanner = new Scanner(System.in);
        card = scanner.nextInt();
        out_time = scanner.nextInt();
        //出栈处理
        for (int i = 0; i < MAXCAR; i++) {
            zanlind zanlind = stack1.pop();
            count--;
            stack2.push(zanlind);
            count2++;
            if (zanlind.number == card) {
                int money = out_time - zanlind.ar_time;
                System.out.println("您应该上交的钱数是(每小时1块钱):" + money);
                stack2.pop();
                count2--;
                for (int j = 0; j < count2; j++) {
                    stack1.push(zanlind);
                    count++;
                }
                //出队处理
                if (!queue.isEmpty()) {
                    zanlind zanlind1 = queue.deQueue();
                    stack1.push(zanlind1);
                    count++;
                    System.out.println("排队中第一辆车,车牌号为" + zanlind1.number + "的车进入停车场");
                }
                return;
            }
        }
    }

    private static void inCar() {
        int card;
        int in_time;
        System.out.println("请输入车牌和进场的时间:");
        Scanner scanner = new Scanner(System.in);
        card = scanner.nextInt();
        in_time = scanner.nextInt();
        //入栈处理
        zanlind zanInode = new zanlind();
        zanInode.ar_time = in_time;
        zanInode.number = card;
        if (count < MAXCAR) {
            stack1.push(zanInode);
            count++;
            if (count <= MAXCAR) {
                System.out.println("您的车已经被停在了" + count + "号车道");
            }
        } else {
            //入队处理
            queue.enQueue(zanInode);
            System.out.println("车队已经满了,请排队等候");
        }
    }

    private static class zanlind {
        int number;
        int ar_time;
    }
}
