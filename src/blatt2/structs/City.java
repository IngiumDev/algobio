package blatt2.structs;

import java.util.Objects;

public class City {
    private int id;
    private String name;
    private double phi;
    private double lambda;

    public City(int id, String name, double phi, double lambda) {
        this.id = id;
        this.name = name;
        this.phi = phi;
        this.lambda = lambda;
    }

    public double[] getCoordinates() {
        return new double[]{phi, lambda};
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPhi(), getLambda());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return getId() == city.getId() && Double.compare(getPhi(), city.getPhi()) == 0 && Double.compare(getLambda(), city.getLambda()) == 0 && getName().equals(city.getName());
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phi=" + phi +
                ", lambda=" + lambda +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setName(String name) {
        this.name = name;
    }
}
