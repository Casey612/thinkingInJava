package ch14;

import ch14.example1.FancyToy;
import ch14.example1.Toy;

/**
 * @author yuzhe
 * @since 9/3/18
 */
public class GenericToyTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<FancyToy> ftClass = FancyToy.class;
        FancyToy ft = ftClass.newInstance();
        Class<? super FancyToy> up = ftClass.getSuperclass();
//        Class<Toy> up2 = ftClass.getSuperclass();
        Object toy1 = up.newInstance();
        Toy toy2 = (Toy) up.newInstance();
    }

}
