package com.al.tobangla;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author al.noman.uap@gmail.com
 * @see //en-us to bn conversions date, time, calendar, ordinal indicator.
 */
public class ToBangla {

    private static ToBangla mInstance = null;
    private static final String Err = "Wrong Format";

    /*Mode Calendar and Normal*/
    public static final int MODE_NORMAL = 0x00;
    public static final int MODE_CALENDAR = 0x01;

    //initializing vars
    private ToBangla() {
    }

    //instantiating with thread safe mechanism
    public static synchronized ToBangla getInstance() {
        if (mInstance == null) {
            return (new ToBangla());
        }
        return mInstance;
    }

    /*
    * Provides the converted number as String in BANGLA (UTF-8)
    * @param date formatted String EN-US
    * @throws NumberFormatException
    * @return date in BN UTF-8
    */
    public String getDate(final String date) throws NumberFormatException {
        String formatted = "";
        for (int i = 0; i < date.length(); i++) {
            //convert each character to bangla
            formatted += numberConverter(date.charAt(i));
        }
        return formatted;
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
        return getDate(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
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
        return getDate(time);
    }

    /*
    * get current time in bangla
    * @return time in String UTF-8
    */
    public String getCurrentTime() {
        return getDate(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
    }


    /*
    * Ordinal Indicators corresponds to the suffixes -st, -nd, -rd, -th
    * @param the targer number pattern with those suffixes
    * @param calendar type | normal type
    * @return in BN
    */
    public String getOrdinalIndicator(final String indicatorPatter, final int mode) {

        switch (mode) {
            case MODE_NORMAL:
                return processSimpleIndicator(indicatorPatter);
            case MODE_CALENDAR:
                return processCalendarIndicator(indicatorPatter);
            default:
                return Err;
        }
    }

    /*
    * @param Month name as String or From System in MMMM pattern
    * @return Month in BN
    */
    public String getMonth(final String month) {
        switch (month.toLowerCase().trim()) {
            case "january":
                return "জানুয়ারী";
            case "february":
                return "ফেব্রুয়ারি";
            case "march":
                return "মার্চ";
            case "april":
                return "এপ্রিল";
            case "may":
                return "মে";
            case "june":
                return "জুন";
            case "july":
                return "জুলাই";
            case "august":
                return "অগাস্ট";
            case "september":
                return "সেপ্টেম্বর";
            case "october":
                return "অক্টোবর";
            case "november":
                return "নভেম্বর";
            case "december":
                return "ডিসেম্বর";
            default:
                return Err;
        }
    }

    /*
    * @param Targer number in [0-9]+  -st, -nd, rd, -th
    * @return in BN format normal format
    */
    private String processSimpleIndicator(String indicatorPattern) {
        int number = Integer.parseInt(indicatorPattern.replaceAll("\\D+", ""));

        if (number == 0 || number > 10) {
            return getDate("" + number) + "তম";
        } else if (number == 1 || number == 5 || number == 7 || number == 8 || number == 9 || number == 10) {
            return getDate("" + number) + "ম";
        } else if (number == 2 || number == 3) {
            return getDate("" + number) + "য়";
        } else if (number == 4) {
            return getDate("" + number) + "র্থ";
        } else if (number == 6) {
            return getDate("" + number) + "ষ্ঠ";
        } else {
            return Err;
        }
    }

    /*
    * @param Targer number in [0-9]+  -st, -nd, rd, -th
    * @return in BN format
    * @throws NumberFormatError
    */
    private String processCalendarIndicator(String indicatorPattern) throws NumberFormatException{
        int number = Integer.parseInt(indicatorPattern.replaceAll("\\D+", ""));

        if (number == 0) {
            return getDate("" + number) + "তম";
        } else if (number == 1) {
            return getDate("" + number) + "লা";
        } else if (number >= 5 && number <= 18) {
            return getDate("" + number) + "ই";
        } else if (number == 2 || number == 3) {
            return getDate("" + number) + "রা";
        } else if (number == 4) {
            return getDate("" + number) + "ঠা";
        } else if (number >= 19 && number <= 31) {
            return getDate("" + number) + "শে";
        } else {
            return Err;
        }
    }

    /*
    * @param number as string EN-US
    * @return number as BN
    */
    public String getNumber(final String number){
        return getDate(number);
    }

}
