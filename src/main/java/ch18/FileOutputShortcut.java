package java.ch18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * @author: yuki
 * @date: 2018/10/9
 */
public class FileOutputShortcut {

    static String file = "src/resources/FileOutputShortcut.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("src/java/java/ch18/FileOutputShortcut.java")
                )
        );
        PrintWriter out = new PrintWriter(file);

        int lineCount = 1;
        String s;
        while((s = in.readLine()) != null){
            out.println(s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));

    }

}
