package seedu.address.model.datetime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WeeklyTimeslotTest {

    private final WeeklyTimeslot weeklyTimeslot =
            WeeklyTimeslot.fromFormattedString("1", "15:00", "17:00");

    @Test
    public void isOverlapping() {
        //different day -> returns false
        assertFalse(weeklyTimeslot.isOverlapping(
                WeeklyTimeslot.fromFormattedString("2", "10:00", "12:00")));

        //different day, overlapping timeslots -> return false
        assertFalse(weeklyTimeslot.isOverlapping(
                WeeklyTimeslot.fromFormattedString("2", "15:00", "16:00")));

        //same day, non-overlapping timeslots -> return false
        assertFalse(weeklyTimeslot.isOverlapping(
                WeeklyTimeslot.fromFormattedString("1", "18:00", "20:00")));

        //same day, overlapping timeslots -> return true
        assertTrue(weeklyTimeslot.isOverlapping(
                WeeklyTimeslot.fromFormattedString("1", "15:00", "16:00")));

        assertTrue(weeklyTimeslot.isOverlapping(
                WeeklyTimeslot.fromFormattedString("1", "16:00", "18:00")));

        assertTrue(weeklyTimeslot.isOverlapping(
                WeeklyTimeslot.fromFormattedString("1", "14:00", "16:00")));

        assertTrue(weeklyTimeslot.isOverlapping(
                WeeklyTimeslot.fromFormattedString("1", "14:00", "18:00")));
    }
}
