package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import static com.company.Main.Gender.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        List<Person> peoples = List.of(
                new Person("OK", FEMALE),
                new Person("AXE", MALE),
                new Person("JACK", MALE),
                new Person("JOHN", MALE),
                new Person("JESSIE", FEMALE),
                new Person("JESSICA", FEMALE),
                new Person("JOCELYN", FEMALE),
                new Person("JOHAN", MALE),
                new Person("GOHAN", TRANS)
        );

        System.out.println("//Imperativ");
        List<Person> females = new ArrayList<>();
        for (Person people : peoples) {
            if (FEMALE.equals(people.gender))
                females.add(people);
        }
        for (Person female : females) {
            System.out.println(female);
        }

        System.out.println("//Declarativ");
        peoples.stream().filter(people -> FEMALE.equals(people.gender)).forEach(System.out::println);

        System.out.println("//List of males");
        List<Person> males = peoples.stream().filter(people -> MALE.equals(people.gender)).collect(Collectors.toList());
        for (Person male : males) {
            System.out.println(male);
        }

        System.out.println("//Function");
        int out = incrementByOneFunc.apply(33);
        System.out.println(out);

        System.out.println("//Chaining function");
        Function<Integer, Integer> addBy1Multi10 = incrementByOneFunc.andThen(multiByTenFunc);
        System.out.println(addBy1Multi10.apply(33));


        System.out.println("//BI-function (3+1)*10=40");
        System.out.println(incrByOneMultiBy10BiFunc.apply(3,10));

        // Customer
        greetCustomer(new Customer("Maria", "00142320003"));
        // Consumer
        greetCustomerConsumer.accept(new Customer("Dave","012324201"));

        // BiConsumer
        greetCustomerConsumerV2.accept(new Customer("Thomas","01232342"),false);

        // Predicate
        System.out.println(checkPhoneNumberValidation.test("0132401")? "Valid": "entry not valid!");

        // Supplier
        System.out.println(getURL.get());
        System.out.println(listSupplier.get());

        // Stream
        peoples.stream().map(person -> person.name).filter(n -> n.length()<=4).forEach(System.out::println);
    }

    static class Person {
        private final String name;
        private final Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE,
        FEMALE,
        TRANS,
    }

    static class Customer {
        private final String name;
        private final String phoneNumber;

        public Customer(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }
    }
    static void greetCustomer(Customer cust){
        System.out.println("Hello "+ cust.name +", your number "+ cust.phoneNumber + " is now registered!");
    }

    //takes 1 input and return 1 output
    static Function<Integer, Integer> incrementByOneFunc = e -> e+1;
    static Function<Integer, Integer> multiByTenFunc = e -> e*10;

    //takes 2 inputs and return 1 output
    static BiFunction<Integer, Integer, Integer> incrByOneMultiBy10BiFunc = (e, a) -> (e+1)*a;

    //Consumer function: takes 1 input and return nothing (void)
    static Consumer<Customer> greetCustomerConsumer = cust -> System.out.println("Hello "+ cust.name +", your number "+ cust.phoneNumber + " is now registered!");
    static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (cust, show) -> System.out.println("Hello "+ cust.name +", your number "+ (show? cust.phoneNumber: "*********") + " is now registered!");

    //Predicate (boolean output, given one input argument)
    static Predicate<String> checkPhoneNumberValidation = myString -> myString.startsWith("01") && myString.endsWith("01") && myString.length()==6;

    //Supplier (just supply or return something)
    static Supplier<String> getURL = () -> "www.google.com";
    static Supplier<List<String>> listSupplier = () -> List.of("Axe","Candle","Tee");
}
