package com.example.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;
import java.util.List;

public class CustomerBackwardReasoningDemo {

    public static void main(String[] args) {
        System.out.println("=== Customer Backward Reasoning Demo ===");
        System.out.println("既存の顧客データを使用して後方推論を実行します\n");

        try {
            // 1. 先に通常のルールでランクを設定
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession normalSession = kieContainer.newKieSession("ksession-rules");

            // 元のテストデータを使用
            List<Customer> customers = Arrays.asList(
                new Customer("John Smith", 8, 450.0, 45),        // Bronze相当
                new Customer("Jane Doe", 25, 2500.0, 250),       // Silver相当
                new Customer("Bob Johnson", 45, 8500.0, 850),    // Gold相当
                new Customer("Alice Brown", 60, 15000.0, 1500),  // Platinum相当
                new Customer("Charlie Davis", 3, 150.0, 15)      // Bronze未満
            );

            // 通常のルールでランクを設定
            for (Customer customer : customers) {
                normalSession.insert(customer);
            }
            normalSession.fireAllRules();
            normalSession.dispose();

            System.out.println("=== 顧客の現在の状況 ===");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
            System.out.println();

            // 2. 後方推論セッションで分析
            KieSession backwardSession = kieContainer.newKieSession("backward-reasoning-session");
            
            for (Customer customer : customers) {
                System.out.println("=== " + customer.getName() + " の後方推論分析 ===");
                backwardSession.insert(customer);
                backwardSession.fireAllRules();
                System.out.println();
            }

            backwardSession.dispose();

        } catch (Exception e) {
            System.err.println("エラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }
}