package com.acme.dbo.txlog.message;

import com.sun.imageio.plugins.common.SimpleRenderedImage;

public class StringMessage extends Message{
    private String  value;
    private String STRING_PREFIX = "string: ";
    private static String stringPattern = "";
    private static int numberStringPattern = 1;

    public StringMessage (String message){
          setValue(message);
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getSTRING_PREFIX() {
        return STRING_PREFIX;
    }
    public int getNumberStringPattern() {
        return numberStringPattern;
    }
    public void incrementNumberStringPattern(){
        numberStringPattern++;
    }
    public void setSTRING_PREFIX(String STRING_PREFIX) {
        this.STRING_PREFIX = STRING_PREFIX;
    }
    public void flushPattern(){
        numberStringPattern = 1;
    }
    public String getStringPattern() {
        return stringPattern;
    }

    public void setStringPattern(String stringPattern) {
        this.stringPattern = stringPattern;
    }
}
