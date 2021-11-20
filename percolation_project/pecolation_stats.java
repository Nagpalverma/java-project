package percolation_project;
import percolation_project.utility.*;
import java.util.*;
public class pecolation_stats {
	
	private final double v;
    private final int time;
    private final double[] fract;
   

    public pecolation_stats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        time = t;
        v = 1.96;
        fract = new double[t];
        for (int i = 0; i < t; i++) {
            double count = 0;
            percolation per = new percolation(n);

            while (!per.percolates()) {
                int x = utility.uniform(1, n );
                int y = utility.uniform(1, n );
                if (!per.isOpen(x, y)) {
                    per.open(x, y);
                    count++;
                }
            }
            fract[i] = count / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
         return utility.mean(fract);
         
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return utility.stddev(fract,mean());
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - v * stddev() / Math.sqrt(time);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + v * stddev() / Math.sqrt(time);
    }


    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("enter size of grid");
        int n = sc.nextInt();
        System.out.println("No of times");
        int t = sc.nextInt();
        pecolation_stats a = new pecolation_stats(n, t);
        System.out.println("mean                    =" + a.mean());
        System.out.println("stddev                  =" + a.stddev());
        System.out.println("p*                  =" + (a.mean()-a.stddev()));
        System.out.println(
                "95% confidence interval = [" + a.confidenceLo() + " , " + a.confidenceHi() + "]");
    }

}
