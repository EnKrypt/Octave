package source;
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

 
import java.awt.image.*;
import java.awt.*;

/**
 * Superclass of any object that the player can interact with on the screen.
**/
abstract public class MapEntity{
	/**
	 * The entity's sprite.
	 */
	Graphic sprite;
    
    /**
     * The x component of the entity's position.
    **/
    int x;
    
    /**
     * The y component of the entity's position.
    **/
    int y;
    
    /**
     * The last x value.
     */
    int prevx;
    
    /**
     * The last y value.
     */
    int prevy;
    
    /**
     * @param sprite A loaded sprite.
	 * @param cmask The path to the entity's collision mask.
	 * @param maskx The x offset of the mask over the sprite.
	 * @param masky The y offset of the mask over the sprite.
    **/
    MapEntity(Graphic g){
    	sprite=g;
    }
    
    /**
     * Draw the entity to the graphic.
     *
     * @param g The graphic to which the entity should be drawn.
    **/
    void draw(Graphics g,ImageObserver o){
    	sprite.draw(g,o,x,y);
    }
	
	/**
	 * Overloadable method called during a step event.
	**/
	void step(){}
}