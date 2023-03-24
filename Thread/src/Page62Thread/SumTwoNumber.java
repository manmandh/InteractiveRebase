package Page62Thread;
import java.util.concurrent.BlockingQueue;

public class SumTwoNumber extends Thread{
    //lớp này chịu trách nhiệm in kết quả phép cộng
    private BlockingQueue<Integer> queue;
    private Thread synchNotify;

    public SumTwoNumber(BlockingQueue<Integer> queue, Thread synchNotify){
        this.queue = queue;
        this.synchNotify =synchNotify;
        //Constructor
    }

    public void run() {
        int S = 0;
        int count = 0;
        boolean check = true;
        while (check){
            try{
            int n1 = queue.take();
            int n2 = queue.take();
            S = n1 + n2;
            count += 2;
                if(queue.isEmpty()){
                Thread.sleep(5000);
                }
                if(count%2==0) {
                synchronized (synchNotify) {
                    synchNotify.notify();
                    }
                }
            }catch (InterruptedException interruptedException){
                System.out.println("Invalid!!");
            }
        }
    }
}
