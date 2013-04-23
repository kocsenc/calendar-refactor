package test.patternTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.std.model.pattern.DayOfWeekPattern;
import com.std.util.range.DateRange;

public class DayOfWeekPatternTest {

	@Test
	public void testGetDates() {
		
		Date d = Calendar.getInstance().getTime();
		DateRange dRange = new DateRange(d,d);
		boolean days[] = new boolean[7];
		for(int i = 0; i < 7; i++){
			days[i] = false;
		}
		DayOfWeekPattern dayPatt = new DayOfWeekPattern(dRange, days);
		assertEquals(0, dayPatt.getDates().size());
		days[0] = true;
		dayPatt.setDays(days);
		assertNotNull(dayPatt.getDates());
	}

	@Test
	public void testOnSunday() {
		
		Date d = Calendar.getInstance().getTime();
		DateRange dRange = new DateRange(d,d);
		boolean days[] = new boolean[7];
		days[Calendar.SUNDAY-1] = true;
		DayOfWeekPattern dayPatt = new DayOfWeekPattern(dRange, days);
		assertTrue(dayPatt.onSunday());
		days[Calendar.SUNDAY-1] = false;
		dayPatt.setDays(days);
		assertFalse(dayPatt.onSunday());
	}

	@Test
	public void testOnMonday() {
		Date d = Calendar.getInstance().getTime();
		DateRange dRange = new DateRange(d,d);
		boolean days[] = new boolean[7];
		days[Calendar.MONDAY-1] = true;
		DayOfWeekPattern dayPatt = new DayOfWeekPattern(dRange, days);
		assertTrue(dayPatt.onMonday());
		days[Calendar.MONDAY-1] = false;
		dayPatt.setDays(days);
		assertFalse(dayPatt.onMonday());
	}

	@Test
	public void testOnTuesday() {
		Date d = Calendar.getInstance().getTime();
		DateRange dRange = new DateRange(d,d);
		boolean days[] = new boolean[7];
		days[Calendar.TUESDAY-1] = true;
		DayOfWeekPattern dayPatt = new DayOfWeekPattern(dRange, days);
		assertTrue(dayPatt.onTuesday());
		days[Calendar.TUESDAY-1] = false;
		dayPatt.setDays(days);
		assertFalse(dayPatt.onTuesday());
	}

	@Test
	public void testOnWednesday() {
		Date d = Calendar.getInstance().getTime();
		DateRange dRange = new DateRange(d,d);
		boolean days[] = new boolean[7];
		days[Calendar.WEDNESDAY-1] = true;
		DayOfWeekPattern dayPatt = new DayOfWeekPattern(dRange, days);
		assertTrue(dayPatt.onWednesday());
		days[Calendar.WEDNESDAY-1] = false;
		dayPatt.setDays(days);
		assertFalse(dayPatt.onWednesday());
	}

	@Test
	public void testOnThursday() {
		Date d = Calendar.getInstance().getTime();
		DateRange dRange = new DateRange(d,d);
		boolean days[] = new boolean[7];
		days[Calendar.THURSDAY-1] = true;
		DayOfWeekPattern dayPatt = new DayOfWeekPattern(dRange, days);
		assertTrue(dayPatt.onThursday());
		days[Calendar.THURSDAY-1] = false;
		dayPatt.setDays(days);
		assertFalse(dayPatt.onThursday());
	}

	@Test
	public void testOnFriday() {
		Date d = Calendar.getInstance().getTime();
		DateRange dRange = new DateRange(d,d);
		boolean days[] = new boolean[7];
		days[Calendar.FRIDAY-1] = true;
		DayOfWeekPattern dayPatt = new DayOfWeekPattern(dRange, days);
		assertTrue(dayPatt.onFriday());
		days[Calendar.FRIDAY-1] = false;
		dayPatt.setDays(days);
		assertFalse(dayPatt.onFriday());
	}

	@Test
	public void testOnSaturday() {
		Date d = Calendar.getInstance().getTime();
		DateRange dRange = new DateRange(d,d);
		boolean days[] = new boolean[7];
		days[Calendar.SATURDAY-1] = true;
		DayOfWeekPattern dayPatt = new DayOfWeekPattern(dRange, days);
		assertTrue(dayPatt.onSaturday());
		days[Calendar.SATURDAY-1] = false;
		dayPatt.setDays(days);
		assertFalse(dayPatt.onSaturday());
	}

}
