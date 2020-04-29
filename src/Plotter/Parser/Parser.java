package Plotter.Parser;

import Plotter.Function.Addition;
import Plotter.Function.Division;
import Plotter.Function.Exponentiation;
import Plotter.Function.Function;
import Plotter.Function.Multiplication;
import Plotter.Function.Negation;
import Plotter.Function.Number;
import Plotter.Function.Subtraction;
import Plotter.Function.Variable;
import Plotter.Parser.Scanner.Scanner;
import Plotter.Parser.Scanner.Token;
import Plotter.Parser.Scanner.TokenType;

public class Parser {
	
	public static Function parse(String input) {
		Scanner scanner = new Scanner(input);
		
		return parseAdditiveExpression(scanner);
	}
	
	private static Function parseAdditiveExpression(Scanner scanner) {
		Function left = parseMultiplicativeExpression(scanner);
		Token token = scanner.nextToken();
		while (true) {
			switch (token.getType()) {
			case PLUS_OP:
				left = new Addition(left, parseMultiplicativeExpression(scanner));
				token = scanner.nextToken();
				break;
			case MINUS_OP:
				left = new Subtraction(left, parseMultiplicativeExpression(scanner));
				token = scanner.nextToken();
				break;
			default:
				scanner.pushBack(token);
				return left;
			}
		}
	}
	
	private static Function parseMultiplicativeExpression(Scanner scanner) {
		// TODO
		Function left = parseExponentiationExpression(scanner);
		Token token = scanner.nextToken();
		while (true) {
			switch (token.getType()) {
				case MULT_OP:
					left = new Multiplication(left, parseExponentiationExpression(scanner));
					token = scanner.nextToken();
					break;
				case DIV_OP:
					left = new Division(left, parseExponentiationExpression(scanner));
					token = scanner.nextToken();
					break;
				default:
					scanner.pushBack(token);
					return left;
			}
		}
	}
	
	private static Function parseExponentiationExpression(Scanner scanner) {
		Function left = parseElementaryExpression(scanner);
		Token token = scanner.nextToken();
		if (token.getType() == TokenType.EXP_OP) {
			left = new Exponentiation(left, parseExponentiationExpression(scanner));
		} else {
			scanner.pushBack(token);
		}
		return left;
	}
	
	private static Function parseElementaryExpression(Scanner scanner) {
		Token token = scanner.nextToken();
		switch (token.getType()) {
		case NUMBER:
			return new Number(Double.parseDouble(token.getContent()));
		case IDENTIFIER:
			return new Variable(token.getContent());
		case MINUS_OP:
			return new Negation(parseElementaryExpression(scanner));
		case LEFT_PAR:
			Function result = parseAdditiveExpression(scanner);
			if (scanner.nextToken().getType() != TokenType.RIGHT_PAR) {
				throw new UnsupportedOperationException();
			}
			return result;
			case PI:
				return new Number(Math.PI);
			case E:
				return new Number(Math.E);

		default:
			throw new UnsupportedOperationException();
		}
	}
	
}
