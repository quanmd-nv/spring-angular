package com.angular.util;

import java.time.Clock;
import java.time.Instant;

public final class DateTimeUtil {

	private DateTimeUtil() {
		// do nothing
	}
	
	public static Instant getInstant() {
		Clock clock = Clock.systemUTC();
		return Instant.now(clock);
	}
}
