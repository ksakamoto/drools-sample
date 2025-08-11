package com.example.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;
import java.util.List;

public class DroolsApplication {
    
    public static void main(String[] args) {
        System.out.println("=== Drools BRMS Sample Application ===\n");
        
        DroolsApplication app = new DroolsApplication();
        
        // Test Person Rules
        app.testPersonRules();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test Product Rules
        app.testProductRules();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test Customer Rules
        app.testCustomerRules();
        
        // Force clean shutdown to avoid ForkJoinPool thread warnings
        System.exit(0);
    }
    
    public void testPersonRules() {
        System.out.println("Testing Person Rules:");
        System.out.println("-".repeat(20));
        
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession();
            
            // Create sample persons
            List<Person> persons = Arrays.asList(
                new Person("Alice", 16),
                new Person("Bob", 25),
                new Person("Charlie", 45),
                new Person("Diana", 70),
                new Person("Eve", 22)
            );
            
            // Insert persons into working memory
            for (Person person : persons) {
                kieSession.insert(person);
            }
            
            // Fire all rules
            int rulesFired = kieSession.fireAllRules();
            System.out.println("\nTotal rules fired: " + rulesFired);
            
            // Print results
            System.out.println("\nFinal Results:");
            for (Person person : persons) {
                System.out.println(person);
            }
            
            kieSession.dispose();
            kieContainer.dispose();
            
        } catch (Exception e) {
            System.err.println("Error executing person rules: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void testProductRules() {
        System.out.println("Testing Product Rules:");
        System.out.println("-".repeat(20));
        
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession();
            
            // Create sample products
            List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 800.0),
                new Product("T-Shirt", "Clothing", 25.0),
                new Product("Java Programming Book", "Books", 60.0),
                new Product("Smartphone", "Electronics", 600.0),
                new Product("Jeans", "Clothing", 80.0),
                new Product("Gaming Console", "Electronics", 450.0)
            );
            
            // Insert products into working memory
            for (Product product : products) {
                kieSession.insert(product);
            }
            
            // Fire all rules
            int rulesFired = kieSession.fireAllRules();
            System.out.println("\nTotal rules fired: " + rulesFired);
            
            // Print results
            System.out.println("\nFinal Results:");
            for (Product product : products) {
                System.out.println(product);
            }
            
            kieSession.dispose();
            kieContainer.dispose();
            
        } catch (Exception e) {
            System.err.println("Error executing product rules: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void testCustomerRules() {
        System.out.println("Testing Customer Rules:");
        System.out.println("-".repeat(20));
        
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession();
            
            // Create sample customers
            List<Customer> customers = Arrays.asList(
                new Customer("John Smith", 8, 450.0),
                new Customer("Jane Doe", 25, 2500.0),
                new Customer("Bob Johnson", 45, 8500.0),
                new Customer("Alice Brown", 60, 15000.0),
                new Customer("Charlie Davis", 3, 150.0)
            );
            
            // Insert customers into working memory
            for (Customer customer : customers) {
                kieSession.insert(customer);
            }
            
            // Fire all rules
            int rulesFired = kieSession.fireAllRules();
            System.out.println("\nTotal rules fired: " + rulesFired);
            
            // Print results
            System.out.println("\nFinal Results:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
            
            kieSession.dispose();
            kieContainer.dispose();
            
        } catch (Exception e) {
            System.err.println("Error executing customer rules: " + e.getMessage());
            e.printStackTrace();
        }
    }
}