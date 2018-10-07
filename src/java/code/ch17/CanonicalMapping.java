package code.ch17;

import java.util.WeakHashMap;

/**
 * @author: yuki
 * @date: 2018/10/7
 */
public class CanonicalMapping {

    public static void main(String[] args) throws InterruptedException {
        int size = 1000;
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<>();
        for(int i = 0; i < size; i++){
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if(i % 3 == 0){
                keys[i] = k;
            }
            map.put(k, v);
        }
        System.gc();
        Thread.currentThread().wait();
    }

}

class Element {
    private String iden;

    public Element(String s) {
        iden = s;
    }

    @Override
    public String toString() {
        return iden;
    }

    @Override
    public int hashCode() {
        return iden.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Element && iden.equals(((Element) obj).iden);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalizing " + getClass().getSimpleName() + " " + iden);
    }
}

class Key extends Element {

    public Key(String s) {
        super(s);
    }
}

class Value extends Element {

    public Value(String s) {
        super(s);
    }
}
