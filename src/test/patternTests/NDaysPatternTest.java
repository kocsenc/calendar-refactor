package test.patternTests;

import com.std.model.pattern.NDaysPattern;
import com.std.util.range.DateRange;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class NDaysPatternTest{

	@Test
	public void testGetDates(){
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		cal.setTime(date);
		cal.add(Calendar.DATE, 12);
		Date endDate = cal.getTime();
		DateRange dRange = new DateRange(date, date);
		int n = 3;
		NDaysPattern nPatt = new NDaysPattern(dRange, n);
		assertEquals(0, nPatt.getDates().size());
		dRange = new DateRange(date, endDate);
		nPatt = new NDaysPattern(dRange, n);
		assertEquals(4, nPatt.getDates().size());
	}

	@Test
	public void testGetInstanceEvery(){
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		cal.setTime(date);
		cal.add(Calendar.DATE, 12);
		Date endDate = cal.getTime();
		DateRange dRange = new DateRange(date, endDate);
		int n = 3;
		NDaysPattern nPatt = new NDaysPattern(dRange, n);
		assertEquals(n, nPatt.instanceEvery());
	}

	@Test
	public void testSetInstanceEveryDays(){
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		cal.setTime(date);
		cal.add(Calendar.DATE, 12);
		Date endDate = cal.getTime();
		DateRange dRange = new DateRange(date, endDate);
		int n = 0;
		NDaysPattern nPatt = new NDaysPattern(dRange, n);
		assertEquals(0, nPatt.instanceEvery());
		nPatt.setInstanceEveryDays(10);
		assertEquals(10, nPatt.instanceEvery());
	}

}
