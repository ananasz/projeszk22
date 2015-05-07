package szd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import static projeszk22.Consts.*;

/*
Ez az osztály a konstruktorban kapott elérési útvonalon lévő fájlból olvas be 
egy sorfolytonosan, főátlóbeli elemek nélkül megadott alsó háromszög mátrixot.
*/
public class MatrixManager {
    
    private final ArrayList<Float> matrix;
    private int n = 1; //n*n-es a teljes mátrix, ha nem adunk meg főátlót, 1től kell indítani
    
    public MatrixManager(File file) throws FileNotFoundException, MatrixException{
        matrix = new ArrayList<>();
        Scanner sc = new Scanner(file);
        sc.useLocale(Locale.US); //float elválasztás . karakterrel (default , lenne)
        int i = 0; //n*n-es mátrix (főátló nélkül vett) alsó háromszögének elemszámát fogja számolni
        int k = 0; //beolvasott elemek száma
        if( !sc.hasNextFloat() )
            throw new MatrixException(SZD_ERR_NO_DATA);
        while(sc.hasNextFloat()){
            ++k;
            if(k>i) i += n++; //ha több elemet olvastunk be, mint i, akkor nagyobb a mátrix, 1 sort hozzáadunk, n-et növeljük
            float num = sc.nextFloat();
            if( num < 0 || num > 1)
                throw new MatrixException(SZD_ERR_INCORRECT_NUMBERS);
            matrix.add(num);
        }
        if(i != k){ //a beolvasott elemek száma nem egyezik meg az n*n-es mátrixhoz várttal.
            throw new MatrixException(SZD_ERR_NOT_TRIANGULAR);
        }
    }

    public MatrixManager(int size) {
        matrix = new ArrayList<>();
        n = size;
        for(int i = 0; i < n *(n-1)/2; i++){
            matrix.add(0.0f);
        }
    }
    
    public float get(int i, int j){
        if(i==j)
            return 1; //1 valószínűséggel éri el saját magát minden csúcs
        if(i < j) //a felső háromszögből kérdezünk le, használjuk a szimmetriát
            return matrix.get( ( j*(j-1) + 2*i )/2 );
        return matrix.get( ( i*(i-1) + 2*j )/2 );
    }
    
    public void set(int i, int j, float value){
        if(i == j)
            return;
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
    
    public void print(){
        System.out.println();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(get(i, j)+" ");
            }
            System.out.println();
        }
    }
}
