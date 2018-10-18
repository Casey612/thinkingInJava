package ch18;

import java.io.*;
import java.util.*;

/**
 * @author yuzhe
 * @since 10/10/18
 */
public class TextFile extends ArrayList<String> {

    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (
                BufferedReader in = new BufferedReader(
                        new FileReader(
                                new File(fileName).getAbsoluteFile()
                        )
                )
        ) {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void write(String fileName, String text) {
        try (
                PrintWriter out = new PrintWriter(
                        new File(fileName).getAbsoluteFile()
                )
        ) {
            out.print(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String fileName) {
        try (
                PrintWriter out = new PrintWriter(
                        new File(fileName).getAbsoluteFile()
                )
        ) {
            for(String item : this){
                out.println(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextFile(String fileName, String spliter){
        super(Arrays.asList(read(fileName).split(spliter)));
        if(get(0).equals("")){
            remove(0);
        }
    }

    public TextFile(String fileName){
        this(fileName, "\n");
    }


    public static void main(String[] args) {
        String file = read("src/main/java/ch18/TextFile.java");
        write("src/main/resources/test.txt", file);
        TextFile text = new TextFile("src/main/resources/test.txt");
        text.write("src/main/resources/test2.txt");
        TreeSet<String> words = new TreeSet<>(
                new TextFile("src/main/java/ch18/TextFile.java", "\\W+")
        );
        System.out.println(words);
        //字符串字母序 区分大小写 数字和大写字母在“a” 之前
        System.out.println(words.headSet("a"));
    }

}
