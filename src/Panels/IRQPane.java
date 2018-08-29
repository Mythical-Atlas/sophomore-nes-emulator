package Panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class IRQPane extends JPanel {
	public JCheckBox external;
	public JCheckBox dpcm;
	public JCheckBox frame;
	public JCheckBox debug;
	
	public IRQPane() {
		setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("IRQs"), new EmptyBorder(4, 4, 4, 4)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add((external = new JCheckBox("External")), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add((dpcm = new JCheckBox("DPCM")), gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        add((frame = new JCheckBox("Frame")), gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        add((debug = new JCheckBox("Debug")), gbc);
	}
}
