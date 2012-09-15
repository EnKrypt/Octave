package source;

import java.awt.event.*;
import javax.swing.*;

public class ToggleGroup implements ActionListener{
	JToggleButton buttons[];
	MapEditor master;
	int id;
	
	ToggleGroup(int id,JToggleButton[] buttons,MapEditor master){
		this.buttons=buttons;
		this.master=master;
		this.id=id;
	}
	
	public void actionPerformed(ActionEvent e){
		for(int i=0;i<buttons.length;++i){
			if(buttons[i]!=e.getSource()){
				buttons[i].setSelected(false);
			}
			else{
				master.toggle(id,i);
			}
		}
		master.repaint();
	}
}
