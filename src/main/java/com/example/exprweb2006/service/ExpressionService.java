package com.example.exprweb2006.service;

import com.example.exprweb2006.db.DBConnection;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpressionService {
    public double calculate(String expressionString) {
        Expression expression = new ExpressionBuilder(expressionString).build();
        return expression.evaluate();
    }

    public void saveToDB(String expression, double result) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement("insert into expression (expr, result) VALUES (?, ?)")) {
            ps.setString(1, expression);
            ps.setDouble(2, result);
            ps.executeUpdate();
        }
    }

    public List<com.example.exprweb2006.tables.Expression> findAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement("select * from expression");
            ResultSet rs = ps.executeQuery()) {
            List<com.example.exprweb2006.tables.Expression> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new com.example.exprweb2006.tables.Expression(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3)));
            }
            return result;
        }
    }

    public Optional<com.example.exprweb2006.tables.Expression> findById(int id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement("select * from expression where id = ?")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new com.example.exprweb2006.tables.Expression(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            } else {
                return Optional.empty();
            }
        }
    }

    public void updateById(int id, String ex, double v) {
        Connection connection = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement("update expression set expr = ?, result = ? where id = ?")) {
            ps.setInt(3, id);
            ps.setString(1, ex);
            ps.setDouble(2, v);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
