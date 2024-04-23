package Model;

import java.util.Objects;

public class Subscore {
    CoordinatePair pair;
    int score;

    public CoordinatePair getPair() {
        return pair;
    }
    public Subscore(int l, int r, int score) {
        this.pair = new CoordinatePair(l, r);
        this.score = score;
    }

    public void setPair(CoordinatePair pair) {
        this.pair = pair;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Subscore(CoordinatePair pair, int score) {
        this.pair = pair;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscore subscore = (Subscore) o;
        return getScore() == subscore.getScore() && Objects.equals(getPair(), subscore.getPair());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPair(), getScore());
    }
}
