package ui.logintest;

public class Readjenkincredentials {

    public static void main(String args[]) {
        String text = System.getenv("msecret");
        System.out.println("secretlength: "+text);
        System.out.println("secret name: "+System.getenv("msecret"));
    }
}
