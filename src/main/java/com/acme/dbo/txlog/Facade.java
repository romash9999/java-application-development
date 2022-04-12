package com.acme.dbo.txlog;

import static com.acme.dbo.txlog.Decorator.MessageDecorator.*;
import static com.acme.dbo.txlog.Printer.ConsolePrinter.*;

public class Facade {
    public static void log(int message) {
        printDecoratedMessage(decorate(message));
    }

    public static void log(byte message) {
        printDecoratedMessage(decorate(message));
    }

    public static void log(char message) {
        printDecoratedMessage(decorate(message));
    }

    public static void log(String message) {
        printDecoratedMessage(decorate(message));
    }
    public static void log(boolean message) {
        printDecoratedMessage(decorate(message));
    }
    public static void log(Object message) {
        printDecoratedMessage(decorate(message));
    }
}
