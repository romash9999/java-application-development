package com.acme.dbo.txlog.decorator;

public class PrefixDecorator{
    public static String decorate(String prefix, String message) {
        return prefix + message;
    }
}
