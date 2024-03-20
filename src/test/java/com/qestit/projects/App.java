package com.qestit.projects;

import com.qestit.common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.qestit.keywords.WebAction;

import java.util.List;

public class App {

    public static void main(String[] args) {
        // Setup WebDriver
        BaseTest base = new BaseTest();
        base.createBrowser();

        try {
            // Open the target URL
            WebAction.openWebsite("https://www.redbus.in/");
            WebAction.maximizeWindow();

            // Click on the calendar opener
            WebElement calendarOpener = WebAction.getWebElement(By.cssSelector("#onwardCal"));
            calendarOpener.click();

            String monthYear = "";
            while (!monthYear.contains("Dez")) { // Loop until the month is December
                // Try to extract month and year
                try {
                    WebElement monthYearElement = WebAction.getWebElement(By.cssSelector(".DayNavigator__IconBlock-qj8jdz-2.iZpveD:nth-of-type(2)"));
                    monthYear = monthYearElement.getText();
                } catch (NoSuchElementException e) {
                    System.out.println("Month/Year element not found");
                }

                // Try to extract holiday count, if available
                String holidayCount = "0"; // Default to 0 if no holidays
                try {
                    WebElement holidayCountElement = WebAction.getWebElement(By.cssSelector(".holiday_count"));
                    holidayCount = holidayCountElement.getText();
                } catch (NoSuchElementException e) {
                    System.out.println("No holidays this month");
                }

                System.out.println("Month/Year: " + monthYear);
                System.out.println("Holiday Count: " + holidayCount);

             // Count weekend days
                List<WebElement> days = WebAction.getWebElements(By.cssSelector(".DayTiles__CalendarDaysSpan-sc-1xum02u-1.bwoYtA"));
                int weekends = 0;
                for (WebElement day : days) {
                    // Assuming weekend days have a distinct class 'weekend-class'
                    if (day.getAttribute("class").contains("DayTiles__CalendarDaysSpan-sc-1xum02u-1 bwoYtA")) { // Replace 'weekend-class' with the actual class if weekends are marked
                        weekends++;
                        System.out.println(day.getText());
                    }
                }
                System.out.println("Number of Weekend Days: " + weekends);


                // Navigate to the next month if not December
                if (!monthYear.contains("Dez")) {
                    WebElement nextMonthButton = WebAction.getWebElement(By.cssSelector(".DayNavigator__IconBlock-qj8jdz-2.iZpveD:nth-of-type(3)"));
                    nextMonthButton.click();
                }
            }
        } finally {
            // Clean up by closing the browser
            base.closeDriver();
        }
    }
}
