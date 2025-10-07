package MethodReferencing;

import java.util.function.Function;

/*
 * @created 08/10/2025 -00:04
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class StringConvertToUpperCase {

    public static void main(String[] args) {

        Function<String, String> funcObj = String::toUpperCase;
        System.out.println(funcObj.apply("bharti singh"));
    }

}
