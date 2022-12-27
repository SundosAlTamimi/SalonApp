package com.example.salonapp.Model;

public class ServiceModel {
    public String serviceName;
    public String periodTime;


    public ServiceModel(String serviceName, String periodTime) {
        this.serviceName = serviceName;
        this.periodTime = periodTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(String periodTime) {
        this.periodTime = periodTime;
    }
}
