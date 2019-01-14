package com.github.YuchenWangSH;

import java.util.HashMap;
import java.util.Map;

public class LineParser {
    private static Map<String, Integer> symTable = new HashMap<>();
    private static int nextRegister = 16;
    static {
        symTable.put("R0", 0);
        symTable.put("R1", 1);
        symTable.put("R2", 2);
        symTable.put("R3", 3);
        symTable.put("R4", 4);
        symTable.put("R5", 5);
        symTable.put("R6", 6);
        symTable.put("R7", 7);
        symTable.put("R8", 8);
        symTable.put("R9", 9);
        symTable.put("R10", 10);
        symTable.put("R11", 11);
        symTable.put("R12", 12);
        symTable.put("R13", 13);
        symTable.put("R14", 14);
        symTable.put("R15", 15);
        symTable.put("SCREEN", 16384);
        symTable.put("KBD", 24576);

        symTable.put("SP", 0);
        symTable.put("LCL", 1);
        symTable.put("ARG", 2);
        symTable.put("THIS", 3);
        symTable.put("THAT", 4);
    }
    private String assemblyLine;
    private Command type;

    // For A and L commands.
    private String symbol;
    // For C commands.
    private String dest, comp, jump;

    public LineParser(String line) {
        assemblyLine = line;
        assemblyLine = removeCommentBlank();
        type = identifyType();

        if (type == Command.L_COMMAND) {
            symTable.put(symbol, getNextLineNumber());
        }
    }

    public Command getType() {
        return type;
    }

    public String toCode() {
        return null;
    }

    public String getSymbol() {
        return symbol;
    }

    public String removeCommentBlank() {
        return null;
    }

    public int getNextLineNumber() {
        return 0;
    }

    /**
     * Identify what kind of command this line is and set the according fields properly.
     * @return Command type. If this line is blank or a comment, return null.
     */
    private Command identifyType() {
        return null;
    }

    private String CToCode() {
        return null;
    }

    private String AToCode() {
        return null;
    }
}
