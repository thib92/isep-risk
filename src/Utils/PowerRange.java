package Utils;

public class PowerRange {

    private int lower;
    private int upper;

    public PowerRange(int upper, int lower) {
        this.lower = lower;
        this.upper = upper;
    }

    public int generatePower() {
        return (int)(Math.random() * (this.getUpper() - this.getLower()));
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
