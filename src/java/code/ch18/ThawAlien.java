package code.ch18;

import java.io.*;

/**
 * @author yuzhe
 * @since 10/17/18
 */
public class ThawAlien {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(new File("..", "X.file"))
        );
        Object mystrery = in.readObject();
        System.out.println(mystrery.getClass());
    }

}
