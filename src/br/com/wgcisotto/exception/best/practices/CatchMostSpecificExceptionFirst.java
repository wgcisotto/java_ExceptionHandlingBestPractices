package br.com.wgcisotto.exception.best.practices;

public class CatchMostSpecificExceptionFirst {

    public void catchMostSpecificExceptionFirst() {
        try {
            doSomething("A message");
        } catch (NumberFormatException e) {
            log.error(e);
        } catch (IllegalArgumentException e) {
            log.error(e);
        }
    }

    // avoid
    public void doNotIgnoreExceptions() {
        try {
            // do something
        } catch (NumberFormatException e) {
            // this will never happen
        }
    }

    // Log the exceptions Example:

    public void logAnException() {
        try {
            // do something
        } catch (NumberFormatException e) {
            log.error("This should never happen: " + e);
        }
    }



}
