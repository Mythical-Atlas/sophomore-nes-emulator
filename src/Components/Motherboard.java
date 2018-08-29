package Components;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import Main.Main;
import Main.Debug.debug;

public class Motherboard {
	int ines;
	int trainer;
	int prgromSize;
	int chrromSize;
	int mapper;	
	
	public CPU cpu;
	PPU ppu;
	
	byte[] rom;
	
	public Motherboard(byte[] rom) {
		this.rom = rom;
		
		if(!(rom[0] == 0x4E && rom[1] == 0x45 && rom[2] == 0x53 && rom[3] == 0x1A)) {
			System.err.println("iNES header is invalid");
			System.exit(1);
		}
		
		trainer = rom[6] & 0x04;
		prgromSize = rom[4] * 16 * 1024; // in bytes
		chrromSize = rom[5] * 8 * 1024;  // in bytes
		
		/*if((rom[7] & 0x0C) == 0x08) {ines = 0;}
		else if((rom[7] & 0x0C) == 0x00) {*/ines = 1;/*}
		else {ines = 2;}*/
		
		if(ines != 1) {
			System.err.println("iNES type unsupported: " + ines);
			System.exit(1);
		}
		
		mapper = ((rom[6] & 0xF0) >> 4) + (rom[7] & 0xF0);
		if(mapper != 0) {
			System.err.println("Unsupported mapper: " + mapper);
			System.exit(1);
		}
		
		cpu = new CPU(rom, 0, prgromSize);
		ppu = new PPU();
	}
	
	public void update(Graphics2D graphics, Main main) {
		//main.controlsPane.run.addActionListener();
		
		if(main.controlsPane.run && cpu.run) {
			for(int i = 0; i < (341.5 * 262 * 4); i++) { // already updating at 60 Hz (60 fps)
				if(!main.controlsPane.run) {break;}
				cpu.update(graphics, main);
				if(!cpu.run) {
					main.controlsPane.run = false;
					break;
				}
			}
		}
		if(main.controlsPane.step) {
			if(cpu.run) {
				while(cpu.cycles > 0) {cpu.update(graphics, main);}
				cpu.update(graphics, main);
			}
			main.controlsPane.run = false;
			main.controlsPane.step = false;
		}
		if(main.controlsPane.power) {
			cpu = new CPU(rom, 0, prgromSize);
			ppu = new PPU();
			main.controlsPane.power = false;
		}
		
		main.registerPane.a.setText(debug.byteJTextField(cpu.a));
		main.registerPane.x.setText(debug.byteJTextField(cpu.x));
		main.registerPane.y.setText(debug.byteJTextField(cpu.y));
		main.registerPane.p.setText(debug.byteJTextField(cpu.flags.getStatusByte()));
		main.registerPane.sp.setText(debug.byteJTextField(cpu.ram.stackPointer));
		main.registerPane.pc.setText(debug.shortJTextField(cpu.pc));
		
		/*main.instructionPane.mnemonic.setText(cpu.instructions.mnemonics[cpu.ram.read(cpu.pc) & 0xFF]);
		main.instructionPane.addressingMode.setText(cpu.instructions.addressingModes[cpu.ram.read(cpu.pc) & 0xFF]);*/
		
		if(cpu.instructions.mnemonics[cpu.ram.read(cpu.pc) & 0xFF] != null) {main.instructionPane.mnemonic.setText(cpu.instructions.mnemonics[cpu.ram.read(cpu.pc) & 0xFF] + " " + cpu.instructions.addressingModes[cpu.ram.read(cpu.pc) & 0xFF]);}
		else {
			main.instructionPane.mnemonic.setText("UNKNOWN");
			//main.instructionPane.mnemonic.setEnabled(false);
		}
		
		main.instructionPane.opcode.setText(debug.byteJTextField(cpu.ram.read(cpu.pc)));
		
		if(cpu.instructions.mnemonics[cpu.ram.read(cpu.pc) & 0xFF] != null) {
			if(cpu.instructions.bytes[cpu.ram.read(cpu.pc) & 0xFF] == 1) {
				main.instructionPane.byte1.setText("00");
				main.instructionPane.byte2.setText("00");
				main.instructionPane.byte1.setEnabled(false);
				main.instructionPane.byte2.setEnabled(false);
			}
			else if(cpu.instructions.bytes[cpu.ram.read(cpu.pc) & 0xFF] == 2) {
				main.instructionPane.byte1.setText(debug.byteJTextField(cpu.ram.read(cpu.pc + 1)));
				main.instructionPane.byte2.setText("00");
				main.instructionPane.byte1.setEnabled(true);
				main.instructionPane.byte2.setEnabled(false);
			}
			else {
				main.instructionPane.byte1.setText(debug.byteJTextField(cpu.ram.read(cpu.pc + 1)));
				main.instructionPane.byte2.setText(debug.byteJTextField(cpu.ram.read(cpu.pc + 2)));
				main.instructionPane.byte1.setEnabled(true);
				main.instructionPane.byte2.setEnabled(true);
			}
		}
		else {
			main.instructionPane.byte1.setText("??");
			main.instructionPane.byte2.setText("??");
			main.instructionPane.byte1.setEnabled(false);
			main.instructionPane.byte2.setEnabled(false);
		}
		
		if(cpu.instructions.mnemonics[cpu.ram.read(cpu.pc) & 0xFF] != null) {main.instructionPane.cycles.setText(debug.byteJTextField((byte)cpu.instructions.cycles[cpu.ram.read(cpu.pc) & 0xFF]));}
		else {
			main.instructionPane.cycles.setText("??");
			//main.instructionPane.cycles.setEnabled(false);
		}
		
		main.flagsPane.c.setSelected(cpu.flags.carryFlag);
		main.flagsPane.z.setSelected(cpu.flags.zeroFlag);
		main.flagsPane.i.setSelected(cpu.flags.interruptFlag);
		main.flagsPane.d.setSelected(cpu.flags.decimalFlag);
		main.flagsPane.b.setSelected(cpu.flags.breakFlag);
		main.flagsPane.r.setSelected(cpu.flags.alwaysSetFlag);
		main.flagsPane.v.setSelected(cpu.flags.overflowFlag);
		main.flagsPane.n.setSelected(cpu.flags.negativeFlag);
		
		//if(!cpu.run) {System.exit(0);}
	}
	
	public void keyPressed(int key) {}
	public void keyReleased(int key) {}

	public void mouseClicked(MouseEvent mouse) {}
	public void mouseEntered(MouseEvent mouse) {}
	public void mouseExited(MouseEvent mouse) {}
	public void mousePressed(MouseEvent mouse) {}
	public void mouseReleased(MouseEvent mouse) {}
	
	/*public class RunListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Motherboard.run = true;
			System.out.println("run");
		}
	}*/
}