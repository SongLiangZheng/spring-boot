package com.hs.slz.mapStruct.entity;


public class BooleanStrFormat {
    public String toStr(Boolean isDisable) {
        if (isDisable) {
            return "Y";
        } else {
            return "N";
        }
    }

    public Boolean toBoolean(String str) {
        return str.equals("Y");
    }
}

