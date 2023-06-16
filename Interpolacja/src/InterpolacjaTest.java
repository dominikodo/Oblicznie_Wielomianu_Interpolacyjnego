
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InterpolacjaTest {

    public static void main(String[] args) {

        boolean wylacz = false;
        while (wylacz==false) {

            Interpolacja interpolacja = new Interpolacja();
            Scanner scanner = new Scanner(System.in);

            String nazwaPliku = new String();

            System.out.println("Podaj nazwe ścieżkę pliku: ");

            nazwaPliku = scanner.nextLine();


            interpolacja.odczytZPliku(nazwaPliku);

            interpolacja.obliczWzor();


            //C:\Users\piese\Desktop\Metody Obliczeniowe\Oblicznie_Wielomianu_Interpolacyjnego\Interpolacja\plik.txt
        }

    }

}
