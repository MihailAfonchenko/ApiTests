import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

public class RequestManager {

    public String get(String urlPath, HashMap<String, String> headers) throws IOException {
        var uri = DataProperties.getProperty("url") + urlPath;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        addHeader(httpget, headers);
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                return EntityUtils.toString(httpEntity);
            } else {
                return "";
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public CloseableHttpResponse post(String urlPath, HashMap<String, String> headers, String requestBody) throws IOException {
        var uri = DataProperties.getProperty("url") + urlPath;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(uri);
        addHeader(httppost, headers);
        httppost.setEntity(new StringEntity(requestBody));
        try {
            CloseableHttpResponse response = httpclient.execute(httppost);
            return response;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private void addHeader(HttpRequestBase httpRequest, HashMap<String, String> headers) {
        for (HashMap.Entry<String, String> item : headers.entrySet()) {
            httpRequest.setHeader(item.getKey(), item.getValue());
        }
    }

    public void verifyJsonObjects(String actual, String expected) {
        Assert.assertTrue(actual.toString().contains(expected.toString()));
    }
}
