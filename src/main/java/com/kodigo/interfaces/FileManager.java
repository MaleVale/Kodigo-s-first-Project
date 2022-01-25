package com.kodigo.interfaces;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface FileManager {
    String generateFile() throws IOException, DocumentException, URISyntaxException;
}
