package ch18;

import java.io.*;

/**
 * @author: yuki
 * @date: 2018/10/9
 */
public class StoringAndRecoveringData {

    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("src/main/resources/Data.txt")
                )
        );
        out.writeDouble(3.141592d);
        out.writeUTF("That is pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();

        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("src/main/resources/Data.txt")
                )
        );

        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }

}
