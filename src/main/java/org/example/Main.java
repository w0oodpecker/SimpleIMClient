package org.example;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    //Сообщения
    private static final String STARTMSG = "Вы вошли в чат. \n" +
            "Набирайте сообщение и нажимайте ENTER. \n" +
            "Для выхода из чата набирите /exit";
    private static final String EXITMSG = "/exit";
    private static final String SERVERNOTRESPONDMSG = "Серер не отвечает :(";
    private static final String ENDMSG = "Вы вышли из чата";
    private static final String ERROREXITMSG = "Что-то пошло не так :(";

    //Настройки
    private static final String NAMEOFSETTINGSFILE = "settings.json"; //Имя файла настроек


    public static void main(String[] args) {

        Settings settings = Settings.getInstance(NAMEOFSETTINGSFILE); //Читаем файл настроек и создаем объект настроек
        Logger logger = Logger.getInstance(settings); //Создаем объект логгера
        Connection connection = null; //Создаем переменную для соединения
        Scanner scanner = new Scanner(System.in); //Создаем объект для работы с консолью

        try {
            connection = new Connection(new Socket(settings.getServerHost(), settings.getServerPort()), logger); //Создаем соединение
            System.out.println(settings.getNameOfClient() + " " + STARTMSG);
            logger.log(STARTMSG);
            while (!connection.getSocket().isClosed()) {
                String message = scanner.nextLine();
                connection.send(settings.getNameOfClient() + ": " + message); // отправляем сообщение на сервер
                if (message.equals(EXITMSG)) {
                    break;
                }
            }
        } catch (IOException exc) {
            System.out.println(SERVERNOTRESPONDMSG);
        } finally {
            try {
                connection.close(); //Закрываем соединение
                logger.log(ENDMSG); //Пишем в лог сообщение о завершении чата
                logger.close(); //Закрываем логгер: файл, стримы
            }
            catch (Exception exc){
                System.out.print(ERROREXITMSG);
            }
            System.out.println(ENDMSG);
        }
    }
}



