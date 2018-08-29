package Panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ControlsPane extends JPanel implements ActionListener {
	JButton runButton;
	JButton stepButton;
	public JButton resetButton;
	JButton powerButton;
	
	public boolean run;
	public boolean step;
	public boolean reset;
	public boolean power;
	
	public ControlsPane() {
		setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("Controls"), new EmptyBorder(4, 4, 4, 4)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add((runButton = new JButton("Run")), gbc);
        runButton.addActionListener(this);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add((stepButton = new JButton("Step")), gbc);
        stepButton.addActionListener(this);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        add((resetButton = new JButton("Reset")), gbc);
        resetButton.addActionListener(this);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        add((powerButton = new JButton("Power")), gbc);
        powerButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == runButton) {run = !run;}
		if(event.getSource() == stepButton) {step = !step;}
		if(event.getSource() == resetButton) {reset = !reset;}
		if(event.getSource() == powerButton) {power = !power;}
	}
}
