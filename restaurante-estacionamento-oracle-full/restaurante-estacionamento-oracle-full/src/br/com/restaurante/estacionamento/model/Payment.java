package br.com.restaurante.estacionamento.model;

import java.time.LocalDateTime;

public class Payment {
    private int id;
    private int vehicleId;
    private double amount;
    private LocalDateTime paidAt;

    public Payment(int id, int vehicleId, double amount, LocalDateTime paidAt) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.amount = amount;
        this.paidAt = paidAt;
    }

    public Payment(int vehicleId, double amount, LocalDateTime paidAt) {
        this.vehicleId = vehicleId;
        this.amount = amount;
        this.paidAt = paidAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}
