package com.foxkiev.app.utils.gson;

import com.foxkiev.app.model.models.Attribute;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by lipcha on 02.03.18.
 */

public class AttributeAdapter extends TypeAdapter<Attribute> {

    @Override
    public void write(JsonWriter out, Attribute value) throws IOException {
        if (value == null) {
            out.nullValue();
        }
    }

    @Override
    public Attribute read(JsonReader in) throws IOException {
        final Attribute attribute = new Attribute();

        while (in.hasNext()){
            JsonToken token = in.peek();

            if (token.equals(JsonToken.BEGIN_ARRAY)){
                in.beginArray();
            }

            if (in.peek().equals(JsonToken.BEGIN_OBJECT)){
                in.beginObject();
            }
            if (in.peek().equals(JsonToken.NAME)){
                if (in.nextName().equals("attribute_code") && in.peek().equals(JsonToken.STRING)){
                    final String attributeCode = in.nextString();
                    if (in.peek().equals(JsonToken.NAME) && in.nextName().equals("value")){
                        if (in.peek().equals(JsonToken.STRING)) {
                            final String value = in.nextString();
                            attribute.put(attributeCode, value);
                        }
                    }
                }
            }
            if (in.peek().equals(JsonToken.BEGIN_ARRAY)) {
                in.beginArray();
            }

            if (in.peek().equals(JsonToken.END_OBJECT)) {
                in.endObject();
            }

            if (in.peek().equals(JsonToken.STRING)){
                in.nextString();
            }
            if (in.peek().equals(JsonToken.END_ARRAY)){
                in.endArray();
            }
        }
        return attribute;
    }
}
