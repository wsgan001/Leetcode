/*
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.

 */
public class LT498_Diagonal_Traverse {
    public int[] findDiagonalOrder(int[][] matrix) {
	if (matrix == null || matrix.length == 0)
	    return new int[0];
	int m = matrix.length, n = matrix[0].length;

	int[] result = new int[m * n];
	int row = 0, col = 0, d = 1;

	for (int i = 0; i < m * n; i++) {
	    result[i] = matrix[row][col];
	    row -= d;
	    col += d;

	    if (row >= m) {
		row = m - 1;
		col += 2;
		d = -d;
	    } // cannot change order with two if with <0
	    if (col >= n) {
		col = n - 1;
		row += 2;
		d = -d;
	    }
	    if (row < 0) {
		row = 0;
		d = -d;
	    }
	    if (col < 0) {
		col = 0;
		d = -d;
	    }
	}

	return result;
    }
}
