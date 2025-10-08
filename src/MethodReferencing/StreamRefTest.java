package MethodReferencing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Supplier;

/*
 * @created 08/10/2025 -01:15
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class StreamRefTest {

    public static void main(String[] args) {

            List<String> names = List.of("Bharti", "singh");
//            //names.stream().forEach(n -> Person::new(n));
//
//        Function<String, Person> function1 = (name) -> {
//            return new Person(name);};
//
//        Function<String, Person> function =

        names.stream().map(Person::new).forEach(System.out::println);

        names.stream().map(Person::new).map(person ->{
            person.setId(ThreadLocalRandom.current().nextInt(1,100));
            return person;
        }).map(Person::getName).forEach(System.out::println);

    }
}
