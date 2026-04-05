// Parent class
class Hillstations {

    void famousfood() {
        System.out.println("Every hill station has its own famous food.");
    }

    void famousfor() {
        System.out.println("Every hill station is famous for something special.");
    }
}

// Subclass 1
class Manali extends Hillstations {

    @Override
    void famousfood() {
        System.out.println("Manali is famous for: Siddu, Trout Fish, and Aktori.");
    }

    @Override
    void famousfor() {
        System.out.println("Manali is famous for: Snow, Adventure Sports, and Rohtang Pass.");
    }
}

// Subclass 2
class Ooty extends Hillstations {

    @Override
    void famousfood() {
        System.out.println("Ooty is famous for: Varkey, Nilgiri Tea, and Homemade Chocolates.");
    }

    @Override
    void famousfor() {
        System.out.println("Ooty is famous for: Tea Gardens, Ooty Lake, and Botanical Gardens.");
    }
}

// Subclass 3
class Shimla extends Hillstations {

    @Override
    void famousfood() {
        System.out.println("Shimla is famous for: Chha Gosht, Sidu, and Babru.");
    }

    @Override
    void famousfor() {
        System.out.println("Shimla is famous for: Mall Road, Jakhu Temple, and Snowfall.");
    }
}

// Main class
class Main {

    public static void main(String[] args) {

        System.out.println("=== Method Overriding - Hill Stations ===\n");

        // Parent class reference pointing to child class objects (Runtime Polymorphism)
        Hillstations h;

        h = new Manali();
        System.out.println("--- Manali ---");
        h.famousfood();
        h.famousfor();

        System.out.println();

        h = new Ooty();
        System.out.println("--- Ooty ---");
        h.famousfood();
        h.famousfor();

        System.out.println();

        h = new Shimla();
        System.out.println("--- Shimla ---");
        h.famousfood();
        h.famousfor();
    }
}