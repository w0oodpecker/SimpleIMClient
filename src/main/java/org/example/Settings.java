package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Settings {

    private String nameOfClient; //Имя клиента
    private String serverHost; //Адрес сервера
    private int serverPort; //Порт чата
    private String formatOfDate; //Формат даты
    private String nameOfLoggerFile; //Имя файла логгера
    private static Settings instance;

    private Settings(String nameOfSettingsFile) {
        Object o = null;
        try {
            o = new JSONParser().parse(new FileReader(nameOfSettingsFile));
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        } catch (ParseException exc) {
            throw new RuntimeException(exc);
        }
        JSONObject j = (JSONObject) o;
        nameOfClient = (String) j.get("nameOfClient");
        serverHost = (String) j.get("serverHost");
        serverPort = Integer.parseInt((String) j.get("serverPort"));
        formatOfDate = (String) j.get("formatOfDate");
        nameOfLoggerFile = (String) j.get("nameOfLoggerFile");
    }

    public String getNameOfClient() {
        return nameOfClient;
    }

    public String getServerHost() {
        return serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getFormatOfDate() {
        return formatOfDate;
    }

    public String getGetNameOfLoggerFile() { return nameOfLoggerFile; }

    public static Settings getInstance(String nameOfSettingsFile) {
        if (instance == null) {
            instance = new Settings(nameOfSettingsFile);
        }
        return instance;
    }
}
