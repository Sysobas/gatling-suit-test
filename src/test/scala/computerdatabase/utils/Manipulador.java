package computerdatabase.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "./src/test/resources/link.properties");
        props.load(file);
        return props;
    }

}