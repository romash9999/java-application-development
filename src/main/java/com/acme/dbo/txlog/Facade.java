package com.acme.dbo.txlog;

import com.acme.dbo.txlog.logger.LogService;
import com.acme.dbo.txlog.message.IntMessage;
import com.acme.dbo.txlog.message.StringMessage;

public class Facade {
    public static void flush(){
        LogService.flush();
    }
    public static void log(String message){
        LogService.log(new StringMessage(message));
    }
    public static void log(int message){
        LogService.log(new IntMessage(message));
    }
}
