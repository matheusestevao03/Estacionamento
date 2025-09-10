package br.com.restaurante.estacionamento.dao;

import br.com.restaurante.estacionamento.exception.EstacionamentoException;
import br.com.restaurante.estacionamento.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public void insert(Payment payment) throws EstacionamentoException {
        String sql = "INSERT INTO payments (vehicle_id, amount, paid_at) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, payment.getVehicleId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setTimestamp(3, Timestamp.valueOf(payment.getPaidAt()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EstacionamentoException("Erro ao registrar pagamento: " + e.getMessage(), e);
        }
    }

    public List<Payment> listAll() throws EstacionamentoException {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("vehicle_id"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("paid_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new EstacionamentoException("Erro ao listar pagamentos: " + e.getMessage(), e);
        }
        return list;
    }
}
