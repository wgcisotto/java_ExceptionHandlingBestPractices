package br.com.wgcisotto.exception.best.practices;

public class NeverCatchThrowableClass {

    public void doNotCatchThrowable() {
        try {
            // do something
        } catch (Throwable t) {
            // don't do this!
        }
    }

    // Always correctly wrap the exceptions in custom exceptions so that stack trace is not lost

    public void sample() {
        try {

        } catch (NoSuchMethodException e) { // incorrect way
            throw new MyServiceException("Some information: " + e.getMessage());

        }
        catch (NoSuchMethodException e) {
            throw new MyServiceException("Some information: " , e);  //Correct way
        }
    }

}
