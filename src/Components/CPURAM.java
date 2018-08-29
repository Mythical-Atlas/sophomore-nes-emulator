package Components;

public class CPURAM{
	byte[] ram;
	
	public byte stackPointer;
	
	public CPURAM(byte[] rom, int trainer, int prgromSize) {
		ram = new byte[0x10000];
		stackPointer = (byte)0xFD;
		
		//for(int i = 0; i < trainer; i++) {}
		
								 /*for(int i = 0; i < prgromSize; i++) {ram[i + 0x8000] = rom[i + 0x10];}
		if(prgromSize == 16000) {*/for(int i = 0; i < prgromSize; i++) {ram[i + 0xC000] = rom[i + 0x10];}//}
	}
	
	public byte read(short address) {
		switch(address) {
			case((short)0x2002): return((byte)0x80);
			default: return(ram[address & 0xFFFF]);
		}
	}
	public void write(short address, byte value) {ram[address & 0xFFFF] = value;}
	
	public byte read(int address) {return(read((short)address));}
	public void write(short address, int value) {write((short)address, (byte)value);}
	public void write(int address, byte value) {write((short)address, (byte)value);}
	public void write(int address, int value) {write((short)address, (byte)value);}
	
	public void push(byte value) {write((short)(0x0100 + stackPointer--), value);}
	public void push(int value) {write((short)(0x0100 + stackPointer--), value);}
	public byte pull() {return(this.read((short)(0x0100 + stackPointer++)));}
	
	public short absolute(Short pc) {return((short)(read(pc + 1) + (read(pc + 2) << 8)));}
	public short immediate(Short pc) {return((short)(pc + 1));}
	public short zeropage(Short pc) {return((short)read(pc + 1));}
	public short indirecty(Short pc) {return((short)read(pc + 1));}
}
