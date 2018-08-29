package Main;

public class Debug {
	public static class debug {
		public static String byteString(byte value) {return("0x" + (Integer.toHexString(value & 0xFF).length() <= 10 ? "00".substring(Integer.toHexString(value & 0xFF).length()) + Integer.toHexString(value & 0xFF) : Integer.toHexString(value & 0xFF)).toUpperCase());}
		public static String shortString(short value) {return("0x" + (Integer.toHexString(value & 0xFFFF).length() <= 10 ? "0000".substring(Integer.toHexString(value & 0xFFFF).length()) + Integer.toHexString(value & 0xFFFF) : Integer.toHexString(value & 0xFFFF)).toUpperCase());}
		
		public static String byteString(int value) {return(byteString((byte)value));}
		public static String shortString(int value) {return(shortString((short)value));}
	
		public static String byteJTextField(byte value) {return(Integer.toHexString(value & 0xFF).length() <= 10 ? "00".substring(Integer.toHexString(value & 0xFF).length()) + Integer.toHexString(value & 0xFF) : Integer.toHexString(value & 0xFF)).toUpperCase();}
		public static String shortJTextField(short value) {return(Integer.toHexString(value & 0xFFFF).length() <= 10 ? "0000".substring(Integer.toHexString(value & 0xFFFF).length()) + Integer.toHexString(value & 0xFFFF) : Integer.toHexString(value & 0xFFFF)).toUpperCase();}
	}
}
