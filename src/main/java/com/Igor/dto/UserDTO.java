package com.Igor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    private int productType;
    private String country;
    private String company;
    @JsonProperty("isOnline")
    private boolean isOnline;
    @JsonProperty("isInstallment")
    private boolean isInstallment;

    public boolean getOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean getInstallment() {
        return isInstallment;
    }

    public void setInstallment(boolean installment) {
        isInstallment = installment;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
