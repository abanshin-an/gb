
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    final static int PORT = 8189;
    public static final String END = "/end";
    private ServerHandler h = null;
    private BufferedReader console;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        Socket socket;
        try {
            System.out.println("Server waiting connection ...");
            ServerSocket serverSocket = new ServerSocket(PORT);
            console = new BufferedReader(new InputStreamReader(System.in));
            socket = serverSocket.accept();
            h = new ServerHandler(socket);
            new Thread(() -> {
                try {
                    System.out.println("Client connected");
                    while (true) {
                        String s = console.readLine();
                        h.sendMsg(s);
                        if (s.equals(END)) {
                            h.interrupt();
                            break;
                        }
                    }
                    h.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}