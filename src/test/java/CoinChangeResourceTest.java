import jakarta.ws.rs.core.Response;
import nus.iss.apis.CoinChangeResource;
import nus.iss.models.CoinChangeRequest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoinChangeResourceTest {
    @Test
    public void testChangeCoinValidRequest() {
        CoinChangeResource resource = new CoinChangeResource();
        CoinChangeRequest req = new CoinChangeRequest();

        //positive use case
        req.setTargetAmount(7.03f);
        req.setDenominations(Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0));

        Response response = resource.changeCoin(req);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertFalse(((List<?>) response.getEntity()).isEmpty());
    }

    @Test
    public void testChangeNoCoin() {
        CoinChangeResource resource = new CoinChangeResource();

        CoinChangeRequest request = new CoinChangeRequest();
        //no coin use case, cos 30c cant make up of 20c and 50c
        request.setTargetAmount(0.3f);
        request.setDenominations(Arrays.asList(0.2, 0.5));

        Response response = resource.changeCoin(request);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(((java.util.List<?>) response.getEntity()).isEmpty());
    }

    @Test
    public void testInvalidRequest() {
        CoinChangeResource resource = new CoinChangeResource();

        CoinChangeRequest request = new CoinChangeRequest();

        request.setTargetAmount(100000f);
        request.setDenominations(Arrays.asList(0.2, 0.5));

        Response response = resource.changeCoin(request);

        assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());
    }
}
