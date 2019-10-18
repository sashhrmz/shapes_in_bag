public class Rectangle extends FlatShape {
    private double side;
    private double high;
    public Rectangle(double side, double high) {
        super(side * high);
        this.side = side;
        this.high = high;
    }
}
