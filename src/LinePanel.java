/**
 * Author: Nicholas P. Cabrera 
 * Version: 2.2 
 * Date: Feb 8, 2020
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LinePanel extends JPanel implements ActionListener{


    private static final long serialVersionUID = 1L;

    private JButton smooth;
    private JButton clear;

    private JLabel pointLabel;

    private MouseHandler mouseHandler = new MouseHandler();

    private ArrayList<PointWPI> points = new ArrayList<PointWPI>();
    private ArrayList<PointWPI> primaryPoints = new ArrayList<PointWPI>();
    
    private Path path = new Path();

    public LinePanel(){
        super(new GridLayout(15,3));

        this.setPreferredSize(new Dimension(700, 450));

        //dimension of the field not scaled is about 629in x 323in(this is truncating the last 1/4in).
        //dimension of the window is height + 60 x height + 90(for aesthetic reasons)(I know its ugly, shut up)

        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);

        this.smooth = new JButton("Smooth Curve");
        this.smooth.setMnemonic(KeyEvent.VK_S);
        this.smooth.setActionCommand("smooth");
        this.smooth.addActionListener(this);
        this.smooth.setToolTipText("Click this button to smooth the drawn path, or Alt-S");
        
        add(this.smooth);

        this.clear = new JButton("Clear Field");
        this.clear.setMnemonic(KeyEvent.VK_C);
        this.clear.setActionCommand("clear");
        this.clear.addActionListener(this);
        this.smooth.setToolTipText("Click this button to clear the field, or Alt-C");

        add(this.clear);

        this.pointLabel = new JLabel("");
        this.pointLabel.setFont(new Font("Verdana",1,15));

        add(this.pointLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if("smooth".equals(e.getActionCommand()) && this.points.size() > 0){
            double weight_smooth = 0.8;                         //anywhere between 0.75 and 0.98
            double tol = 0.001;                                 //the tolerance of values
            double a = 1 - weight_smooth;                       //i don't really know
            Path p = new Path(this.points);                     //instantiating a new path, with the same points as the input
            int[] numPoints = p.numPointForArray(12);           //goto Path.java--> numPointForArray() for description
            
            this.path = new Path(p.generatePath(numPoints));    //goto Path.java--> generatePath() for description
            this.path = path.smoother(a, weight_smooth, tol);   //goto Path.java--> smoother() for description
            this.points.clear();
            this.points = path.pathToArrayList();                
            //converts the path from a Path object back into an ArrayList of points for easy repainting.
            repaint();                                      //repaint using the new smoothed path
        }
        if("clear".equals(e.getActionCommand()) && this.points.size() > 0){
            this.points.clear();
            this.primaryPoints.clear();
            this.pointLabel.setText("");
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //setting draw preferences
        g.setColor(Color.black);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));

        //drawing the field
        g.drawRect(35, 100, 629, 323); //perimeter of the field

        //drawing the points the user inputs
        for(int i = 0; i < points.size(); i++) {    
            /*interate through all points and 
            redraw everytime a new point is added*/            
            if(i > 0){                                                      //if this is not the first point
                //draw a line between the point before and the current point
                PointWPI p1 = new PointWPI(points.get(i));                      //get the current point
                PointWPI p2 = new PointWPI(points.get(i-1));                    //get the last point drawn
                g.fillOval((int)p1.getX() - 3, (int)p1.getY() - 3, 6, 6);   //drawing the point as a small circle
                //draw a line between the two points
                g.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
            }
            else{                                                           //if it is the first point
                //just draw the point, no lines
                PointWPI p = new PointWPI(points.get(i));                       //getting the point
                g.fillOval((int)p.getX() - 3, (int)p.getY() - 3, 6, 6);     //drawing the point as a small circle
            }
        }

        for(int i = 0; i < this.primaryPoints.size(); i++){
            PointWPI p = new PointWPI(this.primaryPoints.get(i));

            if(i > 0){
                if(((int)p.getX() == (int)this.primaryPoints.get(i-1).getX()) && ((int)p.getY() == (int)this.primaryPoints.get(i-1).getY())){
                    //do nothing
                }
                else{
                    this.pointLabel.setText(pointLabel.getText() + primaryPoints.get(i).toString() + ", ");
                }
            }
            else{
                this.pointLabel.setText(primaryPoints.get(i).toString() + ", ");
            }
        }
    }
    
    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            PointWPI point = new PointWPI(p);
            points.add(point);
            int X = (int)p.getX() - 30;
            int Y = (int)p.getY() - 100;

            PointWPI temp = new PointWPI(point);
            temp.setX(X);
            temp.setY(Y);
            primaryPoints.add(temp);
            repaint();
        }
    }    

    private void display() {
        JFrame f = new JFrame("Pure Pursuit UI - Field to Scale");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LinePanel().display();
            }
        });
    }
}