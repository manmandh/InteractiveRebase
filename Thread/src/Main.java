public class Main {
    public static void main(String[] args) {
        // Tạo một đối tượng của lớp con trên và gọi phương thức start() để bắt đầu thread
        MyThread myThread = new MyThread();
        myThread.start();//lệnh Thread sẽ được return ngay khi khởi chạy chứ không đợi Run
        // Tạo một đối tượng của lớp implement Runnable trên
        MyRunnable myRunnable = new MyRunnable();
        // Tạo một đối tượng của lớp Thread với đối tượng MyRunnable như là tham số
        Thread thread = new Thread ( myRunnable);
        // Gọi phương thức start() để bắt đầu thread.
        thread.start();
    }
}
//Sự khác nhau: việc sử dụng Interface Runnable có một cái lợi là giúp ta kế thừa từ 1 lớp khác ngoài lớp thread,
// vì nếu sử dụng Subclass thì chỉ có thể thừa kế một lớp Thread vì java không hỗ trợ đa thừa kế.