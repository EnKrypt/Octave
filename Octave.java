import javax.swing.*;
import java.awt.*;

public class Octave extends JFrame{
    
    Map map;    

    public Octave(){
        JFrame frame=new JFrame("Octave - v1.0 Alpha");
        frame.setSize(500,500);
        Map map=new Map();
        frame.setContentPane(map);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]){
        Octave octave=new Octave();
    }
   
}