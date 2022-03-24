package com.jl.lab;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.jl.lab.dao.ProductDao;
import com.jl.lab.database.ConnectionFactory;
import com.jl.lab.models.ProductModel;

public class App {
    public static void main(String[] args) throws SQLException {

        System.out.println("|----------------------------------|");
        System.out.println("|******** MENU PRODUCT ************|");
        System.out.println("|----------------------------------|");
        System.out.println("|        (1) Get one               |");
        System.out.println("|        (2) Get all               |");
        System.out.println("|        (3) Create                |");
        System.out.println("|        (4) Update                |");
        System.out.println("|        (5) Delete one            |");
        System.out.println("|        (6) Delete all            |");
        System.out.println("|        (7) Create table          |");
        System.out.println("|----------------------------------|");

        ConnectionFactory connection = new ConnectionFactory();
        ProductDao productDao = new ProductDao(connection);

        Scanner scannerString = new Scanner(System.in);
        Scanner scannerNumber = new Scanner(System.in);

        int choise = scannerNumber.nextInt();

        switch (choise) {
            case 1:
                System.out.println("List One:\n");
                System.out.print("Enter product id: ");
                Integer id = scannerNumber.nextInt();
                ProductModel product = productDao.getOne(id);

                if (product != null) {
                    System.out.println("Id: " + product.getId());
                    System.out.println("Name: " + product.getName());
                    System.out.println("Price: " + product.getPrice() + "\n");
                    break;
                }
                break;

            case 2:
                System.out.println("List All:\n");
                ArrayList<ProductModel> products = (ArrayList<ProductModel>) productDao.getAll();
                for (ProductModel item : products) {
                    System.out.println("Id: " + item.getId());
                    System.out.println("Name: " + item.getName());
                    System.out.println("Price: " + item.getPrice() + "\n");
                }
                break;

            case 3:
                System.out.println("Create new product\n");
                System.out.print("Enter product name: ");
                String name = scannerString.nextLine();
                System.out.print("Enter product price: ");
                double price = scannerNumber.nextDouble();
                productDao.create(new ProductModel(name, price));
                break;

            case 4:
                System.out.println("Update:\n");
                System.out.print("Enter product id: ");
                int updateId = scannerNumber.nextInt();
                ProductModel currentProduct = productDao.getOne(updateId);
                if (currentProduct != null) {
                    System.out.print("Enter product name: ");
                    String newName = scannerString.nextLine();
                    System.out.print("Enter product price: ");
                    double newPrice = scannerNumber.nextDouble();
                    ProductModel newProduct = new ProductModel(currentProduct.getId(), newName, newPrice);
                    productDao.update(updateId, newProduct);
                    break;
                }
                System.out.println("Product not found.");
                break;

            case 5:
                System.out.println("Delete One:\n");
                System.out.print("Enter product id: ");
                int deleteId = scannerNumber.nextInt();
                ProductModel productToBeDeleted = productDao.getOne(deleteId);
                if (productToBeDeleted != null) {
                    productDao.deleteOne(deleteId);
                    System.out.println("Products deleted successfully.");
                    break;
                }
                System.out.println("Product not found.");
                break;

            case 6:
                System.out.println("Delete All:\n");
                productDao.deleteAll();
                System.out.println("Product deleted successfully.");
                break;

            case 7:
                System.out.println("Create table:\n");
                productDao.createTable();
                break;

            default:
                System.out.println("Invalid option.");
        }

        scannerString.close();
        scannerNumber.close();
    }
}
