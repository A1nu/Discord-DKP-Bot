package ee.a1nu.discord_dkp_bot.web.util;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class WeekDaysConverter {
    public static Integer daysToMask(List<DayOfWeek> weekDays) {
        int mask = 0;
        for (DayOfWeek day : weekDays) {
            mask += (int) Math.pow(2, day.getValue());
        }
        return mask;
    }

    public static List<DayOfWeek> extractWeekDaysFromMask(int mask) {
        List<DayOfWeek> weekDays = new ArrayList<>();
        List<DayOfWeek> initList = Arrays.stream(DayOfWeek.values()).toList().reversed();
        for (DayOfWeek day : initList) {
            int dayValue = (int) Math.pow(2, day.getValue());
            if (mask >= dayValue) {
                weekDays.add(day);
                mask -= dayValue;
            }
        }
        return weekDays.reversed();
    }
}
