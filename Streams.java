import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javax.swing.LayoutFocusTraversalPolicy;

public class Streams {
    public static void main(String[] args) {
        // Streams

        // Java 8 --> minimal code, functional programing
        // Java 8 --> lambda expression, Streams, Date & Time API

        // lambda expression
        // lambda expression is an anonymous function ( no name, no return type, no
        // access modifier )

        //Thread t1 =new Thread(new Task());

        Thread t1 = new Thread(()->{
            System.out.println("Hello");
        });

        //Lambda expression only implement the functional interface.
        MathOperation sumOperation = (a,b) -> a+b;
        MathOperation subtractOperation = (a,b) -> a-b;
        int res=sumOperation.operate(1, 2);
        System.out.println(res);
        
        // Predicate --> Functional interface (Boolean valued function)
        Predicate<Integer> isEven = x -> x % 2 ==0;
        System.out.println(isEven.test(4));

        Predicate<String> isWordStartingWithA = x->x.toLowerCase().startsWith("a");
        Predicate<String> isWordEndingWithT = x->x.toLowerCase().endsWith("t"); 
        Predicate<String> combine=isWordStartingWithA.and(isWordEndingWithT);
        System.out.println(combine.test("ritik"));

        //Function
        Function<Integer,Integer> doubleIt=x->2*x;
        System.out.println(doubleIt.apply(5));
        Function<Integer,Integer> tripleIt=x->x*3;
        System.out.println(doubleIt.andThen(tripleIt).apply(20));
        Function<Integer,Integer> identity=Function.identity();
        Integer res2 = identity.apply(5);

        //Consumer
        Consumer<Integer> print = x->System.out.println(x);
        print.accept(5);
        List<Integer> list=Arrays.asList(1,2,3);
        Consumer<List<Integer>> printList=x->{
            for(int i:x){
                System.out.println(i);
            }
        };
        printList.accept(list);

        //Supplier
        Supplier<String> giveHelloWorld=()->"Hello World";
        System.out.println(giveHelloWorld.get());

        //Combined example
        Predicate<Integer> predicate= x -> x % 2 == 0;
        Function<Integer,Integer> function = x -> x * x;
        Consumer consumer=x->System.out.println(x);
        Supplier<Integer> supplier=()->100;

        if(predicate.test(supplier.get())){
            consumer.accept(function.apply(supplier.get()));
        }

        //BiPredicate, Biconsumer, BiFunction

        BiPredicate<Integer,Integer> isSumEven = (x,y)-> (x+y)%2==0;
        System.out.println(isSumEven.test(5,6));

        BiConsumer<Integer,String> biConsumer=(x,y)->{
            System.out.println(x);
            System.out.println(y);
        };

        BiFunction<String,String,Integer> biFunction=(x,y) -> (x+y).length();
        System.out.println(biFunction.apply("a", "bc"));

        //UnaryOperator, BinaryOperator
        UnaryOperator<Integer> a = x -> 2 * x;
        BinaryOperator<Integer> b = (x, y) -> x + y;

        //Method reference --> use method without invoking & in place of lambda expression
        List<String> students=Arrays.asList("Ram","Shyam","Ghanshyam");
        students.forEach(x->System.out.println(x));
        students.forEach(System.out::println);
        
        //Constructor reference
        List<String> names=Arrays.asList("A","B","C");
        //List<MobilePhone> mobilePhoneList=names.stream().map(x->new MobilePhone(x)).collect(Collectors.toList());
        List<MobilePhone> mobilePhoneList=names.stream().map(MobilePhone::new).collect(Collectors.toList());
    }

}

class MobilePhone{
    String name;
    public MobilePhone(String name){
        this.name=name;
    }
}
// Runnable is a functional interface so we can directly add this function
// using lambada expersion.
// class Task implements Runnable {

//     public void run() {
//         System.out.println("Hello");
//     }
// }

@FunctionalInterface
interface MathOperation{
    int operate(int a, int b);
    //int point();
}