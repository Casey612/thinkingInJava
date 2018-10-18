package java.ch13;

/**
 * @author yuzhe
 * @since 8/28/18
 */
public class DatabaseException extends Exception {

    public DatabaseException(int transactionId, int queryId, String message) {
        super(String.format("(t%d, q%d) %s", transactionId, queryId, message));
    }

    public static void main(String[] args) {
        try {
            throw new DatabaseException(3, 7, "write failed");
        } catch (DatabaseException e) {
            System.out.println(e);
        }
    }

}
