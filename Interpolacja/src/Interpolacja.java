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
                System.out.println("punkt: ("+punkt.getX()+","+punkt.getY()+")");
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

    void obliczWzor(){

        int n = listaPunktow.size();
        double[] wspolczynniki = new double[n];


        double mianownik = 1 ;

        for (int i = 0; i < n; i++) {
            double[] wspolczynnikiPomocnicze  =  new double[n];
            double[] tablica = new double[n-1];
            int k=0;

            for (int j = 0; j < n; j++) {
                if (j!=i) {
                    tablica[k] = listaPunktow.get(j).getX();
                    //System.out.println("tablica "+ i +": " + tablica[k]);
                    k++;
                }
            }
            for (int j = 0; j < n-1; j++) {
               // System.out.println(" 111tablica "+ i +": " + tablica[j]);
            }


            wspolczynnikiPomocnicze=obliczLicznik(tablica);


            for (int j = 0; j < n; j++) {
                //System.out.println("wspolczynnikiPomocnicze "+ i +": " + wspolczynnikiPomocnicze[j]);
                wspolczynnikiPomocnicze[j] = wspolczynnikiPomocnicze[j] * listaPunktow.get(i).getY()  ;
            }


            for (int j = 0; j < n; j++) {
                if (j!=i) {
                    mianownik = mianownik * (listaPunktow.get(i).getX() - listaPunktow.get(j).getX());
                    System.out.println("mianownik: " + mianownik);
                }
            }

            for (int j = 0; j < n; j++) {
                wspolczynnikiPomocnicze[j] = wspolczynnikiPomocnicze[j] / mianownik;
            }
            mianownik=1;
            for (int j = 0; j < n; j++) {
                wspolczynniki[j] = wspolczynniki[j] + wspolczynnikiPomocnicze[j];
                System.out.println("wspolczynniki "+ i +": " + wspolczynniki[j]);
            }

        }

        String wynik="W(x)= ";
        int stopien = n-1;
        for (int i =0; i<n; i++) {
            if (i==n-1) {

                wynik=wynik + wspolczynniki[i];
            }
            else  {
                wynik= wynik + wspolczynniki[i]+ " x^" + stopien + " + ";
                stopien--;
            }
        }
        System.out.println(wynik);

    }
    double[] obliczLicznik(double[] tablica){

        int n = tablica.length;
        double[] licznik = new double[n+1];



        if(n==6){

            licznik[0]=1;

            licznik[1]=(tablica[0]+tablica[1]+tablica[2]+tablica[3]+tablica[4]+tablica[5])*-1;

            licznik[2]=(tablica[0]*tablica[1])+(tablica[0]*tablica[2])+(tablica[0]*tablica[3])+(tablica[0]*tablica[4])+(tablica[0]*tablica[5])+(tablica[1]*tablica[2])
                    +(tablica[1]*tablica[3])+(tablica[1]*tablica[4])+(tablica[1]*tablica[5])+(tablica[2]*tablica[3])+(tablica[2]*tablica[4])+(tablica[2]*tablica[5])
                    +(tablica[3]*tablica[4])+(tablica[3]*tablica[5])+(tablica[4]*tablica[5]);

            licznik[3]=(tablica[0]*tablica[1]*tablica[2])+(tablica[0]*tablica[1]*tablica[3])+(tablica[0]*tablica[1]*tablica[4])+(tablica[0]*tablica[1]*tablica[5])
                    +(tablica[0]*tablica[2]*tablica[3])+(tablica[0]*tablica[2]*tablica[4])+(tablica[0]*tablica[2]*tablica[5])+(tablica[0]*tablica[3]*tablica[4])
                    +(tablica[0]*tablica[3]*tablica[5])+(tablica[0]*tablica[4]*tablica[5])+(tablica[1]*tablica[2]*tablica[3])+(tablica[1]*tablica[2]*tablica[4])
                    +(tablica[1]*tablica[2]*tablica[5])+(tablica[1]*tablica[3]*tablica[4])+(tablica[1]*tablica[3]*tablica[5])+(tablica[1]*tablica[4]*tablica[5])
                    +(tablica[2]*tablica[3]*tablica[4])+(tablica[2]*tablica[3]*tablica[5])+(tablica[2]*tablica[4]*tablica[5])+(tablica[3]*tablica[4]*tablica[5]);

            licznik[4]=(tablica[0]*tablica[1]*tablica[2]*tablica[3])+(tablica[0]*tablica[1]*tablica[2]*tablica[4])+(tablica[0]*tablica[1]*tablica[2]*tablica[5])
                    +(tablica[0]*tablica[1]*tablica[3]*tablica[4])+(tablica[0]*tablica[1]*tablica[3]*tablica[5])+(tablica[0]*tablica[1]*tablica[4]*tablica[5])
                    +(tablica[0]*tablica[2]*tablica[3]*tablica[4])+(tablica[0]*tablica[2]*tablica[3]*tablica[5])+(tablica[0]*tablica[2]*tablica[4]*tablica[5])
                    +(tablica[0]*tablica[3]*tablica[4]*tablica[5])+(tablica[1]*tablica[2]*tablica[3]*tablica[4])+(tablica[1]*tablica[2]*tablica[3]*tablica[5])
                    +(tablica[1]*tablica[2]*tablica[4]*tablica[5])+(tablica[1]*tablica[3]*tablica[4]*tablica[5])+(tablica[2]*tablica[3]*tablica[4]*tablica[5]);

            licznik[5]=(tablica[0]*tablica[1]*tablica[2]*tablica[3]*tablica[4])+(tablica[0]*tablica[1]*tablica[2]*tablica[3]*tablica[5])+(tablica[0]*tablica[1]*tablica[2]*tablica[4]*tablica[5])
                    +(tablica[0]*tablica[1]*tablica[3]*tablica[4]*tablica[5])+(tablica[0]*tablica[2]*tablica[3]*tablica[4]*tablica[5])+(tablica[1]*tablica[2]*tablica[3]*tablica[4]*tablica[5]);

            licznik[6]=(tablica[0]*tablica[1]*tablica[2]*tablica[3]*tablica[4]*tablica[5]);

        }

        else if(n==5){
            licznik[0]=1;

            licznik[1]=(tablica[0]+tablica[1]+tablica[2]+tablica[3]+tablica[4])*-1;

            licznik[2]=(tablica[0]*tablica[1])+(tablica[0]*tablica[2])+(tablica[0]*tablica[3])+(tablica[0]*tablica[4])+(tablica[1]*tablica[2])
                    +(tablica[1]*tablica[3])+(tablica[1]*tablica[4])+(tablica[2]*tablica[3])+(tablica[2]*tablica[4])+(tablica[3]*tablica[4]);

            licznik[3]=((tablica[0]*tablica[1]*tablica[2])+(tablica[0]*tablica[1]*tablica[3])+(tablica[0]*tablica[1]*tablica[4])+(tablica[0]*tablica[2]*tablica[3])
                    +(tablica[0]*tablica[2]*tablica[4])+(tablica[0]*tablica[3]*tablica[4])+(tablica[1]*tablica[2]*tablica[3])+(tablica[1]*tablica[2]*tablica[4])
                    +(tablica[1]*tablica[3]*tablica[4])+(tablica[2]*tablica[3]*tablica[4]))*-1;

            licznik[4]=(tablica[0]*tablica[1]*tablica[2]*tablica[3])+(tablica[0]*tablica[1]*tablica[2]*tablica[4])+(tablica[0]*tablica[1]*tablica[3]*tablica[4])
                    +(tablica[0]*tablica[2]*tablica[3]*tablica[4])+(tablica[1]*tablica[2]*tablica[3]*tablica[4]);

            licznik[5]=tablica[0]*tablica[1]*tablica[2]*tablica[3]*tablica[4]*-1;

        }
        else if(n==4){
            licznik[0]=1;
            licznik[1]=(tablica[0]+tablica[1]+tablica[2]+tablica[3])*-1;
            licznik[2]=(tablica[0]*tablica[1])+(tablica[0]*tablica[2])+(tablica[0]*tablica[3])+(tablica[1]*tablica[2])+(tablica[1]*tablica[3])+(tablica[2]*tablica[3]);
            licznik[3]=(tablica[0]*tablica[1]*tablica[2])+(tablica[0]*tablica[1]*tablica[3])+(tablica[0]*tablica[2]*tablica[3])+(tablica[1]*tablica[2]*tablica[3])*-1;
            licznik[4]=tablica[0]*tablica[1]*tablica[2]*tablica[3];


        }
        else if(n==3){
            licznik[0]=1;
            licznik[1]=(tablica[0]+tablica[1]+tablica[2])*-1;
            licznik[2]=(tablica[0]*tablica[1])+(tablica[0]*tablica[2])+(tablica[1]*tablica[2])*-1;
            licznik[3]=tablica[0]*tablica[1]*tablica[2];
            //System.out.println(tablica[0]+" "+tablica[1]+" "+tablica[2]);
           // System.out.println("Wielomian: "+licznik[0]+"x^3+"+licznik[1]+"x^2+"+licznik[2]+"x+"+licznik[3]);

        }
        else if(n==2){

            licznik[0]=1;
            licznik[1]=(tablica[0]+tablica[1])*-1;
            licznik[2]=tablica[0]*tablica[1];


        }
        else if(n<6){
            System.out.println("Za duży wielomin");
        }
        else{
            System.out.println("Błąd");
        }

        return licznik;
    }

}

