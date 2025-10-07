package solidPrinciples.OCP;

import solidPrinciples.OCP.impl.CreditCardPaymentProcessor;
import solidPrinciples.OCP.impl.PayPalPaymentProcessor;

/*
 * @created 06/10/2025 -14:23
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class OCPTest {

    public static void main(String[] args) {
        CreditCardPaymentProcessor creditCardPaymentProcessor = new CreditCardPaymentProcessor();
        PayPalPaymentProcessor payPalPaymentProcessor = new PayPalPaymentProcessor();
        processor(creditCardPaymentProcessor, 10);
        processor(payPalPaymentProcessor, 30);
    }
    public static void processor(PaymentProcessor payment, double amount)
    {
        payment.process(amount);
    }
}
