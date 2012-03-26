import java.util.*;

public class mandelColor{
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		double xmin = Double.parseDouble(sc.nextLine());
		double ymin = Double.parseDouble(sc.nextLine());
		double xmax = Double.parseDouble(sc.nextLine());
		double ymax = Double.parseDouble(sc.nextLine());
		
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
				
				
				int test = mandel(i, j);
								
				xPlot = ((i + xTrans) * xScale) - ((512/n)/2);
				yPlot = ((j + yTrans) * yScale) - ((512/n)/2);
				
				double color = 1 - (test/255); 
				
				System.out.println(colorMap[test][0] +" "+ colorMap[test][1] +" "+ colorMap[test][2] + " setrgbcolor " + xPlot + " " + yPlot + " " + (512/n) + " " + (512/n) + " rectfill");
				
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
