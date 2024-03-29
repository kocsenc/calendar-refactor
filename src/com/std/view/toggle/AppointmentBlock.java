package com.std.view.toggle;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

class AppointmentBlock extends JPanel{

	private final Set<AppointmentLabel> appoints;

	public AppointmentBlock(AppointmentLabel r){
		super();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.gridx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;

		setBorder(new EtchedBorder());
		setFont(getFont().deriveFont(14f));
		setOpaque(false);

		constraints.gridheight =
				(int) (r.getAppointment().getDuration() * 1000) / (60 * 15);

		if(constraints.gridheight > 1){
			constraints.weighty = 1;
		}
		else{
			constraints.weighty = .5;
		}

		appoints = new HashSet<AppointmentLabel>();

		appoints.add(r);

		setLayout(new GridLayout(1, 0));

		add(r);

	}

	public void addAppointment(AppointmentLabel temp){


		appoints.add(temp);
		add(temp);


	}

	public Set<AppointmentLabel> getAppointLabels(){
		return appoints;
	}

	public void addAppointmentListener(MouseListener ml){
		for(AppointmentLabel label : appoints){
			label.addMouseListener(ml);
		}
	}


}
