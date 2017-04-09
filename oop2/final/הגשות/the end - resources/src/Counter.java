/**
 * A Counter class.
 *
 * @author Shurgil and barisya
 */
public class Counter {
    private int value;

    /**
     * add number to current count.
     * @param number the number.
     */
    void increase(int number) {
        this.value = this.value + number;
    }
    /**
     * subtract number from current count.
     * @param number the number.
     */
    void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * get current count.
     * @return the value.
     */
    int getValue() {
        return this.value;
    }
}
