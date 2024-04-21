package Model;

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
}
