/* Service Application by Johnny Austor */
import java.util.*;

public class ServiceApplication {
    private boolean running;
    private Thread thread;

    public static void main(String[] args) {
        new ServiceApplication().start();
    }

    private void start() {
        this.running = true;
        System.out.println("Service is running!\n");

        this.thread = new Thread(this::process);
        this.thread.start();

        this.waitForExit();
    }

    private void waitForExit() {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        while (!"exit".equalsIgnoreCase(line)) {
            line = scanner.nextLine();
            System.out.println();
            System.out.println("Type 'exit' to safely close this program!");
        }

        stop();
    }

    private void stop() {
        System.out.println("Stopping service");
        try {
            this.running = false;
            this.thread.interrupt();
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            // log the error
        }
        return;
    }

    private void process() {
        while (this.running) {
            try {
                this.doSomething();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                // log the error
            }
        }
    }

    private void doSomething() {
        // do something
    }
}