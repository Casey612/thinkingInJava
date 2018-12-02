package ch21;


import ch15.Generator;

/**
 * @author: yuki
 * @date: 2018/12/2
 */
public class BasicGenerator {

    public static Generator create(Class tClass){
        return () -> {
            try {
                return tClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

}
