package by.panasyuk.domain;

import by.panasyuk.dao.Identified;

import java.util.Objects;

public class Drug implements Identified<Integer> {
    private int id;
    private String name;
    private boolean isPrescriptionRequired;
    private int price;

    public Drug(int id, String name, boolean isPrescriptionRequired, int price) {
        this.id = id;
        this.name = name;
        this.isPrescriptionRequired = isPrescriptionRequired;
        this.price = price;
    }

    public Drug() {
    }

    @Override
    public Integer getId() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrescriptionRequired() {
        return isPrescriptionRequired;
    }

    public void setPrescriptionRequired(boolean prescriptionRequired) {
        isPrescriptionRequired = prescriptionRequired;
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
        Drug drug = (Drug) o;
        return id == drug.id &&
                isPrescriptionRequired == drug.isPrescriptionRequired &&
                price == drug.price &&
                Objects.equals(name, drug.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isPrescriptionRequired, price);
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPrescriptionRequired=" + isPrescriptionRequired +
                ", price=" + price +
                '}';
    }
}

