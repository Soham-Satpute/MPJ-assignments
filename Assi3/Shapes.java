class Shapes {

    // Method overloading: same method name, different parameters

    // Area of Circle
    double area(double radius) {
        return Math.PI * radius * radius;
    }

    // Area of Rectangle
    double area(double length, double width) {
        return length * width;
    }

    // Area of Triangle
    double area(double base, double height, String shape) {
        return 0.5 * base * height;
    }

    // Area of Square
    int area(int side) {
        return side * side;
    }

    public static void main(String[] args) {

        Shapes s = new Shapes();

        System.out.println("=== Method Overloading - Area of Shapes ===\n");

        System.out.println("Area of Circle (radius=7):           " + s.area(7.0));
        System.out.println("Area of Rectangle (5 x 10):          " + s.area(5.0, 10.0));
        System.out.println("Area of Triangle (base=6, height=4): " + s.area(6.0, 4.0, "triangle"));
        System.out.println("Area of Square (side=5):             " + s.area(5));
    }
}