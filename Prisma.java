public class Prisma extends Shape {
    private double square;
    private double hight;
    Prisma(double square, double hight) {
        super(square * hight);
        this.square = square;
        this.hight = hight;
    }
    public double getSquare() {
        return square;
    }
}
