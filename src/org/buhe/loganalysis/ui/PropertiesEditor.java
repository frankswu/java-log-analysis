/**
 * 
 */
package org.buhe.loganalysis.ui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author buhe
 *
 */
public class PropertiesEditor extends JDialog {

	private static final long serialVersionUID = 3029465484175502188L;
	private Properties p = new Properties();
	private Map<String,JTextField> m = new HashMap<String,JTextField>();
	
	PropertiesEditor(Frame owner,String[] keys){
		super(owner);
		this.setTitle("PropertiesEditor");
		this.setLayout(new GridLayout(keys.length + 1, 2));
		this.setBackground(Color.WHITE);
		this.setModal(true);
		for(String k : keys){
			this.add(buildLabel(k));
			JTextField tf = buildInput();
			this.add(tf);
			m.put(k, tf);
		}
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Entry<String, JTextField> entry : m.entrySet()){
					p.put(entry.getKey(), entry.getValue().getText());
				}
				PropertiesEditor.this.dispose();
			}
		});
		this.add(ok);
		this.pack();
	}
	
	public Properties getProperties(){
		return p;
	}
	
	private JLabel buildLabel(String str) {
		JLabel p = new JLabel(str);
		return p;
	}
	
	private JTextField buildInput() {
		JTextField p = new JTextField(10);
		return p;
	}

}
