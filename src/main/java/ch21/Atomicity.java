package ch21;

/**
 * @author yuzhe
 * @since 11/14/18
 */
public class Atomicity {

    int i;
    void f1(){
        i++;
    }
    void f2(){
        i += 3;
    }

}
