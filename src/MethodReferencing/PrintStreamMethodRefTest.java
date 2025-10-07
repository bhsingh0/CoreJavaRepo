package MethodReferencing;

import java.io.PrintStream;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/*
 * @created 08/10/2025 -00:19
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class PrintStreamMethodRefTest {

    public static void main(String[] args) {

//        Consumer consumer = System.out::println;
//        consumer.accept("bharti");



        BiConsumer<PrintStream, String> biConsumer = PrintStream::println;
        biConsumer.accept(new PrintStream(System.out), "bharti");

    }
}
