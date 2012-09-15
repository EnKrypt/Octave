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
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class MapEditor extends JFrame{
	static final long serialVersionUID=1002;
	
	/**
	 * The current grid mode;
	 */
	int gridmode;
	
	/**
	 * The type to place.
	 */
	int type;
	
	/**
	 * The width width of a grid cell.
	 */
	int cw=16;
	
	/**
	 * The height of a grid cell.
	 */
	int ch=16;
	
	/**
	 * The top element.
	 */
	JPanel top;
	
	/**
	 * The center element.
	 */
	JPanel center;
	
	/**
	 * The right element.
	 */
	JList<String> right;
	
	/**
	 * The bottom element.
	 */
	JPanel bottom;
	
	/**
	 * The grid cells' width.
	 */
	JTextField cellw;
	
	/**
	 * The grid cells' height.
	 */
	JTextField cellh;
	
	/**
	 * The selected values for the toggle groups.
	 */
	public void toggle(int id,int which){
		if(id==0){
			gridmode=which;
		}
		else if(id==1){
			type=which;
		}
	}
	
	JToggleButton imageToggleButton(String fname){
		BufferedImage img=null;
		try{
			img=ImageIO.read(new File(Octave.ICONROOT+fname));
		}
		catch(IOException e){
			System.out.println("Failed to load icon for image button.");
			System.exit(0);
		}
		JToggleButton button=new JToggleButton(new ImageIcon(img));
		return button;
	}
	
	JPanel createTop(){
		top=new JPanel();
		top.setLayout(new BoxLayout(top,BoxLayout.LINE_AXIS));
		top.setPreferredSize(new Dimension(0,32));
		
		JToggleButton nogrid=imageToggleButton("nogrid.png");
		JToggleButton cartesian=imageToggleButton("cartesian.png");
		JToggleButton isometric=imageToggleButton("isometric.png");
		ToggleGroup gridgroup=new ToggleGroup(0,new JToggleButton[]{nogrid,cartesian,isometric},this);
		nogrid.setSelected(true);
		nogrid.addActionListener(gridgroup);
		cartesian.addActionListener(gridgroup);
		isometric.addActionListener(gridgroup);
		
		JToggleButton tile=imageToggleButton("tile.png");
		JToggleButton entity=imageToggleButton("entity.png");
		ToggleGroup typegroup=new ToggleGroup(1,new JToggleButton[]{tile,entity},this);
		tile.setSelected(true);
		tile.addActionListener(typegroup);
		entity.addActionListener(typegroup);
		
		top.add(nogrid);
		top.add(cartesian);
		top.add(isometric);
		
		top.add(tile);
		top.add(entity);
		
		cellw=new JTextField("16");
		cellh=new JTextField("16");
		
		final MapEditor m=this;
		
		cellw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cw=Integer.parseInt(m.cellw.getText());
				m.repaint();
			}
		});
		cellh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ch=Integer.parseInt(m.cellh.getText());
				m.repaint();
			}
		});
		
		top.add(new JLabel("Cell X:"));
		top.add(cellw);
		top.add(new JLabel("Cell Y:"));
		top.add(cellh);
		
		return top;
	}
	
	JPanel createCenter(){
		final MapEditor m=this;
		center=new JPanel(){
			static final long serialVersionUID=1003;
			
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				int w=getWidth();
				int h=getHeight();
				g.setColor(Color.cyan);
				g.drawRect(0,0,w,h);
				g.setColor(Color.black);
				((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
				//cartesian
				if(gridmode==1){
					//vertical
					for(int x=m.cw;x<w;x+=m.cw){
						g.drawLine(x,0,x,h);
					}
					//horizontal
					for(int y=m.ch;y<h;y+=m.ch){
						g.drawLine(0,y,w,y);
					}
				}
				//isometric
				else if(gridmode==2){
					//number of cells in each direction
					int nv=h/m.ch;
					int nh=w/m.cw;

					for (int i=0;i<=(nv+nh)*2;i++) {
						g.drawLine(0,i*m.ch,i*m.ch*2,0);
						g.drawLine(w-i*m.cw*2,0,w,i*m.ch);
					}
				}
			}
			
			@Override
			public Dimension getMinimumSize(){
				return new Dimension(256,256);
			}
		};
		center.setBackground(Color.cyan);
		center.setMinimumSize(new Dimension(256,256));
		return center;
	}
	
	JPanel createBottom(){
		bottom=new JPanel();
		bottom.setLayout(new SpringLayout());
		bottom.setPreferredSize(new Dimension(0,48));
		bottom.setBackground(Color.green);
		
		int panels=bottom.getWidth()/48;
		bottom.add(new JButton(""+panels));
		
		for(int i=0;i<panels;++i){
			bottom.add(new JButton("HELLO"));
		}
		
		return bottom;
	}
	
	JList<String> createRight(){
		DefaultListModel<String> dlm=new DefaultListModel<String>();
		right=new JList<String>(dlm);
		dlm.add(0,"THPARKLES");
		right.setPreferredSize(new Dimension(200,0));
		right.setBackground(Color.magenta);
		return right;
	}
	
	MapEditor(){
		setTitle("Octave Map Editor v1.0.0.0 Alpha");
		setSize(640,480);
		setVisible(true);
		//final MapEditor t=this;
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
				//t.setVisible(false);
			}
		});
		
		Container content=getContentPane();
		
		content.add(createTop(),BorderLayout.PAGE_START);
		content.add(createCenter(),BorderLayout.CENTER);
		content.add(createBottom(),BorderLayout.PAGE_END);
		content.add(createRight(),BorderLayout.LINE_END);
		
		revalidate();
	}
}
