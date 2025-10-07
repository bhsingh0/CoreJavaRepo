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

        BiConsumer<ArrayList<Integer>,Integer> biConsumer = ArrayList::add;
        biConsumer.accept(list,1);
        System.out.println("is integer added : " + list);
    }
}
