package com.Igor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class Models {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "device_type_id")
    private DeviceType deviceType;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private String size;
    @Column(name = "price")
    private Integer price;
    @Column (name = "in_stock")
    private Boolean inStock;

    public Models() {}

    public Long getId() {
        return id;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public Integer getPrice() {
        return price;
    }

    public Boolean getInStock() {
        return inStock;
    }
}
