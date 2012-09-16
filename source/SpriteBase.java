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

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteBase{
    /**
     * A list of the sprite frames.
    **/
    BufferedImage frames[];
    
    /**
     * The number of horizontal images in frames.
    **/
    int framespermode;
    
    /**
     * The number of vertical images in frames.
    **/
    int nummodes;

    /**
     * The width of a frame.
     */
    int fw;
    
    /**
     * The height of a frame.
     */
    int fh;

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
    SpriteBase(String fname,int cols,int rows,int hsep,int vsep,int hoff,int voff){
    	BufferedImage img=null;
        try{
			if (Octave.codeBase!=null)
				img=ImageIO.read(new URL(Octave.codeBase,Octave.IMGROOT+fname));
			else
				img=ImageIO.read(new File(Octave.IMGROOT+fname));
			if(img==null){
				throw new IOException("Sprite");
			}
        }
        catch(IOException e){
			System.out.println("Sprite resource not found.");
			System.exit(0);
		}
        img=Sprite.scale(img,Octave.SCALE);
        //member setting
        framespermode=cols;
        nummodes=rows;
        frames=new BufferedImage[cols*rows];
        //local zooming
        hsep*=Octave.SCALE;
        vsep*=Octave.SCALE;
        hoff*=Octave.SCALE;
        voff*=Octave.SCALE;
        //frame width and height
        fw=(img.getWidth()-hoff)/cols-hsep;
        fh=(img.getHeight()-voff)/rows-vsep;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                frames[i*cols+j]=img.getSubimage(hoff+j*(fw+hsep),voff+i*(fh+vsep),fw,fh);
            }
        }
    }

	/**
	 * Gets the width of all frames.
	 * 
	 * @return The width of the sprite.
	 */
	int getWidth() {
		return fw;
	}

	/**
	 * Gets the height of all frames.
	 * 
	 * @return The height of the sprite.
	 */
	int getHeight() {
		return fh;
	}
	
	/**
	 * Draws the sprite on a Graphics object 
	 */
	void draw(Graphics g,ImageObserver o,int x,int y,int frame,int mode){
		g.drawImage(frames[mode*framespermode+frame],x,y,o);
	}
}
