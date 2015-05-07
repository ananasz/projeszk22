package szd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/*
Ez az osztály a konstruktorban kapott elérési útvonalon lévő fájlból olvas be 
egy sorfolytonosan, főátlóbeli elemek nélkül megadott alsó háromszög mátrixot.
*/
public class MatrixReader {
    
    private ArrayList<Float> matrix = new ArrayList<>();
    private int n = 1; //n*n-es a teljes mátrix, ha nem adunk meg főátlót, 1től kell indítani
    
    public MatrixReader(File file) throws FileNotFoundException, MatrixException{
        Scanner sc = new Scanner(file);
        sc.useLocale(Locale.US); //float elválasztás . karakterrel (default , lenne)
        int i = 0; //n*n-es mátrix (főátló nélkül vett) alsó háromszögének elemszámát fogja számolni
        int k = 0; //beolvasott elemek száma
        while(sc.hasNextFloat()){
            ++k;
            if(k>i) i += n++; //ha több elemet olvastunk be, mint i, akkor nagyobb a mátrix, 1 sort hozzáadunk, n-et növeljük
            float num = sc.nextFloat();
            System.out.println("beolvasva: "+ num);
            if( num < 0 || num > 1)
                throw new MatrixException("Az élsúlyok csak 0 és 1 közötti értékek lehetnek");
            matrix.add(num);
        }
        if(i != k){ //a beolvasott elemek száma nem egyezik meg az n*n-es mátrixhoz várttal.
            throw new MatrixException("A megadott mátrix nem háromszög mátrix");
        }
    }

    public MatrixReader(MatrixReader copy) {
        matrix = copy.getRawData();
        n = copy.getSize();
    }
    
    public float get(int i, int j){
        if(i==j)
            return 1; //1 valószínűséggel éri el saját magát minden csúcs
        if(i < j) //a felső háromszögből kérdezünk le, használjuk a szimmetriát
            return matrix.get( ( j*(j-1) + 2*i )/2 );
        return matrix.get( ( i*(i-1) + 2*j )/2 );
    }
    
    public void set(int i, int j, float value){
        if(i < j) //a felső háromszögben változtatunk, használjuk a szimmetriát
            matrix.set( ( j*(j-1) + 2*i )/2, value);
        else
            matrix.set( ( i*(i-1) + 2*j )/2, value);
    }
    
    public float[] getRow(int i){
       float[] ret = new float[n];
        for(int j = 0; j<n; j++)
            ret[j] = get(i, j);
        return ret;
    }
    
    
    public int getSize(){
        return n;
    }
    
    public ArrayList<Float> getRawData(){
        return matrix;
    }
    
    public void print(){
        System.out.println();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(get(i, j)+" ");
            }
            System.out.println();
        }
        System.out.println("Raw: ");
        for(int i = 0; i < matrix.size(); i++){
            System.out.print(matrix.get(i) + " ");
        }
        System.out.println();
    }
}
