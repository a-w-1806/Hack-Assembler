import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineParserTest {
    private LineParser emptyLine, commentLine;
    private LineParser aPre, aNum, aPreComment;
    private LineParser c, cNoJump, cNoDest;
    private LineParser L;

    @Before
    public void setUp() throws Exception {
        emptyLine = new LineParser("");
        commentLine = new LineParser("// This is a comment.");
        aPre = new LineParser("@R11");
        aNum = new LineParser("@23");
        c = new LineParser("AD = D - 1; JGT");
        cNoJump = new LineParser("D = 0");
        cNoDest = new LineParser("0; JMP");
        aPreComment = new LineParser("  @R1 // This is comment.");
        L = new LineParser("( LOOP )");
    }

    @Test
    public void getType() {
        assertNull(emptyLine.getType());
        assertNull(commentLine.getType());
        assertEquals(Command.A_COMMAND, aPre.getType());
        assertEquals(Command.C_COMMAND, c.getType());
        assertEquals(Command.A_COMMAND, aPreComment.getType());
        assertEquals(Command.L_COMMAND, L.getType());
    }

    @Test
    public void c() {
        assertEquals("AD", c.getDest());
        assertEquals("D-1", c.getComp());
        assertEquals("JGT", c.getJump());
    }

    @Test
    public void cNoJump() {
        assertNull(cNoJump.getJump());
    }

    @Test
    public void cNoDest() {
        assertNull(cNoDest.getDest());
    }

    @Test
    public void toCode() {
        assertEquals("", emptyLine.toCode());
        assertEquals("", commentLine.toCode());
        assertEquals("0000000000001011", aPre.toCode());
        assertEquals("0000000000010111", aNum.toCode());
        assertEquals("1110001110110001", c.toCode());
        assertEquals("0000000000000001", aPreComment.toCode());
        assertEquals("", L.toCode());
    }

    @Test
    public void getSymbol() {
        assertNull(c.getSymbol());
        assertEquals("R11", aPre.getSymbol());
        assertEquals("23", aNum.getSymbol());
        assertEquals("LOOP", L.getSymbol());
    }

    @Test
    public void removeCommentBlank() {
        assertEquals("@R1", aPreComment.getAssemblyLine());
        assertEquals("AD=D-1;JGT", c.getAssemblyLine());
        assertEquals("", commentLine.getAssemblyLine());
    }

//    @Test
//    public void getNextLineNumber() {
//    }
}