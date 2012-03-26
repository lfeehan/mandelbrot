import java.util.*;

public class mandelColor{
	public static void main(String args[]){
		//temp vals: to be read in later
		//assume square for now
		double xmin, ymin, xmax, ymax;
		int n = 256;
		xmin = -1.5;
		ymin = -1.0;
		xmax = 0.5;
		ymax = 1.0;
		
		double width = xmax - xmin;
		double height = ymax - ymin;

		double increment = width/n;
		
		double topRight = 512-((512/n)/2);
		
		double xTrans = 0 - xmin;
		double yTrans = 0 - ymin;
		double xScale = topRight / (xmax + xTrans);
		double yScale = topRight / (ymax + yTrans);
		
		double xPlot, yPlot;
		
		//color section (question part 3)
		Scanner sc = new Scanner(System.in);
		String line = new String();
		double[][] colorMap = new double[256][3];
		int count = 0;
		while (sc.hasNext()){
			line = sc.nextLine();
			String [] tolkens = line.split(" ");
			
			for(int i=0; i<tolkens.length; i++){
				colorMap[count][i] = Double.parseDouble(tolkens[i]);	
			}
			count++;
		}
		
	
		
		
		//plot PS file openers
		System.out.println("%!");
		System.out.println("50 50 translate");
		System.out.println("0 0 512 512 rectstroke");
		
		//plot points
		for(double i=xmin; i<=xmax; i+= width/n){ //divide x (width) into n increments
			for(double j=ymin; j<=ymax; j+=height/n){ //divide y(height) int n
				//prints the x,y
				//System.out.println(i + ", " + j);
				int test = mandel(i, j);
								
				xPlot = ((i + xTrans) * xScale) - ((512/n)/2);
				yPlot = ((j + yTrans) * yScale) - ((512/n)/2);
				
				double color = 1 - (test/255); 
				
				System.out.println(color + " setrgbcolor " + xPlot + " " + yPlot + " " + (512/n) + " " + (512/n) + " rectfill");
				
			}
		}
		
		//plot PS file closer
		System.out.println("showpage");
		
	
	}
	
	public static void translatePS(double x, double y, double color){
		
	}
	
	public static int mandel(double x, double y){
		int count = 0;
		double r=x;
		double s=y;
		
		double exitCondition = 0;
		
		//exceeds 255 = extremely likely in the set
		//r*r + s*s > 4 = not in the set at all.
		
		while ( count < 255 && !(exitCondition>4) ){
			double rTemp = r*r - s*s + x;
			double sTemp = (2*r*s) + y;
			
			r = rTemp;
			s = sTemp;
			exitCondition = (r*r) + (s*s);
			count++;
			
			//System.out.println(x + " " + y +":" + exitCondition);
			
		}
		
		return count;
	}
}