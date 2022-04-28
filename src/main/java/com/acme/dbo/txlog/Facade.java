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
        switch (currentState){
            case UNDEFINED:
                currentState = state.INTEGER;
                integerAccumulator = message;
                break;
            case STRING:
                flush();
                currentState = state.INTEGER;
                integerAccumulator = message;
                break;
            case INTEGER:
                integerAccumulator = integerAccumulator + message;
        }
    }

    public static void log(byte message) {
        printDecoratedMessage(decorate(message));
    }
    public static void log(char message) {
        printDecoratedMessage(decorate(message));
    }
    public static void log(String message) {
        switch (currentState){
            case UNDEFINED:
                currentState = state.STRING;
                stringPattern = message;
                break;
            case STRING:
                if(message.equals(stringPattern)) {
                    numberStringPattern+=1;
                }
                else{
                    flush();
                    stringPattern = message;
                    numberStringPattern = 1;
                }
                break;
            case INTEGER:
                flush();
                stringPattern = message;
                currentState = state.STRING;
        }
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
