package com.kodigo.helpers;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogCreator {
    @Getter
    private Logger logger;
    private static LogCreator logCreator;

    private LogCreator() {
        File file = new File("log.txt");
        if (!file.exists()){
            try {
                if (file.createNewFile()) System.out.println("Log.txt created successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = Logger.getLogger("test");
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
        SimpleFormatter formatter = new SimpleFormatter();
        assert fileHandler != null;
        fileHandler.setFormatter(formatter);
    }

    public static LogCreator getInstance(){
        if (logCreator == null){
            logCreator = new LogCreator();
        }
        return logCreator;
    }

}
