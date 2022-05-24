package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.logger.LoggerState;

public interface Message {
    String save();
    LoggerState changeState(LoggerState state);
}
