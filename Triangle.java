public class Triangle extends FlatShape {
    private double side;
    private double high;
    public Triangle(double side, double high) {
        super(side * high / 2);
        this.side = side;
        this.high = high;
    }
}
