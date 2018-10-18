package java.ch15;

/**
 * @author yuzhe
 * @since 9/6/18
 */
public class Exec34 extends MethodHolder<Exec34>{

    @Override
    Exec34 get(Exec34 arg) {
        return arg;
    }
}

abstract class MethodHolder<T>{
    abstract T get(T arg);
}
