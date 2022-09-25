import java.util.HashMap;

public class ContentBuilder {
    public static String contentBuilder(HashMap<String, String> data) {
        String result = "{";

        for (HashMap.Entry<String, String> item : data.entrySet()) {
            if (result != "{") {
                result += ",";
            }
            result = result + "\"" + item.getKey() + "\"" + ": \"" + item.getValue() + "\"";
        }
        return result + "}";
    }
}
