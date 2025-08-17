package bean;

import java.util.Objects;

public class Apple {
    private Integer id;
    private String color;
    private Double weight;
    private String origin;

    public Apple() {
    }

    public Apple(Integer id, String color, Double weight, String origin) {
        this.id = id;
        this.color = color;
        this.weight = weight;
        this.origin = origin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Apple)) {
            return false;
        }

        Apple that = (Apple) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Apple [id=" + id + ", color=" + color + ", weight=" + weight + ", origin=" + origin + "]";
    }
}