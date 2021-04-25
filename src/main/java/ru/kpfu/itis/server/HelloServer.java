package ru.kpfu.itis.server;

import org.glassfish.tyrus.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.Main;
import javax.websocket.DeploymentException;

@Component
public class HelloServer {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final String SERVER_HOSTNAME = "localhost";
    private static final int SERVER_PORT = 8025;
    private static final String SERVER_CONTEXT_PATH = "";

    private Server server;

    public static final String SERVER_ADDRESS =
            "ws://" + SERVER_HOSTNAME + ":" + SERVER_PORT + SERVER_CONTEXT_PATH;

    public void start() throws DeploymentException {
        try {
            log.info("Starting server for " + SERVER_ADDRESS);

            server = new Server(
                    SERVER_HOSTNAME,
                    SERVER_PORT,
                    SERVER_CONTEXT_PATH,
                    null,
                    HelloServerEndpoint.class
            );

            server.start();
        } catch (DeploymentException e) {
            server = null;
            throw e;
        }
    }

    public void stop() {
        if (server != null) {
            log.info("Stopping server for " + SERVER_ADDRESS);

            server.stop();
        }
    }
}
