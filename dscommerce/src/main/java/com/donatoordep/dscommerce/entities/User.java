package com.donatoordep.dscommerce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cellphone;
    private LocalDate birthDate;
    private String password;
    private String[] roles;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, String cellphone, LocalDate birthDate,
                String password, String[] roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.birthDate = birthDate;
        this.password = password;
        this.roles = roles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) && Objects.equals(cellphone, user.cellphone) &&
                Objects.equals(birthDate, user.birthDate) && Objects.equals(password, user.password)
                && Arrays.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, email, cellphone, birthDate, password);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
