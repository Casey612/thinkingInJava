package code.ch15;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author yuzhe
 * @since 9/5/18
 */
public class Exec22<T> {

    private Class<T> type;

    public Exec22(Class<T> type) {
        this.type = type;
    }

    public T create(Object... args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T>[] constructors = (Constructor<T>[]) this.type.getConstructors();
        for (Constructor<T> constructor : constructors) {
            if (constructor.getParameterCount() == args.length) {
                Class[] argTypes = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    argTypes[i] = args[i].getClass();
                }
                if (Arrays.equals(constructor.getParameterTypes(), argTypes)){
                    return constructor.newInstance(args);
                }
            }else {
                System.out.println("constructor and args not match");
            }
        }
        System.out.println("constructor and args not match");
        return null;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Exec22<Creature> exec22 = new Exec22<>(Creature.class);
        Creature creature = exec22.create("beautiful creature");
        System.out.println(creature);
    }

}

class Creature {

    String name;

    public Creature(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "create name: " + name;
    }
}
