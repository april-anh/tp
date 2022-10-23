package seedu.address.model.datetime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.datetime.DatetimeCommonUtils.DAY_FORMAT_REGEX;
import static seedu.address.model.datetime.DatetimeCommonUtils.TIME_FORMATTER;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a timeslot that repeats every week in ModQuik.
 * Guarantees: immutable
 */

public class WeeklyTimeslot {
    public final DayOfWeek day;
    public final LocalTime startTime;
    public final LocalTime endTime;




    /**
     * Constructs a {@code WeeklyTimeslot}.
     *
     * @param dayString A day, represented by a number from 1 to 7 inclusive.
     * @param startTimeString A validly formatted time for start.
     * @param endTimeString A validly formatted time for end.
     */
    public WeeklyTimeslot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        requireNonNull(day);
        requireNonNull(startTime);
        requireNonNull(endTime);
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String dayString) {

        return dayString.matches(DAY_FORMAT_REGEX);
    }

    private static boolean isValidTime(String datetime) {
        try {
            LocalTime.parse(datetime, TIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if given string is valid as a time range.
     *
     * @param timeStart Starting time
     * @param timeEnd Ending time
     * @return Whether both strings are valid when taken together as a time range
     */
    public static boolean isValidTimeRange(String timeStart, String timeEnd) {
        // Unideal: does additional work by creating objects to check validity.
        // This is to confirm to the rest of the codebase, where checks are done on a
        // "check for permission" in a static method beforehand.
        if (!isValidTime(timeStart) || !isValidTime(timeEnd)) {
            return false;
        }
        LocalTime timeStartObj = LocalTime.parse(timeStart, TIME_FORMATTER);
        LocalTime timeEndObj = LocalTime.parse(timeEnd, TIME_FORMATTER);
        return !timeEndObj.isBefore(timeStartObj);
    }

    public static WeeklyTimeslot fromFormattedString(String dayString, String startTimeString, String endTimeString) {
        DayOfWeek day = DayOfWeek.of(Integer.parseInt(dayString));
        LocalTime startTime = LocalTime.parse(startTimeString, TIME_FORMATTER);
        LocalTime endTime = LocalTime.parse(endTimeString, TIME_FORMATTER);
        return new WeeklyTimeslot(day, startTime, endTime);
    }


    /**
     * Returns the numerical value of the day of the week.
     *
     * @return Day of the week
     */
    public String getDay() {
        return String.valueOf(day.getValue());
    }

    /**
     * Get formatted start time.
     *
     * @return Formatted start time
     */
    public String getStartTimeFormatted() {
        return startTime.format(TIME_FORMATTER);
    }

    /**
     * Get formatted end time.
     *
     * @return Formatted end time
     */
    public String getEndTimeFormatted() {
        return endTime.format(TIME_FORMATTER);
    }


    /**
     * Get day of week in an abbreviated human-friendly form.
     *
     * @return Day of the week
     */
    private String getDayOfWeekReadable() {
        return day.getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }

    @Override
    public String toString() {
        return String.format("%s %s to %s", getDayOfWeekReadable(), startTime, endTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WeeklyTimeslot // instanceof handles nulls
                && startTime.equals(((WeeklyTimeslot) other).startTime) // state check
                && endTime.equals(((WeeklyTimeslot) other).endTime) // state check
                && day.equals(((WeeklyTimeslot) other).day)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, day);
    }

}
