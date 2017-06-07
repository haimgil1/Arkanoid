package read;

/**
 * A LevelSetsInfo class.
 *
 * @author Nir Dunetz and Haim Gil.
 */

public class LevelSetsInfo {

    //Members.
    private String key;
    private String message;
    private String path;

    /**
     * Constructor.
     *
     * @param key     is a key string.
     * @param message is a message string.
     * @param path    is a path string.
     */
    public LevelSetsInfo(String key, String message, String path) {
        this.key = key;
        this.message = message;
        this.path = path;
    }

    /**
     * @return String representing key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return String representing message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return String representing path.
     */
    public String getPath() {
        return this.path;
    }

}