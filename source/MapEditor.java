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
import java.awt.event.*;
import javax.swing.*;

public class MapEditor extends JFrame{
	static final long serialVersionUID=1002;
	
	/**
	 * The main view of the map.
	 */
	JPanel mainview;
	
	MapEditor(){
		setTitle("Octave Map Editor v1.0.0.0 Alpha");
		setSize(640,480);
		setVisible(true);
		final MapEditor t=this;
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
				//t.setVisible(false);
			}
		});
		Container content=getContentPane();
		//Build the basic layout
		JPanel top=new JPanel();
		top.setLayout(new BoxLayout(top,BoxLayout.LINE_AXIS));
		top.setPreferredSize(new Dimension(0,48));
		top.setBackground(Color.blue);
		JPanel center=new JPanel();
		center.setBackground(Color.red);
		JPanel bottom=new JPanel();
		bottom.setLayout(new BoxLayout(bottom,BoxLayout.LINE_AXIS));
		bottom.setPreferredSize(new Dimension(0,64));
		bottom.setBackground(Color.green);
		DefaultListModel<String> dlm=new DefaultListModel<String>();
		JList<String> right=new JList<String>(dlm);
		dlm.add(0,"THPARKLES");
		right.setPreferredSize(new Dimension(200,0));
		right.setBackground(Color.magenta);
		//Add the layout structure
		content.add(top,BorderLayout.PAGE_START);
		content.add(center,BorderLayout.CENTER);
		content.add(bottom,BorderLayout.PAGE_END);
		content.add(right,BorderLayout.LINE_END);
		/*mainview=new JPanel(){
			static final long serialVersionUID=1003;
			public void paintComponent(Graphics g){
				
			}
		};*/
	}
}
