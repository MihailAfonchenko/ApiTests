import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class CreateProjectTest {
    public static RequestManager requestManager;

    @BeforeTest
    public static void setup() {
        requestManager = new RequestManager();
    }

    @Test
    public void createProjectTest() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", DataProperties.getProperty("accept"));
        headers.put("Authorization", DataProperties.getProperty("token"));
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", "TestProject");
        data.put("description", "The project was created to test the API");
        var content = ContentBuilder.contentBuilder(data);
        var response = requestManager.post(DataProperties.getProperty("urnRepo"), headers, content);
        var status = response.getStatusLine().getStatusCode();
        Assert.assertEquals(status, 201);
    }
}
