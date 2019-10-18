import java.util.Collections;
import java.util.Vector;

public class Bag <T extends Shape>{
    private Vector<T> figures = new Vector<T>();
    private double freeVolume;
    private double volume;

    public Bag(double volume) {
        this.freeVolume = volume;
        this.volume = volume;
    }

    public double getVolume() { return volume; }

    public double getFreeVolume() { return freeVolume; }

    public Vector<T> getList() { return figures; }

    public void add(T figure) throws MyException {
        if(figure.getVolume() > this.freeVolume) {
            throw new MyException("The volume u want to pack into your bag is bigger than the volume of your bag. ");
        }
        this.freeVolume = this.freeVolume - figure.getVolume();
        this.figures.add(figure);
        figures.sort(Collections.reverseOrder());
    }

    public void setVolume(double volume) throws MyException {
        if(this.volume - freeVolume >= volume) {
            throw new MyException("The volume you want your bag to be is too " +
                    "small to shapes that are in your bag now. ");
        }
        double packedVolume = this.volume - freeVolume;
        this.volume = volume;
        freeVolume = this.volume - packedVolume;
    }
}
