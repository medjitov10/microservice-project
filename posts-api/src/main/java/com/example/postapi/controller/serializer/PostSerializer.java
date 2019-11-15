package com.example.postapi.controller.serializer;

import com.example.postapi.model.Post;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PostSerializer extends StdSerializer<Post> {

    protected PostSerializer(Class<Post> t) {
        super(t);
    }

    @Override
    public void serialize(Post post, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("id", post.getId());
        gen.writeStringField("title", post.getTitle());
        gen.writeStringField("description", post.getDescription());
        gen.writeObjectFieldStart("user");
        gen.writeObjectField("username", post.getUsername());
        gen.writeEndObject();
        gen.writeEndObject();
    }
}
