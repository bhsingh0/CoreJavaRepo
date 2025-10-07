package solidPrinciples.AllInOne;

/*
 * @created 07/10/2025 -00:34
 * @project CoreJavaRepo
 * @author  bhartisingh
 */

import java.util.List;

// ========== SRP - Single Responsibility Principle ==========
// Each class has one reason to change

class Coffee {
    private String type;
    private double price;

    public Coffee(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() { return type; }
    public double getPrice() { return price; }
}

// ========== OCP - Open/Closed Principle ==========
// Open for extension, closed for modification

interface CoffeeMachine {
    Coffee brew();
}

class BasicCoffeeMachine implements CoffeeMachine {
    public Coffee brew() {
        return new Coffee("Basic Coffee", 2.0);
    }
}

class PremiumCoffeeMachine implements CoffeeMachine {
    public Coffee brew() {
        return new Coffee("Premium Coffee", 3.5);
    }
}

// Can add new machines without changing existing code
class EspressoMachine implements CoffeeMachine {
    public Coffee brew() {
        return new Coffee("Espresso", 4.0);
    }
}

// ========== LSP - Liskov Substitution Principle ==========
// Substitutes should behave correctly

class StudentCoffeeMachine implements CoffeeMachine {
    public Coffee brew() {
        return new Coffee("Student Coffee", 1.5);
    }
    // No weird behavior - can substitute anywhere CoffeeMachine is used
}

// ========== ISP - Interface Segregation Principle ==========
// Small, focused interfaces

interface MilkFrother {
    void frothMilk();
}

interface CoffeeGrinder {
    void grindBeans();
}

// Machines implement only what they need
class AdvancedCoffeeMachine implements CoffeeMachine, MilkFrother {
    public Coffee brew() {
        return new Coffee("Advanced Coffee", 5.0);
    }

    public void frothMilk() {
        System.out.println("Milk frothed!");
    }
    // Doesn't have to implement grindBeans()
}

// ========== DIP - Dependency Inversion Principle ==========
// Depend on abstractions, not concretions

class CoffeeShop {
    private CoffeeMachine coffeeMachine;

    // Depends on abstraction (CoffeeMachine), not concrete class
    public CoffeeShop(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public Coffee serveCoffee() {
        return coffeeMachine.brew();
    }
}

// ========== Demo ==========
public class SimpleSOLIDCoffeeShop {
    public static void main(String[] args) {
        // DIP - We can inject any CoffeeMachine implementation
        CoffeeShop shop1 = new CoffeeShop(new BasicCoffeeMachine());
        CoffeeShop shop2 = new CoffeeShop(new PremiumCoffeeMachine());
        CoffeeShop shop3 = new CoffeeShop(new EspressoMachine());
        CoffeeShop shop4 = new CoffeeShop(new StudentCoffeeMachine()); // LSP

        // OCP - We can easily add new types without changing existing code
        List<CoffeeShop> shops = List.of(shop1, shop2, shop3, shop4);

        for (CoffeeShop shop : shops) {
            Coffee coffee = shop.serveCoffee();
            System.out.println("Serving: " + coffee.getType() + " for $" + coffee.getPrice());
        }

        // ISP - Only implement needed interfaces
        AdvancedCoffeeMachine advancedMachine = new AdvancedCoffeeMachine();
        advancedMachine.frothMilk();
        Coffee advancedCoffee = advancedMachine.brew();
        System.out.println("Advanced: " + advancedCoffee.getType() + " for $" + advancedCoffee.getPrice());
    }
}