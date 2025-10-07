package MethodReferencing;

import java.util.Objects;
import java.util.function.Consumer;

/*
 * @created 07/10/2025 -10:38
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class RefDemo {
    public static void main(String[] args) {
        //  System.out.println("Learning method referencing here....");
//        WorkInter workInter = () -> {
//            System.out.println("Calling interface method implementation ...");
//        };
//
//        workInter.doTask();


//        WorkInter workInter = Stuff::doStuff;
//        workInter.doTask();


        Consumer<Object> consumer = Objects::hashCode;
        consumer.accept(new RefDemo());

    }
}
