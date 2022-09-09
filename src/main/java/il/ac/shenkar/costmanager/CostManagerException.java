package il.ac.shenkar.costmanager;

/**
 * Class of the Cost Manager exception
 */
public class CostManagerException extends Exception {

    public CostManagerException(String message) {
        super(message);
    }

    public CostManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
