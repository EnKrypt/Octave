package source;
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


import java.awt.Rectangle;

/**
 * Wraps all the collision-detection methods in one neat package.
 */
public class CollisionEngine{
	/**
	 * Returns whether or not the two entities have intersecting boundaries
	 * 
	 * This is also used with pixel_collision to ensure there even can be one.
	 * 
	 * @param o1 The first entity to compare.
	 * @param o2 The second entity to check.
	 * @return True if they're intersecting, false otherwise.
	 */
	public static boolean bounds_collision(MapEntity o1,MapEntity o2){
		return new Rectangle(o1.x+o1.mx,o1.y+o1.my,o1.mask.getWidth(),o1.mask.getHeight()).intersects(
			new Rectangle(o2.x+o2.mx,o2.y+o2.my,o2.mask.getWidth(),o2.mask.getHeight())
		);
	}
	
	/**
	 * Returns whether or not the two entities have collided (pixel-perfect)
	 * 
	 * @param o1 The first entity to compare.
	 * @param o2 The second entity to compare.
	 * @return True if they're collided, false otherwise.
	 */
	public static boolean pixel_collision(MapEntity o1,MapEntity o2){
		if(!bounds_collision(o1,o2)){
			return false;
		}
		Rectangle r=new Rectangle(o1.x+o1.mx,o1.y+o1.my,o1.mask.getWidth(),o1.mask.getHeight()).intersection(
			new Rectangle(o2.x+o2.mx,o2.y+o2.my,o2.mask.getWidth(),o2.mask.getHeight())
		);
		for(int y=0;y<r.height;++y){
			for(int x=0;x<r.width;++x){
				//shift to get alpha value
				//if neither bit is transparent at that position, it must be a collision.
				if(o1.mask.getRGB(r.x+x-o1.x-o1.mx,r.y+y-o1.y-o1.my)>>>24!=0 &&
					o2.mask.getRGB(r.x+x-o2.x-o2.mx,r.y+y-o2.y-o2.my)>>>24!=0){
					return true;
				}
			}
		}
		return false;
	}
}
