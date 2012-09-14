/*
Copyright (C) 2012 Arvind Kumar

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

package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Implements he game's conversation box
**/

public class Convobox implements KeyListener{
	
	/**
	 * Array containing different paragraphs to display
	**/
	String[] msgs;
	
	/**
	 * Contains the number of the current displaying character of the paragraph
	**/
	int pos=0;
	
	/**
	 * The index of msgs[] that indicates to the current paragraph
	**/
	int msg=0;
	
	/**
	 * The frame count that controls the speed of the characters to be rendered
	**/
	int count=0;
	
	/**
	 * Flag which represents if there is any more work to be done by the convobox or not
	**/
	boolean done=true;
	
	/**
	 * Flag which represents if the convobox needs to wait for a keypress to continue or not
	**/
	boolean pause=false; 
	
	/**
	 * The speed at which the characters will be displayed.
	**/
	int speed;
	
	/**
	 * The Height of the convobox.
	**/
	int ht;
	
	/**
	 * The Width of the convobox.
	**/
	int wt;
	
	/**
	 * The horizontal offset from the screen.
	**/
	int xoff;
	
	/**
	 * The vertical offset from the screen.
	**/
	int yoff;
	
	/**
	 * Background color for the convobox
	**/
	Color bg=Color.WHITE;
	
	/**
	 * Color of text in convobox
	**/
	Color fg=Color.BLACK;
	
	public Convobox(Color bgc,Color fgc,int xo,int yo,int w,int h){
		xoff=xo;
		yoff=yo;
		wt=w;
		ht=h;
		bg=bgc;
		fg=fgc;
	}
	
	/**
	 * Initiates display of the messages
	 *
	 * @param m The message(s) to be displayed
	 * @param s The speed to display the message
	**/
	public void say(String m,int s){
		msgs=m.split("\t");
		pos=1;
		msg=0;
		count=0;
		done=false;
		speed=s;
		pause=false;
	}
	
	/**
	 * Render the message
	 *
	 * @param g The graphic to which the entity should be drawn.
	**/
	public void draw(Graphics g){
		drawBG(g);
		if (done)
			return;
		count+=speed;
		if (count>=Octave.FPS){
			count=0;
			pos++;
		}
		if (pos>=msgs[msg].length()){
			msg++;
			pause=true;
		}
		if (msg>=msgs.length){
			done=true;
			return;
		}
		if (msgs[msg].charAt(pos)=='\0'){
			pause=true;
		}
		drawText(g,msgs[msg]);
	}
	
	public void keyTyped(KeyEvent e){
	
	}

    public void keyPressed(KeyEvent e){
    	if (e.getKeyCode()==KeyEvent.VK_ENTER){
			pause=false;
			pos=1;
		}
    }

    public void keyReleased(KeyEvent e){
        
    }
	
	/**
	 * Draw out the background
	 *
	 * @param g The graphic to which the entity should be drawn.
	**/
	public void drawBG(Graphics g){
		g.setColor(bg);
		g.fillRect(xoff,yoff,wt,ht);
		g.setColor(Color.BLACK);
		g.drawRect(xoff+10,yoff+10,wt-20,ht-20);
		g.setColor(Color.WHITE);
	}
	
	/**
	 * Draw out the text
	 *
	 * @param g The graphic to which the entity should be drawn.
	**/
	public void drawText(Graphics g,String mes){
		g.setColor(fg);
		g.drawChars(mes.toCharArray(),0,pos,xoff+30,yoff+50);
		g.setColor(Color.WHITE);
	}
}