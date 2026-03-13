public class Main {
    public static void main(String[] args) {
        System.out.println(" Animal Hierarchy \n");

        
        Animal genericAnimal = new Animal("Generic Animal");
        Dog buddy = new Dog("Buddy");
        Cat whiskers = new Cat("Whiskers");
        Bird tweety = new Bird("Tweety");

        
        System.out.println("\n Sounds");
        genericAnimal.makeSound();
        buddy.makeSound();
        whiskers.makeSound();
        tweety.makeSound();

        
        System.out.println("\n Bird Flying ");
        tweety.fly();

        
        System.out.println("\n Polymorphism ");
        Animal animalRefDog = new Dog("Rex");
        Animal animalRefCat = new Cat("Mittens");
        Animal animalRefBird = new Bird("Polly");

        animalRefDog.makeSound();
        animalRefCat.makeSound();
        animalRefBird.makeSound();

        
    }
}
