import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;
    private final BufferedReader bufferedReader;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try {
            if(!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String response = bufferedReader.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            try {
                if(!socket.isClosed()) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
