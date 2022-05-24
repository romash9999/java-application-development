package com.acme.dbo.txlog.message;


import com.acme.dbo.txlog.logger.LogService;
import com.acme.dbo.txlog.logger.LoggerState;
import static com.acme.dbo.txlog.decorator.PrefixDecorator.decorate;
import com.acme.dbo.txlog.saver.ConsoleSever;

public class StringMessage implements Message {
    private final String value;
    private static int numberPatterns = 1;
    private static String pattern = "";
    private final LoggerState localState = LoggerState.STRING;
    private final String PREFIX = "String: ";
    ConsoleSever consoleSever = new ConsoleSever();

    public StringMessage(String message){
        this.value = message;
    }
    @Override
    public String save() {
        //System.out.println(decorate(PREFIX,pattern+" x("+numberPatterns + ")"));
        consoleSever.save(decorate(PREFIX,pattern+" (x"+numberPatterns + ")"));
        return null;
    }
    private void flushAccum(){
        pattern = value;
        numberPatterns = 1;
    }
    @Override
    public LoggerState changeState(LoggerState state) {
        if (state.equals(localState)){
            if (value.equals(pattern)){
                numberPatterns++;
            }
            else{
                save();
                flushAccum();
            }
        }
        else{
            if (state.equals(LoggerState.UNDEFINED)){LogService.currentState = localState;}
            flushAccum();
        }
        return localState;
    }

}
