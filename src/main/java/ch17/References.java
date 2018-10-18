package java.ch17;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * @author: yuki
 * @date: 2018/10/7
 */
public class References {

    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null) {
            System.out.println("in queue: " + inq.get());
        }
    }

    public static void main(String[] args) {
        int size = 10;
        if (args.length > 0) {
            size = new Integer(args[0]);
        }

        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            sa.add(new SoftReference<>(new VeryBig("soft " + i), rq));
            System.out.println("just created: " + sa.getLast());
            checkQueue();
        }

        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            wa.add(new WeakReference<>(new VeryBig("weak " + i), rq));
            System.out.println("just created: " + wa.getLast());
            checkQueue();
        }


        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("soft"));
        WeakReference<VeryBig> w =new WeakReference<VeryBig>(new VeryBig("weak"));

        System.gc();

        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<>(new VeryBig("phantom " + i), rq));
            System.out.println("just created: " + pa.getLast());
            checkQueue();
        }

    }

}

class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String s) {
        this.ident = s;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize:" + ident);
    }
}
