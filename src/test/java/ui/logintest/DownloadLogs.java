package ui.logintest;



import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.saucerest.DataCenter.*;

import java.io.BufferedInputStream;
import java.io.IOException;

public class DownloadLogs {

    public static void main(String args[]) throws IOException {
        SauceREST sauceREST = new SauceREST("oauth-pavanimulkalapally-83b21","c8deacc4-90ef-4a20-8d2e-320a0c1917b2", "EU");
        //String text = sauceREST.getBuild("1a82dad4b82a41ffb0710a6356186e70");
        //String text = sauceREST.getJobInfo("1a82dad4b82a41ffb0710a6356186e70");
        sauceREST.downloadLog("21b746f9c22446a29df6fdc2d0019375", "D:\\Saucelabs\\", "selenium-server.log");
        System.out.println("test");
    }
}