import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Map extends JPanel implements KeyListener{
    
    Char control;
    
    public Map(){
        setFocusable(true);
        addKeyListener(this);
        control=new Char("char.png");
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,500,500);
        g.drawImage(Toolkit.getDefaultToolkit().getImage(control.image),control.x,control.y,this);
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==37){ //left
            control.changeX();
        }
        if (e.getKeyCode()==38){ //up
            control.changeY();
        }
        if (e.getKeyCode()==39){ //right
            control.changeX();
        }
        if (e.getKeyCode()==40){ //down
            control.changeY();
        }
        repaint();
    }
    public void keyReleased(KeyEvent e) {

    }
}