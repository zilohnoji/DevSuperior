package com.donatoordep.lesson03.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

public class ClientDTO {

    private Long id;
    private String cpf;
    private Double income;
    private Integer children;

    @NotNull(message = "Name can´t nullable")
    private String name;

    @PastOrPresent(message = "Invalid date")
    private LocalDate birthDate;

    public ClientDTO(Long id, String cpf, Double income, Integer children,
                     @NotNull(message = "Name can´t nullable") String name, LocalDate birthDate) {
        this.id = id;
        this.cpf = cpf;
        this.income = income;
        this.children = children;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(id, clientDTO.id) && Objects.equals(cpf, clientDTO.cpf) && Objects.equals(income, clientDTO.income) && Objects.equals(children, clientDTO.children) && Objects.equals(name, clientDTO.name) && Objects.equals(birthDate, clientDTO.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, income, children, name, birthDate);
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", income=" + income +
                ", children=" + children +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
