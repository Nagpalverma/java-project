package convex_hull;

import java.util.Comparator;
import java.util.*;
import convex_hull.utility.point_2d;



public class convex_hull {
	
	Stack<point_2d> hull_point=new Stack<>();
	point_2d[] arr=null;
	
	//intialising
	
	convex_hull(point_2d[] a)
	{ arr=a;}
	
	
	//sorting on the basis of y coordinates
	     
	void sort_y()
	{
	int n=arr.length ;
	for(int i=n-1;i>0;i--)
	{
		if(arr[i].y<arr[i-1].y)
			swap(i,i-1);		
		
	}}
	
	
	//for swaping two no
	
	void swap(int a,int b)
	{
		point_2d temp=arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
	}
	
	
	//comparing polar angle of a and b w.r.t  arr[0] point
	
	public boolean isless(point_2d a, point_2d b)
	{
		return arr[0].po.compare(a, b)<1;
	}
	
	
	//main function which calcuate the point
	
	public void result()
	{
		sort_y();
		int n=arr.length;
		
		//we are using insertion sort here to sort on the basis of polar angle 
		for(int i=2;i<n;i++)
		{
			for(int j=i;j>1;j--)
			{
				if(isless(arr[j],arr[j-1]))
					swap(j,j-1);
				else break;
			}
		}
		
		
	
		//traversing in counter clock wise
		
	hull_point.push(arr[0]);
	hull_point.push(arr[1]);
	for(int i=2;i<n;i++)
	{
		point_2d pre=hull_point.pop();
		
		while(ccw(hull_point.peek(),pre,arr[i])<0)
			pre=hull_point.pop();
		
		hull_point.push(pre);
		hull_point.push(arr[i]);
		
		
	}
	
	
		
	}
	
	
	//creating counter clock wise method
	
	public int ccw(point_2d a,point_2d b,point_2d c)
	{
		double m=(b.x-a.x)*(c.y-b.y)-(b.y-a.y)*(c.x-b.x);
		if(m>0)
			return 1;
		else  return -1;
	}
	
	
	

	
	
	
	
	
	//main method
	
	
	public static void main(String[] args) {
		point_2d x0=new point_2d(0,-5);
		point_2d x1=new point_2d(-5,5);
		point_2d x2=new point_2d(5,1);
		point_2d x3=new point_2d(5,5);
		point_2d x4=new point_2d(1,7);
		point_2d x5=new point_2d(3,8);
		point_2d x6=new point_2d(-3,7);
		point_2d x7=new point_2d(-5,1);
		point_2d x8=new point_2d(0,10);
		point_2d x9=new point_2d(-4,2);
		
		
		point_2d[] arr= {x0,x1,x2,x3,x4,x5,x6,x7,x8,x9};
		convex_hull aa=new convex_hull(arr);
		aa.result();	
		
		
		while(!aa.hull_point.isEmpty())
			System.out.println(aa.hull_point.pop());
		char[] c= {'a'};
		String s=new String(c);


	}

}
