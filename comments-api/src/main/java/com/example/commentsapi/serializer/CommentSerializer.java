package com.example.commentsapi.serializer;

import com.example.commentsapi.model.Comment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CommentSerializer extends StdSerializer<Comment> {

    protected CommentSerializer(Class<Comment> t) {
        super(t);
    }

    @Override
    public void serialize(Comment comment, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", comment.getId());
        gen.writeStringField("description", comment.getText());
        gen.writeNumberField("id", comment.getPostId());
        gen.writeObjectFieldStart("user");
        gen.writeObjectField("username", comment.getUsername());
        gen.writeEndObject();
        gen.writeEndObject();
    }
}
