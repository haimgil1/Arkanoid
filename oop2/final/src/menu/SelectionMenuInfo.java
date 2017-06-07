package menu;

/**
 * A SelectionMenuInfo class.
 *
 * @author Nir Dunetz and Haim Gil.
 * @param <T> is void.
 */
public class SelectionMenuInfo<T> {

    private String key;
    private String message;
    private T returnVal;

    /**
     * Constructor.
     *
     * @param key       is a key that turning off the animation.
     * @param message   is the message string.
     * @param returnVal is a the value.
     */
    public SelectionMenuInfo(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return the value.
     */
    public T getReturnVal() {
        return this.returnVal;
    }

}