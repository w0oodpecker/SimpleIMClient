import org.example.Logger;
import org.example.Settings;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;

public class LoggerTest {

    @Test
    public void testLog() throws IOException {
        //assert
        String settingsTestFile = "./src/test/settingsTest.json";
        String logTestFile = "./src/test/logTest.txt";
        File file = new File(logTestFile);
        file.delete();
        Settings settings = Settings.getInstance(settingsTestFile);
        Logger logger = Logger.getInstance(settings);
        FileInputStream inputStream = new FileInputStream(logTestFile);
        String expectationMessage_1 = "Hello";
        String expectationMessage_2 = "Bye-bye";
        logger.log(expectationMessage_1);
        logger.log(expectationMessage_2);
        int i;
        StringBuilder string = new StringBuilder();
        while((i = inputStream.read())!= -1){
            string.append((char) i);
        }
        //act
        System.out.println(string.toString());
        Assertions.assertEquals(true, string.toString().contains(expectationMessage_1));
        Assertions.assertEquals(true, string.toString().contains(expectationMessage_2));
    }

}
