package szd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Ez az osztály a konstruktorban kapott elérési útvonalon lévő fájlból olvas be 
egy sorfolytonosan, főátlóbeli elemek nékül megadott alsó háromszög mátrixot.
*/
public class MatrixReader {
    
    private final ArrayList<Integer> matrix = new ArrayList<>();
    private int n = 1; //n*n-es a teljes mátrix, ha nem adunk meg főátlót, 1től kell indítani
    
    public MatrixReader(File file) throws FileNotFoundException, MatrixException{
        Scanner sc = new Scanner(file);
        
        int i = 0; //n*n-es mátrix (főátló nélkül vett) alsó háromszögének elemszámát fogja számolni
        int k = 0; //beolvasott elemek száma
        while(sc.hasNextInt()){
            ++k;
            if(k>i) i += n++; //ha több elemet olvastunk be, mint i, akkor nagyobb a mátrix, 1 sort hozzáadunk, n-et növeljük
            matrix.add(sc.nextInt());
            System.out.println("----\nbeolvasott: " + k +"\nvárt elemszám: "+ i +"\nn: "+ n);
        }
        if(i != k){ //a beolvasott elemek száma nem egyezik meg az n*n-es mátrixhoz várttal.
            throw new MatrixException("A megadott mátrix nem háromszög mátrix");
        }
    }
    
    public int get(int x, int y){
        return 0;
    }
}
