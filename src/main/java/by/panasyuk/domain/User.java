package by.panasyuk.domain;

import by.panasyuk.repository.Identified;
import lombok.Data;

import java.util.Objects;

@Data
public class User implements Identified<Integer> {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    public enum Role {CLIENT,DOCTOR,ADMIN}
    private Role role;

    public User(int id, String login, String password, String firstName, String lastName, String email, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setRole(role);
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        setRole("client");
    }
    public User() {
    }
    @Override
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role.name();
    }


    public void setRole(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
