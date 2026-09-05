package com.petclinic.testcommon.framework.utils.datetime;

import static com.petclinic.testcommon.model.common.Locale.de_DE;
import static com.petclinic.testcommon.model.common.Locale.getLocale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeUtils {

    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static LocalDate calendar;
    public static final String FEATURE_NOT_ACTIVE_MESSAGE = "Feature is not yet active on this env.";

    private static final ZoneId GERMAN_ZONE = ZoneId.of("Europe/Berlin");

    /**
     * Converts the given UTC ZonedDateTime to German time zone (Europe/Berlin).
     *
     * @param utcDateTime the UTC time to convert
     * @return the ZonedDateTime in German time zone
     */
    public static ZonedDateTime convertUtcToGermanTime(ZonedDateTime utcDateTime) {
        return utcDateTime.withZoneSameInstant(GERMAN_ZONE);
    }

    public static String getCurrentDateFormatted() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = getLocale().equalsIgnoreCase(de_DE)
                ? new SimpleDateFormat("dd.MM.yyyy")
                : new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(today);
    }

    public static String getCurrentDateInFormat(String format) {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(today);
    }

    public static String getFutureDate(int advanceYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(java.util.Calendar.YEAR, advanceYear);
        Date futureDate = calendar.getTime();
        SimpleDateFormat formatter = getLocale().equalsIgnoreCase(de_DE)
                ? new SimpleDateFormat("dd.MM.yyyy")
                : new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(futureDate);
    }

public static String getFutureDateInDdMmYyyy(int advanceYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(java.util.Calendar.YEAR, advanceYear);
        Date futureDate = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(futureDate);
    }

public static String getPastDateInDdMmYyyy(int advanceYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(java.util.Calendar.YEAR, advanceYear);
        Date futureDate = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(futureDate);
    }

    public static String formatDateToEnLocale(String dateCandidate) throws ParseException {
        String enFormat = "yyyy-MM-dd";
        SimpleDateFormat deFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFinal = deFormat.parse(dateCandidate);
        deFormat.applyPattern(enFormat);
        return deFormat.format(dateFinal);
    }

    public static String formatDateToDeLocale(String dateCandidate) throws ParseException {
        String deFormat = "dd.MM.yyyy";
        SimpleDateFormat enFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFinal = enFormat.parse(dateCandidate);
        enFormat.applyPattern(deFormat);
        return enFormat.format(dateFinal);
    }

    public static List<Integer> getCurrentDateWithAddedDaysAsList(int addedDays) {
        Date actualDate = Calendar.getInstance().getTime();
        Date daysAgo = new DateTime(actualDate).plusDays(addedDays).toDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM d");
        List<Integer> list = Arrays.stream(formatter.format(daysAgo).split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return list;
    }

    public static String getCurrentDateInUtc() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
        LocalDateTime date = LocalDateTime.now();
        return dateTimeFormatter.format(date);
    }

    public static String getCurrentDateWithAddedMonthsAndDaysInUtc(int addedMonths, int addedDays) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
        LocalDateTime date = LocalDateTime.now().plusMonths(addedMonths).plusDays(addedDays);
        return dateTimeFormatter.format(date);
    }

    public static String getCurrentDateWithAddedDaysInUtc(int addedDays) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
        LocalDateTime date = LocalDateTime.now().plusDays(addedDays);
        return dateTimeFormatter.format(date);
    }

    public static String getCurrentDateWithAddedDaysAndMinutesInUtc(int addedDays, int addedMinutes) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
        LocalDateTime date = LocalDateTime.now().plusDays(addedDays).plusMinutes(addedMinutes);
        return dateTimeFormatter.format(date);
    }

    public static String getCurrentDateWithAddedMinutesInUtc(int addedMinutes) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
        LocalDateTime date = LocalDateTime.now().plusMinutes(addedMinutes);
        return dateTimeFormatter.format(date);
    }

    public static String getCurrentDateWithAddedSecondsInUtc(int addedSeconds) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
        LocalDateTime date = LocalDateTime.now().plusSeconds(addedSeconds);
        return dateTimeFormatter.format(date);
    }

    public static long getTimeWithAddedMinutes(long time, int addedMinutes) {
        return time + ((long) addedMinutes * 60 * 1000);
    }

    public static String getSpecificYearMinusValue(int subtractYears) {
        calendar = LocalDate.now().minusYears(subtractYears);
        return String.valueOf(calendar.getYear());
    }

    public static String getSpecificDateMinusValue(int subtractYears) {
        calendar = LocalDate.now().minusYears(subtractYears);
        return String.valueOf(calendar);
    }

    public static String getSpecificDatePlusDays(int addedDays) {
        calendar = LocalDate.now().plusDays(addedDays);
        return String.valueOf(calendar);
    }

    public static String getCustomDateMinusDays(String customDate, int substractedDays) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");

        LocalDateTime dateTime = LocalDateTime.parse(customDate, formatter);
        LocalDate calendar = dateTime.toLocalDate().minusDays(substractedDays);
        return calendar.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getCurrentMonth() {
        calendar = LocalDate.now();
        if (calendar.getMonthValue() < 10) {
            return "0" + (calendar.getMonthValue());
        }
        return String.valueOf(calendar.getMonthValue());
    }

    public static void waitForGivenSec(long sec) throws InterruptedException {
        log.info("Waiting for {} seconds to elapse...", sec);
        TimeUnit.SECONDS.sleep(sec);
    }

    @SneakyThrows
    public static int calculateAge(String dateOfBirth) {
        LocalDate dateOfBirthDate = LocalDate.parse(dateOfBirth);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirthDate, currentDate).getYears();
    }

    public static boolean isActivationDateAfter(int year, int month, int day) {
        return LocalDate.now().isAfter(LocalDate.of(year, month, day));
    }
    
    public static boolean isFeatureActive(Month month, int year) {
        return LocalDate.now().isAfter(LocalDate.of(year, month, 01));
    }

    public static boolean isActivationDateBefore(int year, Month month, int day) {
        return LocalDate.now().isBefore(LocalDate.of(year, month, day));
    }

    public static String generateRandomDateInDdMmYyyyFormat(LocalDate startDate ,LocalDate endDate ) {

        // Convert dates to epoch days
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();

        // Generate a random epoch day within the range
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

        // Convert the random epoch day back to a LocalDate object
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);

        // Format the date to dd.MM.yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = randomDate.format(formatter);

        log.info("Generated Date: {}" ,formattedDate);
        return formattedDate;
    }


}