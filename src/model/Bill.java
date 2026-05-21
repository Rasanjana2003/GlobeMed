package model;

import model.enums.BillStatus;
import model.enums.PaymentMethod;

public class Bill {
    private int id;
    private int patientId;
    private int appointmentId;
    private double amount;
    private PaymentMethod  paymentMethod;
    private BillStatus status;
    private String createdAt;
    private String serviceType;
    
    public Bill(){}
    
    public Bill(int id,int patientId,int appointmentId,double amount,PaymentMethod paymentmethod,BillStatus status,String createdAt,String serviceType){
        this.id = id;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentMethod = paymentmethod;
        this.status = status;
        this.createdAt = createdAt;
        this.serviceType = serviceType;
    }
    
    public Bill(int patientId,int appointmentId,double amount,PaymentMethod paymentmethod,BillStatus status,String createdAt,String serviceType){
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentMethod = paymentmethod;
        this.status = status;
        this.createdAt = createdAt;
        this.serviceType = serviceType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    

  
}
