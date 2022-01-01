package com.kodigo.helpers;

import com.kodigo.repository.ProductRepository;

public class ExportProductRepository implements IExportFile{

    @Override
    public boolean export(Object obj) {
        if(obj instanceof ProductRepository){
            return true;
        }
        else
            return false;
    }
}
