package com.github.YuchenWangSH;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String fileName = args[0];
        String machineCode = "";
        String outputFileName = fileName.split("\\.")[0] + ".hack";

        try {
            InputStream is = new FileInputStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String assemblyLine = bufferedReader.readLine();
                // EOF
                if (assemblyLine == null) {
                    break;
                }
                // Compile into machine code

                machineCode += "bluff\n";
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(machineCode);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
