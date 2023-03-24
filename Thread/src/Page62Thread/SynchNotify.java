package Page62Thread;
import java.util.concurrent.BlockingQueue;

public class SynchNotify extends Thread{
    private BlockingQueue<Integer> queue;
    //Khai bao thanh queue
    public SynchNotify(BlockingQueue<Integer> queue) {
        this.queue = queue;
        //Constructor
    }
    //Hàm Run
    public void run(){
        boolean programme = true;
        while (programme){
            try{
                //Chờ tổng hợp => thông báo
                synchronized (this){
                    wait();
                }
                //In ra kết quả
                System.out.println("Sum 2 numbers is: " + queue.take());
                //Kiểm tra điều kiện rỗng
                if(queue.isEmpty()){
                    System.out.println("No value");
                    break;
                }
            }catch (InterruptedException interruptedException){
                System.out.println("Interupted");
            }
        }
    }
}
