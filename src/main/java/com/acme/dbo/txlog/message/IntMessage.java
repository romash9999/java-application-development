package com.acme.dbo.txlog.message;


import com.acme.dbo.txlog.logger.LogService;
import com.acme.dbo.txlog.logger.LoggerState;
import static com.acme.dbo.txlog.decorator.PrefixDecorator.decorate;
import com.acme.dbo.txlog.saver.ConsoleSever;

public class IntMessage implements Message {

    private final int value;
    private static int sumAccum = 0;
    private final LoggerState localState = LoggerState.INTEGER;
    private final String PREFIX = "primitive: ";
    ConsoleSever consoleSever = new ConsoleSever();

    public IntMessage(int message){
        this.value = message;
    }

    @Override
    public String save() {
        consoleSever.save(decorate(PREFIX,String.valueOf(sumAccum)));
        return null;
    }

    @Override
    public LoggerState changeState(LoggerState state) {
        if (state.equals(localState)){
            sumAccum = sumAccum + value;
        }
        else{
            if (state.equals(LoggerState.UNDEFINED)){
                LogService.currentState = localState;
            }
            sumAccum = value;
        }
        return localState;
    }

}
