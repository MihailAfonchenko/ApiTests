import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class ProjectLanguagesTest {
    public static RequestManager requestManager;

    @BeforeTest
    public static void setup() {
        requestManager = new RequestManager();
    }

    @Test
    public void projectLanguagesTest() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", DataProperties.getProperty("accept"));
        headers.put("Authorization", DataProperties.getProperty("token"));
        var entity = requestManager.get(DataProperties.getProperty("urnLanguages"), headers);
        requestManager.verifyJsonObjects(entity, DataProperties.getProperty("language"));
    }
}
