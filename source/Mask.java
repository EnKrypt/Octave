package source;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class Mask extends Graphic{
	/**
	 * The wrapped image for the Mask object.
	 */
	BufferedImage mask;

	/**
	 * @param s The path to the mask's image file.
	 */
	Mask(String s){
        try{
			if(Octave.codeBase!=null)
				mask=ImageIO.read(new URL(Octave.codeBase,Octave.IMGROOT+s));
			else
				mask=ImageIO.read(new File(Octave.IMGROOT+s));
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

	@Override
	void draw(Graphics g,ImageObserver o,int x,int y) {
		g.drawImage(mask,x,y,o);
	}

	@Override
	int getWidth(){
		return mask.getWidth();
	}

	@Override
	int getHeight(){
		return mask.getHeight();
	}
	
	int getRGB(int x,int y){
		return mask.getRGB(x,y);
	}
}
