package ch19;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public enum  Course {

    Appetizer(Food.Appetizer.class),
    DESSERT(Food.Dessert.class);

    private Food[] values;

    Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food selectRandom(){
        return Enums.random(values);
    }
}
