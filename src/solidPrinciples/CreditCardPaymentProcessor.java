package solidPrinciples;

/*
 * @created 06/10/2025 -14:24
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("Processing Credit card payment--" + amount);
    }
}
