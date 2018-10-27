package ch20;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: yuki
 * @date: 2018/10/27
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableColumn {

    ColumnType type() default ColumnType.INTEGER;

    int length() default 0;

    boolean primaryKey() default false;

    boolean unique() default false;

    boolean allowNull() default true;

}
