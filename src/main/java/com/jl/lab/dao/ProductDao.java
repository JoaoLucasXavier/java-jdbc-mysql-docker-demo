package com.jl.lab.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jl.lab.database.ConnectionFactory;
import com.jl.lab.models.ProductModel;

public class ProductDao {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public ProductDao(ConnectionFactory connectionFactory) {
        this.connection = connectionFactory.getConnection();
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS products (\n"
                + "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,\n"
                + "name VARCHAR(30) NOT NULL,\n"
                + "price DOUBLE NOT NULL\n"
                + ")\n";
        this.statement = this.connection.createStatement();
        this.statement.execute(query);
    }

    public ProductModel getOne(Integer id) throws SQLException {
        String query = "SELECT * FROM products WHERE id = ?";
        ProductModel product = null;
        preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            product = new ProductModel(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getDouble("price"));
        }
        return product;
    }

    public List<ProductModel> getAll() throws SQLException {
        String query = "SELECT * FROM products";
        List<ProductModel> products = new ArrayList<ProductModel>();
        this.statement = this.connection.createStatement();
        ResultSet result = this.statement.executeQuery(query);
        while (result.next()) {
            products.add(new ProductModel(result.getInt("id"), result.getString("name"), result.getDouble("price")));
        }
        return products;
    }

    public void create(ProductModel productModel) throws SQLException {
        String query = "INSERT INTO products (name, price) values (?,?)";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setString(1, productModel.getName());
        this.preparedStatement.setDouble(2, productModel.getPrice());
        this.preparedStatement.execute();

    }

    public void update(int id, ProductModel updatedProduct) throws SQLException {
        String query = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setString(1, updatedProduct.getName());
        this.preparedStatement.setDouble(2, updatedProduct.getPrice());
        this.preparedStatement.setInt(3, id);
        this.preparedStatement.executeUpdate();
    }

    public void deleteOne(int id) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setInt(1, id);
        this.preparedStatement.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM products";
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate(query);
    }
}
