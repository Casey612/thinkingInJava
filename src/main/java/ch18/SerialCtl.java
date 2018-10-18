package ch18;

import java.io.*;

/**
 * @author yuzhe
 * @since 10/17/18
 */
public class SerialCtl implements Serializable {

    private String a;
    private transient String b;

    public SerialCtl(String aa, String bb) {
        a = "not transient: " + aa;
        b = "transient: " + bb;
    }

    @Override
    public String toString() {
        return a + "\t" + b;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
        b = (String) stream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtl sc = new SerialCtl("test1", "test2");
        System.out.println("before:" + sc);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buf);
        out.writeObject(sc);

        ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(buf.toByteArray())
        );
        SerialCtl sc2 = (SerialCtl) in.readObject();
        System.out.println("After: " + sc2);
    }
}
