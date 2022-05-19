package com.acme.dbo.txlog.message;

public class Message {
    private String getDecoratedMessage (String prefix, String message){
        return prefix+message;
    }
}
