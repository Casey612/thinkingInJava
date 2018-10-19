package ch19;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public class Meal {

    public static void main(String[] args) {
        for(Course course : Course.values()){
            Food food = course.selectRandom();
            System.out.println(food);
        }
    }

}
