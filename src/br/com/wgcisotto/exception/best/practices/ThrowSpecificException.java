package br.com.wgcisotto.exception.best.practices;

public class ThrowSpecificException {

    public void someMethod(){

        //Example 1:

        // Better
        public void someMethod() throws SpecificException1, SpecificException2 {

        }

        // Avoid
        public void someMethod1() throws Exception { .. }

        //Example 2:

        public void doNotDoThis() throws Exception { ... }

        public void doThis() throws NumberFormatException { ... }
    }
}
