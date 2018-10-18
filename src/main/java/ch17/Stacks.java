package java.ch17;

import java.util.Stack;

/**
 * @author: yuki
 * @date: 2018/10/7
 */
public class Stacks {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        for(Month m : Month.values()){
            stack.push(m.toString());
        }
        stack.add("the last line");
        System.out.println("element 5 = " + stack.elementAt(5));
        System.out.println("popping elements:");
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }

}

enum Month{
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;
}
