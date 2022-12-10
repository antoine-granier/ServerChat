import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        ClientThread client = null;
        try (Socket sock = new Socket("localhost", 2000)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String input, clientName = "";
            client = new ClientThread(sock);
            client.start();

            while (true) {
                if(clientName.equals("")) {
                    System.out.println("Enter your name : ");
                    input = scanner.nextLine();
                    clientName = input;
                } else {
                    String message = clientName + " : ";
                    System.out.println("Wait for a message...");
                    input = scanner.nextLine();
                    outputStream.writeBytes(message + input + "\n");
                    if(input.equals("exit")) {
                        break;
                    }
                }
            }
            //client.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(!client.isInterrupted()) {
                    client.interrupt();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
