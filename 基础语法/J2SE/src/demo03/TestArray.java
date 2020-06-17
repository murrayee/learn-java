package demo03;

public class TestArray {
    public static void main(String[] args) {
        int[][] n = new int[5][5];
        int[][] n1 = {
                {1, 2, 3, 9},
                {4, 56, 7, 8},
                {3, 6, 17, 18},
        };
        for (int i=0;i<n1.length;i++){
            for(int j =0;j<n[i].length;j++){
                System.out.println(n1[i][j]);
            }
        }
    }
}
