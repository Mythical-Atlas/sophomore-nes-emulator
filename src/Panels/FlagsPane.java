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
public class FlagsPane extends JPanel {
	public JCheckBox n;
	public JCheckBox v;
	public JCheckBox r;
	public JCheckBox b;
	public JCheckBox d;
	public JCheckBox i;
	public JCheckBox z;
	public JCheckBox c;
	
	public FlagsPane() {
		setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("Flags"), new EmptyBorder(4, 4, 4, 4)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add((n = new JCheckBox("N")), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add((v = new JCheckBox("V")), gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        add((r = new JCheckBox("R")), gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        add((b = new JCheckBox("B")), gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 0;
        add((d = new JCheckBox("D")), gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 0;
        add((i = new JCheckBox("I")), gbc);
        
        gbc.gridx = 6;
        gbc.gridy = 0;
        add((z = new JCheckBox("Z")), gbc);
        
        gbc.gridx = 7;
        gbc.gridy = 0;
        add((c = new JCheckBox("C")), gbc);
        
        r.setSelected(true);
        r.setEnabled(false);
        b.setEnabled(false);
	}
}
