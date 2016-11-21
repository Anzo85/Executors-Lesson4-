package ClientManagerSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 21.11.2016.
 */
public class ClientMsExecutorTest {

    public static void main(String[] args) {

        List<Client> clients = Arrays.asList(new Client(1, "X1", "X2"),
                new Client(2, "X2", "X2"),
                new Client(3, "X3", "X2"),
                new Client(4, "X4", "X2"),
                new Client(5, "X5", "X2"),
                new Client(6, "X6", "X2"));


        List<ClientTask> clientTaskList = Arrays.asList(
                new ClientTask(clients.get(0)),
                new ClientTask(clients.get(0)),
                new ClientTask(clients.get(2)),
                new ClientTask(clients.get(3)),
                new ClientTask(clients.get(4))
        );

        ClientMSExecutor clientMSExecutor = new ClientMSExecutor();

        for(ClientTask clientTask : clientTaskList ) {
            clientMSExecutor.submit(clientTask , clientTask.getClient());
        }
    }
}
