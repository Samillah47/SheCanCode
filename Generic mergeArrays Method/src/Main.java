import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] numbers1 = {1, 2, 3};
        Integer[] numbers2 = {4, 5, 6};

        Integer[] mergedIntegers =
                GenericArrayUtility.mergeArrays(numbers1, numbers2);

        System.out.println("Merged Integer Array: "
                + Arrays.toString(mergedIntegers));


        String[] names1 = {"Clesence", "Chanisse"};
        String[] names2 = {"Samillah", "Cardine"};

        String[] mergedStrings =
                GenericArrayUtility.mergeArrays(names1, names2);

        System.out.println("Merged String Array: "
                + Arrays.toString(mergedStrings));
    }
}
