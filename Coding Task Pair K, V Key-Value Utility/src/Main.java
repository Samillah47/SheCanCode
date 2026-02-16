public class Main {
    public static void main(String[] args)  {
        Pair<String, Integer> pair1 = new Pair<>("Age", 25);
        Pair<Integer, String> pair2 = new Pair<>(404, "Not Found");

        // Using factory method
        Pair<String, Double> pair3 = Pair.create("Price", 19.99);

        System.out.println("Pair 1: " + pair1);
        System.out.println("Pair 2: " + pair2);
        System.out.println("Pair 3: " + pair3);
        
    }

}

