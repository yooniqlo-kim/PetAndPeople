package com.ssafy.petandpeople.infrastructure.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.HttpURLConnection;

public class ExternalApiClient {

    public static String requestApi(String requestUrl, String apiKey, String returnType, int pageIndex, int pageSize) throws Exception {
        String jsonResponse = "";

        String apiUrl = String.format("%s?apiKey=%s&returnType=%s&pageIndex=%d&pageSize=%d",
                requestUrl, apiKey, returnType, pageIndex, pageSize);

        HttpGet request = new HttpGet(apiUrl);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = client.execute(request);
        ) {
            isValidConnection(httpResponse);
            jsonResponse = EntityUtils.toString(httpResponse.getEntity());
            isValidResponse(jsonResponse);
        }

        return jsonResponse;
    }

    private static void isValidConnection(CloseableHttpResponse httpResponse) throws Exception {
        int statusCode = httpResponse.getStatusLine().getStatusCode();

        if (statusCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("HTTP 요청 실패 :" + statusCode);
        }
    }

    private static void isValidResponse(String jsonResponse) throws Exception {
        String responseCode = parseResponseCode(jsonResponse);

        if (!responseCode.equals("INFO-000")) {
            throw new Exception("유효하지 않은 응답 코드 : " + responseCode);
        }
    }

    private static String parseResponseCode(String jsonResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        return rootNode.path("CODE").asText();
    }

}
