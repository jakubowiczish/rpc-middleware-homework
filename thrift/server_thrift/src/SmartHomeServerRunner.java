import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartHomeServerRunner {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new SmartHomeServerRunnable());
    }
}
