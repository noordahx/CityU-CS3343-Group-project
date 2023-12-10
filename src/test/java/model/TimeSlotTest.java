package model;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeSlotTest {
    TimeSlot slot;

    @BeforeEach
    public void setup() {
        slot = new TimeSlot();
    }

    // test a book method
    @Test
    public void testBook() {
        slot.book("User");
        assertEquals("User", slot.getUserName());
        assertFalse(slot.getStatus());
    }

    // test delete booking method
    @Test
    public void testDelete() {
        slot.book("User");
        slot.delete();
        assertEquals("", slot.getUserName());
        assertTrue(slot.getStatus());
    }

     // Test default constructor
    @Test
    public void testDefaultConstructor() {
        assertEquals("", slot.getUserName());
        assertTrue(slot.getStatus());
    }

    // Test parameterized constructor
    @Test
    public void testParameterizedConstructor() {
        TimeSlot bookedTimeSlot = new TimeSlot("John", false);
        assertEquals("John", bookedTimeSlot.getUserName());
        assertFalse(bookedTimeSlot.getStatus());
    }
    // Test toJson() method
    @Test
    public void testToJson() {
        slot.book("Charlie");
        JSONObject json = slot.toJson();
        assertEquals("Charlie", json.getString("Username"));
        assertFalse(json.getBoolean("status"));
    }
}