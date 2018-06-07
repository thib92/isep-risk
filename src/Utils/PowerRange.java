package Utils;

import java.util.concurrent.ThreadLocalRandom;

public class PowerRange {

    private int lower;
    private int upper;

    public PowerRange(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public int generatePower() {
        return ThreadLocalRandom.current().nextInt(this.lower, this.upper+1);
    }

    public int getLower() {
        return lower;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }

    public int getUpper() {
        return upper;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }
}
