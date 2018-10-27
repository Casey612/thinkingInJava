package ch20;

/**
 * @author: yuki
 * @date: 2018/10/27
 */
public @interface SQLInteger {

    String name() default "";

    Constraints constraints() default @Constraints;

}
