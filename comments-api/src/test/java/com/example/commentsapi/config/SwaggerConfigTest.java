package com.example.commentsapi.config;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class SwaggerConfigTest {
    SwaggerConfig swaggerConfig;

    @Before
    public void init() {
        swaggerConfig = new SwaggerConfig();
    }
    @Test
    public void swaggerConfig_SwaggerConfig_Success() throws IOException, XmlPullParserException {
        assertNotNull(swaggerConfig.api());
    }
}
