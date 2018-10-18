package java.ch18;

import java.io.*;

/**
 * @author yuzhe
 * @since 10/17/18
 */
public class FreezeAlien {

    public static void main(String[] args) throws IOException {
        ObjectOutput out = new ObjectOutputStream(
                new FileOutputStream("X.file")
        );

        Alien quellek = new Alien();
        out.writeObject(quellek);
    }

}
