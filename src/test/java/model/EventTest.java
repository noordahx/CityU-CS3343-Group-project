package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Date d;

	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.

	@BeforeEach
	public void runBefore() {
		e = new Event("Sensor open at door");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
	}

	@Test
	public void testEvent1() {
		assertEquals("Sensor open at door", e.getDescription());
	}

	@Test
	public void testEvent2() {
		assertEquals(d, e.getDate());
	}

	@Test
	public void testEquals1() {
		boolean result = e.equals(null);
		assertEquals(result, false);
	}

	@Test
	public void testEquals2() {
		String temp = "Not an event";
		boolean result = e.equals(temp);
		assertEquals(result, false);
	}

	@Test
	public void testEquals3() {
		Event e2 = new Event("Sensor open at door");
		boolean result = e.equals(e2);
		assertEquals(result, true);
	}

	@Test
	public void testHashcode() {
		assertEquals(13 * d.hashCode() + "Sensor open at door".hashCode(), e.hashCode());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
	}
}
