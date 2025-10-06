package solidPrinciples;

/*
 * @created 06/10/2025 -14:25
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("Processing paypalpayment...."+ amount);
    }
}
