package ClientManagerSystem;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class ClientMSExecutor extends ThreadPoolExecutor {

    private Map<ClientRunnableEntry, Object> clientInProgressMap = new ConcurrentHashMap();
    private BlockingQueue<ClientRunnableEntry> waitClientQueue = new LinkedBlockingQueue();
    private ReentrantLock lock = new ReentrantLock();

    public ClientMSExecutor() {

        super(4, 4, 0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());
    }


    @Override
    public <T> Future<T> submit(Runnable task, T result) {

        if (task == null) throw new NullPointerException();
        RunnableFuture<T> ftask = newTaskFor(task, result);
        ClientRunnableEntry entry = new ClientRunnableEntry(ftask, result);
        if (clientInProgressMap.containsKey(entry)) {
            waitClientQueue.add(entry);
        } else {
            clientInProgressMap.put(entry, result);
            execute(entry.getRf());
        }

        execute(ftask);
        return null;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            lock.lock();
            Object client = null;
            ClientRunnableEntry clientInProgress = null;
            for (ClientRunnableEntry clientRunnableEntry : clientInProgressMap.keySet()) {
                if (r.equals(clientRunnableEntry.getRf())) {
                    client = clientRunnableEntry.client;
                    clientInProgress = clientRunnableEntry;
                    clientInProgressMap.remove(clientInProgress);
                    System.out.println("remove from main clientMap");

                    break;
                }
            }

            for (ClientRunnableEntry ClientInWait : waitClientQueue) {
                if (ClientInWait.equals(clientInProgress)) {
                    waitClientQueue.remove(ClientInWait);
                    System.out.println("remove from Queue");
                    clientInProgressMap.put(ClientInWait, client);
                    execute(ClientInWait.getRf());
                }
            }
        } finally {
            lock.unlock();
        }

    }

    class ClientRunnableEntry<T> {
        public ClientRunnableEntry(RunnableFuture rf, T client) {
            this.rf = rf;
            this.client = client;
        }

        private RunnableFuture rf;
        private T client;

        public RunnableFuture getRf() {
            return rf;
        }

        public void setRf(RunnableFuture rf) {
            this.rf = rf;
        }

        public T getClient() {
            return client;
        }

        public void setClient(T client) {
            this.client = client;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ClientRunnableEntry<?> entry = (ClientRunnableEntry<?>) o;

            return client != null ? client.equals(entry.client) : entry.client == null;

        }

        @Override
        public int hashCode() {
            return client != null ? client.hashCode() : 0;
        }
    }
}


