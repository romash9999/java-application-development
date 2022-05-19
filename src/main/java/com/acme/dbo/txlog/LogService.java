package com.acme.dbo.txlog;

import com.acme.dbo.txlog.message.StringMessage;

import static com.acme.dbo.txlog.decorator.MessageDecorator.decorate;
import static com.acme.dbo.txlog.printer.ConsolePrinter.printDecoratedMessage;


public class LogService {
    private static int integerAccumulator = 0;
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

    public static void log(String message) {
        StringMessage myStringMessage = new StringMessage(message);
        String stringPattern = myStringMessage.getStringPattern();
        //int numberStringPattern = myStringMessage.getNumberStringPattern();
        switch (currentState){
            case UNDEFINED:
                currentState = state.STRING;
                myStringMessage.setStringPattern(message);
                break;
            case STRING:
                if(message.equals(stringPattern)) {
                    myStringMessage.incrementNumberStringPattern();
                }
                else{
                    flush();
                    myStringMessage.setStringPattern(message);
                    myStringMessage.flushPattern();
                }
                break;
            case INTEGER:
                flush();
                myStringMessage.setStringPattern(message);
                currentState = state.STRING;
        }
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
