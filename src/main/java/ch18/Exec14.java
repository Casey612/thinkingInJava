package ch18;

import java.io.*;

/**
 * @author: yuki
 * @date: 2018/10/9
 */
public class Exec14 {

    static String file = "src/main/resources/Exec14.out";


    /**
     * 文件大小较小时，时间差异并不明显。
     * 文件大小增加时，buffered明显慢于directed
     * 18kb时有： buffered:24miles directed:14miles
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        long start1 = System.currentTimeMillis();
        bufferedWR();
        long end1 = System.currentTimeMillis();
        System.out.println("========================================");
        long start2 = System.currentTimeMillis();
        directWR();
        long end2 = System.currentTimeMillis();
        System.out.println("========================================");
        System.out.println("buffered:" + (end1 - start1) + "miles");
        System.out.println("directed:" + (end2 - start2) + "miles");
    }

    public static void bufferedWR() throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader("src/main/java/ch18/Exec14.java")
        );
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file))
        );

        String s;
        while ((s = in.readLine()) != null) {
            out.println(s);
        }
        in.close();
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }

    public static void directWR() throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader("src/main/java/ch18/Exec14.java")
        );
        PrintWriter out = new PrintWriter(
                new FileWriter(file)
        );

        String s;
        while ((s = in.readLine()) != null) {
            out.println(s);
        }
        in.close();
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }

}
