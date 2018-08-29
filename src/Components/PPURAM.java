package Components;

public class PPURAM{
	byte[] ram;
	
	public byte stackPointer = (byte)0xFD;
	
	public PPURAM(byte[] rom, int trainer, int prgromSize) {
		ram = new byte[0x10000];
		
		//for(int i = 0; i < trainer; i++) {}
		
								 /*for(int i = 0; i < prgromSize; i++) {ram[i + 0x8000] = rom[i + 0x10];}
		if(prgromSize == 16000) {*/for(int i = 0; i < prgromSize; i++) {ram[i + 0xC000] = rom[i + 0x10];}//}
	}
	
	public byte read(short address) {return(ram[address & 0xFFFF]);}
	public byte read(int address) {return(ram[address & 0xFFFF]);}
	public void write(short address, byte value) {ram[address & 0xFFFF] = (byte)(value & 0xFF);}
	public void write(short address, int value) {ram[address & 0xFFFF] = (byte)(value & 0xFF);}
	public void write(int address, byte value) {ram[address & 0xFFFF] = (byte)(value & 0xFF);}
	public void write(int address, int value) {ram[address & 0xFFFF] = (byte)(value & 0xFF);}
	
	public void push(byte value) {write((short)(0x0100 + stackPointer--), value & 0xFF);}
	public void push(int value) {write((short)(0x0100 + stackPointer--), value & 0xFF);}
	public byte pull() {return(this.read((short)(0x0100 + stackPointer++)));}
	
	public byte absolute(Short pc) {return(read(read(pc + 1) + (read(pc + 2) << 8)));}
	public byte immediate(Short pc) {return(read(pc + 1));}
}
