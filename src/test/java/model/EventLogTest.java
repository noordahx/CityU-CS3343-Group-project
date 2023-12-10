package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the EventLog class
 */
public class EventLogTest {
	private Event e1;
	private Event e2;
	private Event e3;
	
	@BeforeEach
	public void loadEvents() {
		e1 = new Event("A1");
		e2 = new Event("A2");
		e3 = new Event("A3");
		EventLog el = EventLog.getInstance();
		el.logEvent(e1);
		el.logEvent(e2);
		el.logEvent(e3);
	}
	
	@Test
	public void testLogEvent() {	
		List<Event> l = new ArrayList<Event>();
		
		EventLog el = EventLog.getInstance();
		for (Event next : el) {
			l.add(next);
		}
		
		assertTrue(l.contains(e1));
		assertTrue(l.contains(e2));
		assertTrue(l.contains(e3));
	}

	@Test
	public void testClear() {
		EventLog el = EventLog.getInstance();
		el.clear();
		Iterator<Event> itr = el.iterator();
		assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
		assertEquals("Event log cleared.", itr.next().getDescription());
		assertFalse(itr.hasNext());
	}

	@Test
    public void testGetInstance() {
        EventLog instance1 = EventLog.getInstance();
        EventLog instance2 = EventLog.getInstance();
        assertSame(instance1, instance2);
    }
	
    @Test
    public void testIteratorRemove() {
        EventLog el = EventLog.getInstance();
        Iterator<Event> itr = el.iterator();
        itr.next();
        itr.remove();
        assertFalse(el.iterator().next().getDescription().equals(e1.getDescription()));
    }
}
