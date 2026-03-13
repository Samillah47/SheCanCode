public class Animal {
    protected String name;

    public Animal() {
        this.name = "Generic Animal";
    }

    public Animal(String name) {
        this.name = name;
    }

    public void makeSound() {
        System.out.println(name + " says: Some generic animal sound");
    }
}
