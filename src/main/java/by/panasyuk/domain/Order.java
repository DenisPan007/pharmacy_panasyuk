package by.panasyuk.domain;

import by.panasyuk.repository.Identified;

import java.util.List;
import java.util.Objects;

public class Order implements Identified<Integer> {
    private int id;
    private int userId;
    public enum Status{NEW,AT_WORK,COMPLETED,REJECTED}
    private Status status;
    private int price;
    private List<Item> itemList;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                price == order.price &&
                status == order.status &&
                Objects.equals(itemList, order.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, status, price, itemList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", price=" + price +
                ", itemList=" + itemList +
                '}';
    }
}
