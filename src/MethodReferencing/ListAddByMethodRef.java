package MethodReferencing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

/*
 * @created 08/10/2025 -00:34
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class ListAddByMethodRef {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        BiPredicate<ArrayList<Integer>,Integer> biPredicate = ArrayList::add;
        System.out.println("is integer added : " + biPredicate.test(list,1));
    }
}
