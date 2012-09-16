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
 * A template for entities.
 */
public class EntityType{
	/**
	 * The entity's base sprite.
	 */
	SpriteBase sprite;
	
	/**
	 * The entity's mask.
	 */
	Mask mask;
	
	/**
	 * List of all entity types.
	 */
	static EntityType[] entities={
	};
	
	/**
	 * @param ID The id of the entity type.
	 * @param s The path of the entity's sprite.
	 * @param m The path of the entity's mask.
	 * @param cols The number of columns in the spritesheet.
	 * @param rows The number of rows in the spritesheet.
	 * @param hsep The hoizontal separation between the frames of a spritesheet.
	 * @param vsep The vertical separation between the frames of a spritesheet.
	 * @param hoff The horizontal offset of the spritesheet.
	 * @param voff The vertical offset of the spritesheet.
	 */
	EntityType(String s,String m,int cols,int rows,int hsep,int vsep,int hoff,int voff){
		sprite=new SpriteBase(s,cols,rows,hsep,vsep,hoff,voff);
		mask=new Mask(m);
	}
}
