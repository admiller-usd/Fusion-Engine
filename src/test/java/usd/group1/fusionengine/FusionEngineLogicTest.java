/*                                                 *
 * ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
 *                  Programmers:                   *
 *                  Austin Miller                  *
 *                 Kathrine Lavieri                *
 *                                                 */
package usd.group1.fusionengine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import usd.group1.fusionengine.exceptions.BadFormattedRequestException;
import usd.group1.fusionengine.exceptions.NoUUIDFoundException;
import usd.group1.fusionengine.responses.json.QueryResponse;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class that tests both the logic and restful components of application.
 * Refer to the "Test Cases" submitted as part of GROUP 1's deliverable.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FusionEngineLogicTest {

    // MockMvc allows us to test HTTP Requests
    @Autowired
    private MockMvc mockMvc;

    // TEST CASE 1: Negative LAT/LONG input
    @Test
    public void input_negative_lat_long_should_return_negative_lat_long()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "-3";
        String lon = "-117";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);

        // Assert
        Assert.assertEquals("-3.00000", response.getLatitude());
        Assert.assertEquals("-117.00000", response.getLongitude());
    }

    // TEST CASE 2: Positive LAT/LONG input
    @Test
    public void input_positive_lat_long_should_return_positive_lat_long()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "32";
        String lon = "117";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);

        // Assert
        Assert.assertEquals("32.00000", response.getLatitude());
        Assert.assertEquals("117.00000", response.getLongitude());
    }

    // TEST CASE 3: Hemisphere Validation/Character validation
    @Test
    public void input_north_and_east_should_return_positive_lat_and_positive_long()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "32 18\' 23.1\" N";
        String lon = "117 15\' 41.3\" E";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("32.30642", response.getLatitude());
        Assert.assertEquals("117.26147", response.getLongitude());
    }

    // TEST CASE 4: Hemisphere Validation/Character validation
    @Test
    public void input_south_and_west_should_return_negative_lat_and_negative_long()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "32 18\' 23.1\" S";
        String lon = "117 15\' 41.3\" W";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("-32.30642", response.getLatitude());
        Assert.assertEquals("-117.26147", response.getLongitude());
    }

    // TEST CASE 5: Submit 3 Inputs
    @Test
    public void submit_three_values_via_rest_should_ignore_other_values_and_return_201_created()
            throws Exception {
        // Arrange
        String request = "/submit?latitude=10.12345&longitude=-112.09876&random=blahgarbage";

        // Act and Assert
        mockMvc.perform(post(request)).andDo(print()).andExpect(status().isCreated());
    }

    // TEST CASE 6: Submit only 1 input
    @Test
    public void submit_only_one_coordinate_via_rest_should_throw_400_bad_request()
            throws Exception {
        // Arrange
        String request = "/submit?latitude=5";

        // Act and Assert
        mockMvc.perform(post(request)).andDo(print()).andExpect(status().isBadRequest());
    }

    // TEST CASE 7: Query for a non-existent UUID
    @Test
    public void query_for_missing_uuid_via_rest_should_throw_404_not_found()
            throws Exception {
        // Arrange
        String request = "/query?uuid=12345";

        // Act and Assert
        mockMvc.perform(get(request)).andDo(print()).andExpect(status().isNotFound());
    }

    // TEST CASE 8: Input minimum values
    @Test
    public void input_minimum_values_should_return_minimum_values()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "-90.00000";
        String lon = "-180.00000";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("-90.00000", response.getLatitude());
        Assert.assertEquals("-180.00000", response.getLongitude());
    }

    // TEST CASE 9: Input maximum values
    @Test
    public void input_maximum_values_should_return_maximum_values()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "90.00000";
        String lon = "180.00000";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("90.00000", response.getLatitude());
        Assert.assertEquals("180.00000", response.getLongitude());
    }

    // TEST CASE 10: Input latitude over maximum value
    @Test
    public void input_latitude_over_maximum_value_via_rest_should_throw_400_bad_request()
            throws Exception {
        // Arrange
        String request = "/submit?latitude=91&longitude=12";

        // Act and Assert
        mockMvc.perform(post(request)).andDo(print()).andExpect(status().isBadRequest());
    }

    // TEST CASE 11: Input longitude over maximum value
    @Test
    public void input_longitude_over_maximum_value_via_rest_should_throw_400_bad_request()
            throws Exception {
        // Arrange
        String request = "/submit?latitude=87&longitude=185";

        // Act and Assert
        mockMvc.perform(post(request)).andDo(print()).andExpect(status().isBadRequest());
    }

    // TEST CASE 12: Input latitude under minimum value
    @Test
    public void input_latitude_under_minimum_value_via_rest_should_throw_400_bad_request()
            throws Exception {
        // Arrange
        String request = "/submit?latitude=-93&longitude=12";

        // Act and Assert
        mockMvc.perform(post(request)).andDo(print()).andExpect(status().isBadRequest());
    }

    // TEST CASE 13: Input longitude under minimum value
    @Test
    public void input_longitude_under_minimum_value_via_rest_should_throw_400_bad_request()
            throws Exception {
        // Arrange
        String request = "/submit?latitude=-12&longitude=-182";

        // Act and Assert
        mockMvc.perform(post(request)).andDo(print()).andExpect(status().isBadRequest());
    }

    // TEST CASE 14: Degrees Mins Sec input
    @Test
    public void input_minutes_seconds_format_should_return_decimal_format()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "32 18\' 23.1\" N";
        String lon = "117 15\' 41.3\" E";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("32.30642", response.getLatitude());
        Assert.assertEquals("117.26147", response.getLongitude());
    }

    // TEST CASE 15: Degrees and Decimal Minutes Input
    @Test
    public void input_minutes_format_should_return_decimal_format()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        // 32° 18.385' N
        // 118° 27.876’ E
        String uuid = UUID.randomUUID().toString();
        String lat = "32 18.358\' N";
        String lon = "118 27.876\' E";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("32.30000", response.getLatitude());
        Assert.assertEquals("118.45000", response.getLongitude());
    }

    // TEST CASE 16: Decimal Degrees Input
    @Test
    public void input_floating_points_should_return_floating_points()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "83.98765";
        String lon = "-27.54321";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponse response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("83.98765", response.getLatitude());
        Assert.assertEquals("-27.54321", response.getLongitude());
    }

    // TEST CASE 17: Mismatched input types
    @Test
    public void submit_two_coordinates_via_rest_of_different_types_should_return_200_bad_request()
            throws Exception {
        // Arrange
        String request = "/submit?latitude=10.12345&longitude=32 18.358\' N";

        // Act and Assert
        mockMvc.perform(post(request)).andDo(print()).andExpect(status().isBadRequest());
    }

}