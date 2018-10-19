package ch19;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public interface Food {

    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS;
    }

    enum Dessert implements Food {
        FRUIT, CREME_CARAMEL, LASAGNE, BURRITO;
    }

}
