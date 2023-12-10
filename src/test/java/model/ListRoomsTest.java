package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ListRoomsTest {
    public ListRooms lr;
    public StudyRoom sr;

    @BeforeEach
    public void setup() {
        lr = new ListRooms();
        sr = new StudyRoom("test");
    }

    // test to check if add method works as expected
    @Test
    public void addTest() {
        lr.add(sr);
        assertEquals(sr, lr.get(4));
    }

    // test listRooms size method
    @Test
    public void numRoomsTest() {
        lr.add(sr);
        lr.add(sr);
        assertEquals(6, lr.numRooms());
    }

    // test getRooms method
    @Test
    public void getRoomsTest() {
        lr.add(sr);
        lr.add(sr);
        List<StudyRoom> studyRooms = new ArrayList<>();
        studyRooms.add(sr);
        studyRooms.add(sr);
        assertEquals(studyRooms.size() + 4, lr.getRooms().size());
        assertEquals(studyRooms.get(0).getName(), lr.getRooms().get(4).getName());
    }

    @Test
    public void testConstructor() {
        assertEquals(4, lr.numRooms());
    }

    @Test
    public void testAddRoom() {
        StudyRoom newRoom = new StudyRoom("X500");
        lr.add(newRoom);
        assertEquals(5, lr.numRooms());
        assertEquals(newRoom, lr.get(4));
    }

    @Test
    public void testGetRoom() {
        StudyRoom room = lr.get(2);
        assertEquals("X300", room.getName());
    }

    @Test
    public void testToJson() {
        JSONObject json = lr.toJson();
        assertNotNull(json);
        assertEquals("ListRooms", json.getString("name"));
        JSONArray data = json.getJSONArray("data");
        assertEquals(4, data.length());
        assertEquals("X100", data.getJSONObject(0).getString("name"));
        assertEquals("X200", data.getJSONObject(1).getString("name"));
        assertEquals("X300", data.getJSONObject(2).getString("name"));
        assertEquals("X400", data.getJSONObject(3).getString("name"));
    }

    @Test
    public void testGetRoomOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> lr.get(10));
    }

    @Test
    public void testAddRoomAtIndex() {
        StudyRoom newRoom = new StudyRoom("X500");
        lr.addAt(newRoom, 2);
        assertEquals(5, lr.numRooms());
        assertEquals(newRoom, lr.get(2));
        assertEquals("X300", lr.get(3).getName()); // Check if the room at index 3 is shifted correctly
        assertEquals("X400", lr.get(4).getName()); // Check if the room at index 4 is shifted correctly
    }
}