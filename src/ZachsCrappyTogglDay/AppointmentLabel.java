package ZachsCrappyTogglDay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class AppointmentLabel extends JPanel {
	
	private RefAppointment ref;
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("h:mm a");
	
	public AppointmentLabel(RefAppointment r) {
		super();
		
		JLabel title = new JLabel(ref.getTitle());
		title.setOpaque(false);
		title.setFont(title.getFont().deriveFont(14f));

		JLabel body = new JLabel();
		body.setOpaque(false);
		body.setFont(body.getFont().deriveFont(12f));

		String bodyText = "Location: " + r.getLoctation() + "\n" +
							"Description: " + r.getDescription() + "\n" +
							"Start Time: " + FORMAT.format(r.getStartTime()) + "\n"
							+ "End Time: " + FORMAT.format(r.getEndTime());
		
		body.setText(bodyText);
		
		add(title, BorderLayout.NORTH);
		add(body, BorderLayout.CENTER);
		
		setBackground(new Color(0x0BFFFFFF, true));
		setBorder(new EtchedBorder());
		
		ref = r;
	}
	
	public RefAppointment getAppointment() {
		return ref;
	}

}
