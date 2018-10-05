package code.ch13;

import java.util.List;
import java.util.ArrayList;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class InfiniteRecursion {

    @Override
    public String toString() {
        //error here. String + 重载, 会将this转化为String,从而调用toString()方法,造成递归.
//        return "InfiniteRecuresion" + this + "\n";
        return "InfiniteRecursion" + super.toString();
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            list.add(new InfiniteRecursion());
        }
        System.out.println(list);
    }

}
