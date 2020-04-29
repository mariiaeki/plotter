package Plotter.Parser.Scanner;

public class Scanner {
	
	private Token buffer;
	private String string;
	
	public Scanner(String input) {
		buffer = null;
		string = input;
	}
	
	public Token nextToken() {
		if (buffer != null) {
			Token tmp = buffer;
			buffer = null;
			return tmp;
		}
		
		// ensure first character is no whitespace character//This method returns a copy of the string, with leading and trailing whitespace omitted.
		string = string.trim();

		//end of file
		if (string.length() == 0) {
			return new Token(TokenType.EOF, "");
		}
		
		// handle operators and symbols
		switch (string.charAt(0)) {
		case '+':
			string = string.substring(1, string.length());
			return new Token(TokenType.PLUS_OP, "+");
		case '-':
			string = string.substring(1, string.length());
			return new Token(TokenType.MINUS_OP, "-");
		case '*':
			string = string.substring(1, string.length());
			return new Token(TokenType.MULT_OP, "*");
		case '/': case ':':
			String tmp = string.substring(0, 1);
			string = string.substring(1, string.length());
			return new Token(TokenType.DIV_OP, tmp);
		case '^':
			string = string.substring(1, string.length());
			return new Token(TokenType.EXP_OP, "^");
		case '(':
			string = string.substring(1, string.length());
			return new Token(TokenType.LEFT_PAR, "(");
		case ')':
			string = string.substring(1, string.length());
			return new Token(TokenType.RIGHT_PAR, ")");
		case '|':
			string = string.substring(1, string.length());
			return new Token(TokenType.VERT, "|");
		}
		
		// handle numbers
		if (Character.isDigit(string.charAt(0))) {
			int length = 1;
			while (length < string.length() && Character.isDigit(string.charAt(length))) {
				length++;
			}
			if (length < string.length() && string.charAt(length) == '.') {
				length++;
				while (length < string.length() && Character.isDigit(string.charAt(length))) {
					length++;
				}
			}
			String tmp = string.substring(0, length);
			string = string.substring(length, string.length());
			return new Token(TokenType.NUMBER, tmp);
		}
		
		// handle identifiers
		if (Character.isLowerCase(string.charAt(0))) {
			int length = 1;
			while (length < string.length() && Character.isLowerCase(string.charAt(length))) {
				length++;
			}
			String tmp = string.substring(0, length);
			string = string.substring(length, string.length());
			return new Token(TokenType.IDENTIFIER, tmp);
		}


		if (Character.isUpperCase(string.charAt(0))) {
			int length = 1;
			while (length < string.length() && Character.isUpperCase(string.charAt(length))) {
				length++;
			}
			String tmp = string.substring(0, length);
			string = string.substring(length, string.length());
			if (tmp.equals("PI")){
			return new Token(TokenType.PI, tmp);} else if (tmp.equals("E")){
				return new Token(TokenType.E, tmp);
			}

		}
				
		throw new UnsupportedOperationException();
	}
	
	public void pushBack(Token token) {
		if (buffer != null) {
			throw new UnsupportedOperationException();
		}
		buffer = token;
	}
	
}
