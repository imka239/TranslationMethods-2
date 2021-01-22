import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;

    public Tree parse(String inputString) throws ParseException {
        this.lexer = new Lexer(inputString) ;
        lexer.nextToken();
        return S();
    }


    private Tree S() throws ParseException {
        if (lexer.curToken() == Tokens.Token.VAR) {
            lexer.nextToken();
            Tree sub = N();
            return new Tree("S", new Tree("var"), sub);
        }
        throw new AssertionError();
    }

    private Tree N() throws ParseException {
        if (lexer.curToken() == Tokens.Token.NAME) {
            lexer.nextToken();
            Tree sub = NPrime();
            if (lexer.curToken() != Tokens.Token.SEMICOLON) {
                throw new ParseException("expected semicilon at position", lexer.curPos());
            }
            return new Tree("N", new Tree("name"), sub);
        }
        throw new AssertionError();
    }

    private Tree NPrime() throws ParseException {
        if (lexer.curToken() == Tokens.Token.TWODOTS) {
            lexer.nextToken();
            Tree sub = T();
            return new Tree("NPrime", new Tree(":"), sub, new Tree(";"));
        }
        throw new AssertionError();
    }

    private Tree T() throws ParseException {
        if (lexer.curToken() == Tokens.Token.NAME) {
            List<Tree> children = new ArrayList<>();
            children.add(new Tree("Word"));
            children.add(new Tree("<"));
            lexer.nextToken();
            if (lexer.curToken() != Tokens.Token.LESS) {
                return new Tree("T", new Tree("Word"));
            } else {
                if (lexer.curToken() != Tokens.Token.LESS) {
                    throw new ParseException("expected '<' at position", lexer.curPos());
                }
                do {
                    lexer.nextToken();
                    Tree t1 = T();
                    if (lexer.curToken() != Tokens.Token.COMMA && lexer.curToken() != Tokens.Token.MORE) {
                        throw new ParseException("expected ',' at position", lexer.curPos());
                    }
                    children.add(t1);
                } while (lexer.curToken() != Tokens.Token.MORE);
                lexer.nextToken();
                children.add(new Tree(">"));
                return new Tree("T", children);
            }
        }
        throw new AssertionError();
    }
}
