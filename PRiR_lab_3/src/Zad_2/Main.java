package Zad_2;
import java.util.Random;
public class Main {
    static double wynik = 0;
    boolean isPointInCircle(double x, double y, double Cx, double Cy, double radius) {
        return Math.sqrt(Math.pow((x - Cx), 2) + Math.pow((y - Cy), 2)) <= radius;
    }

    double approximateCricleArea(double radius, double numberOfPoints, int ilosc_watkow) {
        double squareSide = radius * 2;
        double Cx = radius;
        double Cy = radius;
        int pointsInside = 0;
        double x, y;

        Random random = new Random();
        for (int i = 0; i < numberOfPoints/ilosc_watkow; i++) {
            x = random.nextDouble(0,radius/2) * squareSide;
            y = random.nextDouble(0, radius/2) * squareSide;
            if (isPointInCircle(x, y, Cx, Cy, radius))
                pointsInside = pointsInside + 1;
        }
        wynik += pointsInside / numberOfPoints * Math.pow(squareSide, 2);
        return pointsInside / numberOfPoints * Math.pow(squareSide, 2);
    }

    double approximateCricleArea2(double radius, double numberOfPoints, int ilosc_watkow) {
        double squareSide = radius * 2;
        double Cx = radius;
        double Cy = radius;
        int pointsInside = 0;
        double x, y;

        Random random = new Random();
        for (int i = 0; i < numberOfPoints/ilosc_watkow; i++) {
            x = random.nextDouble(0,radius/2) * squareSide;
            y = random.nextDouble(radius/2, radius ) * squareSide;
            if (isPointInCircle(x, y, Cx, Cy, radius))
                pointsInside = pointsInside + 1;
        }
        wynik += pointsInside / numberOfPoints * Math.pow(squareSide, 2);
        return pointsInside / numberOfPoints * Math.pow(squareSide, 2);
    }

    double approximateCricleArea3(double radius, double numberOfPoints, int ilosc_watkow) {
        double squareSide = radius * 2;
        double Cx = radius;
        double Cy = radius;
        int pointsInside = 0;
        double x, y;

        Random random = new Random();
        for (int i = 0; i < numberOfPoints/ilosc_watkow; i++) {
            x = random.nextDouble(radius/2, radius ) * squareSide;
            y = random.nextDouble(0,radius/2) * squareSide;
            if (isPointInCircle(x, y, Cx, Cy, radius))
                pointsInside = pointsInside + 1;
        }
        wynik += pointsInside / numberOfPoints * Math.pow(squareSide, 2);
        return pointsInside / numberOfPoints * Math.pow(squareSide, 2);
    }

    double approximateCricleArea4(double radius, double numberOfPoints, int ilosc_watkow) {
        double squareSide = radius * 2;
        double Cx = radius;
        double Cy = radius;
        int pointsInside = 0;
        double x, y;

        Random random = new Random();
        for (int i = 0; i < numberOfPoints/ilosc_watkow; i++) {
            x = random.nextDouble(radius/2, radius ) * squareSide;
            y = random.nextDouble(radius/2, radius ) * squareSide;
            if (isPointInCircle(x, y, Cx, Cy, radius))
                pointsInside = pointsInside + 1;
        }
        wynik += pointsInside / numberOfPoints * Math.pow(squareSide, 2);
        return pointsInside / numberOfPoints * Math.pow(squareSide, 2);
    }


    public static void main(String[] args) {
        Main main = new Main();
        Thread t1 = new Thread(() -> {
            System.out.println("Pole I cwiartki: " + main.approximateCricleArea(1, 10000000, 4));
        });
        Thread t2 = new Thread(() -> {
            System.out.println("Pole II cwiartki: " + main.approximateCricleArea2(1, 10000000,4));
        });
        Thread t3 = new Thread(() -> {
            System.out.println("Pole III cwiartki: " + main.approximateCricleArea3(1, 10000000,4));
        });
        Thread t4 = new Thread(() -> {
            System.out.println("Pole IV cwiartki: " + main.approximateCricleArea4(1, 10000000,4));
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        while (true) {
            if (t1.isAlive() == false && t2.isAlive() == false && t3.isAlive() == false && t4.isAlive() == false) {
                break;
            }
        }
        System.out.println("");
        System.out.println("Wynik: " +wynik);
    }

}