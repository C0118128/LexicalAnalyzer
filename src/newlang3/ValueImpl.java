package newlang3;

public class ValueImpl extends Value {
	// 変数の定義
	private String sValue;
	private int iValue;
	private double dValue;
	private boolean bValue;
	private ValueType type;
	
	// コンストラクタ
    public ValueImpl(String sValue) {
    	this.sValue = sValue;
    };
    public ValueImpl(int iValue) {
    	this.iValue = iValue;
    };
    public ValueImpl(double dValue) {
    	this.dValue = dValue;
    };
    public ValueImpl(boolean bValue) {
    	this.bValue = bValue;
    };
    public ValueImpl(String str, ValueType type) {
    	this.type = type;
    	
    	switch(type) {
    		case INTEGER:
    			this.iValue = Integer.valueOf(str);
    			break;
    		case DOUBLE:
    			this.dValue = Double.valueOf(str);
    			break;
    		case STRING:
    			this.sValue = str;
    			break;
    		case BOOL:
    			this.bValue = Boolean.valueOf(str);
    			break;
    		default:
    			break;
    	}
    };
	
	@Override
	public String getSValue() {
		// TODO Auto-generated method stub
		return this.sValue;
	}

	@Override
	public int getIValue() {
		// TODO Auto-generated method stub
		return this.iValue;
	}

	@Override
	public double getDValue() {
		// TODO Auto-generated method stub
		return this.dValue;
	}

	@Override
	public boolean getBValue() {
		// TODO Auto-generated method stub
		return this.bValue;
	}

	@Override
	public ValueType getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
}
