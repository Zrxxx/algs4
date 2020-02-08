/* *****************************************************************************
 *  Name: Zhongrx
 *  Date: 20200208
 *  Description: Union-Find
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int count = 0;
    private int size;
    // size for grid ,not for grid matrix size, the size of grid matrix  is n+1 x n+1

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n > 0) {
            size = n + 1;
            grid = new boolean[size][size];
            uf = new WeightedQuickUnionUF(size * size + 1);  // one for the bottom
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    grid[i][j] = false;
                }
            }
        }
        else {
            throw new IllegalArgumentException("OUT OF RANGE");
        }
    }

    private void union(int row, int col) {
        if (row == 1)
            uf.union(row * size + col, 0);
        if (row == size - 1)
            uf.union(size * size, row * size + col);
        if (grid[row - 1][col])
            uf.union(row * size + col, (row - 1) * size + col);
        if (row < size - 1 && grid[row + 1][col])
            uf.union(row * size + col, (row + 1) * size + col);
        if (grid[row][col - 1])
            uf.union(row * size + col, row * size + (col - 1));
        if (col < size - 1 && grid[row][col + 1])
            uf.union(row * size + col, row * size + (col + 1));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (col > 0 && row > 0 && col < size && row < size) {
            if (!grid[row][col]) { // iff grid[row][col] is false
                grid[row][col] = true;
                ++count;
                union(row, col);
            }

        }

        else {
            throw new IllegalArgumentException("OUT OF RANGE");
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (col > 0 && row > 0 && col < size && row < size)
            return grid[row][col];
        else
            throw new IllegalArgumentException("OUT OF RANGE");
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (col > 0 && row > 0 && col < size && row < size)
            return uf.connected(0, row * size + col);
        else
            throw new IllegalArgumentException("OUT OF RANGE");
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, size * size);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(3);
        test.open(3, 1);
        test.open(3, 3);
        test.open(1, 1);
        test.open(2, 1);
        System.out.print(test.count);
        System.out.print(test.percolates());

    }


}
