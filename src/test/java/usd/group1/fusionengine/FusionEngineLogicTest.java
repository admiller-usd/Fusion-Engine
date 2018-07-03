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
import usd.group1.fusionengine.responses.json.QueryResponseSimple;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FusionEngineLogicTest {

    // MockMvc allows us to test HTTP Requests
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void input_negative_lat_should_return_negative_lat()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "-3";
        String lon = "0";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponseSimple response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("-3.00000", response.getLatitude());
    }

    @Test
    public void input_positive_lat_should_return_positive_lat()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "32";
        String lon = "0";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponseSimple response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("32.00000", response.getLatitude());
    }

    @Test
    public void input_negative_lon_should_return_negative_lon()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "0";
        String lon = "-15";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponseSimple response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("-15.00000", response.getLongitude());
    }

    @Test
    public void input_positive_lon_should_return_positive_lon()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "0";
        String lon = "117";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponseSimple response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("117.00000", response.getLongitude());
    }

    @Test
    public void input_floating_points_should_return_floating_points()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "93.98765";
        String lon = "-27.54321";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponseSimple response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("93.98765", response.getLatitude());
        Assert.assertEquals("-27.54321", response.getLongitude());
    }

    @Test
    public void input_minutes_seconds_format_should_return_decimal_format()
            throws BadFormattedRequestException, NoUUIDFoundException {
        // Arrange
        String uuid = UUID.randomUUID().toString();
        String lat = "32 18' 23.1\" N";
        String lon = "117 15’ 41.3\" E";

        // Act
        FusionEngineLogic.convert(lat, lon);
        String latResult = FusionEngineLogic.getLatitude();
        String lonResult = FusionEngineLogic.getLongitude();

        FusionEngineDataStore.storeCoordinates(uuid, latResult, lonResult);
        QueryResponseSimple response = FusionEngineDataStore.retrieveCoordinates(uuid);
        System.out.println(response.getLatitude());

        // Assert
        Assert.assertEquals("32.30642", response.getLatitude());
        Assert.assertEquals("117.26147", response.getLongitude());
    }


    // Below methods test the RESTful interface

    @Test
    public void submit_only_one_coordinate_via_rest_should_throw_400_bad_request()
            throws Exception {
        // Arrange
        String request = "/submit?latitude=5";

        // Act and Assert
        this.mockMvc.perform(post(request)).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void query_for_missing_uuid_via_rest_should_throw_404_not_found()
            throws Exception {
        // Arrange
        String request = "/query?uuid=12345";

        // Act and Assert
        this.mockMvc.perform(get(request)).andDo(print()).andExpect(status().isNotFound());
    }

}