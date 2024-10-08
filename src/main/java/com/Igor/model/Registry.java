package com.Igor.model;


import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "deviceregistry")
public class Registry{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "device_type_id")
//    private Integer deviceTypeId;

    /**
     * Пробую сделать как в репозитории. Может быть получится.
     * https://github.com/okihouse/spring-jpa-querydsl-sample/blob/master/src/main/java/com/boot/jpa/domain/User.java
     * */
    @ManyToOne()
    @JoinColumn(name = "device_type_id")
    private DeviceType deviceType;
    @Column(name = "country_of_origin")
    private String countryOfOrigin;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "online_order_available")
    private Boolean onlineOrderAvailable;
    @Column(name = "installment_available")
    private Boolean installmentAvailable;

    @ElementCollection
    @CollectionTable(name = "registry_products", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "models_in_stock", columnDefinition = "integer")
    private List<Integer> models;

    public Registry() {}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DeviceType getDeviceTypeId() { return deviceType; }

    public void setDeviceTypeId(DeviceType deviceTypeId) {
        this.deviceType = deviceTypeId;
    }

    public List<Integer> getModels() {
        return models;
    }
    public void setModels(List<Integer> modelsInStock) {
        this.models = modelsInStock;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getOnlineOrderAvailable() {
        return onlineOrderAvailable;
    }

    public void setOnlineOrderAvailable(Boolean onlineOrderAvailable) {
        this.onlineOrderAvailable = onlineOrderAvailable;
    }

    public Boolean getInstallmentAvailable() {
        return installmentAvailable;
    }

    public void setInstallmentAvailable(Boolean installmentAvailable) {
        this.installmentAvailable = installmentAvailable;
    }
}
