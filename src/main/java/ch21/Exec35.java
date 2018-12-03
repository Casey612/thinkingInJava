package ch21;

import ch15.Generator;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yuzhe
 * @since 12/3/18
 */
public class Exec35 {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        RequestLine requestLine = new RequestLine();
        RequestGenerator requestGenerator = new RequestGenerator(requestLine);
        exec.execute(requestGenerator);
        WebServerManager webServerManager = new WebServerManager(5, requestLine, exec);
        exec.execute(webServerManager);
    }

}

class WebServerManager implements Runnable {
    private final int size;
    private Semaphore available;
    private volatile boolean[] flags;
    private List<WebServer> servers;
    private ExecutorService exec;

    private RequestLine requests;

    public WebServerManager(int size, RequestLine line, ExecutorService exec) {
        this.exec = exec;
        this.requests = line;
        this.size = size;
        this.available = new Semaphore(size, true);
        this.flags = new boolean[size];
        this.servers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            servers.add(new WebServer(this));
        }
    }

    private synchronized WebServer getServer() throws InterruptedException {
        if (available.tryAcquire()) {
            for (int i = 0; i < size; i++) {
                if (!flags[i]) {
                    flags[i] = true;
                    return servers.get(i);
                }
            }
        }
        return null;
    }

    public synchronized void release(WebServer server) {
        System.out.println(server.toString() + " release");
        int index = servers.indexOf(server);
        if (index != -1) {
            flags[index] = false;
            available.release();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                WebServer server = getServer();
                Request request = requests.take();
                if (!Objects.isNull(server)) {
                    server.setRequest(request);
                    exec.execute(server);
                } else {
                    System.out.println("latest request: " + requests.take().toString());
                    exec.shutdownNow();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("manager interrupted");
        }
    }
}


class WebServer implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Request request;
    private WebServerManager manager;

    public WebServer(WebServerManager manager) {
        this.manager = manager;
    }

    public synchronized void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "WebServer{" + "id=" + id + '}';
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (Objects.isNull(request)) {
                    wait();
                }
                TimeUnit.MILLISECONDS.sleep(request.getTimes());
                System.out.println(this + " processed " + request.toString());
                manager.release(this);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }
}

class RequestLine extends LinkedBlockingQueue<Request> {
}


class RequestGenerator implements Generator<Request>, Runnable {
    private static Random random = new Random();
    private RequestLine line;

    public RequestGenerator(RequestLine line) {
        this.line = line;
    }

    @Override
    public Request next() {
        return new Request(random.nextInt(10));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                this.line.add(next());
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1));
            }
        } catch (InterruptedException e) {
            System.out.println("request generator interrupted");
        }
        System.out.println("request generator stopped");
    }
}

class Request {
    private static int counter = 0;
    private final int id = counter++;
    private final int times;

    public Request(int times) {
        this.times = times;
    }

    public int getTimes() {
        return times;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", times=" + times + '}';
    }
}
