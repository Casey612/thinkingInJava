package ch18;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author yuzhe
 * @since 10/16/18
 */
public class ZIPCompress {

    public static void main(String[] args) throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/ch18/test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("a test of java zipping");

        String[] params = new String[]{
                "src/main/resources/ch18/data.txt",
                "src/main/resources/ch18/data2.txt",
                "src/main/resources/ch18/data3.txt",
                "src/main/resources/ch18/data4.txt",
        };
        for (String param : params) {
            System.out.println("writing file " + param);
            BufferedReader in = new BufferedReader(new FileReader(param));
            //感觉zip entry的作用类似游标 指明压缩中的多个文件的当前位置
            zos.putNextEntry(new ZipEntry(param));
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            out.flush();
            in.close();
        }
        out.close();

        System.out.println("checksum: " + csum.getChecksum().getValue());
        System.out.println("-----------------------------------------------");
        System.out.println("reading file");
        FileInputStream fin = new FileInputStream("src/main/resources/ch18/test.zip");
        CheckedInputStream csumi = new CheckedInputStream(fin, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bin = new BufferedInputStream(in2);
        ZipEntry zipEntry;
        while ((zipEntry = in2.getNextEntry()) != null) {
            System.out.println("reading file " + zipEntry);
            int x;
            while ((x = bin.read()) != -1) {
                System.out.write(x);
            }
        }
        bin.close();
        in2.close();
        System.out.println("----------------------------------------------------");
        ZipFile zf = new ZipFile("src/main/resources/ch18/test.zip");
        Enumeration<? extends ZipEntry> e = zf.entries();
        while(e.hasMoreElements()){
            ZipEntry ze2 = e.nextElement();
            System.out.println("file:" + ze2);
        }

    }

}
