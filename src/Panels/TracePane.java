package Panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class TracePane extends JPanel {
	@SuppressWarnings("rawtypes")
	public DefaultListModel values;
	@SuppressWarnings("rawtypes")
	public JList list;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TracePane() {
		//JScrollPane scroll = new JScrollPane();
		//JPanel oof = new JPanel();
		
		//setLayout(new GridBagLayout());
		//oof.setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("Trace"), new EmptyBorder(4, 4, 4, 4)));
        
        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.anchor = GridBagConstraints.WEST;
        //gbc.insets = new Insets(4, 4, 4, 4);
        //gbc.fill = GridBagConstraints.BOTH;
        
        values = new DefaultListModel();

        //gbc.gridx = 0;
        //gbc.gridy = 0;
        //gbc.fill = GridBagConstraints.BOTH;
        //add(scroll, gbc);
        /*oof.*/add(list = new JList(values));//, gbc);
        //scroll.add(oof, gbc);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        
        // https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
	}
}
