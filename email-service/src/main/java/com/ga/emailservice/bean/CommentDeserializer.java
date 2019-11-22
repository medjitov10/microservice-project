package com.ga.emailservice.bean;


import com.example.commentsapi.model.Comment;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CommentDeserializer extends StdDeserializer<Comment> {

    public CommentDeserializer() {
        this(null);
    }

    public CommentDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Comment deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);


        return new Comment(node.get("text").asText(), node.get("username").asText());
    }

}
