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
public class RegisterPane extends JPanel {
	public JTextField a;
	public JTextField x;
	public JTextField y;
	public JTextField p;
	public JTextField sp;
	public JTextField pc;
	
	public RegisterPane() {
		setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("Registers"), new EmptyBorder(4, 4, 4, 4)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("A: "), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("X: "), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Y: "), gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(new JLabel("P: "), gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(new JLabel("SP: "), gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(new JLabel("PC: "), gbc);
        
        gbc.anchor = GridBagConstraints.EAST;
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add((a = new JTextField(2)), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        add((x = new JTextField(2)), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        add((y = new JTextField(2)), gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        add((p = new JTextField(2)), gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 1;
        add((sp = new JTextField(2)), gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 2;
        add((pc = new JTextField(4)), gbc);
	}
}
