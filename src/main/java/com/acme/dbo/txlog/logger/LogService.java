package com.acme.dbo.txlog.logger;

import com.acme.dbo.txlog.message.Message;
import com.acme.dbo.txlog.saver.ConsoleSever;


public class LogService {
    public static LoggerState currentState = LoggerState.UNDEFINED;
    public static LoggerState newState;
    private static Message currentMessage;
    public static void log(Message message) {
        newState =  message.changeState(currentState);
        if (!newState.equals(currentState)){
            flush();
        }
        currentMessage = message;
        currentState = newState;
    }
    public static void flush(){
        currentMessage.save();
    }

}
