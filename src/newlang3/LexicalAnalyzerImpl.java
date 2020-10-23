package newlang3;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static newlang3.ValueType.DOUBLE;
import static newlang3.ValueType.INTEGER;
import static newlang3.ValueType.STRING;

public class LexicalAnalyzerImpl implements LexicalAnalyzer {
	// 変数の定義
	private PushbackReader reader;
	static Map<String, LexicalUnit> Symbols;
	
	// static変数の定義
	static {
		Symbols = new HashMap<String, LexicalUnit>();
		
        Symbols.put("=", new LexicalUnit(LexicalType.EQ));
        Symbols.put("<", new LexicalUnit(LexicalType.LT));
        Symbols.put(">", new LexicalUnit(LexicalType.GT));
        Symbols.put("<=", new LexicalUnit(LexicalType.LE));
        Symbols.put("=<", new LexicalUnit(LexicalType.LE));
        Symbols.put(">=", new LexicalUnit(LexicalType.GE));
        Symbols.put("=>", new LexicalUnit(LexicalType.GE));
        Symbols.put("<>", new LexicalUnit(LexicalType.NE));
        Symbols.put(".", new LexicalUnit(LexicalType.DOT));
        Symbols.put("+", new LexicalUnit(LexicalType.ADD));
        Symbols.put("-", new LexicalUnit(LexicalType.SUB));
        Symbols.put("*", new LexicalUnit(LexicalType.MUL));
        Symbols.put("/", new LexicalUnit(LexicalType.DIV));
        Symbols.put(")", new LexicalUnit(LexicalType.LP));
        Symbols.put("(", new LexicalUnit(LexicalType.RP));
        Symbols.put(",", new LexicalUnit(LexicalType.COMMA));
        Symbols.put("\n", new LexicalUnit(LexicalType.NL));
	}
	
	// コンストラクタ
	public LexicalAnalyzerImpl(InputStream in) {
		// TODO Auto-generated constructor stub
		reader = new PushbackReader(new InputStreamReader(in));
	}

	@Override
	public LexicalUnit get() throws Exception {
		// TODO Auto-generated method stub
		while(true) {
			int intOneChar = reader.read();
			char oneChar;
			
			if(intOneChar != -1) {
				char tmpChar = (char)intOneChar;
				
				// 読み飛ばし
				if((tmpChar == ' ') || (tmpChar == '\t')){
                    continue;
                }
			}else {
				return new LexicalUnit(LexicalType.EOF);
			}
			
			oneChar = (char)intOneChar;
						
			if(oneChar == '"'){
				// リテラル処理
                reader.unread(intOneChar);
                return getLiteral();
                
            }else if((oneChar >='0' && oneChar <= '9')){
            	// 数字の場合
                reader.unread(intOneChar);
                return getNumber();
                
            }else if((oneChar >= 'a' && oneChar <= 'z') || (oneChar >= 'A' && oneChar <= 'Z')) {
            	// アルファベットの場合
                reader.unread(intOneChar);
                return getString();
            	
            }else {
            	// その他の文字種
            	reader.unread(intOneChar);
                return getSymbol();
                
            }
		}
	}

	@Override
	public LexicalUnit peek() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LexicalUnit peek2() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// local method -------------------------------------------------------------------
	private LexicalUnit getLiteral() throws Exception{
        String literalStr = "";
        reader.read();
        
        while(true){
            int intOneChar = reader.read();
            
            char oneChar = (char) intOneChar;
            
            if (intOneChar != -1) {
            	
            }else {
            	System.out.print(oneChar);
            	throw new Exception("Error");
            }
            
            // char oneChar = (char) intOneChar;
            
            if(oneChar !='"'){
                literalStr += oneChar;
                continue;
            }else{
                break;
            }
        }
        
        return new LexicalUnit(LexicalType.LITERAL,new ValueImpl(literalStr,STRING));
	}
	
    private LexicalUnit getNumber() throws Exception {
        String numberStr = "";
        
        boolean decimalPointFlag = false;
        while (true) {
            int intOneChar = reader.read();
            
            if (intOneChar != -1) {
            	
            }else {
            	break;
            }
            
            char oneChar = (char) intOneChar;

            if ((oneChar >='0' && oneChar <= '9')) {
            	numberStr += oneChar;
                continue;
                
            }else if(oneChar =='.'){
            	numberStr += oneChar;
            	decimalPointFlag = true;
                continue;
                
            }else if(oneChar == '.' && decimalPointFlag){
                throw new Exception("Error");
                
            }else {
            	reader.unread(intOneChar);
                break;
                
            }            
        }
        if(decimalPointFlag){
            return new LexicalUnit(LexicalType.DOUBLEVAL,new ValueImpl(numberStr, DOUBLE));
        }else{
            return new LexicalUnit(LexicalType.INTVAL,new ValueImpl(numberStr, INTEGER));
        }
    }
    
    private LexicalUnit getString() throws Exception {
        String stringStr = "";

        while (true) {
            int intOneChar = reader.read();
            
            if (intOneChar != -1) {
            	
            }else {
            	break;
            }
            
            char oneChar = (char) intOneChar;

            if ((oneChar >= 'a' && oneChar <= 'z') || (oneChar >= 'A' && oneChar <= 'Z') || (oneChar >= '0' && oneChar <= '9')) {
            	stringStr += oneChar;
                continue;
                
            }else {
            	reader.unread(intOneChar);
                break;
                
            }
        }
        return new LexicalUnit(LexicalType.NAME, new ValueImpl(stringStr, STRING));

    }
    
    
    private LexicalUnit getSymbol() throws Exception{
        String symbolStr ="";
         while(true){
             int intOneChar = reader.read();
             
             if (intOneChar != -1) {
             	
             }else {
             	break;
             }
             
             char oneChar = (char) intOneChar;

             if (Symbols.containsKey(symbolStr + oneChar)) {
            	 symbolStr += oneChar;
                 continue;
                 
             }else {
            	 reader.unread(intOneChar);
                 break;
             }    
         }
         return Symbols.get(symbolStr);
    }
}
