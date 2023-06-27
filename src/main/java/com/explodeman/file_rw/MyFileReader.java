package com.explodeman.file_rw;

import java.util.List;

public interface MyFileReader {
    List<String> read();

    long lastFileModified();
}
