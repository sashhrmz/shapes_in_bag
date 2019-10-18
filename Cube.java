public class Cube extends Shape {
    private double side;

    Cube(double side) {
        super(Math.pow(side, 3));
        this.side = side;
    }

    public double getSide() {
        return this.side;
    }
}
