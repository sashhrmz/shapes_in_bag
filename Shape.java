public abstract class Shape implements Comparable<Shape> {
    private double volume;

    public Shape(double volume) {
        this.volume = volume;
    }

    @Override
    public int compareTo(Shape other) {
        if (this.volume > other.volume) {
            return 1;
        } else if (this.volume == other.volume) {
            return 0;
        } else {
            return -1;
        }
    }

    public double getVolume() {
        return this.volume;
    }
}
