package szd;

import java.util.ArrayList;
import java.util.Collection;

public class MatrixPathfinder {
    
    private final MatrixReader matrix;
    private final int n;
    private final ArrayList<Integer> finishedNodes = new ArrayList<>();
    
    public MatrixPathfinder(MatrixReader mRead){
        matrix = mRead;
        n = mRead.getSize();
    }
    
    public MatrixReader getShortestPath(int from, int to){
        MatrixReader ret = new MatrixReader(matrix);
        
        //vesszük a kiinduló csúcsból az élek súlyát, ezzel az első csúcsot feldolgoztuk.
        float[] dist = matrix.getRow(from);
        int[] parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = dist[i] > 0 ? from : -1; //a kiindulóponttal össze nem kötött éleknek a szölője -1
        }
        finishedNodes.clear();
        finishedNodes.add(from);
        while( finishedNodes.size() < n){
            int current = getMaxIndex(dist);
            float cDist = dist[current];
            float[] edgesFromCurrent = matrix.getRow(current); //a jelenleg vizsgált csúcsból vezető élek súlyai
            for( int i = 0; i < n; i++){
                if( edgesFromCurrent[i] * cDist > dist[i]){
                    dist[i] = edgesFromCurrent[i] * cDist;
                    parent[i] = current;
                }
            }
            finishedNodes.add(current);
        }
        int prev; //segédváltozó a legrövidebb útvonal meghatározásához
        do{
            prev = parent[to];
            ret.set(prev, to, -matrix.get(prev, to));
            to = prev;
        }while( prev != from);
        
        return ret;
    }
    
    private int getMaxIndex(float[] row){
        int maxIndex = -1;
        float maxF = -1f;
        for( int i = 0; i < n;i++){
            if( !finishedNodes.contains(i) && row[i] > maxF){
                maxF = row[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
