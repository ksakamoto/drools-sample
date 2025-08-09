# Drools BRMS Sample Application

This is a sample Java application demonstrating the use of Drools as a Business Rules Management System (BRMS).

## Project Structure

```
├── pom.xml                           # Maven configuration with Drools dependencies
├── src/
│   └── main/
│       ├── java/com/example/drools/
│       │   ├── Person.java           # Domain model for person
│       │   ├── Product.java          # Domain model for product
│       │   ├── Customer.java         # Domain model for customer
│       │   └── DroolsApplication.java # Main application class
│       └── resources/
│           └── com/example/drools/   # Proper package structure for DRL files
│               ├── person-rules.drl  # Business rules for person categorization
│               ├── product-rules.drl # Business rules for product discounts
│               └── customer-rules.drl # Business rules for customer membership
│           └── META-INF/
│               └── kmodule.xml       # Drools configuration
```

## Features Demonstrated

### Person Rules
- **Age Categorization**: Automatically categorizes persons as Minor, Adult, or Senior
- **Eligibility Check**: Determines eligibility based on age
- **Special Promotions**: Applies special offers for young adults (18-25)

### Product Rules
- **Electronics Discount**: 10% discount for electronics over $100
- **Clothing Sale**: 15% discount for all clothing items
- **High Value Discount**: Additional 5% off for items over $500
- **Books Discount**: 20% discount for books over $50

### Customer Rules
- **Bronze Membership**: 5-14 purchases, $200-$999 spent
- **Silver Membership**: 15-29 purchases, $1,000-$4,999 spent
- **Gold Membership**: 30+ purchases, $5,000+ spent (includes VIP status)
- **VIP High Spender**: Automatic VIP for customers with $10,000+ total spending
- **Frequent Buyer Bonus**: Special bonus for Gold members with 50+ purchases

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## How to Run

1. **Using Maven:**
   ```bash
   mvn clean compile exec:java -Dexec.mainClass="com.example.drools.DroolsApplication"
   ```

2. **Manual Compilation:**
   ```bash
   # Download dependencies first (or use Maven to compile)
   mvn dependency:copy-dependencies
   
   # Compile
   javac -cp "target/dependency/*" src/main/java/com/example/drools/*.java -d target/classes
   
   # Copy resources
   cp -r src/main/resources/* target/classes/
   
   # Run
   java -cp "target/classes:target/dependency/*" com.example.drools.DroolsApplication
   ```

## Expected Output

The application will execute rules for persons, products, and customers, showing:
- Rule execution messages
- Number of rules fired
- Final state of all objects after rule processing

## Sample Persons Tested
- Alice (16 years) - Minor
- Bob (25 years) - Adult with young adult promotion
- Charlie (45 years) - Adult
- Diana (70 years) - Senior
- Eve (22 years) - Adult with young adult promotion

## Sample Products Tested
- Laptop ($800, Electronics) - Multiple discounts applied
- T-Shirt ($25, Clothing) - Clothing discount
- Java Programming Book ($60, Books) - Books discount
- Smartphone ($600, Electronics) - Electronics + High value discounts
- Jeans ($80, Clothing) - Clothing discount
- Gaming Console ($450, Electronics) - Electronics discount

## Sample Customers Tested
- John Smith (8 purchases, $450) - Bronze membership
- Jane Doe (25 purchases, $2,500) - Silver membership
- Bob Johnson (45 purchases, $8,500) - Gold membership with VIP status
- Alice Brown (60 purchases, $15,000) - Gold membership with VIP status + High spender VIP
- Charlie Davis (3 purchases, $150) - Standard membership

## Key Drools Concepts Demonstrated

1. **Facts**: Person, Product, and Customer objects inserted into working memory
2. **Rules**: Business logic written in DRL (Drools Rule Language)
3. **Working Memory**: Where facts are stored and rules are executed
4. **Rule Engine**: Drools engine that matches patterns and executes actions
5. **KieSession**: The runtime session for rule execution
6. **Rule Chaining**: Rules that trigger other rules through fact modifications
7. **Salience**: Rule priority control for execution order

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