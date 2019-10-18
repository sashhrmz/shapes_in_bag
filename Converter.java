public class Converter {
    public static Prisma convert(FlatShape figure) {
        double hight =  Math.random() * 100;
        Prisma prisma = new Prisma(figure.getSquare(), hight);
        return prisma;
    }
}
