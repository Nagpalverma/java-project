package percolation_project;

import percolation_project.utility.WeightedQuickUnionUF;

public class percolation {

	 private final int top;

	    private boolean[][] grid;
	    private final WeightedQuickUnionUF qu;
	    private int count;
	    private final int size;
	    private final int bottom;

	    // creates n-by-n grid, with all sites initially blocked
	    public percolation(int n) {
	        if (n <= 0)
	            throw new IllegalArgumentException();

	        bottom = n * n + 1;
	        count = 0;
	        top = 0;

	        qu = new WeightedQuickUnionUF(n * n + 2);
	        grid = new boolean[n][n];
	        size = n;
	    }

	    private int index(int row, int col) {
	        return size * (row - 1) + col;
	    }

	    private void exception(int row, int col) {
	        if (row <= 0 || row > size || col <= 0 || col > size)
	            throw new IllegalArgumentException();
	    }

	    // opens the site (row, col) if it is not open already

	    public void open(int row, int col) {
	        exception(row, col);
	        if (!grid[row - 1][col - 1]) {
	            grid[row - 1][col - 1] = true;
	            count++;
	        }

	        if (row == 1)
	            qu.union(index(row, col), top);
	        if (row == size)
	            qu.union(index(row, col), bottom);

	        if (row > 1 && isOpen(row - 1, col))
	            qu.union(index(row, col), index(row - 1, col));
	        if (row < size && isOpen(row + 1, col))
	            qu.union(index(row, col), index(row + 1, col));
	        if (col > 1 && isOpen(row, col - 1))
	            qu.union(index(row, col), index(row, col - 1));
	        if (col < size && isOpen(row, col + 1))
	            qu.union(index(row, col), index(row, col + 1));

	    }

	    // is the site (row, col) open?
	    public boolean isOpen(int row, int col) {
	        exception(row, col);
	        return grid[row - 1][col - 1];
	    }

	    // is the site (row, col) full?
	    public boolean isFull(int row, int col) {
	        exception(row, col);
	        return qu.find(top ,index(row, col));

	    }

	    // returns the number of open sites
	    public int numberOfOpenSites() {
	        return count;

	    }

	    // does the system percolate?
	    public boolean percolates() {

	        return qu.find(top,bottom);
	    }
	
}
