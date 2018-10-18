package ch12;

/**
 * @author yuzhe
 * @since 8/23/18
 */
public class MyExceptionUseCase {

    public static void main(String[] args) {
        try{
            throw new MyException("my exception create");
        } catch (MyException e){
            System.out.println(e.getDesc());
        }
    }

}
