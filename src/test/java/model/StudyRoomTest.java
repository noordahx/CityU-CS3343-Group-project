package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudyRoomTest {
    StudyRoom room;

    @BeforeEach
    public void setup() {
        room = new StudyRoom("Test Room");
    }

    // test empty schedule case
    @Test
    public void testEmptyGetSchedule() {
        List<String> expectedEmpty = new ArrayList<>();
        for (int i = 9; i < 18; i++) {
            expectedEmpty.add("Free");
        }
        assertEquals(expectedEmpty, room.getSchedule());
    }

    // test a case with one non-Free value
    @Test
    public void testNonEmptyGetSchedule() {
        room.bookTimeSlot(9, "User1");
        room.bookTimeSlot(17, "User2");
        List<String> expectedNonEmpty = new ArrayList<>();
        expectedNonEmpty.add("User1");
        for (int i = 10; i < 17; i++) {
            expectedNonEmpty.add("Free");
        }
        expectedNonEmpty.add("User2");
        assertEquals(expectedNonEmpty, room.getSchedule());
    }

    // test booking a timeslot method
    @Test
    public void testBookTimeSlot() {
        room.bookTimeSlot(9, "User");
        assertFalse(room.getAvailability(9));
    }

    // test delete a timeslot method
    @Test
    public void testDeleteTimeSlot() {
        room.bookTimeSlot(17, "User");
        assertFalse(room.getAvailability(17));
        assertEquals("User", room.getTimeSlotUser(17));

        room.deleteTimeSlot(17);
        assertTrue(room.getAvailability(17));
        assertEquals("", room.getTimeSlotUser(17));

    }

    // test getName method
    @Test
    public void testGetName() {
        assertEquals("Test Room", room.getName());
    }

    // test changeTimeSlot method
    @Test
    public void testChangeTimeSlot() {
        TimeSlot ts = new TimeSlot();
        room.changeTimeSlot(0, ts);
        room.changeTimeSlot(1, ts);
        assertEquals(room.getTimeSlot(0), ts);
    }

    // test getTimeSlotUser method when the time slot is not booked
    @Test
    public void testGetTimeSlotUserNotBooked() {
        assertEquals("", room.getTimeSlotUser(9));
    }

    // test getAvailability method when the time slot is not booked
    @Test
    public void testGetAvailabilityNotBooked() {
        assertTrue(room.getAvailability(10));
    }

    // test toJson method
    @Test
    public void testToJson() {
        room.bookTimeSlot(10, "User1");
        room.bookTimeSlot(11, "User2");
        JSONObject json = room.toJson();
        assertNotNull(json);
        assertEquals("Test Room", json.getString("name"));
        JSONArray timeSlotsArray = json.getJSONArray("TimeSlots");
        assertEquals(9, timeSlotsArray.length());
        // Add more assertions to verify the correctness of the generated JSON
    }

    //test bookTimeSlot method when it's already booked
    @Test
    public void testBookingAlreadyBookedTimeslot() {
        room.bookTimeSlot(9, "User1");
        room.bookTimeSlot(9, "User2");
        assertEquals("User1", room.getTimeSlotUser(9));
    }

    //test deleting free timeslot
    @Test
    public void testDeletingFreeTimeslot() {
        room.deleteTimeSlot(10);
        assertTrue(room.getAvailability(10));
    }

    //test changing time slot at invalid index > 11
    @Test
    public void testChangingTimeslotWithInvalidIndex() {
        TimeSlot timeSlot = new TimeSlot();
        assertThrows(IndexOutOfBoundsException.class, () -> room.changeTimeSlot(12, timeSlot));;
    }

    //test toJson of room with no booked timeslots
    @Test
    public void testToJsonWithNoBookedTimeslots() {
        JSONObject json = room.toJson();
        assertNotNull(json);
        assertEquals("Test Room", json.getString("name"));
        JSONArray timeSlotsArray = json.getJSONArray("TimeSlots");
        assertEquals(9, timeSlotsArray.length());
    }
}
