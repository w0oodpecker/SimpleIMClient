package org.example;

import java.io.*;
import java.net.Socket;

public class Connection extends Thread {

    private static final String RECIEVEDMSG = "[Получен]";
    private static final String SENTDMSG = "[Отправлен]";
    private static final String CONNECTEDMSG = "Соединение с сервером установлено";
    private static final String ENDCONNECTEDMSG = "Соединение с сервером завершено";


    private Socket socket; //сокет соединения
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет
    private Logger logger; //Объект журнала


    public Connection(Socket socket, Logger logger) throws IOException {
        this.logger = logger;
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.start();
        System.out.println(CONNECTEDMSG);
        logger.log(CONNECTEDMSG);
    }


    @Override
    public void run() {
        String message;
        try {
            while (!isInterrupted()) {
                if(!this.socket.isClosed()) { //Проверяем не закрыт ли сокет
                    message = this.in.readLine();
                    if(message != null) { //Проверяем не разоравл ли сервер соединение
                        System.out.println(message);
                        logger.log(RECIEVEDMSG + " " + message);
                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
        } catch (IOException exc) {
        }
        finally {
            this.close();
        }
    }


    public void send(String message) {
        try {
            this.out.write(message + "\n");
            this.out.flush();
            logger.log(SENTDMSG + " " + message);
        } catch (IOException exc) {
        }
    }


    public void close() {
        if(!this.socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        }
        try {
            in.close();
            out.close();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        System.out.println(ENDCONNECTEDMSG);
        logger.log(ENDCONNECTEDMSG);
    }

    public Socket getSocket() {
        return socket;
    }
}
