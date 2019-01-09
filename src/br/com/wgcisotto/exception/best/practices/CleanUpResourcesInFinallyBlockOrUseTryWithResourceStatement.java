package br.com.wgcisotto.exception.best.practices;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CleanUpResourcesInFinallyBlockOrUseTryWithResourceStatement {

    private static final Logger LOGGER = Logger.getLogger(CleanUpResourcesInFinallyBlockOrUseTryWithResourceStatement.class.getName());

    public void doNotCloseResourceInTry() {
        FileInputStream inputStream = null;
        try {
            File file = new File("./tmp.txt");
            inputStream = new FileInputStream(file);

            // use the inputStream to read a file

            // do NOT do this
            inputStream.close();
        } catch (FileNotFoundException e) {
            log(e);
        } catch (IOException e) {
            log(e);
        }
    }

    /*
        The problem is that this approach seems to work perfectly fine as long as no exception gets thrown.
    All statements within the try block will get executed, and the resource gets closed.

        The problem is when an exception occurs within a try block and you might not reach the end of the try block.
    And as a result, you will not close the resources. You should, therefore,
    put all your clean up code into the finally block or use a try-with-resources statement.

        You can use either finally block or Java 7’s Try-With-Resource Statement to close the resources.
     */


    //Use finally block
    public void closeResourceInFinally() {
        FileInputStream inputStream = null;
        try {
            File file = new File("./tmp.txt");
            inputStream = new FileInputStream(file);

            // use the inputStream to read a file

        } catch (FileNotFoundException e) {
            log(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log(e);
                }
            }
        }
    }

    //Java 7’s Try-With-Resource Statement
    public void syntax(){
        try { // (open resources here){
            // use resources
        }catch (Exception e){
            // exception handling
        }
    }
    // resources are closed as soon as try-catch block is executed.

    // Example
    public void automaticallyCloseResource() {

        // Example 1
        File file = new File("./tmp.txt");
        try (FileInputStream inputStream = new FileInputStream(file);) {
            // use the inputStream to read a file

        } catch (FileNotFoundException e) {
           log(e);
        } catch (IOException e) {
            log(e);
        }

        // Example 2
        try (BufferedReader br = new BufferedReader(new FileReader(
                "C:\\wgcisotto.txt"))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    try with resources benefits:
        More readable code and easy to write.
        Automatic resource management.
        The number of lines of code is reduced.
        No need to finally block just to close the resources.
        We can open multiple resources in try-with-resources statement separated by a semicolon.

        For example, we can write the following code:

     */

    public void tryWithManyResources(){
        try (BufferedReader br = new BufferedReader(new FileReader(
                "C:\\wgcisotto.txt"));
             java.io.BufferedWriter writer =
                     java.nio.file.Files.newBufferedWriter(FileSystems.getDefault().
                             getPath("C:\\journaldev.txt"), Charset.defaultCharset())) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void log(Exception e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }




}
