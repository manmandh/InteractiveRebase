package Page61Thread;
public class Main {
    public static void main(String[] args) {
        ThreadNotify threadNotify = new ThreadNotify(new Sum2Num(new ThreadNotify()));
        Thread thread1 = new Thread(new Sum2Num(threadNotify));
        Thread thread2 = new Thread(threadNotify);
        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException interruptedException){
            interruptedException.printStackTrace();
        }
    }
}
