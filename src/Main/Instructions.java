package Main;

import Components.CPU;
import Main.Debug.debug;

public class Instructions {
	public int[] bytes;
	public int[] cycles;
	public String[] mnemonics;
	public String[] addressingModes;
	
	public Instructions() {
		bytes = new int[0xFF];
		cycles = new int[0xFF];
		mnemonics = new String[0xFF];
		addressingModes = new String[0xFF];
		
		//newInstruction(0x00, 2, 7, "BRK", "impl");
		newInstruction(0x10, 2, 2, "BPL", "impl");
		newInstruction(0x78, 1, 2, "SEI", "impl");
		newInstruction(0x84, 2, 3, "STY", "zpg");
		newInstruction(0x88, 1, 2, "DEY", "impl");
		newInstruction(0x8E, 3, 4, "STX", "abs");
		newInstruction(0x91, 2, 6, "STA", "ind,y");
		newInstruction(0x9A, 1, 2, "TXS", "impl");
		newInstruction(0xA0, 2, 2, "LDY", "#");
		newInstruction(0xA2, 2, 2, "LDX", "#");
		newInstruction(0xA6, 2, 3, "LDX", "zpg");
		newInstruction(0xA9, 2, 2, "LDA", "#");
		newInstruction(0xAD, 3, 4, "LDA", "abs");
		newInstruction(0xCA, 1, 2, "DEX", "impl");
		newInstruction(0xC6, 2, 5, "DEC", "zpg");
		newInstruction(0xD0, 2, 2, "BNE", "impl");
		newInstruction(0xD8, 1, 2, "CLD", "impl");
		newInstruction(0xEA, 1, 2, "NOP", "impl");
	}
	
	void newInstruction(int opcode, int bytes, int cycles, String mnemonic, String addressingMode) {
		this.bytes[opcode] = bytes;
		this.cycles[opcode] = cycles;
		this.mnemonics[opcode] = mnemonic;
		this.addressingModes[opcode] = addressingMode;
	}
	
	void branch(CPU cpu, boolean doIt) {
		if(doIt) {
			short oldpc = (short)(cpu.pc + 2);
			
			//System.out.println("branched to " + debug.shortString(cpu.pc + cpu.ram.read(cpu.ram.immediate(cpu.pc))) + " by " + debug.shortString(cpu.ram.read(cpu.ram.immediate(cpu.pc))));
			
			/*if(ram.immediate(pc) < 0) {pc += -0x80 + (ram.immediate(pc) - 0x80);}
			else {}*/
			
			cpu.cycles++;
			cpu.pc += cpu.ram.read(cpu.ram.immediate(cpu)) - bytes[cpu.instruction & 0xFF];
			
			if((oldpc & 0xFF00) != (cpu.pc & 0xFF00)) {cpu.cycles++;} // upper byte of pc = page index
		}
	}
	
	public void bpl(CPU cpu) {branch(cpu, !cpu.flags.getNegative());}
	public void bne(CPU cpu) {branch(cpu, !cpu.flags.getZero());}
	public void sei(CPU cpu) {cpu.flags.setInterrupt(true);}
	public void sty(CPU cpu, Short addressingMode) {cpu.ram.write(addressingMode, cpu.y);}
	public void stx(CPU cpu, Short addressingMode) {cpu.ram.write(addressingMode, cpu.x);}
	public void sta(CPU cpu, Short addressingMode) {cpu.ram.write(addressingMode, cpu.a);}
	public void txs(CPU cpu) {cpu.ram.stackPointer = cpu.x;}
	public void ldy(CPU cpu, Short addressingMode) {
		cpu.y = cpu.ram.read(addressingMode);
		cpu.flags.setNegative(cpu.y < 0);
		cpu.flags.setZero(cpu.y == 0);
	}
	public void ldx(CPU cpu, Short addressingMode) {
		cpu.x = cpu.ram.read(addressingMode);
		cpu.flags.setNegative(cpu.x < 0);
		cpu.flags.setZero(cpu.x == 0);
	}
	public void lda(CPU cpu, Short addressingMode) {
		cpu.a = cpu.ram.read(addressingMode);
		cpu.flags.setNegative(cpu.a < 0);
		cpu.flags.setZero(cpu.a == 0);
	}
	public void dey(CPU cpu) {
		cpu.y--;
		cpu.flags.setNegative(cpu.y < 0);
		cpu.flags.setZero(cpu.y == 0);
	}
	public void dex(CPU cpu) {
		cpu.x--;
		cpu.flags.setNegative(cpu.x < 0);
		cpu.flags.setZero(cpu.x == 0);
	}
	public void dec(CPU cpu, Short addressingMode) {
		cpu.ram.write(addressingMode, cpu.ram.read(addressingMode));
		cpu.flags.setNegative(cpu.x < 0);
		cpu.flags.setZero(cpu.x == 0);
	}
	public void cld(CPU cpu) {cpu.flags.setDecimal(false);}
}