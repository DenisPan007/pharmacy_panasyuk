package by.panasyuk.domain;

import by.panasyuk.repository.Identified;

import java.util.Objects;

public class Prescription implements Identified<Integer> {
    int id;
    String description;
    long issueDate;
    long validityDate;
    int drugId;
    int doctorId;
    int userId;

    public Prescription() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(long issueDate) {
        this.issueDate = issueDate;
    }

    public long getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(long validityDate) {
        this.validityDate = validityDate;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return id == that.id &&
                issueDate == that.issueDate &&
                validityDate == that.validityDate &&
                drugId == that.drugId &&
                doctorId == that.doctorId &&
                userId == that.userId &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, issueDate, validityDate, drugId, doctorId, userId);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", issueDate=" + issueDate +
                ", validityDate=" + validityDate +
                ", drugId=" + drugId +
                ", doctorId=" + doctorId +
                ", userId=" + userId +
                '}';
    }
}
