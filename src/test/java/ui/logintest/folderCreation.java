package ui.logintest;

import com.saucelabs.saucerest.SauceREST;
import org.pojava.datetime.DateTime;

import java.io.File;
import java.util.Random;

public class folderCreation {
    //SauceREST sauceREST;

    public static File createTempDir() {
        final String baseTempPath = System.getProperty("java.io.tmpdir");

        Random rand = new Random();
        int randomInt = 1 + rand.nextInt();

        File tempDir = new File(baseTempPath + File.separator + "tempDir" + randomInt);
        if (tempDir.exists() == false) {
            tempDir.mkdir();
        }

        tempDir.deleteOnExit();

        return tempDir;
    }

    public static void main(String args[]) {
         //sauceREST = null;
        File dir = createTempDir();
        SauceREST sauceREST = new SauceREST("oauth-pavanimulkalapally-83b21", "c8deacc4-90ef-4a20-8d2e-320a0c1917b2");
        String username = sauceREST.getUsername();
        String text = sauceREST.getJobInfo("e2ff96c8a1c74665b1a918ce644f6909");
        System.out.println("dsfsd");
//        String dateTime = new DateTime().toString("dd-MM-yy HH:mm:ss");
//        File f = new File(dateTime);
//        if(f.mkdir()) {
//            System.out.println("file got created");
//        } else {
//            System.out.println("file not created");
//        }
   }
}
