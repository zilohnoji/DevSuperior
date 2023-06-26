package com.donatoordep.lesson03.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String cpf;
    private Double income;
    private Integer children;

    public Client() {
    }

    public Client(Long id, @NotNull(message = "Name can´t nullable") String name, LocalDate birthDate, String cpf,
                  Double income, Integer children) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.income = income;
        this.children = children;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(birthDate, client.birthDate) && Objects.equals(cpf, client.cpf) && Objects.equals(income, client.income) && Objects.equals(children, client.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, cpf, income, children);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", cpf='" + cpf + '\'' +
                ", income=" + income +
                ", children=" + children +
                '}';
    }
}
