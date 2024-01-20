package TestCasesFolder;

import PageObjects_FlightReservation.*;
import TestReusables.BaseTest;
import TestReusables.Constant;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FlightBookingTest extends BaseTest {
    RegistrationPage registrationPage;
    @Test(dataProvider = "getData")
    public void flightBookingTest(HashMap<String, String> input) throws IOException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(getProperty(Constant.FLIGHT_BOOKING_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.fillForm(input.get("FirstName"), input.get("LastName"), input.get("Email"),
                input.get("Password"), input.get("Street"), input.get("City"), input.get("State"), input.get("Zipcode"));

        RegistrationConfirmationPage registrationConfirmationPage = registrationPage.Register();
        Assert.assertTrue(registrationConfirmationPage.isAt());

        FlightSearchPage flightSearchPage = registrationConfirmationPage.goToFlightSearch();
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.setPassenger(input.get("Passengers"));

        FlightSelectionPage flightSelectionPage = flightSearchPage.searchFlight();
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlight();

        ConfirmationPage confirmationPage = flightSelectionPage.confirmFlight();
        Assert.assertTrue(confirmationPage.isAt());
        Assert.assertEquals(confirmationPage.getPrice(), input.get("ExpectedPrice"));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> JsonData = readJsonData("TestData/FlightBookingData.json");
        Object[][] data = new Object[JsonData.size()][1];
        for(int i = 0; i< JsonData.size(); i++){
            data[i][0] = JsonData.get(i);
        }
        return data;
    }
}
