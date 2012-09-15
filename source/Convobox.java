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

/**
 * Implements the game's conversation box.
**/
public class Convobox implements KeyListener{
	/**
	 * The padding of the convobox from the sides of the screen.
	 */
	static final int PADDING=8;
	
	/**
	 * The value of the key that continues a conversation.
	 */
	static final int CONTKEY=KeyEvent.VK_ENTER;
	
	/**
	 * Font of the text in the convobox.
	**/
	Font font;
	
	/**
	 * The array containing different paragraphs to display.
	**/
	String[] msgs;
	
	/**
	 * The number of the current displaying character of the paragraph.
	**/
	int pos=0;
	
	/**
	 * The index of msgs[] that indicates the current paragraph.
	**/
	int msg=0;
	
	/**
	 * The frame count that controls the speed of the characters to be rendered.
	**/
	int count=0;
	
	/**
	 * Flag which represents if there is any more work to be done by the convobox.
	**/
	boolean done=true;
	
	/**
	 * Flag which represents if the convobox is waiting for a key press.
	**/
	boolean pause=false; 
	
	/**
	 * The speed at which the characters will be displayed.
	**/
	int speed;
	
	/**
	 * Background color for the convobox.
	**/
	Color bg=Color.WHITE;
	
	/**
	 * Color of text in convobox.
	**/
	Color fg=Color.BLACK;
	
	/**
	 * The map to which the convobox is being rendered.
	 */
	Map map;
	
	/**
	 * @param m The map to which the convobox is being rendered.
	 * @param s The speed of the characters being rendered.
	 */
	public Convobox(Map m,int s){
		map=m;
		speed=s;
		m.addKeyListener(this);
	}
	
	/**
	 * @param m The map to which the convobox is being rendered.
	 */
	public Convobox(Map m){
		map=m;
		speed=Octave.FPS;
		m.addKeyListener(this);
	}
	
	/**
	 * Get the width of the convobox.
	 * 
	 * @return The convobox's width.
	 */
	int getWidth(){
		return map.getWidth()-PADDING*2;
	}
	
	/**
	 * Get the height of the convobox.
	 * 
	 * @return The convobox's height.
	 */
	int getHeight(){
		return map.getHeight()/3-PADDING;
	}
	
	/**
	 * Returns the vertical offset of the convobox.
	 * 
	 * @return The vertical offset.
	 */
	int getVOff(){
		return map.getHeight()*2/3;
	}
	
	/**
	 * Returns the horizontal offset of the convobox.
	 * 
	 * @return The horizontal offset.
	 */
	int getHOff(){
		return PADDING;
	}
	
	/**
	 * Initiates the conversation.
	 *
	 * @param m The message(s) to be displayed.
	**/
	public void say(String m){
		msgs=m.split("\t");
		pos=1;
		msg=0;
		count=0;
		done=false;
		pause=false;
	}
	
	/**
	 * Initiates the conversation with a given speed.
	 *
	 * @param m The message(s) to be displayed.
	 * @param s The speed of the message(s).
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
		//Don't render anything if it's done and not pausing
		if(done && !pause){
			return;
		}
		//Don't render anything if there's no more messages
		if(msg>=msgs.length){
			return;
		}
		drawBG(g);
		//Increment count and check if it's time to add another character
		count+=speed;
		if(count>=Octave.FPS){
			count=0;
			++pos;
		}
		//Pause if the current message is done
		if(pos>=msgs[msg].length()){
			pause=true;
			//makes sure pos stays within range
			pos-=pos%msgs[msg].length();
		}
		//Be done if there are no more messages
		if(msg>=msgs.length){
			done=true;
		}
		//Draw the text up to the current position (ignoring \0)
		drawText(g,msgs[msg].substring(0,pos).replace("\0",""));
	}
	
	public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e){
    	if(e.getKeyCode()==CONTKEY){
    		if(pause){
				pause=false;
				pos=1;
				++msg;
    		}
		}
    }

    public void keyReleased(KeyEvent e){}
	
	/**
	 * Draw out the background
	 *
	 * @param g The graphic to which the entity should be drawn.
	**/
	public void drawBG(Graphics g){
		int x=getHOff(),y=getVOff();
		int w=getWidth(),h=getHeight();
		g.setColor(bg);
		g.fillRect(x,y,w,h);
		g.setColor(fg);
		g.drawRect(x+10,y+10,w-20,h-20);
	}
	
	/**
	 * Draw out the text
	 *
	 * @param g The graphic to which the entity should be drawn.
	 * @param m The message to draw.
	**/
	public void drawText(Graphics g,String m){
		g.setFont(font);
		g.setColor(fg);
		g.drawString(m,getHOff()+25,getVOff()+35);
	}
}