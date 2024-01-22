package com.cimb.demo.common.utils;

import com.cimb.demo.entity.FormEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {
    private final static Gson gson = new Gson();

    public static JsonObject getJsonObject(String json) {
        return gson.fromJson(json, JsonObject.class);
    }

    public static JsonArray getJsonArray(final FormEntity form) {
        return gson.fromJson(form.getComponents(), JsonArray.class);
    }

    public static JsonElement getJsonObject(JsonObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            return jsonObject.get(key);
        }
        return null;
    }
}
