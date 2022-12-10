import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final ArrayList<Connection> connections = new ArrayList<Connection>();
    public static void main(String[] args) throws IOException {
        try {
            int port = Integer.parseInt("2000");
            ServerSocket server = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for client...");
                Socket client = server.accept();
                System.out.println("New client !");
                Connection connection = new Connection(client);
                connections.add(connection);
                connection.start();

            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}

