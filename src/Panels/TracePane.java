package Panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class TracePane extends JPanel {
	public JList trace;
	
	public TracePane() {
		setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("Trace"), new EmptyBorder(4, 4, 4, 4)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 4, 4, 4);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add((trace = new JList()), gbc);
        
        // https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
	}
}
