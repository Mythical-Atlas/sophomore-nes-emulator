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
	
/*

abs			....	absolute	 			OPC $LLHH	 	operand is address $HHLL *
abs,X		....	absolute, X-indexed	 	OPC $LLHH,X	 	operand is address; effective address is address incremented by X with carry **
abs,Y		....	absolute, Y-indexed	 	OPC $LLHH,Y	 	operand is address; effective address is address incremented by Y with carry **
#			....	immediate	 			OPC #$BB	 	operand is byte BB
ind			....	indirect	 			OPC ($LLHH)	 	operand is address; effective address is contents of word at address: C.w($HHLL)
X,ind		....	X-indexed, indirect	 	OPC ($LL,X)	 	operand is zeropage address; effective address is word in (LL + X, LL + X + 1), inc. without carry: C.w($00LL + X)
ind,Y		....	indirect, Y-indexed	 	OPC ($LL),Y	 	operand is zeropage address; effective address is word in (LL, LL + 1) incremented by Y with carry: C.w($00LL) + Y
rel			....	relative	 			OPC $BB	 		branch target is PC + signed offset BB ***
zpg			....	zeropage	 			OPC $LL	 		operand is zeropage address (hi-byte is zero, address = $00LL)
zpg,X		....	zeropage, X-indexed	 	OPC $LL,X	 	operand is zeropage address; effective address is address incremented by X without carry **
zpg,Y		....	zeropage, Y-indexed	 	OPC $LL,Y	 	operand is zeropage address; effective address is address incremented by Y without carry **

*/
	
	public short absolute(CPU cpu)  {return((short)(read(cpu.pc + 1) + (read(cpu.pc + 2) << 8)));}
	public short absolutex(CPU cpu) {return((short)(read(cpu.pc + 1) + (read(cpu.pc + 2) << 8) + cpu.x));}
	public short absolutey(CPU cpu) {return((short)(read(cpu.pc + 1) + (read(cpu.pc + 2) << 8) + cpu.y));}
	public short immediate(CPU cpu) {return((short)(cpu.pc + 1));}
	public short indirect(CPU cpu)  {return((short)read(cpu.pc + 1));}
	public short xindirect(CPU cpu) {return((short)read(cpu.pc + 1 + cpu.x));}
	public short indirecty(CPU cpu) {return((short)(read(cpu.pc + 1) + cpu.y));}
	public short zeropage(CPU cpu)  {return((short)read(cpu.pc + 1));}
	public short zeropagex(CPU cpu) {return((short)((read(cpu.pc + 1) & 0xFF00) + (read(cpu.pc + 1) + cpu.x) & 0xFF));}
	public short zeropagey(CPU cpu) {return((short)((read(cpu.pc + 1) & 0xFF00) + (read(cpu.pc + 1) + cpu.y) & 0xFF));}
}
