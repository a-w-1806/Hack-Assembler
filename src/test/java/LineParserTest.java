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
        assertEquals(Command.A_COMMAND, A_pre.getType());
        assertEquals(Command.C_COMMAND, C.getType());
        assertEquals(Command.A_COMMAND, A_pre_comment.getType());
        assertEquals(Command.L_COMMAND, L.getType());
    }

    @Test
    public void toCode() {
        assertEquals("", emptyLine.toCode());
        assertEquals("", commentLine.toCode());
        assertEquals("0000000000001011", A_pre.toCode());
        assertEquals("0000000000010111", A_num.toCode());
        assertEquals("1110001110110001", C.toCode());
        assertEquals("0000000000000001", A_pre_comment.toCode());
        assertEquals("", L.toCode());
    }

    @Test
    public void getSymbol() {
        assertNull(C.getSymbol());
        assertEquals("R11", A_pre.getSymbol());
        assertEquals("23", A_num.getSymbol());
        assertEquals("LOOP", L.getSymbol());
    }

    @Test
    public void removeCommentBlank() {
        assertEquals("@R1", A_pre_comment.getAssemblyLine());
        assertEquals("AD=D-1;JGT", C.getAssemblyLine());
        assertEquals("", commentLine.getAssemblyLine());
    }

//    @Test
//    public void getNextLineNumber() {
//    }
}