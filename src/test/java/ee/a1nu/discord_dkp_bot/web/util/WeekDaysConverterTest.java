package ee.a1nu.discord_dkp_bot.web.util;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.List;

import static ee.a1nu.discord_dkp_bot.web.util.WeekDaysConverter.daysToMask;
import static ee.a1nu.discord_dkp_bot.web.util.WeekDaysConverter.extractWeekDaysFromMask;
import static org.junit.jupiter.api.Assertions.assertEquals;


class WeekDaysConverterTest {
    @Test
    public void validateMaskGenerationForMonday() {
        List<DayOfWeek> days = List.of(DayOfWeek.MONDAY);
        assertEquals(2, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForTuesday() {
        List<DayOfWeek> days = List.of(DayOfWeek.TUESDAY);
        assertEquals(4, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForWednesday() {
        List<DayOfWeek> days = List.of(DayOfWeek.WEDNESDAY);
        assertEquals(8, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForThursday() {
        List<DayOfWeek> days = List.of(DayOfWeek.THURSDAY);
        assertEquals(16, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForFriday() {
        List<DayOfWeek> days = List.of(DayOfWeek.FRIDAY);
        assertEquals(32, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForSaturday() {
        List<DayOfWeek> days = List.of(DayOfWeek.SATURDAY);
        assertEquals(64, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForSunday() {
        List<DayOfWeek> days = List.of(DayOfWeek.SUNDAY);
        assertEquals(128, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForAllWorkDays() {
        List<DayOfWeek> days = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
        assertEquals(62, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForWedAndFri() {
        List<DayOfWeek> days = List.of(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);
        assertEquals(40, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForAllWeekendDays() {
        List<DayOfWeek> days = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        assertEquals(192, daysToMask(days));
    }

    @Test
    public void validateMaskGenerationForAllWeekDays() {
        List<DayOfWeek> days = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        assertEquals(254, daysToMask(days));
    }

    @Test
    public void extractAllWeekdaysFromMask() {
        int mask = 254;

        assertEquals(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY), extractWeekDaysFromMask(mask));
    }

    @Test
    public void extractWeekendFromMask() {
        int mask = 192;

        assertEquals(List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY), extractWeekDaysFromMask(mask));
    }

    @Test
    public void extractWorkDaysFromMask() {
        int mask = 62;

        assertEquals(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY), extractWeekDaysFromMask(mask));
    }

    @Test
    public void extractMonAndSunFromMask() {
        int mask = 130;

        assertEquals(List.of(DayOfWeek.MONDAY, DayOfWeek.SUNDAY), extractWeekDaysFromMask(mask));
    }
}