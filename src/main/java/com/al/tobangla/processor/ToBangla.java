package com.al.tobangla.processor;

import com.al.tobangla.utils.ProcessType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author al.noman.uap@gmail.com
 * @see en-us to bn conversions date, time, calendar, ordinal indicator, amount, numbers
 */
public class ToBangla {

    private static final String DD_MM_YYYY = "dd/MM/yyyy";
    private static final String HH_MM_SS = "HH:mm:ss";
    private static final String invalidFormat = "Wrong Format";

    private static volatile ToBangla mInstance = null;

    //initializing vars
    private ToBangla() {
    }

    //instantiating with thread safe mechanism
    public static ToBangla getInstance() {
        if (mInstance == null) {
            synchronized (ToBangla.class) {
                if (mInstance == null) {
                    mInstance = new ToBangla();
                }
            }
        }
        return mInstance;
    }

    /*
    * Provides the converted number as String in BANGLA (UTF-8)
    * @param date formatted String EN-US
    * @throws NumberFormatException
    * @return date in BN UTF-8
    */
    public String changeByChar(final String date) throws NumberFormatException {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < date.length(); i++) {
            //convert each numeric character to bangla
            formatted.append(numberConverter(date.charAt(i)));
        }
        return formatted.toString();
    }

    /*
    * Convert EN-US Days to BN
    * @param day in String EN-US
    * @return day in String UTF-8
    */
    public String getDay(final String day) {

        switch (day.toLowerCase().trim()) {
            case "sunday":
                return "রবিবার";
            case "monday":
                return "সোমবার";
            case "tuesday":
                return "মঙ্গলবার";
            case "wednesday":
                return "বুধবার";
            case "thursday":
                return "বৃহস্পতিবার";
            case "friday":
                return "শুক্রবার";
            case "saturday":
                return "শনিবার";
            default:
                return day;
        }
    }

    /*
    * Convert EN-US number characters to Bangla
    * @param character EN-US
    * @return BN numeric character
    */
    private char numberConverter(final char en) {

        switch (en) {
            case '1':
                return '১';
            case '2':
                return '২';
            case '3':
                return '৩';
            case '4':
                return '৪';
            case '5':
                return '৫';
            case '6':
                return '৬';
            case '7':
                return '৭';
            case '8':
                return '৮';
            case '9':
                return '৯';
            case '0':
                return '০';
            default:
                return en;
        }
    }

    /*
    * get todays' date in bangla
    * @return date in String UTF-8
    */
    public String getTodayDate() {
        return changeByChar(new SimpleDateFormat(DD_MM_YYYY).format(Calendar.getInstance().getTime()));
    }

    /*
    * get today's name in bangla
    * @return day in String UTF-8
    */
    public String getToday() {
        return getDay(new SimpleDateFormat("EEEE", Locale.US).format(Calendar.getInstance().getTime()));
    }

    /*
    * get any timestamp in bangla
    * @param time in String EN-US
    * @return time in String UTF-8
    */
    public String getTime(final String time) {
        return changeByChar(time);
    }

    /*
    * get any date in bangla
    * @param date in String EN-US
    * @return date in String UTF-8
    */
    public String getDate(final String time) {
        return changeByChar(time);
    }

    /*
    * get current time in bangla
    * @return time in String UTF-8
    */
    public String getCurrentTime() {
        return changeByChar(new SimpleDateFormat(HH_MM_SS).format(Calendar.getInstance().getTime()));
    }


    /*
    * Ordinal Indicators corresponds to the suffixes -st, -nd, -rd, -th
    * @param the targer number pattern with those suffixes
    * @param calendar type | normal type
    * @return in BN
    */
    public String getOrdinalIndicator(final String text, final ProcessType mode) {

        switch (mode) {
            case ORDINAL_INDICATOR_FOR_DATE:
                return processForDateTypeOrdinalIndicator(text);
            case ORDINAL_INDICATOR_FOR_NUMERIC_ORDER:
                return processForNumericOrdinalIndicator(text);
            default:
                return invalidFormat;
        }
    }

    /*
    * @param Month name as String or From System in MMMM pattern
    * @return Month in BN
    */
    public String getMonth(final String month) {

        switch (month.toLowerCase().trim()) {

            case "january":
            case "jan":
                return "জানুয়ারী";

            case "february":
            case "feb":
                return "ফেব্রুয়ারি";

            case "march":
            case "mar":
                return "মার্চ";

            case "april":
            case "apr":
                return "এপ্রিল";

            case "may":
                return "মে";

            case "june":
            case "jun":
                return "জুন";

            case "july":
            case "jul":
                return "জুলাই";

            case "august":
            case "aug":
                return "অগাস্ট";

            case "september":
            case "sep":
                return "সেপ্টেম্বর";

            case "october":
            case "oct":
                return "অক্টোবর";

            case "november":
            case "nov":
                return "নভেম্বর";

            case "december":
            case "dec":
                return "ডিসেম্বর";
            default:
                return invalidFormat;
        }
    }

    /*
    * @param Month name as String or From System in MMMM pattern
    * @return Month in BN
    */
    public String getMonthByNumber(final String month) {

        switch (Integer.parseInt(month)) {
            case 1:
                return "জানুয়ারী";
            case 2:
                return "ফেব্রুয়ারি";
            case 3:
                return "মার্চ";
            case 4:
                return "এপ্রিল";
            case 5:
                return "মে";
            case 6:
                return "জুন";
            case 7:
                return "জুলাই";
            case 8:
                return "অগাস্ট";
            case 9:
                return "সেপ্টেম্বর";
            case 10:
                return "অক্টোবর";
            case 11:
                return "নভেম্বর";
            case 12:
                return "ডিসেম্বর";
            default:
                return invalidFormat;
        }
    }

    /*
    * @param Targer number in [0-9]+  -st, -nd, rd, -th
    * @return in BN format normal format
    */
    private String processForNumericOrdinalIndicator(String text) {

        int number = Integer.parseInt(text.replaceAll("\\D+", ""));

        if (number == 0 || number > 10) {
            return changeByChar("" + number) + "তম";
        } else if (number == 1 || number == 5 || number == 7 || number == 8 || number == 9 || number == 10) {
            return changeByChar("" + number) + "ম";
        } else if (number == 2 || number == 3) {
            return changeByChar("" + number) + "য়";
        } else if (number == 4) {
            return changeByChar("" + number) + "র্থ";
        } else if (number == 6) {
            return changeByChar("" + number) + "ষ্ঠ";
        } else {
            return invalidFormat;
        }
    }

    /*
    * @param Targer number in [0-9]+  -st, -nd, rd, -th
    * @return in BN format normal format
    */
    public String getNumericOrderIndicator(String digit) {

        int number = Integer.parseInt(digit);

        if (number == 0 || number > 10) {
            return changeByChar("" + number) + "তম";
        } else if (number == 1 || number == 5 || number == 7 || number == 8 || number == 9 || number == 10) {
            return changeByChar("" + number) + "ম";
        } else if (number == 2 || number == 3) {
            return changeByChar("" + number) + "য়";
        } else if (number == 4) {
            return changeByChar("" + number) + "র্থ";
        } else if (number == 6) {
            return changeByChar("" + number) + "ষ্ঠ";
        } else {
            return invalidFormat;
        }
    }

    /*
    * @param Targer number in [0-9]+  -st, -nd, rd, -th
    * @return in BN format
    * @throws NumberFormatError
    */
    private String processForDateTypeOrdinalIndicator(String text) throws NumberFormatException {

        int number = Integer.parseInt(text.replaceAll("\\D+", ""));

        if (number == 0) {
            return changeByChar("" + number) + "তম";
        } else if (number == 1) {
            return changeByChar("" + number) + "লা";
        } else if (number >= 5 && number <= 18) {
            return changeByChar("" + number) + "ই";
        } else if (number == 2 || number == 3) {
            return changeByChar("" + number) + "রা";
        } else if (number == 4) {
            return changeByChar("" + number) + "ঠা";
        } else if (number >= 19 && number <= 31) {
            return changeByChar("" + number) + "শে";
        } else {
            return invalidFormat;
        }
    }

    /*
        * @param Target number in [0-9]+  -st, -nd, rd, -th
        * @return in BN format
        * @throws NumberFormatError
        */
    public String getNumericCalendarIndicator(String digit) throws NumberFormatException {

        int number = Integer.parseInt(digit);

        if (number == 0) {
            return changeByChar("" + number) + "তম";
        } else if (number == 1) {
            return changeByChar("" + number) + "লা";
        } else if (number >= 5 && number <= 18) {
            return changeByChar("" + number) + "ই";
        } else if (number == 2 || number == 3) {
            return changeByChar("" + number) + "রা";
        } else if (number == 4) {
            return changeByChar("" + number) + "ঠা";
        } else if (number >= 19 && number <= 31) {
            return changeByChar("" + number) + "শে";
        } else {
            return invalidFormat;
        }
    }

    /*
      * @param date in String
      * @return Ordinal bangla String
      * @return Error Message
      */
    public String getBanglaOrdinalDate(String date) {

        try {
            String[] dates = date.split("-");

            return getNumericCalendarIndicator(dates[2]) + " " + getMonthByNumber(dates[1]) + ", " + getNumber(dates[0]);
        } catch (IndexOutOfBoundsException iobe) {
            throw new IndexOutOfBoundsException(invalidFormat);
        }
    }

    /*
    * @param number as string EN-US
    * @return number as BN
    */
    public String getNumber(final String number) {
        return changeByChar(number);
    }

}
