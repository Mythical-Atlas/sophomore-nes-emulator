package Components;

import java.awt.Graphics2D;

import Main.Debug.debug;
import Main.Instructions;
import Main.Main;

public class CPU {
	public int cycles;
	
	boolean run;
	
	public byte a; // bytes are signed by default in java
	public byte x;
	public byte y;
	public byte instruction;
	
	public CPURAM ram;
	public CPUFlags flags;
	
	public short pc;
	
	Instructions instructions;
	
	public CPU(byte[] rom, int trainer, int prgromSize) {
		ram = new CPURAM(rom, trainer, prgromSize);
		
		//System.out.println("ROM at " + debug.shortString(0x00C2) + " = " + debug.byteString(rom[0x00C2]));
		
		instructions = new Instructions();
		flags = new CPUFlags();
		
		run = true;
		cycles = 0;
		pc = (short)0xC000;
	}
	
	public void update(Graphics2D graphics, Main main) {
		if(cycles == 0) {
			instruction = ram.read(pc);
			
			switch(instruction) {
				case((byte)0x10): instructions.bpl(this); 				   	   break; // branch on plus
				case((byte)0x78): instructions.sei(this); 					   break; // set interrupt disable
				case((byte)0x84): instructions.sty(this, ram.zeropage(this));  break; // store y at zeropage
				case((byte)0x88): instructions.dey(this);  					   break; // decrement y
				case((byte)0x8E): instructions.stx(this, ram.absolute(this));  break; // store x at absolute
				case((byte)0x91): instructions.sta(this, ram.indirecty(this)); break; // store accumulator at indirect, y-indexed
				case((byte)0x9A): instructions.txs(this); 					   break; // transfer x to stack pointer
				case((byte)0xA0): instructions.ldy(this, ram.immediate(this)); break; // load y from immediate
				case((byte)0xA2): instructions.ldx(this, ram.immediate(this)); break; // load x from immediate
				case((byte)0xA6): instructions.ldx(this, ram.zeropage(this));  break; // load x from zeropage
				case((byte)0xA9): instructions.lda(this, ram.immediate(this)); break; // load accumulator from immediate
				case((byte)0xAD): instructions.lda(this, ram.absolute(this));  break; // load accumulator from absolute
				case((byte)0xCA): instructions.dex(this); 					   break; // decrement x
				case((byte)0xD0): instructions.bne(this); 				   	   break; // branch on not zero
				case((byte)0xD8): instructions.cld(this); 					   break; // clear decimal
				case((byte)0xEA): 											   break; // no operation
				default:
					System.err.println("Unsupported instruction: " + debug.byteString(ram.read(pc)) + " at " + debug.shortString(pc));
					//System.out.println("");
					/*System.out.println("PC = " + debug.shortString(pc));
					System.out.println("A = " + debug.byteString(a));
					System.out.println("X = " + debug.byteString(x));
					System.out.println("Y = " + debug.byteString(y));
					System.out.println("S = " + debug.byteString(ram.stackPointer));
					System.out.println("N V R B D I Z C");
					System.out.println(flags.getStatusString());*/
					
					run = false;
					break;
			}
			
			if(run) {
				cycles += instructions.cycles[instruction & 0xFF]; // & 0xFF necessary to prevent negative int
				pc += instructions.bytes[instruction & 0xFF];
			}
		}
		
		if(run) {cycles--;}
	}
}
