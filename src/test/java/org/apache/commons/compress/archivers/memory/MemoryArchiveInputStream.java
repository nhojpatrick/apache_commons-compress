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
package org.apache.commons.compress.archivers.memory;

import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveInputStream;

/**
 * A test input stream.
 */
public final class MemoryArchiveInputStream extends ArchiveInputStream<MemoryArchiveEntry> {

    private final String[] fileNames;
    private final String[] content;
    private int p;

    public MemoryArchiveInputStream(final String[][] pFiles) {
        final int pFilesLength = pFiles.length;
        fileNames = new String[pFilesLength];
        content = new String[pFilesLength];

        for (int i = 0; i < pFilesLength; i++) {
            final String[] nameAndContent = pFiles[i];
            fileNames[i] = nameAndContent[0];
            content[i] = nameAndContent[1];
        }
        p = 0;
    }

    @Override
    public MemoryArchiveEntry getNextEntry() throws IOException {
        if (p >= fileNames.length) {
            return null;
        }
        return new MemoryArchiveEntry(fileNames[p]);
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public String readString() {
        return content[p++];
    }

}
