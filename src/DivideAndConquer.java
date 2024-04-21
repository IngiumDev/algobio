import Model.MSSFinder;
import Model.Subscore;

public class DivideAndConquer implements MSSFinder {
    public static void main(String[] args) {

    }

    @Override
    public Subscore findMSS(int[] arr, int n) {
        return MSS_DC(arr, 1, n);
    }
    public Subscore MSS_DC(int[] arr, int i, int j) {
        if (i==j){
                if (arr[i] > 0) {
                    return new Subscore(i, i, arr[i]);
                } else {
                    return new Subscore(i, i-1, 0);
                }
        } else {
            int m = (i+j)/2;
            Subscore left = MSS_DC(arr, i, m);
            Subscore right = MSS_DC(arr, m+1, j);
            // TODO: Implement the rest of the algorithm
        }

    }
}
