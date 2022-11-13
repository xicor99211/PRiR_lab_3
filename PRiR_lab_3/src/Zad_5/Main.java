package Zad_5;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ktory program (1/2/3): ");
        int wybor = scanner.nextInt();

        System.out.print("Ilosc filozofow (2-100): ");
        int ilosc = scanner.nextInt();
        if(ilosc > 100 || ilosc < 2 || wybor <1 || wybor > 3)
        {
            System.out.println("Bledne dane");
            System.exit(0);
        }

        System.out.print("Co ma zjeść filozof: ");

        Scanner scanner2 = new Scanner(System.in);

        String danie = scanner2.nextLine();

        if(wybor == 1)
        {
            Filozof f1 = new Filozof(1);
            f1.MAX = ilosc;
            f1.uruchom();
            f1.danie = danie;
        }
        else if(wybor == 2)
        {
            Filozof2 f2 = new Filozof2(1);
            f2.MAX = ilosc;
            f2.uruchom();
            f2.danie = danie;
        }
        else if(wybor == 3)
        {
            Filozof3 f3 = new Filozof3(1);
            f3.MAX = ilosc;
            f3.uruchom();
            f3.danie = danie;
        }
    }
}
