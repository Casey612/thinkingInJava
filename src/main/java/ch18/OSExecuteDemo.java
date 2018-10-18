package java.ch18;

/**
 * @author yuzhe
 * @since 10/12/18
 */
public class OSExecuteDemo {

    public static void main(String[] args) {
        OSExecute.command("javap target/classes/java/ch18/OSExecuteDemo.class");
        System.out.println("=========================================");
        OSExecute.commandList("javap target/classes/java/ch18/OSExecuteDemo.class");
    }

}
