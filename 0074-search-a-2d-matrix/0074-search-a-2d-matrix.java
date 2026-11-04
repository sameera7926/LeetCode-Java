
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int row = matrix.length;
        int column = matrix[0].length;

        int low = 0;
        int high = row * column - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int n = mid / column;
            int m = mid % column;

            if (matrix[n][m] == target) {
                return true;
            } 
            else if (matrix[n][m] < target) {
                low = mid + 1;
            } 
            else {
                high = mid - 1;
            }
        }

        return false;
    }
}