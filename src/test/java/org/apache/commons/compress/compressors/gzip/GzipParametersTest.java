/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.commons.compress.compressors.gzip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.util.zip.Deflater;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests {@link GzipParameters}.
 */
public class GzipParametersTest {

    @Test
    public void testDeflaterStrategy() {
        final GzipParameters gzipParameters = new GzipParameters();
        assertEquals(Deflater.DEFAULT_STRATEGY, gzipParameters.getDeflateStrategy());
        gzipParameters.setDeflateStrategy(Deflater.HUFFMAN_ONLY);
        assertEquals(Deflater.HUFFMAN_ONLY, gzipParameters.getDeflateStrategy());
    }

    @ParameterizedTest
    //@formatter:off
    @CsvSource({
        "          , hello\0world, false",
        "ISO-8859-1, hello\0world, false",
        "UTF-8     , hello\0world, false",
        "UTF-16BE  , helloworld, false"
    })
    //@formatter:on
    public void testIllegalCommentOrFileName(final Charset charset, final String text) {
        final GzipParameters gzipParameters = new GzipParameters();
        // null resets to default value
        gzipParameters.setFileNameCharset(charset);
        assertThrows(IllegalArgumentException.class, () -> gzipParameters.setComment(text));
        assertNull(gzipParameters.getComment());
        assertThrows(IllegalArgumentException.class, () -> gzipParameters.setFilename(text));
        assertNull(gzipParameters.getFileName());
        assertThrows(IllegalArgumentException.class, () -> gzipParameters.setFileName(text));
        assertNull(gzipParameters.getFileName());
    }

    @ParameterizedTest
    //@formatter:off
    @CsvSource({
        "          , helloworld",
        "          , helloéworld",
        "ISO-8859-1, helloworld",
        "ISO-8859-1, helloéworld",
        "UTF-8     , helloworld",
        "UTF-8     , helloéworld"
    })
    //@formatter:on
    public void testLegalCommentOrFileName(final Charset charset, final String text) {
        final GzipParameters gzipParameters = new GzipParameters();
        // null resets to default value
        gzipParameters.setFileNameCharset(charset);
        gzipParameters.setComment(text);
        assertEquals(text, gzipParameters.getComment());
        gzipParameters.setFilename(text);
        assertEquals(text, gzipParameters.getFileName());
        gzipParameters.setFileName(text);
        assertEquals(text, gzipParameters.getFileName());
    }

    @Test
    public void testToString() {
        final GzipParameters gzipParameters = new GzipParameters();
        assertTrue(gzipParameters.toString().contains("UNKNOWN"));
        gzipParameters.setOS(GzipParameters.OS.Z_SYSTEM);
        assertTrue(gzipParameters.toString().contains("Z_SYSTEM"));
    }
}
