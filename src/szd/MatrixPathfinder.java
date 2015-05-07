package szd;

import java.util.ArrayList;
import static projeszk22.Consts.*;

public class MatrixPathfinder {
    
    private final MatrixManager matrix;
    private final int n;
    private final ArrayList<Integer> finishedNodes = new ArrayList<>();
    
    public MatrixPathfinder(MatrixManager matrix){
        this.matrix = matrix;
        n = matrix.getSize();
    }
    
    public MatrixManager getShortestPath(int from, int to) throws PathException{
        int node1 = Math.min(from, to);
        int node2 = Math.max(from, to);
        MatrixManager path = new MatrixManager(n);
        
        //vesszük a kiinduló csúcsból az élek súlyát, ezzel az első csúcsot feldolgoztuk.
        float[] dist = matrix.getRow(node1);
        int[] parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = dist[i] > 0 ? node1 : -1; //a kiindulóponttal össze nem kötött éleknek a szülője -1
        }
        
        finishedNodes.clear();
        finishedNodes.add(node1);
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
        if( parent[node2] == -1)
            throw new PathException(SZD_ERR_NO_PATH);
        do{
            prev = parent[node2];
            path.set(prev, node2, 1);
            node2 = prev;
        }while( prev != node1);
        
        return path;
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
