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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * An animated image.
**/
public class Sprite extends Graphic{
	SpriteBase base;
	
    /**
     * The mode of the entity (used to implement different animation cycles).
    **/
    int mode;
    
    /**
     * The current frame of the current cycle.
    **/
    int frame;
    
    /**
     * The counter for the sprite image updater;
    **/
    int updatecount;
    
    /**
     * The speed at which the entity's sprite updates.
    **/
    int updaterate;
    
    /**
     * Load a spritesheet from a file.
     * 
     * @param fname The filename of the spritesheet.
     * @param cols The number of columns.
     * @param rows The number of rows.
     * @param hsep The horizontal separation of each frame.
     * @param vsep The vertical separation of each frame.
     * @param hoff The horizontal offset of the upper left frame.
     * @param voff The vertical offset of the upper left frame.
     */
    Sprite(String fname,int cols,int rows,int hsep,int vsep,int hoff,int voff){
    	base=new SpriteBase(fname,cols,rows,hsep,vsep,hoff,voff);
    }

    /**
     * Load a spritesheet from a file.
     * 
     * @param fname The filename of the spritesheet.
     * @param cols The number of columns.
     * @param rows The number of rows.
     * @param hsep The horizontal separation of each frame.
     * @param vsep The vertical separation of each frame.
     */
    Sprite(String fname,int cols,int rows,int hsep,int vsep){
    	this(fname,cols,rows,hsep,vsep,0,0);
    }

    /**
     * Load a spritesheet from a file.
     * 
     * @param fname The filename of the spritesheet.
     * @param cols The number of columns.
     * @param rows The number of rows.
     */
    Sprite(String fname,int cols,int rows){
    	this(fname,cols,rows,0,0,0,0);
    }

    /**
     * Load a spritesheet from a file.
     * 
     * @param fname The filename of the spritesheet.
     */
    Sprite(String fname){
    	this(fname,1,1,0,0,0,0);
    }

    /**
     * Load a spritesheet from a file.
     * 
     * @param fname The filename of the spritesheet.
     */
    Sprite(SpriteBase b){
    	base=b;
    }

    /**
     * Private helper function that scales a BufferedImage.
     * 
     * @param img The image to scale.
     * @param zoom The amount by which to scale the image.
     * @return The scaled image.
     */
    static BufferedImage scale(BufferedImage img,int zoom){
    	int w=img.getWidth()*zoom;
    	int h=img.getHeight()*zoom;
    	BufferedImage resized=new BufferedImage(w,h,img.getType());
        Graphics2D g=resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.drawImage(img,0,0,w,h,0,0,img.getWidth(),img.getHeight(),null);
        g.dispose();
        return resized;
    }
    
    /**
     * Draw the entity to the graphic.
    *
    * @param g The graphic to which the entity should be drawn.
   **/
   void draw(Graphics g,ImageObserver o,int x,int y){
       base.draw(g,o,x,y,frame,mode);
       //Update the update count/frame
       updatecount+=updaterate;
       if(updatecount>=Octave.FPS){
    	   frame=(frame+1)%base.framespermode;
           updatecount=0;
       }
   }
   
   /**
    * Sets the sprite mode.
    *
    * @param m The new mode.
   **/
   void setMode(int m){
       mode=m;
   }
   
   /**
    * Sets the sprite's update speed.
    * @param s The new update speed (in cycles/update).
    */
   void setSpeed(int s){
	   updaterate=s;
   }
   
   /**
    * Sets the sprite's frame.
    * @param f The new frame.
    */
   void setFrame(int f){
	   frame=f;
   }
   
   int getWidth(){
	   return base.getWidth();
   }
   
   int getHeight(){
	   return base.getHeight();
   }
}
