/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int n;
    private final double[] list;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("OUT OF RANGE");
        this.n = n;
        this.list = new double[trials];
        double count = 0;
        for (int i = 0; i < trials; ++i) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                if (!test.isOpen(x, y)) {
                    test.open(x, y);
                    ++count;
                }


            }
            list[i] = count / (this.n * this.n);
            count = 0;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(list);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(list);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - CONFIDENCE_95 * this.stddev() / Math.sqrt(n);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + CONFIDENCE_95 * this.stddev() / Math.sqrt(n);
    }

    // test client (see below)
    public static void main(String[] args) {
        int a = StdIn.readInt();
        int b = StdIn.readInt();
        PercolationStats percolationStats = new PercolationStats(a, b);
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval " + percolationStats.confidenceLo() + ", "
                               + percolationStats.confidenceHi());
    }
}
