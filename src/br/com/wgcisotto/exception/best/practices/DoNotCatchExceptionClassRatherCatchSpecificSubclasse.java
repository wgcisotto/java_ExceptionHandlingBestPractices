package br.com.wgcisotto.exception.best.practices;

public class DoNotCatchExceptionClassRatherCatchSpecificSubclasse {

    public void downCastPrimitiveType() {
        try {
            System.out.println("i");
        } catch(Exception e) {
            e.printStackTrace();
        } catch(RuntimeException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }
    // Better
    public void someMethod(){
        try{
            throw new SpecificException1();
            // code here
        }catch(SpecificException1 exception){

        }catch (Exception e) {

        }
    }

}
