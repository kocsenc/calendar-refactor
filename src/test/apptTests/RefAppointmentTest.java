package test.apptTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.RefAppointment;
import com.std.model.pattern.DayOfWeekPattern;
import com.std.model.pattern.NDaysPattern;
import com.std.model.pattern.RecurrencePattern;
import com.std.util.range.DateRange;

public class RefAppointmentTest {

	@Test
	public void testGetTemplate() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		Date d = Calendar.getInstance().getTime();
		RefAppointment appt = new RefAppointment(d, apptTemp);

		assertEquals(apptTemp, appt.getTemplate());
	}

	@Test
	public void testGetStartDate() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		Date d = Calendar.getInstance().getTime();
		RefAppointment appt = new RefAppointment(d, apptTemp);

		assertEquals(d, appt.getStartDate());
	}

	@Test
	public void testSetStartDate() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(new Date(), apptTemp);
		
		appt.setStartDate(d);
		assertNotNull(appt.getStartDate());
		assertEquals(d, appt.getStartDate());
	}

	@Test
	public void testGetDuration() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		Date d = Calendar.getInstance().getTime();
		RefAppointment appt = new RefAppointment(d, apptTemp);
		appt.setDuration(565555);
		assertEquals(apptTemp.getDuration(), appt.getDuration());
	}

	@Test
	public void testSetDuration() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		Date d = Calendar.getInstance().getTime();
		RefAppointment appt = new RefAppointment(d, apptTemp);
		appt.setDuration(565555);
		assertNotNull(appt.getDuration());
		assertEquals(565555, appt.getDuration());
	}

	@Test
	public void testGetEndDate() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		Date d = Calendar.getInstance().getTime();
		RefAppointment appt = new RefAppointment(d, apptTemp);
		appt.setEndDate(d);
		assertEquals(d, appt.getEndDate());
	}

	@Test
	public void testSetEndDate() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		Date d = Calendar.getInstance().getTime();
		RefAppointment appt = new RefAppointment(d, apptTemp);
		appt.setEndDate(d);
		assertNotNull(appt.getEndDate());
		assertEquals(d, appt.getEndDate());	}

	@Test
	public void testGetLocation() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		String loc = apptTemp.getLocation();
		assertEquals(loc, appt.getLocation());
	}

	@Test
	public void testSetLocation() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		assertNotNull(appt.getLocation());
		assertEquals(apptTemp.getLocation(), appt.getLocation());
	}

	@Test
	public void testGetPattern() {
		boolean days[] = new boolean[7];
		days[0] = true;
		days[4] = true;
		days[6] = true;

		RecurrencePattern pattern =
			new DayOfWeekPattern(
					(new DateRange(new Date(), new Date())), days);
		
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		apptTemp.setPattern(pattern);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		assertEquals(apptTemp.getPattern(), appt.getPattern());
	}

	@Test
	public void testGetPattern2() {
		
		int n = 2;
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);

		RecurrencePattern pattern =
			new NDaysPattern(
					(new DateRange(new Date(), new Date())), n);

		apptTemp.setPattern(pattern);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		assertNotNull(appt.getDuration());
		assertEquals(apptTemp.getPattern(), appt.getPattern());
	}

	@Test
	public void testSetPattern() {
		boolean days[] = new boolean[7];
		days[0] = true;
		days[4] = true;
		days[6] = true;

		RecurrencePattern pattern =
			new DayOfWeekPattern(
					(new DateRange(new Date(), new Date())), days);
		
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		appt.setPattern(pattern);
		assertNotNull(appt.getPattern());
		assertEquals(apptTemp.getPattern(), appt.getPattern());
	}
	
	@Test
	public void testSetPattern2() {
		int n = 2;
		RecurrencePattern pattern =
			new NDaysPattern(
					(new DateRange(new Date(), new Date())), n);
		
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		appt.setPattern(pattern);
		assertNotNull(appt.getPattern());
		assertEquals(pattern, appt.getPattern());
	}

	@Test
	public void testGetDescription() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		assertEquals(apptTemp.getDescription(), appt.getDescription());
	}

	@Test
	public void testSetDescription() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		String description = "new desc";
		appt.setDescription(description);
		assertNotNull(appt.getDescription());
		assertEquals(description, appt.getDescription());
	}

	@Test
	public void testGetTitle() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		assertEquals(apptTemp.getTitle(), appt.getTitle());
	}

	@Test
	public void testSetTitle() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		String title = "new title";
		
		appt.setTitle(title);
		assertNotNull(appt.getTitle());
		assertEquals(title, appt.getTitle());
	}

	@Test
	public void testGetDateRange() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		DateRange range = new DateRange(d,d);
		appt.setDateRange(range);
		assertEquals(range, appt.getDateRange());
	}

	@Test
	public void testSetDateRange() {
		AppointmentTemplate apptTemp = 
			new AppointmentTemplate("title","location","desc.", 8885);
		
		Date d = Calendar.getInstance().getTime();
		
		RefAppointment appt = new RefAppointment(d, apptTemp);
		
		DateRange range = new DateRange(d,d);
		appt.setDateRange(range);
		assertNotNull(appt.getDateRange());
		assertEquals(range, appt.getDateRange());
	}

}
