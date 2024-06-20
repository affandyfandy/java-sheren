import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModificationExample {
    public static void main(String[] args) {
        List<String> sharedList = new CopyOnWriteArrayList<>();
        sharedList.add("test1");
        sharedList.add("test2");
        sharedList.add("test3");

        Runnable task = () -> {
            for (String test : sharedList) {
                System.out.println(test);
                if ("test2".equals(test)) {
                    sharedList.remove(test);
                }
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}

/* Output:
 * test1
 * test2
 * test3
 * test1
 * test2
 * test3
 */