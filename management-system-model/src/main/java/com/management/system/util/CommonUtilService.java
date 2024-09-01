package com.management.system.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Service
public class CommonUtilService {

    private static final Gson gson = new Gson();

    public static <T> T convertModel(Object object, Class<T> tClass) {
        var json = gson.toJson(object);
        return gson.fromJson(json, tClass);

    }

    public static <T>T deserialize(byte[] data,Class<T> clazz) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return convertModel(is.readObject(),clazz);
    }
}
