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
     * The constant representing a direction going up and right.
    **/
    public static final int UPRIGHT=0;

    /**
     * The constant representing a direction going down and right.
    **/
    public static final int DOWNRIGHT=1;

    /**
     * The constant representing a direction going down and left.
    **/
    public static final int DOWNLEFT=2;

    /**
     * The constant representing a direction going up and left.
    **/
    public static final int UPLEFT=3;

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
     * @param url The "URL" (or local file) of the character's sprite.
    **/
    public Character(){
        super("Character.png",3,4,0);
    }
    
    /**
     * Move the character in the direction it's moving by its speed.
    **/
    public void move(){
        switch(direction){
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
        direction=d;
        speed=2;
        imgspeed=WALKSPEED;
        setMode(d);
    }
    
    /**
     * Stop moving in a given direction.
     * 
     * @param d The direction
    **/
    public void ungoDirection(int d){
        if(direction==d){
           	speed=0;
            imgspeed=0;
            frame=1;
        }
    }
    
    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                goDirection(Character.UPLEFT);
                break;
            case KeyEvent.VK_UP:
                goDirection(Character.UPRIGHT);
                break;
            case KeyEvent.VK_RIGHT:
                goDirection(Character.DOWNRIGHT);
                break;
            case KeyEvent.VK_DOWN:
                goDirection(Character.DOWNLEFT);
                break;
            default:
                return;
        }
    }

    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            	ungoDirection(Character.UPLEFT);
                break;
            case KeyEvent.VK_UP:
            	ungoDirection(Character.UPRIGHT);
                break;
            case KeyEvent.VK_RIGHT:
            	ungoDirection(Character.DOWNRIGHT);
                break;
            case KeyEvent.VK_DOWN:
            	ungoDirection(Character.DOWNLEFT);
                break;
            default:
                return;
        }
    }
}