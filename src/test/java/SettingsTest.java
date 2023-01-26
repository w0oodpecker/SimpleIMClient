import org.example.Settings;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SettingsTest {

    @Test
    public void testSettings() {
        //assert
        String expectationNameOfClient = "Путин В.В."; //Имя клиента
        String expectationServerHost = "localhost"; //Адрес сервера
        int expectationServerPort = 8080; //Порт чата
        String expectationFormatOfDate = "yyyy-MM-dd HH:mm:ss"; //Формат даты
        String expectationNameOfLoggerFile = "log.txt"; //Имя файла логгера
        Settings setting = Settings.getInstance("Settings.json");
        //act
        Assertions.assertEquals(expectationNameOfClient, setting.getNameOfClient());
        Assertions.assertEquals(expectationServerHost, setting.getServerHost());
        Assertions.assertEquals(expectationServerPort, setting.getServerPort());
        Assertions.assertEquals(expectationFormatOfDate, setting.getFormatOfDate());
        Assertions.assertEquals(expectationNameOfLoggerFile, setting.getGetNameOfLoggerFile());
    }
}
