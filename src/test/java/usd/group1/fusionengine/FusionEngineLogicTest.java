package usd.group1.fusionengine;

import org.junit.Assert;
import org.junit.Test;
import usd.group1.fusionengine.exceptions.BadFormattedRequestException;
import usd.group1.fusionengine.exceptions.NoUUIDFoundException;
import usd.group1.fusionengine.responses.json.QueryResponseSimple;

import java.util.UUID;

public class FusionEngineLogicTest {

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

        // Assert
        Assert.assertEquals(-3, response.getLatitude());
    }

}