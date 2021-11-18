package ui.logintest;

import org.testng.annotations.Test;

public class Readjenkincredentials {

@Test
public void check() {
    String text = System.getProperty("read");
    System.out.println("secretlength: " + text);
    System.out.println("secret name: " + System.getProperty("read"));
}

}
