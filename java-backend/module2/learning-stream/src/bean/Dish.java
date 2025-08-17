package bean;

import java.util.Objects;

import common.Kind;

public class Dish {
    private String id;
    private String name;
    private Double calories;
    private Kind kind;
    private Boolean isVeggie;

    public Dish() {
    }

    public Dish(String id, String name, Double calories, Kind kind, Boolean isVeggie) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.kind = kind;
        this.isVeggie = isVeggie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Boolean getIsVeggie() {
        return isVeggie;
    }

    public void setIsVeggie(Boolean isVeggie) {
        this.isVeggie = isVeggie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Dish)) {
            return false;
        }

        Dish that = (Dish) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Dish [id=" + id + ", name=" + name + ", calories=" + calories + ", isVeggie=" + isVeggie + "]";
    }
}
