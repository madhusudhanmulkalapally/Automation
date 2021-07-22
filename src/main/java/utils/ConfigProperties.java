package utils;

import java.util.Properties;

public class ConfigProperties {

    private static Properties configProperties;
    private static ConfigProperties SINGLE_INSTANCE=null;

    public static ConfigProperties getInstance() {

        if(SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE= new ConfigProperties();
            loadConfig();
        }
        return SINGLE_INSTANCE;

    }

    public static void loadConfig() {
        try{
            if(null==configProperties)
                configProperties=new Properties();
            configProperties.load(SecretProperties.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 public String getConfig(String config) {
     loadConfig();
     String env = System.getProperty("env.type");
     String secretWithEnv = "";
     if(null !=env) {
         secretWithEnv = config+"_"+env
;     }
String configValue = configProperties.getProperty(secretWithEnv);
     if(null!=configValue){
         return configValue;
     }
     config = configProperties.getProperty(config);
     return config;
 }
}

