# Drools BRMS Sample Application

This is a comprehensive Java application demonstrating advanced Drools features including both **forward reasoning** (classification and rule execution) and **backward reasoning** (gap analysis and requirement computation) patterns for business rules management.

## Project Structure

```
├── pom.xml                             # Maven configuration with Drools dependencies
├── src/
│   └── main/
│       ├── java/com/example/drools/
│       │   ├── Person.java             # Domain model for person
│       │   ├── Product.java            # Domain model for product  
│       │   ├── Customer.java           # Extended customer model with rank system
│       │   ├── CustomerRank.java       # Enum defining 4-tier rank system
│       │   ├── RankRequirement.java    # Model for backward reasoning analysis
│       │   ├── DroolsApplication.java  # Main demo (forward reasoning only)
│       │   ├── CustomerBackwardReasoningDemo.java  # Two-phase demo
│       │   └── CombinedReasoningApplication.java   # Real-world business flow
│       └── resources/
│           ├── com/example/drools/     # Forward reasoning rules
│           │   ├── person-rules.drl    # Age categorization rules
│           │   ├── product-rules.drl   # Product discount rules
│           │   └── customer-rules.drl  # Customer rank assignment rules
│           ├── com/example/drools/backward/  # Backward reasoning rules
│           │   └── backward-reasoning-rules.drl  # Gap analysis rules
│           └── META-INF/
│               └── kmodule.xml         # Dual knowledge base configuration
```

## Features Demonstrated

### Forward Reasoning (Classification & Rule Execution)

#### Person Rules
- **Age Categorization**: Automatically categorizes persons as Minor, Adult, or Senior
- **Eligibility Check**: Determines eligibility based on age
- **Special Promotions**: Applies special offers for young adults (18-25)

#### Product Rules
- **Electronics Discount**: 10% discount for electronics over $100
- **Clothing Sale**: 15% discount for all clothing items
- **High Value Discount**: Additional 5% off for items over $500
- **Books Discount**: 20% discount for books over $50

#### Customer Rules (Enhanced with Rank System)
- **Bronze Membership**: 5+ purchases, ¥200+ spent
- **Silver Membership**: 10+ purchases, ¥5,000+ spent, 100+ loyalty points
- **Gold Membership**: 25+ purchases, ¥15,000+ spent, 300+ loyalty points
- **Platinum Membership**: 50+ purchases, ¥50,000+ spent, 1,000+ loyalty points
- **VIP Status**: Automatic for high spenders (¥10,000+)

### Backward Reasoning (Gap Analysis)
- **Requirement Computation**: Calculate specific deficits for next rank
- **Gap Analysis**: Identify missing purchase count, spending amount, and loyalty points
- **Achievement Validation**: Recognize when customers already meet next rank criteria
- **Personalized Insights**: Generate specific numerical targets for rank advancement

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## How to Run

### 1. Forward Reasoning Demo (Traditional Drools)
```bash
mvn clean compile exec:java -Dexec.mainClass="com.example.drools.DroolsApplication"
```
Demonstrates standard person, product, and customer classification rules.

### 2. Backward Reasoning Demo (Gap Analysis)
```bash
mvn compile exec:java -Dexec.mainClass="com.example.drools.CustomerBackwardReasoningDemo"
```
Shows two-phase approach: forward classification → backward gap analysis.

### 3. Combined Reasoning Demo (Real-World Business Flow)
```bash
mvn compile exec:java -Dexec.mainClass="com.example.drools.CombinedReasoningApplication"
```
Realistic business scenarios with diverse customer profiles and comprehensive analysis.

### 4. Manual Compilation (if needed)
```bash
mvn dependency:copy-dependencies
javac -cp "target/dependency/*" src/main/java/com/example/drools/*.java -d target/classes
cp -r src/main/resources/* target/classes/
java -cp "target/classes:target/dependency/*" com.example.drools.DroolsApplication
```

## Sample Output Examples

### Forward Reasoning (DroolsApplication)
```
=== Drools BRMS Sample Application ===

Testing Person Rules:
Rule applied: Bob is categorized as Adult
Rule applied: Diana is categorized as Senior with special benefits
Total rules fired: 5

Testing Customer Rules:
Gold membership with VIP status applied for: Bob Johnson  
Silver membership applied for: Jane Doe
Bronze membership applied for: John Smith
Total rules fired: 5
```

### Backward Reasoning Gap Analysis
```
=== Backward Reasoning Analysis for John Smith ===
Current Status: Customer{name='John Smith', currentRank=BRONZE, loyaltyPoints=45}

--- Silverランクになるための不足要件 ---
❌ Silverランクになるためには以下が不足しています:
  • 追加購入回数: 2回 (現在: 8回, 必要: 10回)
  • 追加購入金額: ¥4550 (現在: ¥450, 必要: ¥5000)
  • 追加ロイヤルティポイント: 55pt (現在: 45pt, 必要: 100pt)
```

### Combined Business Flow Analysis
```
╔════════════════════════════════════════╗
║ 顧客: 鈴木健一                         ║  
╚════════════════════════════════════════╝
📊 【STEP 1: 前方推論による現在ランク判定】
判定結果: 鈴木健一 → Silverランク (Silver)

🎯 【STEP 2: 後方推論による次ランク提案】
--- Goldランクになるための不足要件 ---
  • 追加購入回数: 3回 (現在: 22回, 必要: 25回)
  • 追加購入金額: ¥11800 (現在: ¥3200, 必要: ¥15000)
```

## Test Data Examples

### Realistic Customer Scenarios
- **新規太郎** (2 purchases, ¥150) - New customer needing substantial growth
- **佐藤次郎** (12 purchases, ¥800) - Bronze customer close to Silver  
- **田中美咲** (18 purchases, ¥1,800) - Recently promoted to Silver
- **鈴木健一** (22 purchases, ¥3,200) - Silver customer approaching Gold
- **中村エリカ** (65 purchases, ¥58,000) - VIP customer exceeding all thresholds

## Key Drools Concepts Demonstrated

### Core Engine Concepts
1. **Facts**: Domain objects (Person, Product, Customer) inserted into working memory
2. **Rules**: Business logic written in DRL (Drools Rule Language)  
3. **Working Memory**: Runtime storage where facts are maintained and rules executed
4. **Rule Engine**: Pattern matching engine that evaluates conditions and executes actions
5. **KieSession**: Runtime session managing rule execution lifecycle

### Advanced Patterns  
6. **Multiple Knowledge Bases**: Separate rule sets for forward vs backward reasoning
7. **Named Sessions**: Targeted rule execution with specific session configurations
8. **Rule Chaining**: Cascading rule execution where one rule's actions trigger others
9. **Salience**: Priority-based rule execution ordering
10. **Forward Reasoning**: Data-driven classification and decision making
11. **Backward Reasoning**: Goal-oriented gap analysis and requirement computation

### Business Logic Patterns
12. **Customer Lifecycle Management**: Automatic rank progression and status updates
13. **Requirement Analysis**: Calculating specific deficits for goal achievement  
14. **Multi-criteria Evaluation**: Complex business rules with multiple conditions
15. **Real-world Integration**: Patterns suitable for CRM and loyalty program systems

---

# How to Create New Rules

This section provides a complete guide for adding new business rules to the Drools BRMS application.

## Quick Start: Adding Rules to Existing Domains

### For Person Rules
1. Edit `src/main/resources/com/example/drools/person-rules.drl`
2. Add new rule following the existing pattern:

```drl
rule "New Person Rule"
when
    $person : Person(condition)
then
    // Your business logic
    System.out.println("New rule applied for: " + $person.getName());
end
```

3. Recompile and run:
```bash
mvn clean compile exec:java -Dexec.mainClass="com.example.drools.DroolsApplication"
```

## Complete Guide: Adding New Domain with Rules

### Step 1: Create Domain Model
Create a new Java class in `src/main/java/com/example/drools/`:

```java
package com.example.drools;

public class YourDomain {
    private String property1;
    private int property2;
    
    // Constructors, getters, setters, toString()
}
```

### Step 2: Create DRL Rules File
Create `src/main/resources/com/example/drools/yourdomain-rules.drl`:

```drl
package com.example.drools

import com.example.drools.YourDomain

rule "Your Rule Name"
salience 10  // Higher number = higher priority
when
    $obj : YourDomain(property1 == "someValue", property2 >= 100)
then
    $obj.setSomeProperty("newValue");
    System.out.println("Rule applied: " + $obj.toString());
end
```

### Step 3: Update Application Class
Add a test method to `DroolsApplication.java`:

```java
public void testYourDomainRules() {
    System.out.println("Testing Your Domain Rules:");
    System.out.println("-".repeat(20));
    
    try {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        
        // Create sample objects
        List<YourDomain> objects = Arrays.asList(
            new YourDomain("value1", 50),
            new YourDomain("value2", 150)
        );
        
        // Insert into working memory
        for (YourDomain obj : objects) {
            kieSession.insert(obj);
        }
        
        // Fire rules
        int rulesFired = kieSession.fireAllRules();
        System.out.println("\nTotal rules fired: " + rulesFired);
        
        // Print results
        for (YourDomain obj : objects) {
            System.out.println(obj);
        }
        
        kieSession.dispose();
        
    } catch (Exception e) {
        System.err.println("Error executing rules: " + e.getMessage());
        e.printStackTrace();
    }
}
```

Add the method call to `main()`:
```java
app.testYourDomainRules();
```

## Rule Design Best Practices

### 1. Use Salience for Rule Priority
```drl
rule "High Priority Rule"
salience 100
when
    condition
then
    action
end

rule "Low Priority Rule"
salience 10
when
    condition  
then
    action
end
```

### 2. Complex Conditions
```drl
rule "Complex Condition Rule"
when
    $customer : Customer(purchaseCount >= 10, totalSpent > 1000.0, membershipLevel != "VIP")
then
    $customer.setMembershipLevel("Premium");
end
```

### 3. Rule Chaining
```drl
rule "Set Category"
salience 20
when
    $person : Person(age >= 18, category == null)
then
    $person.setCategory("Adult");
end

rule "Use Category"
salience 10
when
    $person : Person(category == "Adult", age >= 18, age <= 25)
then
    System.out.println("Young adult promotion for: " + $person.getName());
end
```

### 4. Multiple Object Rules
```drl
rule "Cross-Object Rule"
when
    $customer : Customer(membershipLevel == "Gold")
    $product : Product(type == "Electronics", price > 500.0)
then
    $product.setDiscount($product.getDiscount() + 10);
    System.out.println("Gold member electronics bonus applied");
end
```

## File Structure Requirements

**✅ Correct Structure:**
```
src/main/resources/com/example/drools/
├── person-rules.drl
├── product-rules.drl
├── customer-rules.drl
└── yourdomain-rules.drl
```

**❌ Incorrect Structure:**
```
src/main/resources/
├── person-rules.drl  # Wrong - missing package path
└── product-rules.drl # Wrong - missing package path
```

## Testing Your Rules

1. **Compile**: `mvn clean compile`
2. **Run**: `mvn exec:java -Dexec.mainClass="com.example.drools.DroolsApplication"`
3. **Verify output**: Check rule execution messages and final object states

## Common Issues and Solutions

### Issue: "Cannot find KieSession"
**Solution**: Verify `kmodule.xml` configuration and DRL file placement

### Issue: "Package does not exist"
**Solution**: Ensure DRL files are in correct package structure under `src/main/resources/`

### Issue: Rules not firing
**Solution**: Check rule conditions and verify facts are properly inserted into working memory

---

This comprehensive guide enables you to extend the Drools BRMS application with any new business rules or domains!