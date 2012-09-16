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
 * An generic abstracted base class for all graphical objects.
 */
public abstract class Graphic{
	/**
	 * Draw the Graphic object to a Graphics object at (x,y)
	 * 
	 * @param g The Graphics object.
	 * @param o The image observer.
	 * @param x The x offset.
	 * @param y The y offset.
	 */
	abstract void draw(Graphics g,ImageObserver o,int x,int y);
	
	abstract int getWidth();
	
	abstract int getHeight();
}
