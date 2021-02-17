package com.example.tabapp;

import java.io.File;

public class DataModel {
    private File file;
    private String type;

    public DataModel(File file, String type) {
        this.file = file;
        this.type = type;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
