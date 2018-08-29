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
				case((byte)0x10): instructions.bpl(this); 				     break; // branch on plus
				case((byte)0x78): instructions.sei(this); 					 break; // set interrupt disable
				case((byte)0x84): instructions.sty(this, ram.zeropage(pc));  break; // store y zeropage
				case((byte)0x8E): instructions.stx(this, ram.absolute(pc));  break; // store X absolute
				case((byte)0x9A): instructions.txs(this); 					 break; // transfer X to stack pointer
				case((byte)0xA0): instructions.ldy(this, ram.immediate(pc)); break; // load Y immediate
				case((byte)0xA2): instructions.ldx(this, ram.immediate(pc)); break; // load X immediate
				case((byte)0xA6): instructions.ldx(this, ram.zeropage(pc));  break; // load X zeropage
				case((byte)0xA9): instructions.lda(this, ram.immediate(pc)); break; // load accumulator immediate
				case((byte)0xAD): instructions.lda(this, ram.absolute(pc));  break; // load accumulator absolute
				case((byte)0xCA): instructions.dex(this); 					 break; // decrement X
				case((byte)0xD8): instructions.cld(this); 					 break; // clear decimal
				case((byte)0xEA): 											 break; // no operation
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
