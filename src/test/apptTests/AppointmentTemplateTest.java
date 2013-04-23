package test.apptTests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.std.model.appointment.AppointmentTemplate;
import com.std.model.pattern.DayOfWeekPattern;
import com.std.model.pattern.NDaysPattern;
import com.std.model.pattern.RecurrencePattern;
import com.std.util.range.DateRange;

public class AppointmentTemplateTest {

	@Test
	public void testGetTitle() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("1", "2","3", 0);
		apptTemp.setTitle("title");
		String t = "title";
		assertEquals(t, apptTemp.getTitle());
	}

	@Test
	public void testGetDescription() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("1", "2","3", 0);
		apptTemp.setDescription("description");
		String d = "description";
		assertEquals(d, apptTemp.getDescription());
	}

	@Test
	public void testGetLocation() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("1", "2","3", 0);
		apptTemp.setLocation("location");
		String l = "location";
		assertEquals(l, apptTemp.getLocation());
	}

	@Test
	public void testGetDuration() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("1", "2","3", 0);
		apptTemp.setDuration(32138644);
		assertEquals(32138644, apptTemp.getDuration());
	}

	@Test
	public void testGetPattern() {
		boolean days[] = new boolean[7];
		days[0] = true;
		days[4] = true;
		days[6] = true;
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("1", "2","3", 0);

		RecurrencePattern pattern =
			new DayOfWeekPattern(
					(new DateRange(new Date(), new Date())), days);

		apptTemp.setPattern(pattern);
		assertEquals(pattern, apptTemp.getPattern());
	}

	@Test
	public void testGetPattern2() {
		int n = 2;
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("1", "2","3", 0);

		RecurrencePattern pattern =
			new NDaysPattern(
					(new DateRange(new Date(), new Date())), n);

		apptTemp.setPattern(pattern);
		assertEquals(pattern, apptTemp.getPattern());
	}

	@Test
	public void testSetTitle() {
		AppointmentTemplate appt=
			new AppointmentTemplate("1", "2","3", 0);
		appt.setTitle("title");
		assertNotNull(appt.getTitle());
		assertEquals("title should be title", "title", appt.getTitle());
	}

	@Test
	public void testSetDescription() {
		AppointmentTemplate appt=
			new AppointmentTemplate("1", "2","3", 0);
		appt.setDescription("description");
		assertNotNull(appt.getTitle());
		assertEquals("title should be description", "description", appt.getDescription());
	}

	@Test
	public void testSetLocation() {
		AppointmentTemplate appt=
			new AppointmentTemplate("1", "2","3", 0);
		appt.setDescription("location");
		assertNotNull(appt.getLocation());
		assertEquals("title should be Location", "location", appt.getDescription());
	}

	@Test
	public void testSetDuration() {
		AppointmentTemplate appt=
			new AppointmentTemplate("1", "2","3", 0);
		long l = 8456;
		appt.setDuration(l);
		assertNotNull(appt.getDuration());
		System.out.println(appt.getDuration());
		assertEquals(l, appt.getDuration());
	}

	@Test
	public void testSetPattern() {
		
		boolean days[] = new boolean[7];
		days[0] = true;
		days[4] = true;
		days[6] = true;
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("1", "2","3", 0);

		RecurrencePattern pattern =
			new DayOfWeekPattern(
					(new DateRange(new Date(), new Date())), days);

		apptTemp.setPattern(pattern);
		assertNotNull(apptTemp.getPattern());
		assertSame(pattern, apptTemp.getPattern());
	}

	@Test
	public void testSetPattern2() {
		int n = 2;
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("1", "2","3", 0);

		RecurrencePattern pattern =
			new NDaysPattern(
					(new DateRange(new Date(), new Date())), n);

		apptTemp.setPattern(pattern);
		assertNotNull(apptTemp.getPattern());
		assertSame(pattern, apptTemp.getPattern());
	}

}
