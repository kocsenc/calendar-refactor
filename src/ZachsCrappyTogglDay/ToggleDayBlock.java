package ZachsCrappyTogglDay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EtchedBorder;

public class ToggleDayBlock extends JToggleButton {

	private JLabel date;
	private JPanel center;
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("MMM dd" );
	private Date today;
	private MouseListener apptListener;
	
	public ToggleDayBlock(Date d) {
		super();

		
		date = new JLabel();
		date.setBorder(new EtchedBorder());
		date.setOpaque(false);
		
		setBorder(new EtchedBorder());
		this.setLayout(new BorderLayout());
		
		JPanel north = new JPanel();
		north.setBackground(new Color(0x0BFFFFFF, true));
		north.setLayout(new GridLayout(1, 3));
		north.add(date);
		add(north, BorderLayout.NORTH);
		
		center = new JPanel();
		center.setOpaque(true);
		center.setBackground(new Color(0xBBFFFFFF, true));
		center.setLayout(new GridBagLayout());
		
		
		JScrollPane scroll = new JScrollPane(center);
		
		add(scroll, BorderLayout.CENTER);
		
		today = d;
	}
	
	public void update(Set<RefAppointment> refSet, Date newDate, RefAppointment selected) {
		
		center.removeAll();
		
		HashMap<RefAppointment, AppointmentBlock> addedAppointments = new HashMap<RefAppointment, AppointmentBlock>();

		
		for (RefAppointment app : refSet) {
			boolean hasAdded = false;
			//See if it conflicts with any other appointment
			AppointmentLabel temp = new AppointmentLabel(app);
			if (app.equals(selected)) {
				temp.setBackground(new Color(0x00FFFFFF, true));
				temp.setOpaque(false);
			}
			for (RefAppointment added : addedAppointments) {
				if (CalendarModelUtiltiy.conflictingAppointments(added, app)) {

					addedAppointments.get(added).addApointment(temp);
					addedAppointments.put(app, addedAppointments.get(added));
					hasAdded = true;
				}
			}
			
			if (!hasAdded) {
				AppointmentBlock newBlock = new AppointmentBlock(temp);
				addedAppointments.put(app, newBlock);
			}
			
		}
		
		
		
		today = newDate;
		date.setText(FORMAT.format(today));
		date.setOpaque(false);
		date.setFont(date.getFont().deriveFont(18f));
		
		
	}
	
	public void addAppointmentListener(MouseListener aL) {
		apptListener = aL;
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		ToggleDayBlock t = new ToggleDayBlock();
		t.update(Calendar.getInstance().getTime());
		f.add(t);
		f.setVisible(true);
		f.setSize(600,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
