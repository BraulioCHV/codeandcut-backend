package org.code_cut.code_cutSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServices;

    private String name;
    private String description;
    private int price;

    // Constructores
    public Service() {}

    public Service(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters y Setters
    public Long getIdServices() {
        return idServices;
    }

    public void setIdServices(Long idServices) {
        this.idServices = idServices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}