package br.com.restaurante.estacionamento.model;

import java.time.LocalDateTime;

public class Vehicle {
    private int id;
    private String plate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double paidAmount;

    public Vehicle(int id, String plate, LocalDateTime entryTime, LocalDateTime exitTime, double paidAmount) {
        this.id = id;
        this.plate = plate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.paidAmount = paidAmount;
    }

    public Vehicle(String plate, LocalDateTime entryTime) {
        this.plate = plate;
        this.entryTime = entryTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPlate() { return plate; }
    public void setPlate(String plate) { this.plate = plate; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount; }
}
