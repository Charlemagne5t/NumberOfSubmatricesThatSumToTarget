public class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] prefixSum = new int[m][n];
        prefixSum[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++){
            prefixSum[0][i] = prefixSum[0][i - 1] + matrix[0][i];
        }
        for (int i = 1; i < m; i++){
            prefixSum[i][0] = prefixSum[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1] + matrix[i][j];
            }
        }


        int count = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                count += sum(prefixSum, target, i, j);
            }
        }



        return count;
    }
    private int sum(int[][] prefixSum, int target, int iS, int jS){

        int m = prefixSum.length;
        int n = prefixSum[0].length;
        int count = 0;
        for(int i = iS; i < m; i++){
            for(int j = jS; j < n; j++){
                int s = prefixSum[i][j];

                if (jS > 0) {
                    s -= prefixSum[i][jS - 1];
                }


                if (iS > 0) {
                    s -= prefixSum[iS - 1][j];
                }

                if (iS > 0 && jS > 0) {
                    s += prefixSum[iS - 1][jS - 1];
                }
                if(target == s){
                    count++;
                }
            }

        }
        return count;
    }
}
