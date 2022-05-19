package com.acme.dbo.txlog;

import static com.acme.dbo.txlog.decorator.MessageDecorator.*;
import static com.acme.dbo.txlog.printer.ConsolePrinter.*;



public class Facade {
    private static int integerAccumulator = 0;
    private static int numberStringPattern = 1;
    private static String stringPattern = "";
    private enum state {STRING,INTEGER,UNDEFINED;};
    private static state currentState = state.UNDEFINED;

    public static void log(int message) {
    LogService.log(message);
    }

    public static void log(byte message) {
        printDecoratedMessage(decorate(message));
    }
    public static void log(char message) {
        printDecoratedMessage(decorate(message));
    }
    public static void log(String message) {
        LogService.log(message);
    }
    public static void log(boolean message) {
        printDecoratedMessage(decorate(message));
    }
    public static void log(Object message) {
        printDecoratedMessage(decorate(message));
    }
    public static void flush(){
        switch (currentState){
            case INTEGER:
                printDecoratedMessage(decorate(integerAccumulator));
                integerAccumulator = 0;
                break;
            case STRING:
                printDecoratedMessage(decorate(stringPattern)+" (x"+numberStringPattern+")\n");
        }
    }
}
