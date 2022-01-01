package com.kodigo.helpers;

import lombok.Getter;

public interface IExportFile {
    String dir = "";
    boolean export(Object obj);
}

