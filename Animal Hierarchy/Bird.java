public class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    public void fly() {
        System.out.println(name + " is Flying!");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Chirp! Chirp!");
    }
}
