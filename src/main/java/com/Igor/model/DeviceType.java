package com.Igor.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "devicetype")
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    public DeviceType() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
