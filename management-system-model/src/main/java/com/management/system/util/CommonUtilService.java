package com.management.system.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class CommonUtilService {

    private static final Gson gson = new Gson();

    public static <T> T convertModel(Object object, Class<T> tClass) {
        var json = gson.toJson(object);
        return gson.fromJson(json, tClass);

    }
}
