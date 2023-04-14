package com.odo.zadania.mob;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Interpolacja {
//test

    private ArrayList<Punkt> listaPunktow;

    Interpolacja(){

        listaPunktow=new ArrayList<>();



    }

    void odczytZPliku (String nazwaPliku){

        File file = new File(nazwaPliku);//tworzenie obiektu file
        //System.out.println("test");

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextDouble()) {

                Punkt punkt=new Punkt();

                //System.out.println("test");

                punkt.setLocation(scanner.nextDouble(), scanner.nextDouble());
                listaPunktow.add(punkt);

                //System.out.println("punkt"+ scanner.nextDouble()+","+scanner.nextDouble());
                System.out.println("punkt"+punkt.getX()+","+punkt.getY());
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
        //System.out.println(listaPunktow);
    }

    /*void obliczWzor(){

        //String wielomian="test wiel";

        int n = listaPunktow.size();

        double[] wspolczynniki = new double[n];



        wspolczynniki[0] = listaPunktow.get(0).getY();

        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1; j--) {
                double licznik = wspolczynniki[j - 1] - wspolczynniki[j];
                double mianownik = listaPunktow.get(j - 1).getX() - listaPunktow.get(j + i - 1).getX();
                wspolczynniki[j] = licznik / mianownik;
            }
            wspolczynniki[0] = listaPunktow.get(0).getY();
        }


        int nw = wspolczynniki.length;

        for (int i = 0; i < nw; i++) {
            System.out.print(wspolczynniki[i]);
            if (i > 0) {
                System.out.print(" * x");
                for (int j = 1; j < i; j++) {
                    System.out.print(" * (x - " + listaPunktow.get(j - 1).getX() + ")");
                }
            }
            System.out.print(" + ");
        }
        System.out.println("0");

       // return wielomian;
    }*/

    void obliczWzor(){

        int n = listaPunktow.size();
        double[] wspolczynniki = new double[n];

        // Ustawienie pierwszych współczynników
        for (int i = 0; i < n; i++) {
            wspolczynniki[i] = listaPunktow.get(i).getY();
        }

        // Obliczanie kolejnych współczynników
        for (int i = 1; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                double licznik = wspolczynniki[j] - wspolczynniki[j - 1];
                double mianownik = listaPunktow.get(j).getX() - listaPunktow.get(j - i).getX();
                wspolczynniki[j] = licznik / mianownik;
            }
        }

        // Wypisywanie wzoru wielomianu
        int nw = wspolczynniki.length;
        String wynik="W(x)= ";

        for (int i = nw; i > 0; i--) {
            if ((i-1)==0) {

                wynik=wynik + wspolczynniki[i];
            }
            else  {
                wynik= wynik + wspolczynniki[i-1]+ " x^" + (i-1) + " + ";
            }
        }
        System.out.println(wynik);
    }

}

