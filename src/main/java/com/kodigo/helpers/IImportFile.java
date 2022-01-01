package com.kodigo.helpers;

import com.kodigo.models.Product;

import java.util.ArrayList;

public interface IImportFile {
    String dir = "";
    ArrayList<Product> startImport();
}
