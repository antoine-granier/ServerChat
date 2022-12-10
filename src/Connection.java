import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection extends Thread {
    private final Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public void interrupt() {
        super.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String command = bufferedReader.readLine();
                if (command.split(" : ")[1].equals("exit")) {
                    System.out.println("Disconnected    ");
                    //socket.close();
                    break;
                }
                if(command.split(" : ")[1] != null || !command.split(" : ")[1].equals("")) {
                    outputStream.writeBytes("OK : " + command + "\n");
                    System.out.println(command);
                }
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            try {
                if(!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
