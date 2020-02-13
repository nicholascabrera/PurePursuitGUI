/**
 * Author: Nicholas P. Cabrera 
 * Version: 2.4
 * Date: Feb 12, 2020
 */

import java.awt.Color;
import java.util.ArrayList;

 public class FieldElement{

    private ArrayList<PointWPI> sides = new ArrayList<PointWPI>();
    
    private int x, y;

    private ArrayList<LineWPI> figure = new ArrayList<LineWPI>();

    private Color myColor;

    public FieldElement(){
    }

    public FieldElement(ArrayList<PointWPI> sides, int x, int y){
        this.x = x;
        this.y = y;

        this.sides = sides;

        this.configFigure();
    }

    public FieldElement(ArrayList<PointWPI> sides, PointWPI location, Color color){
        this.x = (int)location.getX();
        this.y = (int)location.getY();

        this.sides = sides;

        this.myColor = color;

        this.configFigure();
    }

    public FieldElement(PointWPI[] side, PointWPI location, Color color){
        this.x = (int)location.getX();
        this.y = (int)location.getY();

        for(PointWPI point: side){
            this.sides.add(point);
        }

        this.myColor = color;

        this.configFigure();
    }

    public FieldElement(ArrayList<LineWPI> someFigure){
        this.figure = someFigure;
    }

    public void configFigure(){
        LineWPI side;
        for(int i = 0; i < sides.size(); i++){
            if(i > 0){
                side = new LineWPI((int)(sides.get(i).getX() + this.x), (int)(sides.get(i).getY() + this.y), (int)(sides.get(i-1).getX() + this.x), (int)(sides.get(i-1).getY() + this.y));
                figure.add(side);
            }else{
                side = new LineWPI((int)(sides.get(i).getX() + this.x), (int)(sides.get(i).getY() + this.y), (int)(sides.get(sides.size() - 1).getX() + this.x), (int)(sides.get(sides.size() - 1).getY() + this.y));
                figure.add(side);
            }
        }
    }

    public void setSides(ArrayList<PointWPI> sides){
        this.sides = sides;
        this.configFigure();
    }

    public ArrayList<PointWPI> getSides(){
        return this.sides;
    }

    public ArrayList<LineWPI> getFigure(){
        return this.figure;
    }

    public void setColor(Color color){
        this.myColor = color;
    }

    public Color getColor(){
        return this.myColor;
    }
 }