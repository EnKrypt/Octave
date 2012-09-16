/*
Copyright (C) 2012 Arvind Kumar

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation,either version 3 of the License,or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not,see <http://www.gnu.org/licenses/>

Please note that in the event that any source file or other resource in this project does not include the above header,it should be assumed to be under the same license.
*/

package source;

import java.awt.*;
import java.awt.image.*;

/**
 * A generic abstract class for all entities that handle collisions.
 */
public class Collidable extends MapEntity{	
	/**
	 * The entity's collision mask.
	**/
	Mask mask;
    
    /**
     * The mask's horizontal offset from the entity's x position component.
     */
    int mx;
    
    /**
     * The mask's vertical offset from the entity's y position component.
     */
    int my;
    
    /**
     * Whether or not the entity is solid.
     */
    boolean solid;
    
    Collidable(Graphic g,Mask m,int maskx,int masky){
    	super(g);
    	mask=m;
        mx=maskx*Octave.SCALE;
        my=masky*Octave.SCALE;
    }
    
    //Overloaded to allow for drawing mask when debugging
    void draw(Graphics g,ImageObserver o){
    	super.draw(g,o);
        //mask.draw(g,o,x+mx,y+my);
    }
	
	/**
	 * Overloadable method called when entity collides with another.
	 * 
	 * @param other The other entity involved in the collision.
	 */
	void collide(Collidable other){}
}
