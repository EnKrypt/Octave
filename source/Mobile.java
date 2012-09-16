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

/**
 * A generic abstract class for all entities that move.
 */
abstract public class Mobile extends Collidable{
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
     * The speed of the entity in any direction (in pixels/cycle).
    **/
    int speed;

    /**
     * The direction of the entity.
     * @see source.Character.RIGHT
     * @see source.Character.LEFT
     * @see source.Character.UP
     * @see source.Character.DOWN
     * @see source.Character.UPRIGHT
     * @see source.Character.DOWNRIGHT
     * @see source.Character.DOWNLEFT
     * @see source.Character.UPLEFT
    **/
    int direction;
    
    /**
     * @param s The entity's sprite (it's assumed mobs have an animation).
     * @param mask The entity's mask.
     * @param maskx The entity's mask x offset.
     * @param masky The entity's mask y offset.
     */
	Mobile(Sprite s,Mask mask,int maskx,int masky){
		super(s,mask,maskx,masky);
	}
	
	/**
	 * Move the entity.
	 */
	void move(){
    	prevx=x;
    	prevy=y;
        switch(direction){
        	//2* to accommodate only one component being changed
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
}
