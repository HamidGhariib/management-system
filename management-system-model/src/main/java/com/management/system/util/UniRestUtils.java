package com.management.system.util;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

public class UniRestUtils {
    static Gson gson = new Gson();
    protected final static Logger logger = LogManager.getLogger(UniRestUtils.class);

    public static String post(String url, Object request, HashMap<String, String> headers) {
        HttpRequestWithBody postRequest = Unirest.post(url);
        if (headers != null)
            postRequest.headers(headers);
        try {
            String requestGsonStr = gson.toJson(request);
            logger.info("UniRestUtils post method url is "+url);
            logger.info("UniRestUtils post method request is "+requestGsonStr);
            logger.info("UniRestUtils post method headers is "+headers);
            HttpResponse<String> response = postRequest.body(requestGsonStr).asString();
            if (200 != response.getStatus()) {
                throw new HttpClientErrorException(
                        HttpStatus.valueOf(response.getStatus()),
                        response.getBody());
            }
            return response.getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getClass().getSimpleName() + ":" + e.getMessage());
        }
    }

    public static String get(String url, HashMap<String, Object> parameters, Map<String, String> headers) {
        HttpRequest httpRequest = Unirest
                .get(url)
                .queryString(parameters)
                .header("Content-Type", "application/json");
        if (!StringUtils.isEmpty(headers))
            httpRequest.headers(headers);
        try {
            HttpResponse<String> response = httpRequest.asString();
            if (200 != response.getStatus()) {
                throw new HttpClientErrorException(
                        HttpStatus.valueOf(response.getStatus()),
                        response.getStatusText());
            }
            return response.getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getClass().getSimpleName() + ":" + e.getMessage());
        }
    }
}
