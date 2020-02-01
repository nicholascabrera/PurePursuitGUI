import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LinePanel extends JPanel implements ActionListener{

    private JButton smooth;

    private static final long serialVersionUID = 8298251132940870997L;
    private MouseHandler mouseHandler = new MouseHandler();
    private ArrayList<Points> points = new ArrayList<Points>();
    private Path path = new Path();

    public LinePanel(){
        this.setPreferredSize(new Dimension(859, 500));

        //dimension of the field scaled down by 2 is 799 x 410.

        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);

        this.smooth = new JButton("Smooth Curve");
        this.smooth.setMnemonic(KeyEvent.VK_S);
        this.smooth.setActionCommand("smooth");
        this.smooth.addActionListener(this);
        this.smooth.setToolTipText("Click this button to smooth the drawn path.");

        add(this.smooth);
    }

    public void actionPerformed(ActionEvent e) {
        if("smooth".equals(e.getActionCommand())){

            double weight_smooth = 0.8; //anywhere between 0.75 and 0.98
            double tol = 0.001;
            double a = 1 - weight_smooth;
            Path p = new Path(points);
            int[] numPoints = p.numPointForArray(20);
            
            path = new Path(p.generatePath(numPoints));
            path = path.smoother( a, weight_smooth, tol);
            points = path.pathToArrayList();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g.drawRect(30, 40, 799, 410);
        g2d.setColor(Color.blue);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        for(int i = 0; i < points.size(); i++) {
            if(i > 0){
                Points p1 = new Points(points.get(i));
                Points p2 = new Points(points.get(i-1));
                g.fillOval((int)p1.getX() - 3, (int)p1.getY() - 3, 6, 6);
                g.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
            }
            else{
                Points p = new Points(points.get(i));
                g.fillOval((int)p.getX() - 3, (int)p.getY() - 3, 6, 6);
            }
        }
    }
    
    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            Points point = new Points(p);

            points.add(point);
            repaint();
        }
    }    

    private void display() {
        JFrame f = new JFrame("LinePanel");
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