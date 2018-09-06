package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Components.Motherboard;
import Main.Debug.debug;
import Panels.ControlsPane;
import Panels.FlagsPane;
import Panels.IRQPane;
import Panels.InstructionPane;
import Panels.RegisterPane;
import Panels.TimingPane;
import Panels.TracePane;

@SuppressWarnings("serial")
public class Main extends JPanel implements KeyListener, Runnable {
	int currentState;
	Thread thread;
	Motherboard motherboard;
	
	public RegisterPane registerPane;
	public TimingPane timingPane;
	public ControlsPane controlsPane;
	public IRQPane irqPane;
	public FlagsPane flagsPane;
	public InstructionPane instructionPane;
	public TracePane tracePane;
	JPanel nesPane;
	
	static byte[] rom;
	
	static final int FPS = 60;
	static final int SCALE = 2;
	static final int WIDTH = 256;
	static final int HEIGHT = 240;
	
	GridBagConstraints gbc;

	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Invalid number of arguments");
			System.exit(1);
		}
		
		File file = new File(args[0]);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			rom = new byte[(int)file.length()];
			fileInputStream.read(rom);
			fileInputStream.close();
		}
		catch (Exception e) {e.printStackTrace();}
		
		JFrame frame = new JFrame("Ben Correll's NES Emulator");
		
		//frame.setBounds(center.x - WIDTH * SCALE / 2, center.y - HEIGHT * SCALE / 2, WIDTH * SCALE, HEIGHT * SCALE);
		frame.setContentPane(new Main());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Main() {
		setFocusable(true);
		requestFocus();
		setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(2, 2, 2, 2);
        
        registerPane = new RegisterPane();
        timingPane = new TimingPane();
        controlsPane = new ControlsPane();
        nesPane = new JPanel();
        irqPane = new IRQPane();
        flagsPane = new FlagsPane();
        instructionPane = new InstructionPane();
        tracePane = new TracePane();
        
        //JScrollPane scroll = new JScrollPane(tracePane);
        
        nesPane.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        tracePane.setPreferredSize(new Dimension(256, 240));
        
        gbc.weightx = 0;
        gbc.weighty = 0;
        
        addPane(controlsPane, 	 0, 0, 2, 1, GridBagConstraints.HORIZONTAL);
        addPane(registerPane, 	 0, 1, 1, 1, GridBagConstraints.HORIZONTAL);
        addPane(timingPane,	 	 1, 1, 1, 1, GridBagConstraints.HORIZONTAL);
        addPane(irqPane, 	 	 0, 2, 2, 1, GridBagConstraints.HORIZONTAL);
        addPane(flagsPane, 	 	 0, 3, 2, 1, GridBagConstraints.HORIZONTAL);
        addPane(instructionPane, 0, 4, 1, 1, GridBagConstraints.HORIZONTAL);
        addPane(nesPane,	 	 2, 0, 1, 8, GridBagConstraints.NONE);
        addPane(tracePane,		 3, 0, 1, 8, GridBagConstraints.NONE);
        
        controlsPane.resetButton.setEnabled(false);
	}

	public void addNotify() {
		super.addNotify();
		
		addKeyListener(this);
		//addMouseListener(this);
		
		thread = new Thread(this);
		thread.start();
	}

	@SuppressWarnings("static-access")
	public void run() {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D)image.getGraphics();
		
		motherboard = new Motherboard(rom);
		registerPane.a.setText(debug.byteJTextField(motherboard.cpu.a));
		registerPane.x.setText(debug.byteJTextField(motherboard.cpu.x));
		registerPane.y.setText(debug.byteJTextField(motherboard.cpu.y));
		registerPane.p.setText(debug.byteJTextField(motherboard.cpu.flags.getStatusByte()));
		registerPane.sp.setText(debug.byteJTextField(motherboard.cpu.ram.stackPointer));
		registerPane.pc.setText(debug.shortJTextField(motherboard.cpu.pc));
		
		while(true) {
			long start = System.nanoTime();
			
			motherboard.update(graphics, this);
			
			Graphics graphicsTemp = getGraphics();
			
			graphicsTemp.drawImage(image, nesPane.getX(), nesPane.getY(), WIDTH * SCALE, HEIGHT * SCALE, null);
			graphicsTemp.dispose();
			
			long wait = 1000 / FPS - (System.nanoTime() - start) / 1000000;
			if(wait <= 0) {wait = 0;}
			
			try {thread.sleep(wait);}
			catch(Exception e) {e.printStackTrace();}
		}
	}

	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {motherboard.keyPressed(key.getKeyCode());}
	public void keyReleased(KeyEvent key) {motherboard.keyReleased(key.getKeyCode());}
	
	public void mouseClicked(MouseEvent mouse) {motherboard.mouseClicked(mouse);}
	public void mouseEntered(MouseEvent mouse) {motherboard.mouseEntered(mouse);}
	public void mouseExited(MouseEvent mouse) {motherboard.mouseExited(mouse);}
	public void mousePressed(MouseEvent mouse) {motherboard.mousePressed(mouse);}
	public void mouseReleased(MouseEvent mouse) {motherboard.mouseReleased(mouse);}
	
	void addPane(JPanel pane, int x, int y, int w, int h, int fill) {
		gbc.gridx = x;
        gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
        gbc.fill = fill;
        
        this.add(pane, gbc);
	}
	void addPane(JScrollPane pane, int x, int y, int w, int h, int fill) {
		gbc.gridx = x;
        gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
        gbc.fill = fill;
        
        this.add(pane, gbc);
	}
}