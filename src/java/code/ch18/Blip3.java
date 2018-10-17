package code.ch18;

import java.io.*;

/**
 * @author yuzhe
 * @since 10/17/18
 */
public class Blip3 implements Externalizable {

    private int i;
    private String s;

    public Blip3() {
        System.out.println("blip3 constructor");
    }

    public Blip3(int i, String s) {
        System.out.println("blip3(int i, String s) constructor");
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString() {
        return s + i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("blip3 writeExternal");
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("blip3 readExternal");
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("constructing objects:");
        Blip3 blip3 = new Blip3(47, "A String");
        System.out.println(blip3);
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("Blip3.out")
        );

        System.out.println("saving b3:");
        out.writeObject(blip3);
        out.close();

        System.out.println("recovering b3: ");
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Blip3.out")
        );
        blip3 = (Blip3) in.readObject();
        System.out.println(blip3);
    }

}
