package network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPConnection {
    final private Socket socket;
    final private Thread rxThread;
    final private BufferedReader in;
    final private BufferedWriter out;
    final private TCPConnectionListener eventListener;

    public TCPConnection(TCPConnectionListener listener, String ipAddress, int port) throws IOException {
        this(listener,new Socket(ipAddress,port));
    }

    public TCPConnection(TCPConnectionListener listener, Socket socket) throws IOException {
        this.socket=socket;
        this.eventListener=listener;
        in= new BufferedReader( new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        rxThread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(TCPConnection.this);
                    while (!rxThread.isInterrupted()) {
                        String msg = in.readLine();
                        if (msg == null || msg.equals("/end")) {
                            rxThread.interrupt();
                            break;
                        }
                        eventListener.onReceiveString(TCPConnection.this, msg);
                    }
                } catch (IOException e) {
                    if (!rxThread.interrupted())
                        eventListener.onException(TCPConnection.this,e);
                } finally {
                    eventListener.onDisconnect(TCPConnection.this);
                }
            }

        });
        rxThread.start();
    }

    public synchronized void sendString(String value){
        try {
            out.write(value+"\r\n");
            out.flush();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this,e);
            disconnect();
        }
    }

    public synchronized void disconnect(){
        rxThread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this,e);
        }
    }

//    @Override
//    public String toString() {
//        return "TCPConnection{" + socket.getInetAddress()+":"+socket.getPort()+'}';
//    }
}