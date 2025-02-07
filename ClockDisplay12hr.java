/**
 * The ClockDisplay12hr class implements a 12-hour format digital clock display.
 * The clock shows hours and minutes with AM/PM notation.
 * The range of the clock is 12:00 AM (midnight) to 11:59 PM.
 *
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. When minutes roll over to zero, 
 * the hour increments. The AM/PM period also updates accordingly.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2026.02.29 (Modified for 12-hour format)
 */
public class ClockDisplay12hr
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String period;          // Stores "AM" or "PM"
    private String displayString;   // Simulates the actual display

    /**
     * Constructor for ClockDisplay12hr objects. 
     * This constructor creates a new clock set at 12:00 AM.
     */
    public ClockDisplay12hr()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        period = "AM";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay12hr objects.
     * Creates a new clock set at the specified hour and minute.
     */
    public ClockDisplay12hr(int hour, int minute, String period)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, period);
    }

    /**
     * This method should be called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if (minutes.getValue() == 0) {  // When minutes roll over
            hours.increment();
            if (hours.getValue() == 12) {
                period = period.equals("AM") ? "PM" : "AM";  // Toggle AM/PM
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour, minute, and AM/PM period.
     */
    public void setTime(int hour, int minute, String period)
    {
        if (hour < 1 || hour > 12 || minute < 0 || minute >= 60 || 
            (!period.equals("AM") && !period.equals("PM"))) {
            System.out.println("Invalid time input!");
            return;
        }
        hours.setValue(hour == 12 ? 12 : hour);  // Ensures 12 stays 12
        minutes.setValue(minute);
        this.period = period;
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM AM/PM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " " + period;
    }
}
