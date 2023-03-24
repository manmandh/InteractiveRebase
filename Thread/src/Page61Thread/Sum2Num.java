package Page61Thread;

import java.util.Scanner;

public class Sum2Num implements Runnable {
    private final Scanner scanner;
    private ThreadNotify threadNotify;
    public Sum2Num(ThreadNotify threadNotify) {
        this.scanner = new Scanner(System.in);
        this.threadNotify= threadNotify;
    }

    @Override
    public void run() {
        boolean check = true;
        while (check) {
            System.out.println("Enter number 1 : ");
            int num1 = scanner.nextInt();
            System.out.println("Enter number 2 : ");
            int num2 = scanner.nextInt();

            int sum = num1 + num2;
            System.out.println("Addition result: " + num1 + " + " + num2 + " is " + sum);

            synchronized (threadNotify) {
                threadNotify.notify();
            }
        }
    }
}
