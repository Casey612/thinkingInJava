package ch20;

/**
 * @author: yuki
 * @date: 2018/10/27
 */
public @interface Uniqueness {
    Constraints constraints() default @Constraints(unique = true);
}
