package org.example.domain.validation;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new NullObjectException(message);
        }
        return obj;
    }
    public static String requireNonBlank(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new EmptyStringException(message);
        }
        return value;
    }

    public static <N extends Number> N requireNonNegative(N number, String message) {
        if (number == null) {
            throw new NullObjectException(message);
        }
        if (number.doubleValue() < 0) {
            throw new NegativeNumberException(message);
        }
        return number;
    }

    public static LocalDate requireNotInFuture(LocalDate date, String message) {
        if (date == null) {
            throw new NullObjectException(message);
        }
        if (date.isAfter(LocalDate.now())) {
            throw new DateInFutureException(message);
        }
        return date;
    }

    public static void requireEqual(Object a, Object b, String message) {
        if (!Objects.equals(a, b)) {
            throw new ObjectsNonEqualException(message);
        }
    }

    public static <T> Collection<T> requireNonEmptyCollection(Collection<T> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new EmptyCollectionException(message);
        }
        return collection;
    }

    public static <T> T requireNonNullOrDefault(T obj, T defaultObj) {
        if(obj == null) {
            if(defaultObj == null) {
                throw new NullObjectException("Both object and default object are null");
            }
            return defaultObj;
        }
        return obj;
    }

    public static String requireCorrectStringRegex(String value, String regex, String message) {
        if (value == null || !value.matches(regex)) {
            throw new InvalidStringFormatException(message);
        }
        return value;
    }

    public static <T extends Number> T requireMinimumValue(T number, T minValue, String message) {
        if (number == null || minValue == null) {
            throw new NullObjectException(message);
        }
        if (number.doubleValue() < minValue.doubleValue()) {
            throw new ValueBelowMinimumException(message);
        }
        return number;
    }

    public static <T extends Number> T requireMaximumValue(T number, T maxValue, String message) {
        if (number == null || maxValue == null) {
            throw new NullObjectException(message);
        }
        if (number.doubleValue() > maxValue.doubleValue()) {
            throw new ValueAboveMaximumException(message);
        }
        return number;
    }

    public static <T extends Number> T requirePositiveNumber(T number, String message) {
        if (number == null) {
            throw new NullObjectException(message);
        }
        if (number.doubleValue() <= 0) {
            throw new NonPositiveNumberException(message);
        }
        return number;
    }

    public static <T extends Comparable<T>> void requiresGreaterThen(T a, T b, String message) {
        if (a == null || b == null) {
            throw new NullObjectException(message);
        }
        if (a.compareTo(b) <= 0) {
            throw new InvalidComparisonException(message);
        }
    }

    public static <T> void requireGreaterThen(T a, T b, Comparator<T> comparator, String message) {
        if (a == null || b == null || comparator == null) {
            throw new NullObjectException(message);
        }
        if (comparator.compare(a, b) <= 0) {
            throw new InvalidComparisonException(message);
        }
    }

    public static <T extends Comparable<T>> void requireLessThen(T a, T b, String message) {
        if (a == null || b == null) {
            throw new NullObjectException(message);
        }
        if (a.compareTo(b) >= 0) {
            throw new InvalidComparisonException(message);
        }
    }

    public static <T> void requireLessThen(T a, T b, Comparator<T> comparator, String message) {
        if (a == null || b == null || comparator == null) {
            throw new NullObjectException(message);
        }
        if (comparator.compare(a, b) >= 0) {
            throw new InvalidComparisonException(message);
        }
    }

    public static <T extends Number> void requireValueInRange(T value, T minValue, T maxValue, String message) {
        if (value == null || minValue == null || maxValue == null) {
            throw new NullObjectException(message);
        }
        if (value.doubleValue() < minValue.doubleValue() || value.doubleValue() > maxValue.doubleValue()) {
            throw new ValueOutOfRangeException(message);
        }
    }

    public static void requireCondition(boolean condition, String message) {
        if (!condition) {
            throw new ConditionNotMetException(message);
        }
    }
}

