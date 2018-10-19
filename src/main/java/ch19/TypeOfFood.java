package ch19;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public class TypeOfFood {

    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        System.out.println(food);
        food = Food.Dessert.FRUIT;
        System.out.println(food);
    }

}
