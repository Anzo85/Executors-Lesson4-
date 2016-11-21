package ClientManagerSystem;

import java.util.concurrent.Callable;

public class ClientTask implements Runnable {

    private Client client;

    public Client getClient() {
        return client;
    }

    public ClientTask(Client client) {
        this.client = client;
    }

    public void run() {

        System.out.println("client" + client);

    }


}
