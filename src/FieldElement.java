/**
 * Author: Nicholas P. Cabrera 
 * Version: 2.2 
 * Date: Feb 8, 2020
 */

import java.util.ArrayList;

 public class FieldElement{

    private ArrayList<PointWPI> sides;

    private ArrayList<LineWPI> figure;

    public FieldElement(ArrayList<PointWPI> sides){
        this.sides = sides;

        this.configFigure();
    }

    public void configFigure(){
        LineWPI side;
        for(int i = 0; i < sides.size(); i++){
            if(i > 0){
                side = new LineWPI(sides.get(i), sides.get(i+1));
                figure.add(side);
            }else{
                side = new LineWPI(sides.get(i), sides.get(sides.size() - 1));
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
 }