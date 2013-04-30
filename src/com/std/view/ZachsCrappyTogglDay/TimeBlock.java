package com.std.view.ZachsCrappyTogglDay;

import javax.swing.*;
import java.awt.*;

public class TimeBlock extends JPanel{
	private GridBagConstraints constraints;

	public TimeBlock(int increments){

		super();

		setOpaque(true);
		setBackground(new Color(0xBBFFFFFF, true));

		constraints = new GridBagConstraints();

		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.gridx = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.gridheight = increments;

		if(increments > 1){
			constraints.weighty = 1;
		}
		else{
			constraints.weighty = .5;
		}

		constraints.gridwidth = GridBagConstraints.REMAINDER;

	}

	public GridBagConstraints getConstraints(){
		return constraints;
	}


}
