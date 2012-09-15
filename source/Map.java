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

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implements the game's map
**/
public class Map extends JPanel{
	static final long serialVersionUID=1001;
    /**
     * The player's character instance.
     *
     * @see Character
    **/
    Character player;
	
	/**
	 * The convobox instance.
	**/
	Convobox cbox;
	
	/**
	 * A list of entities to render to the map.
	**/
	ArrayList<MapEntity> entities;

    public Map(){
		entities=new ArrayList<MapEntity>(1);
        setFocusable(true);
        loadPlayground();
    }
    
    public void loadPlayground(){
    	Octave.game.setSize(480,400);
        player=new Character(120,90);
		cbox=new Convobox(this);
        addKeyListener(player);
		add(player);
    	int s=13;
    	int wh=18;
    	add(new Block("bluebox.png",240-18,0));
		add(new Block("greenbox.png",206,200));
    	for(int i=0;i<s;++i){
    		add(new Block("bluebox.png",240-wh-i*16,i*8));
    		add(new Block("whitebox.png",240-wh+i*16,i*8));
    	}
    	for(int i=1;i<s;++i){
    		add(new Block("redbox.png",i*16-3,8*s+i*8-8));
    		add(new Block("greenbox.png",480-i*16-65,8*s+i*8-8));
    	}
    	add(new MapEntity(new Sprite("tree1.png"),"tree1_mask.png",21,55),480/2-61,270/2-81).solid=true;
    	add(new MapEntity(new Sprite("rock1.png"),"rock1_mask.png",0,14),240-33,16).solid=true;
    	add(new TileEntity("rug.png"),60,270/2-17);
    	add(new TileEntity("helloworld.png"){
    		void collide(MapEntity other){
    			if(other==player){
    				System.out.println("Hello world!");
    			}
    		}
    	},320,270/2-32);
		cbox.say("Octave loaded.\0\0\0.\0\0\0.\0\0\0\0\0 Have fun!\tMessage 2.",20);
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,500,500);
		for(MapEntity entity:entities){
			entity.draw(g,this);
		}
		cbox.draw(g);
    }
    
	/**
	 * Go through one game step.
	**/
	public void step(){
		MapEntity o1,o2;
		Collections.sort(entities,new RenderOrder());
		for(int i=0;i<entities.size();i++){
			o1=entities.get(i);
			o1.step();
			//Check for collisions
			for(int j=i+1;j<entities.size();j++){
				o2=entities.get(j);
				if(CollisionEngine.pixel_collision(o1,o2)){
					o1.collide(o2);
					o2.collide(o1);
				}
			}
		}
        repaint();
    }
	
	/**
	 * Add an entity to the map.
	 * 
	 * @param e The MapEntity to add.
	 * @return The entity added.
	 */
	public MapEntity add(MapEntity e){
		entities.add(e);
		return e;
	}
	
	/**
	 * Add an entity to the map at a location.
	 * 
	 * @param e The MapEntity to add.
	 * @param x The x position component.
	 * @param y The y position component.
	 * @return The entity added.
	 */
	public MapEntity add(MapEntity e,int x,int y){
		entities.add(e);
		e.x=x;
		e.y=y;
		return e;
	}
}