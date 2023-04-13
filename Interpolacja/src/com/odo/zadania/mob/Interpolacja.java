package com.odo.zadania.mob;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Interpolacja {
//test

    private ArrayList<Point> listaPunktow;

    Interpolacja(){

        listaPunktow=new ArrayList<>();

        /*
        Point punkt = new Point(1,2);

        Point punkt2 = new Point(2,3);

        Point punkt3 = new Point(3,4);

        listaPunktow.add(punkt);

        listaPunktow.add(punkt2);
        listaPunktow.add(punkt3);

       // System.out.println(listaPunktow);
       */

    }

    void odczytZPliku (String nazwaPliku){

        File file = new File(nazwaPliku);//tworzenie obiektu file
        //System.out.println("test");

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                Point punkt=new Point();

                System.out.println("test");

                punkt.setLocation(scanner.nextDouble(), scanner.nextDouble());
                listaPunktow.add(punkt);

                //listaPunktow.add(Double.parseDouble(scanner.nextLine()));
               // System.out.println(punkt);
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku lub nie da się otworzyć");
            System.exit(0);
        }
        catch (InputMismatchException e) {
            System.out.println("Dane w pliku nie są liczbami");
            System.exit(0);
        }
        System.out.println(listaPunktow);
    }

    String obliczWzor(){

        String wielomian="test wiel";

        return wielomian;
    }

}
