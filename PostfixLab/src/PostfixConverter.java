/**
 * Project: Infix -> Postfix conversion
 * 
 * @author TODO: Sam Rickert
 *
 */

public class PostfixConverter {

	/**
	 * Convert an infix expression to a postfix expression. Assumes the
	 * expression uses only integers, parenthesies, and the operator set {+, -,
	 * *, /, ^}.
	 * 
	 * @param formula
	 *            The infix expession.
	 * @return The postfix expression.
	 * @exception Throw
	 *                Illegal Argument exception if the parenthesis don't match
	 *                up correctly.
	 */
	public static String infix2postfix(String formula) {
		// Initializes stack and tokenizer, declare variables
		Tokenizer t = new Tokenizer(formula);
		CharStack S = new CharStack(20);
		StringBuilder r = new StringBuilder();
		int parenCount = 0;
		String temp = t.next();

		// counts number of parentheses in infix expression
		for (int i = 0; i < formula.length(); i++) {
			if (formula.charAt(i) == '(')
				parenCount++;
			else if (formula.charAt(i) == ')')
				parenCount--;
		}

		// if unbalanced parentheses, throw exception
		if (parenCount != 0)
			throw new IllegalArgumentException();

		// while there are tokens left in t
		while (!temp.equals("")) {
			char c = temp.charAt(0);
			// if c is a '(' push it on s
			if (c == '(') {
				S.push(c);
			}
			// if c is a ')' keep popping from S and adding to r until you hit a
			// '('
			else if (c == ')') {
				while (S.peek() != '(') {
					r.append(S.pop());
					r.append(" ");
				}
				S.pop();
			}
			// if c is a '^' push it on the stack
			else if (c == '^') {
				S.push(c);
			}

			// if c is an operator, while the operator at the top of S does not
			// have a lower precedence than c, pop s and add it to r, then
			// repeat
			else if (t.isOperator(c)) {
				if (S.isEmpty())
					S.push(c);
				else {
					while (!S.isEmpty() && S.peek() != '(' && t.operatorPrioity(c) <= t.operatorPrioity(S.peek())) {
						r.append(S.pop());
						r.append(" ");
					}
					S.push(c);
				}
			}

			// if t is an operand, add it to r
			else if (t.isDigit(c)) {
				r.append(c);
				r.append(" ");
			}
			// increments t
			temp = t.next();
		}

		// adds what is left of the stack to r
		while (!S.isEmpty()) {
			r.append(S.pop());
			r.append(" ");
		}
		// returns the postfix string
		return r.toString();
	}

	/**
	 * Given postfix expression, calculate the value.
	 * 
	 * @param s
	 *            The postfix expression.
	 * @return double: the calculated value.
	 */
	public static double evaluatePostfix(String s) {
		// Initializes stack and tokenizer, declare variables
		DoubleStack S = new DoubleStack(20);
		Tokenizer t = new Tokenizer(s);
		int parenCount = 0;
		int digCount = 0;
		int operCount = 0;
		String temp = t.next();

		// counts parentheses
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == ')')
				parenCount++;
		}

		// if there is a parentheses, throw an illegal argument exception
		if (parenCount != 0)
			throw new IllegalArgumentException();

		// counts number of operators and digits in s
		// if isOperator, increment opCount by 1, if is space do nothing, and
		// if is digit increment digCount by 1
		for (int i = 0; i < s.length(); i++) {
			if (t.isOperator(s.charAt(i)))
				operCount++;
			else if (s.charAt(i) == ' ') {
			} else {
				digCount++;
			}
			// If number is two digits long, decrement digCount by 1 to account
			// for counting a two digit number twice
			if (i != 0 && t.isDigit(s.charAt(i)) && t.isDigit(s.charAt(i - 1)))
				digCount--;
		}

		// if there is a wrong number of digits or operators, throw an illegal
		// argument exception
		if (digCount - operCount != 1)
			throw new IllegalArgumentException();

		// while t still has tokens
		while (!temp.equals("")) {

			// if t is an operator, pop doubles into variables
			// then add, subtract, multiply, divide, or exponent based on c
			if (t.isOperator(temp)) {
				double d2 = S.pop();
				double d1 = S.pop();
				if (temp.equals("+"))
					S.push(d1 + d2);
				else if (temp.equals("-"))
					S.push(d1 - d2);
				else if (temp.equals("*"))
					S.push(d1 * d2);
				else if (temp.equals("/"))
					S.push(d1 / d2);
				else if (temp.equals("^"))
					S.push(Math.pow(d1, d2));
			}
			// if t is a number, push it onto the stack as a double
			else {
				S.push(Double.parseDouble(temp));
			}

			// increment to next token
			temp = t.next();
		}
		// return the only number left in stack which is the answer
		return S.peek();
	}
}
