package com.example.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;
import java.util.List;

/**
 * 実際のアプリケーションで使用される前方推論と後方推論の組み合わせデモ
 * 
 * フロー:
 * 1. 前方推論: 顧客データから現在のランクを判定・分類
 * 2. 後方推論: 次のランクに必要な条件を逆算して提案
 */
public class CombinedReasoningApplication {

    private final KieServices kieServices;
    private final KieContainer kieContainer;

    public CombinedReasoningApplication() {
        this.kieServices = KieServices.Factory.get();
        this.kieContainer = kieServices.getKieClasspathContainer();
    }

    public static void main(String[] args) {
        System.out.println("=== 前方推論 + 後方推論 統合デモ ===");
        System.out.println("実際のビジネスアプリケーションでの使用例を再現します\n");

        CombinedReasoningApplication app = new CombinedReasoningApplication();
        
        // 実際のビジネスシナリオに近い顧客データ
        List<Customer> customers = app.createRealisticCustomerData();
        
        // 各顧客に対して前方推論 → 後方推論のフローを実行
        for (Customer customer : customers) {
            app.processCustomer(customer);
        }
        
        System.out.println("=== まとめ ===");
        System.out.println("• 顧客の現在のランクを自動判定");
        System.out.println("• 次のランクへの不足条件を具体的に数値で提示");
    }

    /**
     * 実際のビジネスシナリオに近い顧客データを作成
     */
    private List<Customer> createRealisticCustomerData() {
        return Arrays.asList(
            // 新規顧客 - まだランクなし
            new Customer("新規太郎", 2, 150.0, 15),
            
            // Bronze候補 - もう少しでBronze
            new Customer("山田花子", 4, 380.0, 40),
            
            // Bronze顧客 - Silverを目指したい
            new Customer("佐藤次郎", 12, 800.0, 120),
            
            // Silver候補 - 条件をほぼ満たしている
            new Customer("田中美咲", 18, 1800.0, 180),
            
            // Silver顧客 - Goldを目指している
            new Customer("鈴木健一", 22, 3200.0, 320),
            
            // Gold候補 - 高額購入者だが購入頻度が低い
            new Customer("高橋麗子", 28, 12000.0, 280),
            
            // 既にGold - Platinumを目指せるか？
            new Customer("渡辺優一", 35, 7500.0, 750),
            
            // VIP顧客 - 既にPlatinum相当
            new Customer("中村エリカ", 65, 58000.0, 1800)
        );
    }

    /**
     * 個別の顧客に対して前方推論→後方推論のフローを実行
     */
    private void processCustomer(Customer customer) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ 顧客: " + String.format("%-20s", customer.getName()) + "                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
        
        try {
            // Step 1: 前方推論による現在ランクの判定
            System.out.println("📊 【STEP 1: 前方推論による現在ランク判定】");
            applyForwardReasoning(customer);
            
            System.out.println();
            
            // Step 2: 後方推論による次のランクへの提案
            System.out.println("🎯 【STEP 2: 後方推論による次ランク提案】");
            applyBackwardReasoning(customer);
            
            System.out.println("\n" + "═".repeat(75) + "\n");
            
        } catch (Exception e) {
            System.err.println("❌ 顧客処理中にエラーが発生: " + e.getMessage());
        }
    }

    /**
     * 前方推論: 顧客の現在の状況からランクを判定
     */
    private void applyForwardReasoning(Customer customer) {
        KieSession forwardSession = kieContainer.newKieSession("ksession-rules");
        
        System.out.println("入力データ: " + customer.toString());
        
        // 前方推論ルールを適用
        forwardSession.insert(customer);
        int rulesFired = forwardSession.fireAllRules();
        
        System.out.println("✅ 前方推論完了: " + rulesFired + " 個のルールが適用されました");
        System.out.println("判定結果: " + customer.getName() + " → " + 
            customer.getCurrentRank().getDisplayName() + "ランク (" + customer.getMembershipLevel() + 
            (customer.isVipStatus() ? ", VIP" : "") + ")");
        
        forwardSession.dispose();
    }

    /**
     * 後方推論: 次のランクに必要な条件を逆算して提案
     */
    private void applyBackwardReasoning(Customer customer) {
        CustomerRank nextRank = customer.getCurrentRank().getNextRank();
        
        if (nextRank == null) {
            System.out.println("🏆 既に最高ランク(Platinum)に到達しています！");
            System.out.println("💎 VIP特典やロイヤルティプログラムでさらなる価値を提供しましょう");
            return;
        }
        
        KieSession backwardSession = kieContainer.newKieSession("backward-reasoning-session");
        
        // 後方推論ルールを適用
        backwardSession.insert(customer);
        backwardSession.fireAllRules();
        
        
        backwardSession.dispose();
    }

}