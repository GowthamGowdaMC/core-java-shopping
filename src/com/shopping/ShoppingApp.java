package com.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Product {
	int id;
	String name;
	double price;

	Product(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
}

class ShoppingCart {
	ArrayList<Product> items;

	ShoppingCart() {
		items = new ArrayList<>();
	}

	void addItem(Product product) {
		items.add(product);
	}

	double getTotalCost() {
		double total = 0.0;
		for (Product product : items) {
			total += product.price;
		}
		return total;
	}

	void viewCart() {
		if (items.isEmpty()) {
			System.out.println("Your cart is empty.");
		} else {
			System.out.println("Your cart contains:");
			for (Product product : items) {
				System.out.println(product.id + ": " + product.name + " - ₹" + product.price);
			}
			System.out.println("Total Cost: ₹" + getTotalCost());
		}
	}
}

class Customer {
	String username;
	String password;
	ShoppingCart cart;

	Customer(String username, String password) {
		this.username = username;
		this.password = password;
		cart = new ShoppingCart();
	}
}

public class ShoppingApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Product> products = new HashMap<>();
		products.put(1, new Product(1, "Laptop", 1000.0));
		products.put(2, new Product(2, "Smartphone", 800.0));
		products.put(3, new Product(3, "Headphones", 50.0));
		products.put(4, new Product(4, "Backpack", 80.0));

		HashMap<String, Customer> customers = new HashMap<>();
		customers.put("user1", new Customer("user1", "pass1"));
		customers.put("user2", new Customer("user2", "pass2"));

		System.out.println("Welcome to the  Shopping App!");

		boolean loggedIn = false;
		Customer currentCustomer = null;

		while (!loggedIn) {
			System.out.print("Enter your username: ");
			String username = sc.next();
			System.out.print("Enter your password: ");
			String password = sc.next();

			currentCustomer = customers.get(username);
			if (currentCustomer != null && currentCustomer.password.equals(password)) {
				loggedIn = true;
				System.out.println("Login successful!");
			} else {
				System.out.println("Invalid credentials. Please try again.");
			}
		}

		boolean continueShopping = true;
		do {
			System.out.println("\nSelect an option:");
			System.out.println("1. View Products");
			System.out.println("2. View Cart");
			System.out.println("3. Add Products to Cart");
			System.out.println("4. Checkout");
			System.out.println("5. Logout");

			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("\nAvailable Products:");
				System.out.println("-------------------------------------------------------");
				System.out.printf("%-5s %-20s %-15s %n", "ID", "Product Name", "Price (₹)");
				System.out.println("-------------------------------------------------------");
				for (Product product : products.values()) {
					System.out.printf("%-5s %-20s %-15s %n", product.id, product.name, product.price);
				}
				break;
			case 2:
				currentCustomer.cart.viewCart();
				break;
			case 3:
				System.out.print("Enter product ID to add to cart: ");
				int productID = sc.nextInt();
				Product selectedProduct = products.get(productID);
				if (selectedProduct != null) {
					currentCustomer.cart.addItem(selectedProduct);
					System.out.println(selectedProduct.name + " added to cart.");
				} else {
					System.out.println("Invalid product ID.");
				}
				break;
			case 4:
				System.out.println("Checkout successful!");
				System.out.println("Thank you for shopping with us!");
				continueShopping = false;
				break;
			case 5:
				loggedIn = false;
				System.out.println("Logged out successfully!");
				break;
			default:
				System.out.println("Invalid choice. Try again.");
				break;
			}

		} while (continueShopping && loggedIn);

		sc.close();
	}
}



