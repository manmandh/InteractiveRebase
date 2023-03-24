package Page61Thread;
public class ThreadNotify implements Runnable {
    private Sum2Num sum2Num;

    public ThreadNotify(Sum2Num sum2Num) {
        this.sum2Num = sum2Num;
    }
    public ThreadNotify() {

    }

    @Override
    public void run() {
        boolean programme = true;
        while (programme) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Print results after 5 seconds:");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}