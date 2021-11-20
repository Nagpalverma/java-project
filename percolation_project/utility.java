package percolation_project;

import java.util.*;

public class utility {


	static class WeightedQuickUnionUF{
		int[] id,sz;
		WeightedQuickUnionUF(int n)
		{
			id=new int[n*n];
			sz=new int[n*n];
			for(int i =0;i<n*n;i++)
			{
				id[i]=i;
				sz[i]=1;
			}
		}
		int root(int a)
		{
			while(a!=id[a])
			{
				id[a]=id[id[a]];
				a=id[a];	
			}
			return a;
		}
		
		boolean find(int p,int q) {
			return root(p)==root (q);
		}
		void union(int p,int q)
		{
			int x=root(p);
			int y=root(q);
			int s1=sz[x];
			int s2=sz[y];
			if(s1>s2)
			{
				id[y]=x;
				sz[x]+=s2;
			}
			else 
			{
				id[x]=y;
				sz[y]+=s1;
			}
		}
	}
	
	public static double mean(double[] arr)
	{
		int n=arr.length;
		double sum=0;
		for(double i:arr)
			sum+=i;
		return sum/n;
	}
	
	public static double stddev(double[] arr,double mean)
	{
		double[] a=Arrays.copyOf(arr, arr.length);
		for(int i=0;i<arr.length;i++)
			a[i]=Math.pow ( Math.abs(arr[i]-mean),2);
		
		return Math.sqrt(mean(a));		
		
	}
	public static int uniform(int a,int b)
	{
		return (int)(Math.random()*(b-a+1)+a);
	}
}
