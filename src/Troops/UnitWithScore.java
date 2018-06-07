package Troops;

public class UnitWithScore implements Comparable<UnitWithScore> {

    private Unit unit;
    private int score;

    public UnitWithScore(Unit unit, int score) {
        this.unit = unit;
        this.score = score;
    }

    public UnitWithScore(Unit unit) {
        System.out.println(unit);
        this.unit = unit;
        this.score = unit.getPowerRange().generatePower();
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(UnitWithScore o) {
        // Reverse sort order to get highest scores first
        return Integer.compare(o.getScore(), this.getScore());
    }

    @Override
    public String toString() {
        return "UnitWithScore{" +
                "unit=" + unit.getTroupType().toString() +
                ", score=" + score +
                '}';
    }
}
