package com.github.YuchenWangSH;

import java.io.*;

public class HackAssembler {

    public static String compile(String path) {
        firstPass(path);
        return secondPass(path);
    }

    private static String secondPass(String path) {
        String machineCode = "";
        try {
            InputStream is = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String assemblyLine = bufferedReader.readLine();
                // EOF
                if (assemblyLine == null) {
                    break;
                }
                LineParser line = new LineParser(assemblyLine);
                if (line.getType() == null || line.getType() == Command.L_COMMAND) {
                    continue;
                }
                machineCode += line.toCode() + "\n";
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return machineCode;
    }

    private static void firstPass(String path) {
        // First pass.
        try {
            InputStream is = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String assemblyLine = bufferedReader.readLine();
                // EOF
                if (assemblyLine == null) {
                    break;
                }
                LineParser line = new LineParser(assemblyLine);
                // Update symbol table is implicitly handled in the constructor of LineParser.
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String machineCode = compile(args[0]);
        String outputFilePath = args[0].split("\\.")[0] + ".hack";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            writer.write(machineCode);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
