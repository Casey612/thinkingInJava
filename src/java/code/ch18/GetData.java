package code.ch18;

import java.nio.ByteBuffer;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public class GetData {

    private static final int SIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            if (byteBuffer.get(i) != 0) {
                System.out.println("index " + i + " is nonzero");
            }
        }
        System.out.println(byteBuffer.limit());
        System.out.println("-----------------------------------------");
        byteBuffer.rewind();
        byteBuffer.asCharBuffer().put("Howdy!");
        char c;
        while ((c = byteBuffer.getChar()) != 0) {
            System.out.print(c);
        }
        System.out.println();
        System.out.println("-----------------------------------------");


    }

}
