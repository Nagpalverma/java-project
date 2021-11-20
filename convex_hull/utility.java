package convex_hull;

import java.util.Comparator;



public class utility {
	
	
	public  static class point_2d
	{ 
	
		final double x,y;
		//intialising coordinates
		point_2d(double a,double b)
		{
			x=a;y=b;
		}
		
		//po= polar order
		
		public final  Comparator<point_2d> po=new polar_order();
		
		
		//craeting counter clock wise method
		public int ccw(point_2d a,point_2d b,point_2d c)
		{
			//we are doing cross product here for two lines (a to b),(b to  c)
			double m=(b.x-a.x)*(c.y-b.y)-(b.y-a.y)*(c.x-b.x);
			if(m>0)
				return 1;
			else  return -1;
		}
		
		
		//overriding toString()
		public String toString()
		{
			return x+"    "+y;
		}
		
		
		//implementing  compare() for comparing two points on the basis of polar angle
		
 		public class polar_order implements Comparator<point_2d>
		{
			public int compare(point_2d a,point_2d b)
			{				
			double x1=a.x-x;
			double y1=a.y-y;
			double x2=b.x-x;
	        double y2=b.y-y;
	         
	        //this means that polar angle of a and b w.r.t this point 0
	         if(y1==0 && y2==0)return 0;
	         
	         // a has low polar angle than b
	         else if(y1>=0 && y2<0)return -1;
	         
	         //a has more polar angle than b
	         else if(y1<0 && y2>=0)return 1;
	         
	         //if both points lies above x axis or below x axis then
	         //if ccw then a is less than b
	         //else grater than b
	         else 
	        	 return -ccw(point_2d.this,a,b);
			
			}
		}
	}
	

}
