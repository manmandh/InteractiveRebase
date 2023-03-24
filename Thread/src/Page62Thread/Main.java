package Page62Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Thread synchNotify= new SynchNotify(queue);
        synchNotify.start();

        Thread sumTwoNumber = new SumTwoNumber(queue, synchNotify);
        sumTwoNumber.start();

        //Thêm số
        for(int i = 1; i<=20; i++){
            queue.put(i);
        }

        //Chờ luồng kết thúc
        sumTwoNumber.join();
        synchNotify.join();

    }
}
