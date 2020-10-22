package newlang3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {

	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream("test1.bas");
		LexicalAnalyzer lex = new LexicalAnalyzerImpl(in);
		while (true) {
			LexicalUnit unit = lex.get();
			System.out.println(unit);
			if (unit.getType() == LexicalType.EOF) break;
		}
		System.out.println("that's all");

	}

}
