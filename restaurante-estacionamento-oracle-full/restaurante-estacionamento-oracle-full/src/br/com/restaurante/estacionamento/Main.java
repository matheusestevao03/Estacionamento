package br.com.restaurante.estacionamento;

import br.com.restaurante.estacionamento.dao.PaymentDAO;
import br.com.restaurante.estacionamento.dao.VehicleDAO;
import br.com.restaurante.estacionamento.exception.EstacionamentoException;
import br.com.restaurante.estacionamento.model.Payment;
import br.com.restaurante.estacionamento.model.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final double RATE_PER_HOUR = 40.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VehicleDAO vehicleDAO = new VehicleDAO();
        PaymentDAO paymentDAO = new PaymentDAO();

        while (true) {
            System.out.println("===== Estacionamento Restaurante =====");
            System.out.println("1 - Registrar entrada de veículo");
            System.out.println("2 - Registrar saída de veículo");
            System.out.println("3 - Listar veículos estacionados");
            System.out.println("4 - Listar pagamentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            try {
                if (op == 1) {
                    System.out.print("Placa do veículo: ");
                    String plate = sc.nextLine();
                    Vehicle v = new Vehicle(plate, LocalDateTime.now());
                    vehicleDAO.insert(v);
                    System.out.println("Entrada registrada para " + plate);
                } else if (op == 2) {
                    System.out.print("Placa do veículo: ");
                    String plate = sc.nextLine();
                    Vehicle v = vehicleDAO.findByPlate(plate);
                    if (v == null) {
                        System.out.println("Veículo não encontrado ou já saiu.");
                        continue;
                    }
                    v.setExitTime(LocalDateTime.now());
                    long minutes = Duration.between(v.getEntryTime(), v.getExitTime()).toMinutes();
                    double hours = Math.ceil(minutes / 60.0);
                    double amount = hours * RATE_PER_HOUR;
                    v.setPaidAmount(amount);
                    vehicleDAO.updateExit(v);
                    paymentDAO.insert(new Payment(v.getId(), amount, LocalDateTime.now()));
                    System.out.println("Saída registrada. Valor a pagar: R$" + amount);
                } else if (op == 3) {
                    List<Vehicle> vehicles = vehicleDAO.listParked();
                    System.out.println("Veículos estacionados:");
                    for (Vehicle v : vehicles) {
                        System.out.println("- " + v.getPlate() + " (entrada: " + v.getEntryTime() + ")");
                    }
                } else if (op == 4) {
                    List<Payment> payments = paymentDAO.listAll();
                    System.out.println("Pagamentos registrados:");
                    for (Payment p : payments) {
                        System.out.println("- Veículo " + p.getVehicleId() + ": R$" + p.getAmount() + " em " + p.getPaidAt());
                    }
                } else if (op == 0) {
                    System.out.println("Encerrando...");
                    break;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (EstacionamentoException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }
}
