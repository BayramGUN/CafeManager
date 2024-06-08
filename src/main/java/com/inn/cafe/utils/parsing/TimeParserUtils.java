package com.inn.cafe.utils.parsing;

import java.util.regex.Pattern;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


import java.util.regex.Matcher;

public class TimeParserUtils {
  public static long ParseDurationToMillis(String duration) {
    long millis = 0;
    // Regular expressions to match each unit
    Pattern pattern = Pattern.compile("(\\d+)([wdhms])");
    Matcher matcher = pattern.matcher(duration);

    while (matcher.find()) {
      int value = Integer.parseInt(matcher.group(1));
      char unit = matcher.group(2).charAt(0);
      switch (unit) {
        case 'w':
          millis += value * 7 * 24 * 60 * 60 * 1000; // Convert weeks to milliseconds
          break;
        case 'd':
          millis += value * 24 * 60 * 60 * 1000; // Convert days to milliseconds
          break;
        case 'h':
          millis += value * 60 * 60 * 1000; // Convert hours to milliseconds
          break;
        case 'm':
          millis += value * 60 * 1000; // Convert minutes to milliseconds
          break;
        case 's':
          millis += value * 1000; // Convert seconds to milliseconds
          break;
        default:
          throw new IllegalArgumentException("Invalid unit: " + unit);
      }
    }
    return millis;
  }
  public static String GetErrorTime() {
    long epochTimeMillis = System.currentTimeMillis();
    Instant instant = Instant.ofEpochMilli(epochTimeMillis);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Europe/Istanbul"));
    return formatter.format(instant);
  }
}
