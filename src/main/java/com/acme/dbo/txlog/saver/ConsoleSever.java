package com.acme.dbo.txlog.saver;

public class ConsoleSever implements Saver{
    @Override
    public void save(String decoratedMessage) {
        System.out.println(decoratedMessage);
    }
}
