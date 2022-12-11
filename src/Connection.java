import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection extends Thread {
    private final Socket socket;
    private BufferedReader bufferedReader = null;
    private DataOutputStream outputStream = null;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeBytes(message + "\n");
    }

    public Socket getSocket() {
        return socket;
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
            while (true) {
                String command = bufferedReader.readLine();
                if (command.split(" : ")[1].equals("exit")) {
                    System.out.println("Disconnected    ");
                    //socket.close();
                    break;
                }else if(command.split(" : ")[1] != null || !command.split(" : ")[1].equals("")) {
                    Server.sendMessage(socket, command);
                    //outputStream.writeBytes("OK : " + command + "\n");
                    //System.out.println(command);
                }
            }
        } catch (IOException e) {
            this.interrupt();
            e.printStackTrace();
        }
    }
}
