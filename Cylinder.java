public class Cylinder extends RotationBodies {
    private double high;

    public Cylinder(double radius, double high) {
        super(radius, Math.PI * radius * radius * high);
        this.high = high;
    }

    public double getHigh() {
        return this.high;
    }
}
