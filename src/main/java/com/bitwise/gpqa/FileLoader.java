package com.bitwise.gpqa;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by rahul.nair@bitwiseglobal.com .
 */
public class FileLoader {

    static String inputFile = null;
    static String outputFile= null;
    public static void main(String args[]) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");
            prop.load(input);
            inputFile = prop.getProperty("input");
            outputFile = prop.getProperty("output");
            String line = null;

            try {

                FileReader fileReader =
                        new FileReader(inputFile);

                BufferedReader bufferedReader =
                        new BufferedReader(fileReader);

                line = bufferedReader.readLine();
                if(line!=null){
                    processInstruction(line);
                }else System.out.println("Please enter first line as instruction");
                bufferedReader.close();
            }
            catch(FileNotFoundException ex) {
                System.out.println(
                        "Unable to open file '" +
                                inputFile + "'");
            }
            catch(IOException ex) {
                System.out.println(
                        "Error reading file '"
                                +inputFile + "'");
                // Or we could just do this:
                // ex.printStackTrace();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void processInstruction(String line) {
        String[] instructions = line.split("[\\s]+");
        boolean allOkay = checkFormat(instructions);
        if(allOkay){
            Extract.instruct(instructions, inputFile);
            Transform.instruct(instructions);
            Load.instruct(instructions, outputFile);
        }else {
            System.out.println("There was a problem parsing the input!");
        }
    }

    private static boolean checkFormat(String[] instructions) {
        boolean returnValue= true;
        for(String temp : instructions){
            if(!temp.matches("-[etl][a-z]+[0-9a-z]*[-]*")){
                returnValue = false;
            }
        }
        System.out.println(returnValue);
        return returnValue;
    }
}