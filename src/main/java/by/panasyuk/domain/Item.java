package by.panasyuk.domain;

import by.panasyuk.repository.Identified;

import java.util.Objects;

public class Item implements Identified<Integer> {
    private int id;
    private  Drug drug;
    private int orderId;
    private int amount;

    public Item(Drug drug, int amount) {
        this.drug = drug;
        this.amount = amount;
    }

    public Item(Drug drug) {
        this.drug = drug;
    }

    public Item() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override

    public Integer getId() {
        return id;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                orderId == item.orderId &&
                amount == item.amount &&
                Objects.equals(drug, item.drug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, drug, orderId, amount);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", drug=" + drug +
                ", orderId=" + orderId +
                ", amount=" + amount +
                '}';
    }
}
