package Components;

public class CPUFlags {
	public boolean carryFlag;     // 0
	public boolean zeroFlag;      // 1
	public boolean interruptFlag; // 2
	public boolean decimalFlag;   // 3
	public boolean breakFlag;     // 4
	public boolean alwaysSetFlag; // 5
	public boolean overflowFlag;  // 6
	public boolean negativeFlag;  // 7
	
	public CPUFlags() {
		carryFlag = false;
		zeroFlag = false;
		interruptFlag = true;
		decimalFlag = false;
		breakFlag = false;
		alwaysSetFlag = true;
		overflowFlag = false;
		negativeFlag = false;
	}
	
	public void setCarry(boolean value) {carryFlag = value;}
	public void setZero(boolean value) {zeroFlag = value;}
	public void setInterrupt(boolean value) {interruptFlag = value;}
	public void setDecimal(boolean value) {decimalFlag = value;}
	public void setOverflow(boolean value) {overflowFlag = value;}
	public void setNegative(boolean value) {negativeFlag = value;}
	
	public boolean getNegative() {return(negativeFlag);}
	
	public byte getStatusByte() {
		return(byte)((negativeFlag  ? 0x80 : 0x00) +
					 (overflowFlag  ? 0x40 : 0x00) +
					 (alwaysSetFlag ? 0x20 : 0x00) +
					 (breakFlag     ? 0x10 : 0x00) +
					 (decimalFlag   ? 0x08 : 0x00) +
					 (interruptFlag ? 0x04 : 0x00) +
					 (zeroFlag      ? 0x02 : 0x00) +
					 (carryFlag     ? 0x01 : 0x00));
	}
	public String getStatusString() {
		return((negativeFlag  ? "1" : "0") + " " +
			   (overflowFlag  ? "1" : "0") + " " +
			   (alwaysSetFlag ? "1" : "0") + " " +
			   (breakFlag     ? "1" : "0") + " " +
			   (decimalFlag   ? "1" : "0") + " " +
			   (interruptFlag ? "1" : "0") + " " +
			   (zeroFlag      ? "1" : "0") + " " +
			   (carryFlag     ? "1" : "0"));
	}
}
