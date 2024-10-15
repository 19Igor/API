package com.Igor.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public class FilterDTO {
    private String deviceType;
    private String color;
    private Integer lowPriceThreshold;
    private Integer highPriceThreshold;

    public String getDeviceType() {
        return deviceType;
    }

    public String getColor() {
        return color;
    }

    public Integer getLowPriceThreshold() {
        return lowPriceThreshold;
    }

    public Integer getHighPriceThreshold() {
        return highPriceThreshold;
    }
}
