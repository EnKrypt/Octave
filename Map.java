/*
Copyright (C) 2012 Aravind Kumar

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>

Please note that in the event that any source file or other resource in this project does not include the above header, it should be assumed to be under the same license.
*/

 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Implements the game's map
**/
public class Map extends JPanel implements ActionListener{
    /**
     * The player's character instance.
     *
     * @see com.octave.game.Character
    **/
    Character player;
    
    /**
     * Handles the game step timing.
    **/
    Timer step;

    public Map(){
        player=new Character();
        addKeyListener(player);
        setFocusable(true);
        step=new Timer(Octave.DELAY,this);
        step.start();
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,500,500);
        player.draw(g,this);
    }
    
    public void actionPerformed(ActionEvent e){
        //game step event
        player.move();
        repaint();
        step.start();
    }
}