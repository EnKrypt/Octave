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

 import java.awt.event.*;

/**
 * Implements the player's avatar.
**/
public class Character extends MapEntity implements KeyListener{
	/**
	 * The image speed when the character is walking
	**/
	public static final int WALKSPEED=8;
	
	/**
	 * No direction
	 */
	public static final int NODIR=-1;
	
	/**
	 * The constant representing down.
	 */
	public static final int DOWN=0;
	
	/**
	 * The constant representing left.
	 */
	public static final int LEFT=1;
	
	/**
	 * The constant representing right.
	 */
	public static final int RIGHT=2;
	
	/**
	 * The constant representing up.
	 */
	public static final int UP=3;

    /**
     * The constant representing a direction going down and right.
    **/
    public static final int DOWNRIGHT=4;

    /**
     * The constant representing a direction going down and left.
    **/
    public static final int DOWNLEFT=5;

    /**
     * The constant representing a direction going up and left.
    **/
    public static final int UPLEFT=6;
	
    /**
     * The constant representing a direction going up and right.
    **/
    public static final int UPRIGHT=7;

    /**
     * The speed of the character in any direction (used to calculate movement).
    **/
    int speed;

    /**
     * The direction of the character
     * @see com.octave.game.Character.UPRIGHT
     * @see com.octave.game.Character.DOWNRIGHT
     * @see com.octave.game.Character.DOWNLEFT
     * @see com.octave.game.Character.UPLEFT
    **/
    int direction;
    
    /**
     * Key values (used to calculate direction).
     * 
     * left right up down
     */
    boolean[] keys=new boolean[4];
    
    public Character(){
        super("character.png","character_mask.png",6,26,6,8,0,0,0,0);
    }
    
    public void step(){
    	prevx=x;
    	prevy=y;
        switch(direction){
        	//2* to accommodate only one component being changed
        	//Sqrt of 3 is double. Multiplying a double with an integer will truncate the decimal anyway.
        	case DOWN:
        		y+=speed*2;
        		break;
        	case LEFT:
        		x-=speed*2;
        		break;
        	case RIGHT:
        		x+=speed*2;
        		break;
        	case UP:
        		y-=speed*2;
        		break;
            case UPRIGHT:
                x+=2*speed;
                y-=speed;
                break;
            case DOWNRIGHT:
                x+=2*speed;
                y+=speed;
                break;
            case DOWNLEFT:
                x-=2*speed;
                y+=speed;
                break;
            case UPLEFT:
                x-=2*speed;
                y-=speed;
                break;
        }
    }
    
    /**
     * Start moving in a given direction.
     * 
     * @param d The new direction.
    **/
    public void goDirection(int d){
    	if(d==NODIR){
    		speed=0;
    		imgspeed=0;
    		frame=4;
    	}
    	else{
	        direction=d;
	        speed=2;
	        imgspeed=WALKSPEED;
	        setMode(d);
    	}
    }
    
    /**
     * Calculates the direction to go in.
     */
    int getDirection(){
        int h=(keys[1]?1:0)-(keys[0]?1:0);
        int v=(keys[3]?1:0)-(keys[2]?1:0);
        if(h==0 && v==0){
        	return NODIR;
        }
        if(h==0 && v<0){
        	return UP;
        }
        else if(h>0 && v<0){
        	return UPRIGHT;
        }
        else if(h>0 && v==0){
        	return RIGHT;
        }
        else if(h>0 && v>0){
        	return DOWNRIGHT;
        }
        else if(h==0 && v>0){
        	return DOWN;
        }
        else if(h<0 && v>0){
        	return DOWNLEFT;
        }
        else if(h<0 && v==0){
        	return LEFT;
        }
        else if(h<0 && v<0){
        	return UPLEFT;
        }
        return NODIR;
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
    
    public void collide(MapEntity other){
    	if(other instanceof Block){
    		//Reset location (should resolve collision)
    		x=prevx;
    		y=prevy;
    	}
    }
}