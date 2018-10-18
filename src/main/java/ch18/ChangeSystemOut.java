package java.ch18;

import java.io.PrintWriter;

/**
 * @author yuzhe
 * @since 10/12/18
 */
public class ChangeSystemOut {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("hello, world");
    }

}
