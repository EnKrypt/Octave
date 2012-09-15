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
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
import java.io.File;
import java.net.*;

/**
 * Superclass of any object that the player can interact with on the screen.
**/
public class MapEntity{
	/**
	 * The entity's sprite.
	 */
	Sprite sprite;
	
	/**
	 * The entity's collision mask.
	**/
	BufferedImage mask;
    
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
    
    /**
     * @param sprite A loaded sprite.
	 * @param cmask The path to the entity's collision mask.
	 * @param maskx The x offset of the mask over the sprite.
	 * @param masky The y offset of the mask over the sprite.
    **/
    MapEntity(Sprite s,String cmask,int maskx,int masky){
    	sprite=s;
        mx=maskx*Octave.SCALE;
        my=masky*Octave.SCALE;
        try{
			if (Octave.codeBase!=null)
				mask=ImageIO.read(new URL(Octave.codeBase,Octave.IMGROOT+cmask));
			else
				mask=ImageIO.read(new File(Octave.IMGROOT+cmask));
			if(mask==null){
				throw new IOException("Mask");
			}
        }
        catch(IOException e){
			System.out.println("Mask resource not found.");
			System.exit(0);
		}
        mask=Sprite.scale(mask,Octave.SCALE);
    }
    
    /**
     * @param sprite The filename to a sprite.
	 * @param cmask The path to the entity's collision mask.
	 * @param maskx The x offset of the mask over the sprite.
	 * @param masky The y offset of the mask over the sprite.
    **/
    MapEntity(String s,String cmask,int maskx,int masky){
    	this(new Sprite(s),cmask,maskx,masky);
    }
    
    /**
     * @param sprite A loaded sprite.
	 * @param cmask The path to the entity's collision mask.
    **/
    MapEntity(Sprite s,String cmask){
    	this(s,cmask,0,0);
    }
    
    /**
     * @param sprite The filename to a sprite.
	 * @param cmask The path to the entity's collision mask.
    **/
    MapEntity(String s,String cmask){
    	this(s,cmask,0,0);
    }
    
    /**
     * Draw the entity to the graphic.
     *
     * @param g The graphic to which the entity should be drawn.
    **/
    void draw(Graphics g,ImageObserver o){
    	sprite.draw(g,o,x,y);
        //g.drawImage(mask,x+mx,y+my,o);
    }
	
	/**
	 * Overloadable method called during a step event.
	**/
	void step(){}
	
	/**
	 * Overloadable method called when entity collides with another.
	 * 
	 * @param other The other entity involved in the collision.
	 */
	void collide(MapEntity other){}
}