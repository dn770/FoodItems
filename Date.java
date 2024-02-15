/** 
 * Task 12 question 1 - The class Date
 * This class represents a Date object. 
 * 
 *  @Author Netanel Dahan 313565871
 *  @Version (2020a)
 */
public class Date {
    // declarations
    private int _day ;
    private int _month ;
    private int _year ;

    //default values
    private final int  DEFAULT_DAY= 1 ;
    private final int  DEFAULT_MONTH = 1 ;
    private final int  DEFAULT_YEAR= 2000 ;

    // first and last for day values
    private final int  FIRST_DAY = 1 ;
    private final int  LAST_DAY_LONG_MONTHS = 31 ;
    private final int  LAST_DAY_SHORT_MONTHS = 30 ;
    private final int  LAST_DAY_COMMON_FEBRUARY = 28 ;
    private final int  LAST_DAY_LEAP_FEBRUARY = 29 ;

    // min and max for month and year values
    private final int  FIRST_MONTH = 1 ;
    private final int LAST_MONTH = 12 ;
    private final int MAX_YEAR = 9999 ;
    private final int MIN_YEAR = 1000 ;

    private final int MONTHS_IN_YEAR = 12 ;
    private final int DAYS_IN_YEAR = 365 ;
    private final int DAYS_IN_WEEK = 7 ;

    // The names of the months
    private final int JANUARY = 1 ;
    private final int FEBRUARY = 2 ;
    private final int MARCH = 3 ;
    private final int APRIL = 4 ;
    private final int MAY = 5 ;
    private final int JUNE = 6 ;
    private final int JULY = 7 ;
    private final int AUGUST =8 ;
    private final int SEPTEMBER = 9 ;
    private final int OCTOBER = 10 ;
    private final int NOVEMBER = 11 ;
    private final int DECEMBER = 12 ;

    private final int NEXT = 1;

    // constractors definitions
    /**
     * Constructs a date object.
     * Construct a new date instance with the specified day, month and year.
     * day should be between 1-31 ,(if the month have this days).
     * month should be between 1-12.
     * year should be between 1000 - 9999, 
     * otherwise the date should be set to 01/01/2000.
     * @param day the day of the date
     * @param month the month of the date
     * @param year the year of the date
     */
    public Date(int day, int month, int year){
        if (validDate (day, month, year)){
            _day = day ;
            _month = month ; 
            _year = year ;
        }
        else{ // not a valid date
            _day = DEFAULT_DAY ;
            _month = DEFAULT_MONTH ;
            _year = DEFAULT_YEAR ;
        }
    } // end of the constractor

    /**
     * Copy constructor for Date.
     * Construct a date with the same variables as another date.
     * @param other The date object from which to construct the new date
     */
    public Date (Date other){
        _day = other._day ;
        _month = other._month ;
        _year = other._year ;
    }

    // method definitions
    /**
     * Returns the day of the date.
     * @return The day of the date
     */
    public int getDay() {
        return _day;
    }

    /**
     * Returns the month of the date.
     * @return The month of the date
     */
    public int getMonth() {
        return _month;
    }

    /**
     * Returns the year of the date.
     * @return The year of the date
     */
    public int getYear() {
        return _year;
    }

    /**
     * Changes the day of the date.
     *   If illegal number is received day will be unchanged.
     * @param dayToSet The new day 
     */
    public void setDay (int dayToSet){
        if (validDate (dayToSet, _month, _year))
            _day=dayToSet ;
    }

    /**
     * Changes the month of the date.
     *   If illegal number is received month will be unchanged.
     * @param monthToSet The new month 
     */
    public void setMonth (int monthToSet){
        if (validDate (_day, monthToSet, _year))
            _month=monthToSet ;
    }

    /**
     * Changes the year of the date.
     *   If illegal number is received year will be unchanged.
     * @param yearToSet The new year 
     */
    public void setYear (int yearToSet){
        if (validDate (_day, _month, yearToSet))
            _year=yearToSet ;
    }

    /**
     * Check if the received date is equal to this date.
     * @param other The date to be compared with this date
     * @return True if the received date is  equal to this date
     */
    public boolean equals(Date other) {
        if((_day==other._day)&&(_month==other._month)&&(_year==other._year))
            return true;
        // else - not equales dates
        return false;
    }

    /**
     * Check if this date is  before a received date.
     * @param other The date to check if this date is before
     * @return True if this date is before other date
     */
    public boolean before (Date other) {
        if (_year < other._year)
            return true ;
        if (_year > other._year)
            return false ;
        // same year
        if (_month < other._month)
            return true ;
        if (_month > other._month)
            return false ;
        // same month
        if (_day < other._day)
            return true ;
        // after or equals
        return false ;
    } // end of the method before

    /**
     * Check if this date is after a received date.
     * @param other The date to check if this date is after
     * @return True if this date is after other date
     */
    public boolean after (Date other)
    {
        return other.before(this); // 2 after 1 = 1 before 2
    }

    /**
     * Calculates the difference (in days) between two dates.
     * @param other The date to check the difference to. 
     * @return the number of days between the dates
     */
    public int difference (Date other) {
        // calculate the num of days since the beginning of the Christian counting for the dates,
        // and Calculates the difference in absolute value. 
        return   Math.abs (calculateDate (other._day, other._month,other._year)
            - calculateDate(this._day,this._month,this._year))  ;

    }

    /**
     * Return a string representation of this date (dd/mm/yyyy).
     * @return String representation of this date (dd/mm/yyyy)
     */
    public String toString() {
        String date="";

        if (_day<10) // Adds 0 before single digit value of day
            date+="0"+_day+"/";
        else // Double digit of day value
            date+=_day+"/";
        if (_month<10) // Adds 0 before single digit value of month
            date+="0"+_month+"/";
        else // Double digit of day value
            date+=_month+"/";    
        date+= _year ;

        return date;
    }

    /**
     * calculate the date of tomorrow. 
     * @return the date of tomorrow
     */ 
    public Date tomorrow () {
        int tomorrow= _day + NEXT ;
        int month= _month ;
        int year= _year ;

        if (!validDate(tomorrow, _month, _year)){
            if (month==DECEMBER){ // the last day of the year
                tomorrow= FIRST_DAY ;
                month= JANUARY ;
                year= _year+ NEXT;
            }
            else { // the last day of month
                month+= NEXT ;
                tomorrow= FIRST_DAY ;
            }
            if (year > MAX_YEAR) { // the last day of the max year or after it
                tomorrow = DEFAULT_DAY ;
                month = DEFAULT_MONTH ;
                year = DEFAULT_YEAR ;
            }
        }
        return (new Date (tomorrow, month, year)) ;
    }

    /**
     * calculate the day of the week that this date occurs on :
     *  0-Saturday 1-Sunday 2-Monday etc.
     *  @return the day of the week that this date occurs on
     */
    public int dayInWeek () { 
        int day = _day ;
        int month =_month ;
        int year = _year ;

        // According the given formula:
        // Day = (D + (26×(M+1))/10 + Y + Y/4 + C/4 - 2×C) mod 7
        if (month < MARCH) { // JANUARY or FEBRUARY
            year--;       
            month = month + MONTHS_IN_YEAR;  
        }
        int dIW = (day + (26*(month+NEXT))/10 + year%100 + year%100/4
                + year/100/4 - 2* (year/100))%DAYS_IN_WEEK;

        return Math.floorMod(dIW,7) ; // positive value

    }

    //Checks whether the date is valid according to the calendar.
    private boolean validDate ( int day, int month, int year) {
        if (day<FIRST_DAY || day>LAST_DAY_LONG_MONTHS || month<FIRST_MONTH|| month>LAST_MONTH )
            return false ;
        if (year<MIN_YEAR || year>MAX_YEAR )
            return false ;
        if (day == LAST_DAY_SHORT_MONTHS || day == LAST_DAY_LONG_MONTHS )
            switch (month) {
                case JANUARY:
                case MARCH:
                case MAY :
                case JULY :
                case AUGUST:
                case OCTOBER :
                case DECEMBER : 
                // months that have 31 days
                return true ; 
                case APRIL:
                case JUNE :
                case SEPTEMBER :
                case NOVEMBER :
                // months that have 30 days
                return (day==LAST_DAY_SHORT_MONTHS) ? true:false ;
                case FEBRUARY : // have 28 or 29 days only
                return false ;
            } // end of switch
        if (month== FEBRUARY && day== LAST_DAY_LEAP_FEBRUARY ) {
            if (year % 400 == 0) // leap year
                return true;
            else if (year % 4 == 0 && year %100 != 0) // leap year
                return true;
            else // Not a leap year- FEBRUARY have not 29 days
                return false;
        } // end of if block
        // else- valid date
        return true ;

    } // end of the method validDate

    // computes the day number since the beginning of the Christian counting of years 
    private int calculateDate (int day, int month,int year) { 
        if (month < MARCH) { // JANUARY or FEBRUARY
            year--;       
            month = month + MONTHS_IN_YEAR;  
        } 
        // a given formula :
        return (DAYS_IN_YEAR * year + year/4 - year/100 + year/400 + ((month+NEXT) * 306)/10 + (day - 62));
    }      
}// end of class Date