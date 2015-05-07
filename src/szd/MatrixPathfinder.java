package szd;

public class MatrixPathfinder {
    
    private final MatrixReader matrix;
    private final int n;
    
    public MatrixPathfinder(MatrixReader mRead){
        matrix = mRead;
        n = mRead.getSize();
    }
    
    public MatrixReader getShortestPath(int v1, int v2){
        MatrixReader ret = new MatrixReader(matrix);
        
        return ret;
    }
}
