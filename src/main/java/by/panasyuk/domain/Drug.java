package by.panasyuk.domain;

import by.panasyuk.repository.Identified;

import java.util.Objects;

public class Drug implements Identified<Integer> {
    private int id;
    private String name;
    private boolean isPrescriptionRequired;
    private int price;
    private Manufacturer manufacturer;
    private ReleaseForm releaseForm;

    public Drug(int id, String name, boolean isPrescriptionRequired, int price, Manufacturer manufacturer, ReleaseForm releaseForm) {
        this.id = id;
        this.name = name;
        this.isPrescriptionRequired = isPrescriptionRequired;
        this.price = price;
        this.manufacturer = manufacturer;
        this.releaseForm = releaseForm;
    }

    public Drug(String name, boolean isPrescriptionRequired, int price, Manufacturer manufacturer, ReleaseForm releaseForm) {
        this.name = name;
        this.isPrescriptionRequired = isPrescriptionRequired;
        this.price = price;
        this.manufacturer = manufacturer;
        this.releaseForm = releaseForm;
    }

    public Drug() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ReleaseForm getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(ReleaseForm releaseForm) {
        this.releaseForm = releaseForm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsPrescriptionRequired() {
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
                Objects.equals(name, drug.name) &&
                Objects.equals(manufacturer, drug.manufacturer) &&
                Objects.equals(releaseForm, drug.releaseForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isPrescriptionRequired, price, manufacturer, releaseForm);
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPrescriptionRequired=" + isPrescriptionRequired +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                ", releaseForm=" + releaseForm +
                '}';
    }
}

