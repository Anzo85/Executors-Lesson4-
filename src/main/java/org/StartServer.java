package org;

import org.glassfish.tyrus.server.Server;

import java.util.Scanner;

public class StartServer {

    public static void main(String[] args) throws Exception {

        //hostName, port, rootPath, (Set)(new HashSet(Arrays.asList(configuration))));
        Server server = new Server("localhost", 9090, "/websockets", CuctomerServerSocket.class);
        server.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        server.stop();

    }


}
