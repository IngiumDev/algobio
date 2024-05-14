package blatt1.Model;

import java.util.Objects;

public class CoordinatePair {
    int l;
    int r;

    public CoordinatePair(int l, int r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getL(), getR());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatePair that = (CoordinatePair) o;
        return getL() == that.getL() && getR() == that.getR();
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
