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
public class TimingPane extends JPanel {
	public JTextField scanline;
	public JTextField vramAddress;
	public JTextField ticks;
	
	public TimingPane() {
		setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("Timing"), new EmptyBorder(4, 4, 4, 4)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 4, 4, 4);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Scanline: "), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("VRAM addr: "), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Ticks: "), gbc);
        
        gbc.anchor = GridBagConstraints.EAST;
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add((scanline = new JTextField(2)), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        add((vramAddress = new JTextField(2)), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        add((ticks = new JTextField(4)), gbc);
	}
}
