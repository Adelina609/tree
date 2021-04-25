package ru.kpfu.itis.client;

import javafx.concurrent.Task;
import org.glassfish.tyrus.client.ClientManager;
import ru.kpfu.itis.server.HelloServer;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;

public class HelloTask extends Task<String> {
    private static final String SERVER_ENDPOINT_ADDRESS =
            HelloServer.SERVER_ADDRESS + "/";

    private final String name;

    public HelloTask(String name) {
        this.name = name;
    }

    @Override
    protected String call() throws IOException, TimeoutException {
        String response = null;

        HelloClientEndpoint clientEndpoint = new HelloClientEndpoint(
                name
        );

        try {
            ClientManager client = ClientManager.createClient();
            client.connectToServer(
                    clientEndpoint,
                    URI.create(SERVER_ENDPOINT_ADDRESS)
            );

            response = clientEndpoint.getResponse();
        } catch (DeploymentException e) {
            throw new IOException(e);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }

        return response;
    }

}
