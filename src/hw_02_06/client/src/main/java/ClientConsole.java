import network.TCPConnection;
import network.TCPConnectionListener;

import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class ClientConsole implements TCPConnectionListener {
    private final static String IP_ADR = "localhost";
    private static final int IP_PORT = 8189;
    private TCPConnection connection;

    private ClientConsole() {
        Scanner sc = new Scanner(System.in);
        try {
            connection = new TCPConnection(this, IP_ADR, IP_PORT);
            while (true) {
                String s = sc.nextLine();
                connection.sendString(s);
                if (s.equals("/end")) {
                    break;
                }
            }
        } catch (IOException e) {
            printMsg("Connection: %s exception: %s", connection, e);
        }
    }

    public static void main(String[] args) {
        new ClientConsole();
    }

    private synchronized void printMsg(String format, Object... args) {
        System.out.println(new Formatter().format(format, args));
    }

    @Override
    public void onConnectionReady(TCPConnection connection) {
        if (connection != null)
            printMsg("Connection ready");
    }

    @Override
    public void onReceiveString(TCPConnection connection, String value) {
        printMsg(value);
    }

    @Override
    public void onDisconnect(TCPConnection connection) {
        printMsg("Connection close");
    }

    @Override
    public void onException(TCPConnection connection, Exception e) {
        printMsg("Connection: %s exception: %s", connection, e);
    }
}