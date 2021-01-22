import java.text.ParseException;

public class Lexer {
    private String inputString;
    private int curPos;
    private Tokens.Token curToken;

    public Lexer(String input) {
        this.inputString = input;
        curPos = 0;
        nextChar();
    }

    private boolean is_Blank(int c) {
        if (c == inputString.length()) {
            return true;
        }
        c = inputString.charAt(c);
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    private void nextChar() {
        curPos++;
    }

    public void nextToken() throws ParseException {
        if (curPos > inputString.length() + 1) {
            throw new ParseException("There are no more chars", curPos);
        }
        while (is_Blank(curPos - 1)) {
            nextChar();
        }
        switch (inputString.charAt(curPos - 1)) {
            case ':':
                nextChar();
                curToken = Tokens.Token.TWODOTS;
                break;
            case ';':
                nextChar();
                curToken = Tokens.Token.SEMICOLON;
                break;
            case ',':
                nextChar();
                curToken = Tokens.Token.COMMA;
                break;
            case '<':
                nextChar();
                curToken = Tokens.Token.LESS;
                break;
            case '>':
                nextChar();
                curToken = Tokens.Token.MORE;
                break;
            default:
                if ((inputString.charAt(curPos - 1) >= 'a' && inputString.charAt(curPos - 1) <= 'z') ||
                        (inputString.charAt(curPos - 1) >= 'A' && inputString.charAt(curPos - 1) <= 'Z') ||
                        inputString.charAt(curPos - 1) == '_') {
                    int startOfWord = curPos - 1;
                    int endOfWord = curPos - 1;
                    do {
                        endOfWord += 1;
                    } while ((endOfWord < inputString.length()) && ((inputString.charAt(endOfWord) >= 'a' && inputString.charAt(endOfWord) <= 'z') ||
                            (inputString.charAt(endOfWord) >= 'A' && inputString.charAt(endOfWord) <= 'Z') || inputString.charAt(endOfWord) == '_' ||
                            (inputString.charAt(endOfWord) >= '0' && inputString.charAt(endOfWord) <= '9')));
                    String word = inputString.substring(startOfWord, endOfWord);
                    if (word.matches("var")) {
                        curToken = Tokens.Token.VAR;
                    } else {
                        curToken = Tokens.Token.NAME;
                    }
                    curPos = endOfWord + 1;
                } else {
                    throw new ParseException("Illegal Character" + inputString.charAt(curPos - 1), curPos);
                }
        }
    }
    public Tokens.Token curToken() {
        return curToken ;
    }
    public int curPos() {
        return curPos;
    }

}
