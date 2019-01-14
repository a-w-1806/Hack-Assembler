package test;

import com.github.YuchenWangSH.Command;
import com.github.YuchenWangSH.LineParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineParserTest {
    private LineParser emptyLine, commentLine, A_pre, A_num, C, A_pre_comment, L;

    @Before
    public void setUp() throws Exception {
        emptyLine = new LineParser("");
        commentLine = new LineParser("// This is a comment.");
        A_pre = new LineParser("@R11");
        A_num = new LineParser("@23");
        C = new LineParser("AD = D - 1; JGT");
        A_pre_comment = new LineParser("  @R1 // This is comment.");
        L = new LineParser("( LOOP )");
    }

    @Test
    public void getType() {
        assertNull(emptyLine.getType());
        assertNull(commentLine.getType());
        assertEquals(A_pre.getType(), Command.A_COMMAND);
        assertEquals(C.getType(), Command.C_COMMAND);
        assertEquals(A_pre_comment.getType(), Command.A_COMMAND);
        assertEquals(L.getType(), Command.L_COMMAND);
    }

    @Test
    public void toCode() {
        assertEquals(emptyLine.toCode(), "");
        assertEquals(commentLine.toCode(), "");
        assertEquals(A_pre.toCode(), "0000000000001011");
        assertEquals(A_num.toCode(), "0000000000010111");
        assertEquals(C.toCode(), "1110001110110001");
        assertEquals(A_pre_comment.toCode(), "0000000000000001");
        assertEquals(L.toCode(), "");
    }

    @Test
    public void getSymbol() {
        assertNull(C.getSymbol());
        assertEquals(A_pre.getSymbol(), "R11");
        assertEquals(A_num.getSymbol(), "23");
        assertEquals(L.getSymbol(), "LOOP");
    }

    @Test
    public void removeCommentBlank() {
        assertEquals(A_pre_comment.removeCommentBlank(), "@R11");
        assertEquals(C.removeCommentBlank(), "AD=D-1;JGT");
        assertEquals(commentLine.removeCommentBlank(), "");
    }

//    @Test
//    public void getNextLineNumber() {
//    }
}