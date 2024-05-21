package blatt2.structs;

import java.util.Objects;

public class CityPair {
    private final City city1;
    private final City city2;

    public CityPair(City city1, City city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city1, city2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityPair cityPair = (CityPair) o;
        return Objects.equals(city1, cityPair.city1) && Objects.equals(city2, cityPair.city2) || Objects.equals(city1, cityPair.city2) && Objects.equals(city2, cityPair.city1);
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }
}
