package com.bitwise.gpqa;

/**
 * Created by rahul.nair@bitwiseglobal.com .
 */
public class Extract {
        final String tempFile= Transform.getFileLocation();
    public static void instruct(String[] instructions, String inputFile) {
        for(int i = 1; i <= instructions.length; i++){
            try{
                if(instructions[i-1].startsWith("-e")){
                    process(instructions[i-1]);
                }
            }catch(Exception e){
                System.out.println("Instruction argument "+ i + " failed!");
            }
        }
    }

    private static void process(String instruction) {
    }
}
