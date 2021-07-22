package ui.core;

public class CoreUtils {

    static {
        System.setProperty("https.protocols", "TLSv1, TLSv1.1, TLSv1.2");
        if(System.getProperty("env.type") == null) {
            System.setProperty("env.type", "QA");
        }
    }
}
