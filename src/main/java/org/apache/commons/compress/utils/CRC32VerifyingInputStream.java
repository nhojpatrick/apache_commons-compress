/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.compress.utils;

import java.io.InputStream;
import java.util.zip.CRC32;

/**
 * A stream that verifies the CRC of the data read once the stream is exhausted.
 *
 * @NotThreadSafe
 * @since 1.6
 * @deprecated Use {@link org.apache.commons.io.input.ChecksumInputStream}.
 */
@Deprecated
public class CRC32VerifyingInputStream extends ChecksumVerifyingInputStream {
    /**
     * Constructs a new instance.
     *
     * @param in            the stream to wrap
     * @param size          the of the stream's content
     * @param expectedCrc32 the expected checksum
     * @deprecated No longer used.
     */
    @Deprecated
    public CRC32VerifyingInputStream(final InputStream in, final long size, final int expectedCrc32) {
        this(in, size, expectedCrc32 & 0xFFFFffffL);
    }

    /**
     * Constructs a new instance.
     *
     * @param in            the stream to wrap
     * @param size          the of the stream's content
     * @param expectedCrc32 the expected checksum
     * @since 1.7
     */
    public CRC32VerifyingInputStream(final InputStream in, final long size, final long expectedCrc32) {
        super(new CRC32(), in, size, expectedCrc32);
    }

}
