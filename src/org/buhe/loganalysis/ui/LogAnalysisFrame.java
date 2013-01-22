/**
 * 
 */
package org.buhe.loganalysis.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.buhe.loganalysis.common.ComponentContainer;
import org.buhe.loganalysis.common.ENV;
import org.buhe.loganalysis.common.Named;
import org.buhe.loganalysis.core.LogAnalysis;
import org.buhe.loganalysis.interceptor.Configurable;
import org.buhe.loganalysis.interceptor.LogAnalysisInterceptor;


/**
 * @author buhe
 * 
 */
public class LogAnalysisFrame extends JFrame {

	private static final long serialVersionUID = 100099L;
	private JTextField intput;
	private JTextField output;

	private JComboBox reader;
	private JComboBox writer;
	private List<JCheckBoxWithProperties> checkBoxGroup;

	LogAnalysisFrame() {
		initUI();
	}

	private void initUI() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 400);
		this.setTitle("Log Analysis ( " + ENV.VERSION + " )");
		this.setLayout(new GridLayout(5, 1));
		this.setBackground(Color.WHITE);
		addTitlePane();
		addIOPane();
		addRWPane();
		addInterceptorPane();
		addButtonPane();
	}

	private void addInterceptorPane() {
		JPanel p = build();
		checkBoxGroup = buildCheckBoxGroup(ComponentContainer
				.getLogAnalysisInterceptors());
		for (JCheckBox cb : checkBoxGroup) {
			p.add(cb);
		}
		this.add(p);

	}

	private void addButtonPane() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton s = buildButton("Analyse");
		s.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					doSend();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});
		p.add(s);
		this.add(p);

	}

	private void doSend() throws NamingException {
		String intput = this.intput.getText();
		String output = this.output.getText();
		String reader = this.reader.getSelectedItem().toString();
		String writer = this.writer.getSelectedItem().toString();
		List<LogAnalysisInterceptor> interceptors = getSelectedinterceptors();
		LogAnalysis la = new LogAnalysis(interceptors,
				ComponentContainer.getLogReader(reader),
				ComponentContainer.getLogWriter(writer), new File(intput),
				new File(output));

		la.start();
	}

	private List<LogAnalysisInterceptor> getSelectedinterceptors() {
		List<LogAnalysisInterceptor> selectedInterceptor = new ArrayList<LogAnalysisInterceptor>();
		for (JCheckBox cb : checkBoxGroup) {
			if (cb.isSelected()) {
				selectedInterceptor.add(ComponentContainer.getLogAnalysisInterceptor(cb.getText()));
			}
		}
		return selectedInterceptor;
	}

	private JButton buildButton(String name) {
		return new JButton(name);
	}

	private void addIOPane() {
		JPanel p = build();
		p.add(new JLabel("Input File : "));
		intput = buildReadonlyInput("");
		p.add(intput);
		p.add(buildFileBrowseButton(intput,JFileChooser.FILES_ONLY));
		p.add(new JLabel("Output Directory : "));
		output = buildReadonlyInput("");
		p.add(output);
		p.add(buildFileBrowseButton(output,JFileChooser.DIRECTORIES_ONLY));
		this.add(p);

	}

	private void addRWPane() {
		JPanel p = build();
		p.add(new JLabel("Log Reader : "));
		reader = build(ComponentContainer.getLogReaders());
		p.add(reader);
		p.add(new JLabel("Log Writer : "));
		writer = build(ComponentContainer.getLogWriters());
		p.add(writer);
		this.add(p);

	}

	private void addTitlePane() {
		JPanel p = buildCenterPanel();
		p.add(new JLabel("Log Analysis ( " + ENV.VERSION + " )"));
		this.add(p);

	}

	private JPanel buildCenterPanel() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		p.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		return p;
	}

	private JComboBox build(List<? extends Named> components) {
		JComboBox cb = new JComboBox();
		for (Named c : components) {
			cb.addItem(c.name());
		}
		return cb;
	}

	private List<JCheckBoxWithProperties> buildCheckBoxGroup(List<? extends Named> components) {
		List<JCheckBoxWithProperties> cbs = new ArrayList<JCheckBoxWithProperties>();
		for (Named c : components) {
			final JCheckBoxWithProperties cb = new JCheckBoxWithProperties(c.name());
			final LogAnalysisInterceptor i = ComponentContainer.getLogAnalysisInterceptor(c.name());
			cb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(cb.isSelected()){
						//拦截器可配置
						if(i instanceof Configurable){
							PropertiesEditor pe = new PropertiesEditor(LogAnalysisFrame.this,((Configurable) i).getKeys());
							pe.setLocation(cb.getLocationOnScreen());
							pe.setVisible(true);
							Properties p = pe.getProperties();
							cb.setProperties(p);
							((Configurable) i).setProperties(p);
						}
					}
				}
			});
			cbs.add(cb);
		}
		return cbs;
	}

	private JPanel build() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		p.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
		return p;
	}

	private JTextField buildReadonlyInput(String str) {
		JTextField p = new JTextField(str, 10);
		p.setEditable(false);
		return p;
	}
	
	private JButton buildFileBrowseButton(final JTextField textField,final int mode){
		JButton fb = new JButton("Browse");
		fb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(mode);
				fc.showOpenDialog(LogAnalysisFrame.this);
				if(fc.getSelectedFile() != null){
					textField.setText(fc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		return fb;
	}
	
	class JCheckBoxWithProperties extends JCheckBox{
		private static final long serialVersionUID = 2027555909415155892L;
		private Properties p = new Properties();

		public JCheckBoxWithProperties(String name) {
			super(name);
		}

		public Properties getProperties() {
			return p;
		}

		public void setProperties(Properties p) {
			this.p = p;
		}
		
	}

}
