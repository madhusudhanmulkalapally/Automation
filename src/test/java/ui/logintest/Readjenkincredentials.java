package ui.logintest;

import org.testng.annotations.Test;

public class Readjenkincredentials {

@Test
public void check() {
    String text = System.getenv("read");
    System.out.println("secretlength: " + text.length());
    System.out.println("secret name: " + text);
}

}
