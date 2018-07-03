package usd.group1.fusionengine;

import org.junit.Assert;
import org.junit.Ignore;
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

}