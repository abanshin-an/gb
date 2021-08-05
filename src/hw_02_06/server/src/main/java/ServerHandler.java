import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerHandler {

    private BufferedWriter out = null;
    private BufferedReader in = null;
    private Socket socket;
    private Thread rxThread;
    //    DataInputStream in;
//    DataOutputStream out;


    public ServerHandler(Socket socket) {
        try {
            this.socket = socket;
//            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            rxThread = new Thread( ()-> {
                try {
                    String str;
                    while (true) {
                        str = in.readLine();
//                        str = in.readUTF();
                        if (str.equals("/end")) {
                            System.out.println("Client disconnected");
                            rxThread.interrupt();
                            break;
                        }
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    if (!rxThread.isInterrupted())
                        e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                        System.out.println("socket.close();");
                    } catch (IOException e) {
                        if (!rxThread.isInterrupted())
                            e.printStackTrace();
                    }
                }
            });
            rxThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void interrupt() {
        rxThread.interrupt();
    }
    public synchronized void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMsg(String s) {
        try {
//            out.writeUTF(s);
            out.write(s + "\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}