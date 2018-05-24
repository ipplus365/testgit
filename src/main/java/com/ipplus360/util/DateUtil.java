package com.ipplus360.util;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * Created by 辉太郎 on 2017/8/1.
 */
public class DateUtil {

    public static String getNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public static long getNowMillis() {
        return Instant.now().toEpochMilli();
    }

    public static int getHour() {
        return LocalDateTime.now().getHour();
    }

    public static LocalDate getYesterday(LocalDate date) {
        return date.minusDays(1);
    }

    public static LocalDate getLastMonday(LocalDate date) {
        LocalDate lastWeek = date.minusWeeks(1);
        return lastWeek.with(DayOfWeek.MONDAY);
    }

    public static LocalDate getLastSunday(LocalDate date) {
        LocalDate lastWeek = date.minusWeeks(1);
        return lastWeek.with(DayOfWeek.SUNDAY);
    }

    public static LocalDate getNextMonday(LocalDate date) {
        LocalDate lastWeek = date.plusWeeks(1);
        return lastWeek.with(DayOfWeek.MONDAY);
    }

    public static LocalDate getNextSunday(LocalDate date) {
        LocalDate lastWeek = date.plusWeeks(1);
        return lastWeek.with(DayOfWeek.SUNDAY);
    }

    public static int getWeekOfYear(LocalDate date) {
        LocalDate lastWeek = date.minusWeeks(1);
        return lastWeek.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    }

    public static LocalDate getLastMonthStart(LocalDate date) {
        LocalDate lastMonthStart = date.minusMonths(1);
        return lastMonthStart.with(firstDayOfMonth());
    }

    public static LocalDate getLastMonthEnd(LocalDate date) {
        LocalDate lastMonthStart = date.minusMonths(1);
        return lastMonthStart.with(lastDayOfMonth());
    }

    public static LocalDateTime getNextYearTime(LocalDateTime localDateTime) {
        localDateTime = localDateTime.plusYears(1L);
        return localDateTime;
    }

    public static LocalDateTime getNextYearTime(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        localDateTime = localDateTime.plusYears(1L);
        return localDateTime;
    }

    public static int getMonth(LocalDate date) {
        return date.getMonth().getValue();
    }

    public static Date localDateTime2Date(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
    }

    public static Date localDate2Date(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate date2LocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDate.from(date.toInstant().atZone(ZoneId.systemDefault()));
    }

    public static LocalDateTime getEndOfDay(LocalDate localDate) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }

        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    public static long getSeconds(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }
    public static Date ldt2Date(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getNextXYear(Date date, int yearCount) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return localDateTime.plusYears(yearCount);
    }
}