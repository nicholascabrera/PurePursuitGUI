/**
 * Author: Nicholas P. Cabrera 
 * Version: 2.3 
 * Date: Feb 8, 2020
 */

public class VectorWPI{

    private PointWPI point1;
    private PointWPI point2;
    private double magnitude;
    private double direction;

    public VectorWPI(){
        this.point1 = new PointWPI(0,0);
        this.point2 = new PointWPI(10, 10);
        this.magnitude = this.calculateMagnitude();
        this.direction = this.calculateDirection();
    }

    public VectorWPI(PointWPI p1, PointWPI p2){
        this.point1 = new PointWPI(p1);
        this.point2 = new PointWPI(p2);
        this.magnitude = this.calculateMagnitude();
        this.direction = this.calculateDirection();
    }

    public double calculateMagnitude() {
        double x = point2.getX() - point1.getX();
        double y = point2.getY() - point1.getY();
        return this.magnitude = Math.sqrt((x*x) + (y*y));
    }

    public double calculateDirection() {
        double x = point2.getX() - point1.getX();
        double y = point2.getY() - point1.getY();
        return direction = Math.atan(y/x);
    }

    public double getMagnitude(){
        return this.magnitude;
    }

    public double getDirection(){
        return this.direction;
    }

    public double dot(VectorWPI vector) {
        double theta = Math.abs(getDirection() - vector.getDirection());
        return this.magnitude * vector.getMagnitude() * Math.cos(theta);
    }

}