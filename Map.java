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

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implements the game's map
**/
public class Map extends JPanel{
	/**
	 * To make Eclipse shut up.
	 */
	static final long serialVersionUID=1001;
    /**
     * The player's character instance.
     *
     * @see Character
    **/
    Character player;
	
	/**
	 * A list of entities to render to the map.
	**/
	ArrayList<MapEntity> entities;

    public Map(){
        player=new Character();
        addKeyListener(player);
		entities=new ArrayList<MapEntity>(1);
		add(player);
		add(new Block("whitebox.png",160,160));
        setFocusable(true);
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,500,500);
		for(MapEntity entity:entities){
			entity.draw(g,this);
		}
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
	 */
	public void add(MapEntity e){
		entities.add(e);
	}
}