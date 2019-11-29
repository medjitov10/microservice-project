package com.example.postapi.serializer;

import com.example.postapi.bean.Comment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CommentSerializer extends StdSerializer<Comment> {

    public CommentSerializer() {
        this(null);
    }

    public CommentSerializer(Class<Comment> t) {
        super(t);
    }


    @Override
    public void serialize(Comment comment, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", comment.getId());
        gen.writeStringField("text", comment.getText());
        gen.writeNumberField("postId", comment.getPostId());
        gen.writeObjectFieldStart("user");
        gen.writeStringField("username", comment.getUser().getUsername());
        gen.writeEndObject();
        gen.writeEndObject();
    }
}
