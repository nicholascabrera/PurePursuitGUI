/**
 * Author: Nicholas P. Cabrera 
 * Version: 2.3
 * Date: Feb 8, 2020
 */

public class LineWPI{
    
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private PointWPI startPoint;
    private PointWPI endPoint;

    public LineWPI(){
        this.startX = 0;
        this.startY = 0;
        this.endX = 20;
        this.endY = 20;

        this.startPoint = new PointWPI(this.startX, this.startY);
        this.endPoint = new PointWPI(this.endX, this.endY);
    }
    
    public LineWPI(int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

        this.startPoint = new PointWPI(this.startX, this.startY);
        this.endPoint = new PointWPI(this.endX, this.endY);
    }

    public LineWPI(PointWPI startPoint, PointWPI endPoint){
        this.startX = (int)startPoint.getX();
        this.startY = (int)startPoint.getY();
        this.endX = (int)endPoint.getX();
        this.endY = (int)endPoint.getY();

        this.startPoint = new PointWPI(this.startX, this.startY);
        this.endPoint = new PointWPI(this.endX, this.endY);
    }

    public LineWPI(LineWPI someLine){
        this.startPoint = new PointWPI(someLine.getStartPoint());
        this.endPoint = new PointWPI(someLine.getEndPoint());

        this.startX = (int)this.startPoint.getX();
        this.startY = (int)this.startPoint.getY();
        this.endX = (int)this.endPoint.getX();
        this.endY = (int)this.endPoint.getY();

    }

    public PointWPI getStartPoint(){
        return this.startPoint;
    }

    public PointWPI getEndPoint(){
        return this.endPoint;
    }

    public int getStartX(){
        return this.startX;
    }

    public int getStartY(){
        return this.startY;
    }

    public int getEndX(){
        return this.endX;
    }

    public int getEndY(){
        return this.endY;
    }

    public void setStartPoint(PointWPI set){
        this.startPoint = new PointWPI(set);

        this.startX = (int)set.getX();
        this.startY = (int)set.getY();
    }

    public void setEndPoint(PointWPI set){
        this.endPoint = new PointWPI(set);

        this.endX = (int)set.getX();
        this.endY = (int)set.getY();
    }
}