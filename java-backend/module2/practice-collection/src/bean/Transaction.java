package bean;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private Integer id;
    private Trader trader;
    private LocalDateTime createdTime;
    private Double value;

    public Transaction() {
    }

    public Transaction(Integer id, Trader trader, LocalDateTime createdTime, Double value) {
        this.id = id;
        this.trader = trader;
        this.createdTime = createdTime;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", trader=" + trader +
                ", createdTime=" + createdTime +
                ", value=" + value +
                '}';
    }
}
