package newlang3;

public enum LexicalType {
	LITERAL(""),	// ������萔�@�i��F�@�g������h�j
	INTVAL(""),		// �����萔	�i��F�@�R�j
	DOUBLEVAL(""),	// �����_�萔	�i��F�@�P�D�Q�j
	NAME(""),		// �ϐ�		�i��F�@i�j
	IF("IF"),			// IF
	THEN("THEN"),		// THEN
	ELSE("ELSE"),		// ELSE
	ELSEIF("ELSEIF"),		// ELSEIF
	ENDIF("ENDIF"),		// ENDIF
	FOR("FOR"),		// FOR
	FORALL("FORALL"),		// FORALL
	NEXT("NEXT")	,	// NEXT
	EQ("="),			// =
	LT("<"),			// <
	GT(">"),			// >
	LE("<="),			// <=, =<
	GE(">="),			// >=, =>
	NE("<>"),			// <>
	FUNC("FUNCTION"),		// SUB
	DIM("DIM"),		// DIM
	AS("AS"),			// AS
	END("END"),		// END
	NL("\n"),			// ���s
	DOT("."),		// .
	WHILE("WHILE"),		// WHILE
	DO("DO"),			// DO
	UNTIL("UNTIL"),		// UNTIL
	ADD("+"),		// +
	SUB("-"),		// -
	MUL("*"),		// *
	DIV("/"),		// /
	LP("("),			// (
	RP(")"),			// )
	COMMA(","),		// ,
	LOOP("LOOP"),		// LOOP
	TO("TO"),			// TO
	WEND("WEND"),		// WEND
	EOF(""),		// end of file
	;
	
	String notation;
	
	public String getNotation() {
		return notation;
	}
	
	LexicalType(String notation) {
		this.notation = notation;
	}
}
