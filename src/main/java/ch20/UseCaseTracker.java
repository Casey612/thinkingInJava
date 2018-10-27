package ch20;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: yuki
 * @date: 2018/10/27
 */
public class UseCaseTracker {

    public static void trackUseCase(List<Integer> useCases, Class<?> cl){
        for(Method m : cl.getDeclaredMethods()){
            UseCase useCase = m.getAnnotation(UseCase.class);
            if(useCase != null){
                System.out.println("found use case: " + useCase.id() + "; desc: " + useCase.description());
                useCases.remove(new Integer(useCase.id()));
            }
        }

        for(int i :  useCases){
            System.out.println("warning: minssing use case:" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 46, 47, 48, 49);
        trackUseCase(useCases, PasswordUtil.class);
    }

}
