package com.menasha.eai.common.string;

import java.io.StringWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.SystemUtils;
import org.apache.xml.utils.XMLChar;

/**
 * Contains static utility functions for converting types and values to and from
 * Strings.
 * 
 * @author Jason Reimer
 */
public class StringUtil {

	/**
	 * A replacement for the valueOf() method that allows for nulls or padded
	 * Strings. If the value is null, null is returned. Otherwise, the string is
	 * trimmed and converted.
	 * 
	 * @param value
	 *            a string.
	 * @return Returns null or a Long instance.
	 */
	public static Long convertToLong(String value) {
		return convertToLong(value, null);
	}

	public static String PadRight(String str, int TotalChars, char PadChar) {
		str = "" + str;

		if (str.length() < TotalChars) {

			StringBuffer sb = new StringBuffer(str);

			for (int i = 0; i < TotalChars - str.length(); i++) {
				sb.append(PadChar);
			}

			return sb.toString();

		} else {
			return str;
		}

	}

	public static String PadLeft(String str, int TotalChars, char PadChar) {
		str = "" + str;

		if (str.length() < TotalChars) {

			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < TotalChars - str.length(); i++) {
				sb.append(PadChar);
			}

			sb.append(str);

			return sb.toString();

		} else {
			return str;
		}

	}

	/**
	 * A replacement for the valueOf() method that allows for nulls or padded
	 * Strings. If the value is null, defaultValue is returned. Otherwise, the
	 * string is trimmed and converted.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            the instance value to return if value is null.
	 * @return the defaultValue, or the converted value.
	 */
	public static Long convertToLong(String value, Long defaultValue) {
		value = trimToNull(value);
		return value == null ? defaultValue : Long.valueOf(value);
	}

	/**
	 * A replacement for the valueOf() method that allows for nulls or padded
	 * Strings. If the value is null, defaultValue is returned. Otherwise, the
	 * string is trimmed and converted.
	 * 
	 * @param value
	 *            a string.
	 * @return Returns the converted reference.
	 */
	public static Integer convertToInteger(String value) {
		return convertToInteger(value, null);
	}

	/**
	 * A replacement for the valueOf() method that allows for nulls or padded
	 * Strings. If the value is null, defaultValue is returned. Otherwise, the
	 * string is trimmed and converted.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            the instance value to return if value is null.
	 * @return the defaultValue, or the converted value.
	 */
	public static Integer convertToInteger(String value, Integer defaultValue) {
		value = trimToNull(value);
		return value == null ? defaultValue : Integer.valueOf(value);
	}

	/**
	 * A replacement for the valueOf() method that allows for nulls or padded
	 * Strings. If the value is null, defaultValue is returned. Otherwise, the
	 * string is trimmed and converted.
	 * 
	 * @param value
	 *            a string.
	 * @return Returns the converted reference.
	 */
	public static Double convertToDouble(String value) {
		return convertToDouble(value, null);
	}

	/**
	 * A replacement for the valueOf() method that allows for nulls or padded
	 * Strings. If the value is null, defaultValue is returned. Otherwise, the
	 * string is trimmed and converted.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            the instance value to return if value is null.
	 * @return the defaultValue, or the converted value.
	 */
	public static Double convertToDouble(String value, Double defaultValue) {
		value = trimToNull(value);
		return value == null ? defaultValue : Double.valueOf(value);
	}

	/**
	 * A replacement for the valueOf() method that allows for nulls or padded
	 * Strings. If the value is null, defaultValue is returned. Otherwise, the
	 * string is trimmed and converted.
	 * 
	 * @param value
	 *            a string.
	 * @return Returns the converted reference.
	 */
	public static Float convertToFloat(String value) {
		return convertToFloat(value, null);
	}

	/**
	 * A replacement for the valueOf() method that allows for nulls or padded
	 * Strings. If the value is null, defaultValue is returned. Otherwise, the
	 * string is trimmed and converted.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            the instance value to return if value is null.
	 * @return the defaultValue, or the converted value.
	 */
	public static Float convertToFloat(String value, Float defaultValue) {
		value = trimToNull(value);
		return value == null ? defaultValue : Float.valueOf(value);
	}

	/**
	 * Parses a string for a date value, and returns a Date instance. If the
	 * value is null, the defaultValue is returned. Otherwise the value is
	 * parsed using the DateFormat pattern constants.
	 * 
	 * @param value
	 *            a date string.
	 * @param dateStyle
	 *            the given date formatting style.
	 * @param timeStyle
	 *            the given time formatting style.
	 * @param defaultValue
	 *            the instance value to return if value is null.
	 * @throws ParseException
	 *             If the given string cannot be parsed as a date.
	 * @return If value is null, the defaultValue reference will be returned.
	 *         Otherwise a Date.
	 */
	public static java.util.Date convertToDate(String value, int dateStyle,
			int timeStyle, java.util.Date defaultValue) throws ParseException {

		DateFormat format = null;

		value = trimToNull(value);
		if (value == null) {
			return defaultValue;
		}

		if (dateStyle >= 0 && timeStyle >= 0) {
			format = DateFormat.getDateTimeInstance(dateStyle, timeStyle);
		} else if (dateStyle >= 0) {
			format = DateFormat.getDateInstance(dateStyle);
		} else if (timeStyle >= 0) {
			format = DateFormat.getTimeInstance(timeStyle);
		} else {
			format = DateFormat.getInstance();
		}

		return format.parse(value);
	}

	/**
	 * Parses a string for a date value, and returns a Date. If the value is
	 * null, null is returned Otherwise the value is parsed using the default
	 * formatting style for the default locale.
	 * 
	 * @param value
	 *            a date string.
	 * @throws ParseException
	 *             If the given string cannot be parsed as a date.
	 * @return If value is null, null is returned. Otherwise a Date.
	 */
	public static java.util.Date convertToDate(String value)
			throws ParseException {
		return convertToDate(value, -1, -1, null);
	}

	/**
	 * Parses a string for a date value, and returns a Date. If the value is
	 * null, the defaultValue is returned. Otherwise the value is parsed using
	 * the default formatting style for the default locale.
	 * 
	 * @param value
	 *            a date string.
	 * @param defaultValue
	 *            the instance value to return if value is null.
	 * @throws ParseException
	 *             If the given string cannot be parsed as a date.
	 * @return If value is null, the defaultValue reference will be returned.
	 *         Otherwise a Date.
	 */
	public static java.util.Date convertToDate(String value,
			java.util.Date defaultValue) throws ParseException {
		return convertToDate(value, -1, -1, defaultValue);
	}

	/**
	 * Parses a string for a date value, and returns a Date instance. If the
	 * value is null, null is returned. Otherwise the value is parsed using the
	 * DateFormat pattern constants.
	 * 
	 * @param value
	 *            a date string.
	 * @param dateStyle
	 *            the given date formatting style.
	 * @param timeStyle
	 *            the given time formatting style.
	 * @throws ParseException
	 *             If the given string cannot be parsed as a date.
	 * @return If value is null, the defaultValue reference will be returned.
	 *         Otherwise a Date.
	 */
	public static java.util.Date convertToDate(String value, int dateStyle,
			int timeStyle) throws ParseException {
		return convertToDate(value, dateStyle, timeStyle, null);
	}

	/**
	 * Parses a string for a date value, and returns a Date instance. If the
	 * value is null, null is returned. Otherwise the value is parsed using the
	 * DateFormat pattern constants.
	 * 
	 * @param value
	 *            a date string.
	 * @param dateStyle
	 *            the given date formatting style.
	 * @throws ParseException
	 *             If the given string cannot be parsed as a date.
	 * @return If value is null, the defaultValue reference will be returned.
	 *         Otherwise a Date.
	 */
	public static java.util.Date convertToDate(String value, int dateStyle)
			throws ParseException {
		return convertToDate(value, dateStyle, -1, null);
	}

	/**
	 * Parses a string for a date value, and returns a Date instance. If the
	 * value is null, the defaultValue reference is returned. Otherwise the
	 * value is parsed using the DateFormat pattern constants.
	 * 
	 * @param value
	 *            a date string.
	 * @param dateStyle
	 *            the given date formatting style.
	 * @param defaultValue
	 *            the instance value to return if value is null.
	 * @throws ParseException
	 *             If the given string cannot be parsed as a date.
	 * @return If value is null, the defaultValue reference will be returned.
	 *         Otherwise a Date.
	 */
	public static java.util.Date convertToDate(String value, int dateStyle,
			java.util.Date defaultValue) throws ParseException {
		return convertToDate(value, dateStyle, -1, defaultValue);
	}

	/**
	 * Takes a String date and the format the date is in i.e. "10/10/2002",
	 * "MM/dd/yyyy"
	 * 
	 */
	public static java.util.Date convertToDate(String value, String format)
			throws ParseException {

		StringTokenizer st = new StringTokenizer(value, "/");
		String m = st.nextToken();
		String d = st.nextToken();
		String y = st.nextToken();

		if (m.length() == 1)
			m = "0" + m;
		if (d.length() == 1)
			d = "0" + d;

		// Here is where I introduce a Y3K Bug.
		if (y.length() == 2)
			y = "20" + y;

		return new SimpleDateFormat(format).parse(m + "/" + d + "/" + y);
	}

	/**
	 * Trims the value and converts zero-length Strings to null, or simply
	 * returns null if the value is null.
	 * 
	 * @param value
	 *            The string to trim or convert to null.
	 * @return Returns null if value is null, or value.trim().length()==0.
	 *         Otherwise returns the trimmed value.
	 */
	public static String trimToNull(String value) {
		return trimToNull((Object) value);
	}

	public static String trimToNull(Object value) {
		String buffer = null;

		if (value == null) {
			return null;
		} else {
			buffer = value.toString().trim();
			if (buffer.length() == 0) {
				return null;
			} else {
				return buffer;
			}
		}
	}

	/**
	 * A flexible substitute for the parseLong method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the Long's bounds, 0 will be
	 * returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @return the long represented by the value, or 0.
	 */
	public static long safeParseLong(String value) {
		return safeParseLong(value, 0);
	}

	/**
	 * A flexible substitute for the parseLong method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the Long's bounds, the defaultValue
	 * will be returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @param defaultValue
	 *            the defaultValue to return in case the value is null or cannot
	 *            be parsed.
	 * @return the long represented by the value, or the defaultValue.
	 */
	public static long safeParseLong(String value, long defaultValue) {
		return safeParseLong(value, Long.MIN_VALUE, Long.MAX_VALUE,
				defaultValue);
	}

	/**
	 * A flexible substitute for the parseLong method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the range specified by the minVal
	 * and maxVal parameters, the defaultValue will be returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @param minVal
	 *            the minimum value that will be accepted.
	 * @param maxVal
	 *            the maximum value that will be accepted.
	 * @param defaultValue
	 *            the defaultValue to return in case the value is null or cannot
	 *            be parsed.
	 * @return the long represented by the value, or the defaultValue.
	 */
	public static long safeParseLong(String value, long minVal, long maxVal,
			long defaultValue) {

		return (long) safeParseDouble(value, minVal, maxVal, defaultValue);
	}

	/**
	 * A flexible substitute for the parseFloat method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the float's bounds, 0 will be
	 * returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @return the float represented by the value, or 0.
	 */
	public static float safeParseFloat(String value) {
		return safeParseFloat(value, 0f);
	}

	/**
	 * A flexible substitute for the parseFloat method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the float's bounds, the defaultValue
	 * will be returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @param defaultValue
	 *            the defaultValue to return in case the value is null or cannot
	 *            be parsed.
	 * @return the float represented by the value, or the defaultValue.
	 */
	public static float safeParseFloat(String value, float defaultValue) {
		return (float) safeParseDouble(value, Float.MIN_VALUE, Float.MAX_VALUE,
				defaultValue);
	}

	/**
	 * A flexible substitute for the parseDouble method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the double's bounds, 0 will be
	 * returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @return the double represented by the value, or 0.
	 */
	public static double safeParseDouble(String value) {
		return safeParseDouble(value, 0d);
	}

	/**
	 * A flexible substitute for the parseDouble method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the double's bounds, the
	 * defaultValue will be returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @param defaultValue
	 *            the defaultValue to return in case the value is null or cannot
	 *            be parsed.
	 * @return the double represented by the value, or the defaultValue.
	 */
	public static double safeParseDouble(String value, double defaultValue) {
		return safeParseDouble(value, Double.MIN_VALUE, Double.MAX_VALUE,
				defaultValue);
	}

	/**
	 * A flexible substitute for the parseDouble method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the double's bounds, the
	 * defaultValue will be returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @param minVal
	 *            the minimum value that will be accepted.
	 * @param maxVal
	 *            the maximum value that will be accepted.
	 * @param defaultValue
	 *            the defaultValue to return in case the value is null or cannot
	 *            be parsed.
	 * @return the double represented by the value, or 0.
	 */
	public static double safeParseDouble(String value, double minVal,
			double maxVal, double defaultValue) {

		double newValue = defaultValue;
		value = trimToNull(value);

		if (value != null) {
			try {
				newValue = Double.parseDouble(value);

				if (newValue < minVal || newValue > maxVal) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException nfex) {
				newValue = defaultValue;
			}
		}

		return newValue;
	}

	/**
	 * A flexible substitute for the parseInt method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the int's bounds, 0 will be
	 * returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @return the int represented by the value, or 0.
	 */
	public static int safeParseInt(String value) {
		return safeParseInt(value, 0);
	}

	/**
	 * A flexible substitute for the parseInt method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the int's bounds, the defaultValue
	 * will be returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @param defaultValue
	 *            the defaultValue to return in case the value is null or cannot
	 *            be parsed.
	 * @return the int represented by the value, or the defaultValue.
	 */
	public static int safeParseInt(String value, int defaultValue) {
		return (int) safeParseLong(value, Integer.MIN_VALUE, Integer.MAX_VALUE,
				defaultValue);
	}

	/**
	 * A flexible substitute for the parseShort method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the short's bounds, 0 will be
	 * returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @param defaultValue
	 *            the defaultValue to return in case the value is null or cannot
	 *            be parsed.
	 * @return the short represented by the value, or the defaultValue.
	 */
	public static short safeParseShort(String value, short defaultValue) {
		return (short) safeParseLong(value, Short.MIN_VALUE, Short.MAX_VALUE,
				defaultValue);
	}

	/**
	 * A flexible substitute for the parseShort method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the short's bounds, the defaultValue
	 * will be returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @return the short represented by the value, or 0.
	 */
	public static short safeParseShort(String value) {
		return safeParseShort(value, (short) 0);
	}

	/**
	 * A flexible substitute for the parseByte method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the byte's bounds, 0 will be
	 * returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @return the byte represented by the value, or 0.
	 * 
	 */
	public static byte safeParseByte(String value) {
		return safeParseByte(value, (byte) 0);
	}

	/**
	 * A flexible substitute for the parseByte method. The method will accept
	 * null values, or padded numeric strings. If the string is null, not
	 * numeric, or the number falls outside the byte's bounds, the defaultValue
	 * will be returned.
	 * 
	 * @param value
	 *            the string to parse. May be a null reference.
	 * @param defaultValue
	 *            the defaultValue to return in case the value is null or cannot
	 *            be parsed.
	 * @return the byte represented by the value, or the defaultValue.
	 */
	public static byte safeParseByte(String value, byte defaultValue) {
		return (byte) safeParseLong(value, Byte.MIN_VALUE, Byte.MAX_VALUE,
				defaultValue);
	}

	/**
	 * The functions checks the incoming value for null, and converts it to a
	 * zero-length String. If the value is not null, then the String is trimmed
	 * and returned.
	 * 
	 * @param value
	 *            A String to check for null.
	 * @return Returns the trimmed value, or a zero-length string.
	 */
	public static String fixNull(String value) {
		return fixNull((Object) value, "");
	}

	/**
	 * This function converts a null object reference to a zero-length String.
	 * If the value is not null, then the object is converted to a String, is
	 * trimmed, then and returned.
	 * 
	 * @param value
	 *            An Object to check for null.
	 * @return Returns the Object's trimmed string value, or a zero-length
	 *         string.
	 */
	public static String fixNull(Object value) {
		return fixNull((Object) value, "");
	}

	/**
	 * The functions checks the incoming value for null, and returns the
	 * defaultValue, or if the value is not null, then the value is trimmed and
	 * returned.
	 * 
	 * @param value
	 *            The value to check for null.
	 * @param defaultValue
	 *            The default value to use if value is null.
	 * @return A trimmed instance of value, or the defaultValue parameter value.
	 */
	public static String fixNull(String value, String defaultValue) {
		return fixNull((Object) value, defaultValue);
	}

	/**
	 * The functions checks the incoming value for null, and returns the
	 * defaultValue, or if the value is not null, then the value is trimmed and
	 * returned.
	 * 
	 * @param value
	 *            The value to check for null.
	 * @param defaultValue
	 *            The default value to use if value is null.
	 * @return A trimmed instance of value, or the defaultValue parameter value.
	 */
	public static String fixNull(Object value, String defaultValue) {
		String buffer = trimToNull(value);

		if (buffer == null) {
			return defaultValue;
		} else {
			return buffer;
		}
	}

	/**
	 * The functions checks the incoming value for null. If the value is null, a
	 * zero-length string is returned. If not, the Date is formated using one of
	 * the date formatting constants from the java.text.DateFormat class.
	 * 
	 * @param value
	 *            A Date variable that contains only date data.
	 * @param dateStyle
	 *            One of the DateFormat constants that specify date formats.
	 * @return A zero-length string if value is null, or a formatted date
	 *         string.
	 */
	public static String fixNull(Date value, int dateStyle) {
		return fixNull(value, dateStyle, 0, "");
	}

	/**
	 * The functions checks the incoming value for null. If the value is null, a
	 * zero-length string is returned. If not, the Date is formated using the
	 * date and time formatting constants from the java.text.DateFormat class.
	 * 
	 * @param value
	 *            A Date variable that contains date and time data.
	 * @param dateStyle
	 *            One of the DateFormat constants that specify date formats.
	 * @param timeStyle
	 *            One of the DateFormat constants that specify time formats.
	 * @return A zero-length string if value is null, or a formatted date/time
	 *         string.
	 */
	public static String fixNull(Date value, int dateStyle, int timeStyle) {
		return fixNull(value, dateStyle, timeStyle, "");
	}

	/**
	 * The functions checks the incoming value for null. If the value is null,
	 * the defaultValue is returned. If not, the Date is formated using the date
	 * formatting constants from the java.text.DateFormat class.
	 * 
	 * @param value
	 *            A Date variable that contains date data.
	 * @param dateStyle
	 *            One of the DateFormat constants that specify date formats.
	 * @param defaultValue
	 *            A default value to use if the value is null.
	 * @return A zero-length string if value is null, or a formatted date
	 *         string.
	 */
	public static String fixNull(Date value, int dateStyle, String defaultValue) {
		return fixNull(value, dateStyle, 0, defaultValue);
	}

	/**
	 * The functions checks the incoming value for null. If the value is null,
	 * the defaultValue is returned. If not, the Date is formated using the date
	 * and time formatting constants from the java.text.DateFormat class.
	 * 
	 * @param value
	 *            A Date variable that contains date and time data.
	 * @param dateStyle
	 *            One of the DateFormat constants that specify date formats.
	 * @param timeStyle
	 *            One of the DateFormat constants that specify time formats.
	 * @param defaultValue
	 *            A default value to use if the value is null.
	 * @return A zero-length string if value is null, or a formatted date
	 *         string.
	 */
	public static String fixNull(Date value, int dateStyle, int timeStyle,
			String defaultValue) {
		DateFormat df = null;

		if (value == null) {
			return defaultValue;
		}

		if (timeStyle == 0) {
			df = DateFormat.getDateInstance(dateStyle);
		} else {
			df = DateFormat.getDateTimeInstance(dateStyle, timeStyle);
		}

		return df.format(value);
	}

	/**
	 * @param value
	 * @param pattern
	 * @param defaultValue
	 * @return
	 */
	public static String fixNull(Date value, String pattern, String defaultValue) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return value == null ? defaultValue : formatter.format(value);
	}

	/**
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static String fixNull(Date value, String pattern) {
		return fixNull(value, pattern, "");
	}

	/**
	 * Returns true if the value is null or a zero-length string.
	 * 
	 * @param value
	 *            The value to check for null or zero-length.
	 * @return True if null or zero-length.
	 */
	public static boolean isBlank(String value) {
		return (trimToNull(value) == null);
	}

	/**
	 * Returns true if the value is null or a zero-length string.
	 * 
	 * @param value
	 *            The value to check for null or zero-length.
	 * @return True if null or zero-length.
	 */
	public static boolean isBlank(Object value) {
		return (trimToNull(value) == null);
	}

	/**
	 * Replaces all occurances of the find that are found in value, with the
	 * expression string.
	 * 
	 * @param value
	 *            The string to search and replace upon.
	 * @param find
	 *            The string to search for.
	 * @param expression
	 *            The value used to replace occurances of the contents of the
	 *            find string.
	 * @return a string.
	 */
	public static String replace(String value, String find, String expression) {
		StringBuffer sb = null;
		int index = value.indexOf(find);
		int newIndex = 0;
		int count = 0;

		if (index == -1) {
			return value;
		}

		sb = new StringBuffer(value);

		while (index > -1) {
			newIndex = count * (expression.length() - 1) + index;
			sb.replace(newIndex, newIndex + find.length(), expression);
			index = value.indexOf(find, index + find.length());
			count++;
		}

		return sb.toString();
	}

	/**
	 * Splits a string into an array, by the provided delimiter.
	 * 
	 * @param msg
	 *            The string to split.
	 * @param delimiter
	 *            The delimiter that separate the string tokens.
	 * @return Returns an array that has a length equal to the number of
	 *         delimited tokens in the msg.
	 */
	public static String[] split(String msg, String delimiter) {
		if (msg == null || delimiter == null)
			return null;

		StringTokenizer st = new StringTokenizer(msg, delimiter);
		String[] array = new String[st.countTokens()];

		for (int i = 0; i < array.length; i++) {
			array[i] = st.nextToken();
		}

		return array;
	}

	/**
	 * Capilalizes the first character of a string.
	 * 
	 * @param value
	 *            The string to capitalize.
	 * @return Returns the capitalized string.
	 */
	public static String capitalize(String value) {
		if (value == null || value.length() < 1)
			return value;

		StringBuffer sb = new StringBuffer(value);
		char c = Character.toUpperCase(sb.charAt(0));
		sb.setCharAt(0, c);

		return sb.toString();
	}

	public static int[] convertToIntArray(String[] values) {
		return convertToIntArray(values, null);
	}

	public static int[] convertToIntArray(String[] values, int[] defaultValues) {
		if (values == null || values.length < 1) {
			return defaultValues;
		} else {
			int[] newValues = new int[values.length];

			for (int i = 0; i < values.length; i++) {
				newValues[i] = safeParseInt(values[i]);
			}

			return newValues;
		}
	}

	public static String[] convertToStrArray(Object source) {
		if (source == null) {
			return null;
		} else {
			String[] dest = new String[Array.getLength(source)];

			for (int i = 0; i < dest.length; i++) {
				dest[i] = String.valueOf(Array.get(source, i));
			}

			return dest;
		}
	}

	public static List convertToList(String source) {
		List newList = new ArrayList();
		newList.add(source);
		return newList;
	}

	public static String fill(char value, int count) {
		return fillBuffer(value, count).toString();
	}

	public static StringBuffer fillBuffer(char value, int count) {
		return fillBuffer(null, value, count);
	}

	public static StringBuffer fillBuffer(StringBuffer buffer, char value,
			int count) {
		if (buffer == null) {
			buffer = new StringBuffer(count);
		}

		for (int i = 0; i < count; i++) {
			buffer.append(value);
		}

		return buffer;
	}

	public static String join(Object array) {
		return join(array, ",");
	}

	public static String join(Object array, String delimiter) {
		if (!array.getClass().isArray()) {
			throw new IllegalArgumentException(
					"The array parameter must be an array.");
		}

		StringBuffer buffer = new StringBuffer();
		int length = Array.getLength(array);

		for (int i = 0; i < length; i++) {
			buffer.append(Array.get(array, i).toString());
			if (i != length - 1) {
				buffer.append(delimiter);
			}
		}

		return buffer.toString();
	}

	public static String safeToLower(String string) {
		if (string != null) {
			return string.toLowerCase();
		} else
			return null;
	}

	public static String safeToUpper(String string) {
		if (string != null) {
			return string.toUpperCase();
		} else
			return null;
	}

	public static String createRandom() throws Exception {
		String randomString;

		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
		randomString = new Integer(prng.nextInt()).toString();
		randomString = randomString.replace('-', ' ');

		return randomString;
	}

	public static String removePrefix(String string, String prefix) {
		String removePrefix = new String();
		for (int i = 0; i < string.length(); i++) {
			if (!string.substring(i, i + 1).equals(prefix)) {
				removePrefix = string.substring(i);
				break;
			}
		}

		return removePrefix;
	}

	public static String inputStreamToString(java.io.InputStream is) {
		java.io.DataInputStream din = new java.io.DataInputStream(is);

		StringWriter sw = new java.io.StringWriter();
		int c;

		try {
			while ((c = din.read()) != -1) {
				sw.write(c);
			}
			sw.flush();
			sw.close();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			try {
				is.close();
			} catch (Exception ex) {
			}
		}
		return sw.toString();
	}

	public static String addDecimal(String amount, int decimalPlace) {
		if (amount != null) {
			if (-1 == amount.indexOf('.')) {
				BigDecimal decimal = new BigDecimal(amount);
				amount = decimal.movePointLeft(decimalPlace).toString();
			}
		}
		return amount;
	}

	public static String safeReplace(String stringIn, String oldValue,
			String newValue) {
		return StringUtil.replace(StringUtil.fixNull(stringIn), oldValue,
				newValue);
	}

	public static String htmlEncodeXml(String xml, boolean encodeSpaces) {
		StringBuffer stringbuffer = new StringBuffer(xml.length());

		int i = xml.length();
		for (int j = 0; j < i; j++) {
			char c = xml.charAt(j);
			if (c == ' ') {
				if (encodeSpaces) {
					stringbuffer.append("&nbsp;");
				} else {
					stringbuffer.append(' ');
				}
			} else {
				if (c == '"')
					stringbuffer.append("&quot;");
				else if (c == '&')
					stringbuffer.append("&amp;");
				else if (c == '<')
					stringbuffer.append("&lt;");
				else if (c == '>')
					stringbuffer.append("&gt;");
				else if (c == '\n') {
					stringbuffer.append("<br>");
				} else {
					stringbuffer.append(c);
				}
			}

		}
		return stringbuffer.toString();
	}

	public static String wrap(String str, int wrapLength, String newLineStr,
			boolean wrapLongWords) {
		if (str == null) {
			return null;
		}
		if (newLineStr == null) {
			newLineStr = SystemUtils.LINE_SEPARATOR;
		}
		if (wrapLength < 1) {
			wrapLength = 1;
		}
		int inputLineLength = str.length();
		int offset = 0;
		StringBuffer wrappedLine = new StringBuffer(inputLineLength + 32);

		while ((inputLineLength - offset) > wrapLength) {
			if (str.charAt(offset) == ' ') {
				offset++;
				continue;
			}
			int spaceToWrapAt = str.lastIndexOf(' ', wrapLength + offset);

			if (spaceToWrapAt >= offset) {
				// normal case
				wrappedLine.append(str.substring(offset, spaceToWrapAt));
				wrappedLine.append(newLineStr);
				offset = spaceToWrapAt + 1;

			} else {
				// really long word or URL
				if (wrapLongWords) {
					// wrap really long word one line at a time
					wrappedLine.append(str.substring(offset, wrapLength
							+ offset));
					wrappedLine.append(newLineStr);
					offset += wrapLength;
				} else {
					// do not wrap really long word, just extend beyond limit
					spaceToWrapAt = str.indexOf(' ', wrapLength + offset);
					if (spaceToWrapAt >= 0) {
						wrappedLine
								.append(str.substring(offset, spaceToWrapAt));
						wrappedLine.append(newLineStr);
						offset = spaceToWrapAt + 1;
					} else {
						wrappedLine.append(str.substring(offset));
						offset = inputLineLength;
					}
				}
			}
		}

		// Whatever is left in line is short enough to just pass through
		wrappedLine.append(str.substring(offset));

		return wrappedLine.toString();
	}

	public static String replaceWhitespace(String input, String replacement) {

		StringBuffer buffer = new StringBuffer(input.trim());

		int i = 0;
		while ((i = buffer.indexOf(" ")) != -1) {
			if (buffer.charAt(i + 1) == ' ') {
				buffer.deleteCharAt(i);
			} else {
				buffer.replace(i, i + 1, "|");
			}
		}

		return buffer.toString();
	}

	public static String extractJustNumbers(String ediItem) {
		String returnValue = new String();
		StringBuffer sb = new StringBuffer();
		for (Character character : ediItem.toCharArray()) {
			if (Character.isDigit(character)) {
				sb.append(character);
			}
		}
		if (sb.length() > 0) {
			returnValue = sb.toString();
		}
		return returnValue;
	}
	
	public static String removeInvalidXMLChars(String input) {
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < input.length(); i++) {
			if (!XMLChar.isInvalid(input.charAt(i))) {
				buffer.append(input.charAt(i));
			}
		}
		
		return buffer.toString();
	}
}
