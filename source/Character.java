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

import java.awt.event.*;

/**
 * Implements the player's avatar.
**/
public class Character extends Mobile implements KeyListener{
	/**
	 * The image speed when the character is walking
	**/
	public static final int WALKSPEED=8;
    
    /**
     * Key values (used to calculate direction).
     * 
     * left right up down
     */
    boolean[] keys=new boolean[4];
    
    public Character(int x,int y){
        super(new Sprite("character.png",3,8),new Mask("character_mask.png"),9,26);
        this.x=x;
        this.y=y;
		solid=true;
		((Sprite)sprite).setFrame(1);
    }
    
    /**
     * Start moving in a given direction.
     * 
     * @param d The new direction.
    **/
    public void goDirection(int d){
    	if(d==NODIR){
    		speed=0;
    		((Sprite)sprite).setSpeed(0);
    		((Sprite)sprite).setFrame(1);
    	}
    	else{
	        direction=d;
	        speed=2;
	        ((Sprite)sprite).setSpeed(WALKSPEED);
	        ((Sprite)sprite).setMode(d);
    	}
    }
    
    /**
     * Calculates the direction to go in.
     */
    int getDirection(){
        int h=(keys[1]?1:0)-(keys[0]?1:0);
        int v=(keys[3]?1:0)-(keys[2]?1:0);
        if(h==0 && v==0){
        	return Mobile.NODIR;
        }
        if(h==0 && v<0){
        	return Mobile.UP;
        }
        else if(h>0 && v<0){
        	return Mobile.UPRIGHT;
        }
        else if(h>0 && v==0){
        	return Mobile.RIGHT;
        }
        else if(h>0 && v>0){
        	return Mobile.DOWNRIGHT;
        }
        else if(h==0 && v>0){
        	return Mobile.DOWN;
        }
        else if(h<0 && v>0){
        	return Mobile.DOWNLEFT;
        }
        else if(h<0 && v==0){
        	return Mobile.LEFT;
        }
        else if(h<0 && v<0){
        	return Mobile.UPLEFT;
        }
        return Mobile.NODIR;
    }
    
    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e){
    	//horizontal and vertical
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            	keys[0]=true;
                break;
            case KeyEvent.VK_UP:
            	keys[2]=true;
                break;
            case KeyEvent.VK_RIGHT:
            	keys[1]=true;
                break;
            case KeyEvent.VK_DOWN:
            	keys[3]=true;
                break;
            default:
                return;
        }
        goDirection(getDirection());
    }

    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
	        case KeyEvent.VK_LEFT:
	        	keys[0]=false;
	            break;
	        case KeyEvent.VK_UP:
	        	keys[2]=false;
	            break;
	        case KeyEvent.VK_RIGHT:
	        	keys[1]=false;
	            break;
	        case KeyEvent.VK_DOWN:
	        	keys[3]=false;
	            break;
            default:
                return;
        }
        goDirection(getDirection());
    }
    
    public void step(){
    	move();
    }
    
    public void collide(Collidable other){
    	if(other.solid){
    		//Reset location (should resolve collision)
    		x=prevx;
    		y=prevy;
    	}
    }
}