package com.kodigo.helpers;

import com.kodigo.models.Customer;

public class ExportCustomer implements IExportFile{
    @Override
    public boolean export(Object obj) {
        if(obj instanceof Customer){
            return true;
        }
        else{
            return false;
        }
    }
}
