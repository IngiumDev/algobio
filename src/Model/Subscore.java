package Model;

public class Subscore {
    Subscore subscore;
    int score;

    public Subscore getSubscore() {
        return subscore;
    }

    public void setSubscore(Subscore subscore) {
        this.subscore = subscore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Subscore(Subscore subscore, int score) {
        this.subscore = subscore;
        this.score = score;
    }
}
