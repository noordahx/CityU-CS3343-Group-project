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
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Sensor open at door");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
	}

	@Test
	public void testEvent() {
		assertEquals("Sensor open at door", e.getDescription());
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
	public void testHashcode() {
		assertEquals(13 * d.hashCode() + "Sensor open at door".hashCode(), e.hashCode());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
	}
}
