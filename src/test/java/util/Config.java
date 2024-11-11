package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES_FILE = "config/default.properties";
    private static Properties myProperties;

    public static void initialize(){

        //load default properties
        myProperties = loadProperties();

        //check for any override
        for(String key: myProperties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                myProperties.setProperty(key, System.getProperty(key));
            }
        }

        //print
        log.info("Test Properties");
        log.info("********************************");
        for (String key: myProperties.stringPropertyNames()){
            log.info("{}={}", key, myProperties.getProperty(key));
        }
    }

    //this method reads all updated values once initialized in method initialize
    public static String get(String key){
        return myProperties.getProperty(key);
    }

    private static Properties loadProperties(){
        Properties prop = new Properties();
        try (InputStream stream = ResourceLoader.getResource((DEFAULT_PROPERTIES_FILE))){
            prop.load(stream);
            if (stream == null) {
                throw new IllegalArgumentException("File not found: " + DEFAULT_PROPERTIES_FILE);
            }
        }
        catch (Exception e){
            log.error("Unable to read properties file: {}", DEFAULT_PROPERTIES_FILE, e);
        }
        return prop;
    }









}
