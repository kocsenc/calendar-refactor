package test.rangeTests;

import com.std.util.range.DateRange;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateRangeTest{

	@Test
	public void testEqualsObject(){
		Calendar cal = Calendar.getInstance();
		Date startD = cal.getTime();
		cal.add(Calendar.DATE, 5);
		Date endD = cal.getTime();
		DateRange dRange = new DateRange(startD, endD);
		DateRange dRange2 = new DateRange(startD, endD);
		assertTrue(dRange.equals(dRange2));
		dRange2 = new DateRange(startD, startD);
		assertFalse(dRange.equals(dRange2));
	}

	@Test
	public void testGetDurationInMS(){
		Calendar cal = Calendar.getInstance();
		Date startD = cal.getTime();
		int numDays = 5;
		cal.add(Calendar.DATE, numDays);
		Date endD = cal.getTime();
		DateRange dRange = new DateRange(startD, endD);
		assertEquals((endD.getTime()
				- startD.getTime()), dRange.getDurationInMS());
	}

	@Test
	public void testGetEndDate(){
		Calendar cal = Calendar.getInstance();
		Date startD = cal.getTime();
		int numDays = 5;
		cal.add(Calendar.DATE, numDays);
		Date endD = cal.getTime();
		DateRange dRange = new DateRange(startD, endD);
		assertEquals(endD, dRange.getEndDate());
	}

	@Test
	public void testGetStartDate(){
		Calendar cal = Calendar.getInstance();
		Date startD = cal.getTime();
		int numDays = 5;
		cal.add(Calendar.DATE, numDays);
		Date endD = cal.getTime();
		DateRange dRange = new DateRange(startD, endD);
		assertEquals(startD, dRange.getStartDate());
	}

}
