import java.util.function.BiFunction;

public class FunctionalCalc {
    public static Integer calculator(
        int num1,
        int mum2,
        BiFunction<Integer, Integer, Integer> operation){
            return operation.apply(num1, mum2);
    }
}
