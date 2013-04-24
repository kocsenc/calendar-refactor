package ZachsCrappyTogglDay;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class AppointmentBlock extends JPanel {
	
	private Set<AppointmentLabel> appoints;
	private GridBagConstraints constraints;
	
	public AppointmentBlock(AppointmentLabel r) {
		super();
		constraints = new GridBagConstraints();
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.gridx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		
		setBorder(new EtchedBorder());
		setFont(getFont().deriveFont(14f));
		setOpaque(false);
		
		constraints.gridheight = (int)(r.getAppointment().getDuration()*1000)/(60*15);
		
		if (constraints.gridheight > 1) {
			constraints.weighty = 1;
		}
		else {
			constraints.weighty = .5;
		}
		
		appoints = new HashSet<AppointmentLabel>();
		
		appoints.add(r);
				
		setLayout(new GridLayout(1,0));
		
		add(r);
		
	}
	
	public void addAppointment(AppointmentLabel temp) {
		

		appoints.add(temp);
		add(temp);

		
	}
	
	public Set<AppointmentLabel> getAppointLabels() {
		return appoints;
	}
	
	public void addAppointmentListener(MouseListener ml) {
		for (AppointmentLabel label: appoints) {
			label.addMouseListener(ml);
		}
	}
	

}
