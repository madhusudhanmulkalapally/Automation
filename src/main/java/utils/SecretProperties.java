package utils;

import java.util.Properties;

public class SecretProperties {

    private static Properties secretsProperties;
    CypherEncoder encoder = new CypherEncoder();

    public static void loeadSecrets() {
        try {
            if (null == secretsProperties)
                secretsProperties = new Properties();
                secretsProperties.load(SecretProperties.class.getClassLoader().getResourceAsStream("secret.properties"));
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        public String getSecret(String secret){

        loeadSecrets();
        String env = System.getProperty("env.type");
        String secretWithEnv="";
        if(null != env)
        {
            secretWithEnv=secret + "_"+env;
        }
        String encriptedString  = secretsProperties.getProperty(secretWithEnv);
        if(null != encriptedString) {
            String decryptedString = encoder.decryptToString(encriptedString);
            return  decryptedString;
        }
        encriptedString  = secretsProperties.getProperty(secret);
        String decryptString = encoder.decryptToString(encriptedString);
        return  decryptString;
    }
}
