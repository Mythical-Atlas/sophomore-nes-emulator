package Panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class InstructionPane extends JPanel {
	public JTextField mnemonic;
	public JTextField addressingMode;
	public JTextField opcode;
	public JTextField byte1;
	public JTextField byte2;
	public JTextField cycles;
	
	public InstructionPane() {
		setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("Instruction"), new EmptyBorder(4, 4, 4, 4)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Mnemonic: "), gbc);
        
        /*gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Addressing Mode: "), gbc);*/
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Opcode: "), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Cycles: "), gbc);
        
        gbc.anchor = GridBagConstraints.EAST;
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add((mnemonic = new JTextField()), gbc);
        
        /*gbc.gridx = 1;
        gbc.gridy = 1;
        add((addressingMode = new JTextField()), gbc);*/
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        add((opcode = new JTextField(2)), gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add((byte1 = new JTextField(2)), gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add((byte2 = new JTextField(2)), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        add((cycles = new JTextField(2)), gbc);
	}
}
