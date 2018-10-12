package code.ch18;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author yuzhe
 * @since 10/12/18
 */
public class BinaryFile {


    public static byte[] read(File bFile) throws IOException {
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(bFile)
        );
        try {
            byte[] data = new byte[in.available()];
            in.read(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static byte[] read(String path) throws IOException {
        return read(new File(path).getAbsoluteFile());
    }

    public static void main(String[] args) {
        String path = "target/classes/code/ch18/TextFile.class";
        try {
            byte[] data = read(path);
            byte[] magic = new byte[4];
            Integer num = 0;
            for(int i = 0; i < 4; i++){
                magic[i] = data[i];
                num <<= 8;
                num += (magic[i] & 0Xff);
            }
            System.out.println(Arrays.toString(magic));
            System.out.println(Integer.toBinaryString(num));
            System.out.println(Integer.toHexString(num));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
