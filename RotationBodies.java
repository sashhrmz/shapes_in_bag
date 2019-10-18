public class RotationBodies extends Shape {

    private double radius;

    public RotationBodies(double radius, double volume)  {
        super(volume);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
