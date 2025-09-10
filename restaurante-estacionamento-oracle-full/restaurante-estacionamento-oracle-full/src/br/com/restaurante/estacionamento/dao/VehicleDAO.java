package br.com.restaurante.estacionamento.dao;

import br.com.restaurante.estacionamento.exception.EstacionamentoException;
import br.com.restaurante.estacionamento.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public void insert(Vehicle vehicle) throws EstacionamentoException {
        String sql = "INSERT INTO vehicles (plate, entry_time) VALUES (?, ?)";
        // CORREÇÃO APLICADA AQUI para o erro ORA-17132
        String[] primaryKeyColumn = { "id" };
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql, primaryKeyColumn)) {
            stmt.setString(1, vehicle.getPlate());
            // Isto agora funciona, pois a coluna no BD é TIMESTAMP
            stmt.setTimestamp(2, Timestamp.valueOf(vehicle.getEntryTime()));
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vehicle.setId(rs.getInt(1)); // Agora retorna o ID corretamente
                }
            }
        } catch (SQLException e) {
            // Adicionado o código do erro para facilitar o debug
            throw new EstacionamentoException("Erro ao inserir veículo: " + e.getMessage() + " (SQLState: " + e.getSQLState() + ")", e);
        }
    }

    public void updateExit(Vehicle vehicle) throws EstacionamentoException {
        String sql = "UPDATE vehicles SET exit_time = ?, paid_amount = ? WHERE id = ?";
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            // Isto agora funciona, pois a coluna no BD é TIMESTAMP
            stmt.setTimestamp(1, Timestamp.valueOf(vehicle.getExitTime()));
            stmt.setDouble(2, vehicle.getPaidAmount());
            stmt.setInt(3, vehicle.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EstacionamentoException("Erro ao atualizar saída do veículo: " + e.getMessage(), e);
        }
    }

    public Vehicle findByPlate(String plate) throws EstacionamentoException {
        String sql = "SELECT * FROM vehicles WHERE plate = ? AND exit_time IS NULL";
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, plate);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Isto agora funciona, pois a coluna no BD é TIMESTAMP
                    return new Vehicle(
                            rs.getInt("id"),
                            rs.getString("plate"),
                            rs.getTimestamp("entry_time").toLocalDateTime(),
                            rs.getTimestamp("exit_time") != null ? rs.getTimestamp("exit_time").toLocalDateTime() : null,
                            rs.getDouble("paid_amount")
                    );
                }
            }
        } catch (SQLException e) {
            throw new EstacionamentoException("Erro ao buscar veículo: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Vehicle> listParked() throws EstacionamentoException {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE exit_time IS NULL";
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Isto agora funciona, pois a coluna no BD é TIMESTAMP
                list.add(new Vehicle(
                        rs.getInt("id"),
                        rs.getString("plate"),
                        rs.getTimestamp("entry_time").toLocalDateTime(),
                        null,
                        0.0 // O valor pago é 0 se ainda está estacionado
                ));
            }
        } catch (SQLException e) {
            throw new EstacionamentoException("Erro ao listar veículos: " + e.getMessage(), e);
        }
        return list;
    }
}