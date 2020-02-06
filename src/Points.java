import java.awt.Point;

public class Points {
	private double x;
	private double y;
	private double targetV;
	private double fIndex;

	public Points(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Points(double x, double y, double tarV) {
		this.x = x;
		this.y = y;
		this.targetV = tarV;
	}

	public Points(Points p){
		this.x = p.getX();
		this.y = p.getY();
	}

	public Points(Point p){
		this.x = p.getX();
		this.y = p.getY();
	}

	public void setVel(double v){
		targetV = v;
	}

	public double getVel(){
		return targetV;
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public double fIndex() {
		return fIndex;
	}

	public void setIndex(double i) {
		fIndex = i;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public double distFrom(Points p) {
		return Math.sqrt(Math.abs(p.getX()-x)*Math.abs(p.getX()-x) + Math.abs(p.getY()-y)*Math.abs(p.getY()-y));
	}

	public static double curvature(double L, Points cPosition, double rAngle, Points lPoint) {
		
		//variable instantiation
		double localX = cPosition.getX();
		double localY = cPosition.getY();
		
		double a = -Math.tan(rAngle);
		double b = 1;
		double c = Math.tan(rAngle) * localX;
		
		//calculations
		double side = Math.signum(Math.sin(rAngle) * (lPoint.getX() - localX) 
				- Math.cos(rAngle) * (lPoint.getY() - localY));
		double x = Math.abs(a * lPoint.getX() + b * lPoint.getY() + c)
				/ Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		double curvature = (2*x)/(Math.pow(L, 2));
		double sCurvature = side * curvature;
		
		return sCurvature;
	}

	public String toString(){
		return "(" + this.x + "," + this.y + ")";
	}
}