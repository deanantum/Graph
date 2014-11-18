package guiWindowBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class LabGuiWindowBuilder extends JFrame {

	private JPanel contentPane;
	private JLabel mainLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabGuiWindowBuilder frame = new LabGuiWindowBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LabGuiWindowBuilder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setContentPane();
		setContentPane(contentPane);
		
		JLabel lblTitle = newLblTitle();
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		newMainLabel();
		contentPane.add(mainLabel, BorderLayout.CENTER);
		
		JPanel radioPanel = newRadioPanel();
		contentPane.add(radioPanel, BorderLayout.WEST);
	}

	private JPanel newRadioPanel() {
		JPanel radioPanel = new JPanel();
		radioPanel.setBorder(new EmptyBorder(10, 7, 0, 0));
		
		radioPanel.setLayout(new GridLayout(10, 1, 0, 0));
		
		final JRadioButton rdbtnForeground = newRdbtnForeground();
		radioPanel.add(rdbtnForeground);		
		
		JRadioButton rdbtnBackground = newRdbtnBackground();
		radioPanel.add(rdbtnBackground);		
		
		JButton btnIncrement = newBtnIncrement();
		radioPanel.add(btnIncrement);
		
		return radioPanel;
	}

	private JButton newBtnIncrement() {
		JButton btnIncrement = new JButton("Increment");		
		btnIncrement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = mainLabel.getText();
				int parseNum = Integer.parseInt( number );
				parseNum++;
				mainLabel.setText( String.valueOf( parseNum ));
			}
		});
		return btnIncrement;
	}

	private JRadioButton newRdbtnBackground() {
		final JRadioButton rdbtnBackground = new JRadioButton("Background");
		buttonGroup.add(rdbtnBackground);
		rdbtnBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JRadioButton radioButton = (JRadioButton) e.getSource();
				if( rdbtnBackground.isSelected() ){
					mainLabel.setBackground( Color.DARK_GRAY );
				}else{
					mainLabel.setBackground( new Color(95, 158, 160) );
				}
			}
		});
		return rdbtnBackground;
	}

	private JRadioButton newRdbtnForeground() {
		final JRadioButton rdbtnForeground = new JRadioButton("Foreground");
		buttonGroup.add(rdbtnForeground);
		rdbtnForeground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( rdbtnForeground.isSelected() ){
					mainLabel.setForeground( Color.MAGENTA );
				}else{
					mainLabel.setForeground( Color.BLACK );
				}
				
				/*JRadioButton radioButton = (JRadioButton) e.getSource();
				if( radioButton.isSelected() ){
					mainLabel.setForeground( Color.MAGENTA );
				}else{
					mainLabel.setForeground( Color.BLACK );
				}*/
			}
		});
		return rdbtnForeground;
	}

	private void newMainLabel() {
		mainLabel = new JLabel("10");
		mainLabel.setOpaque(true);
		mainLabel.setBackground(new Color(95, 158, 160));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Tahoma", Font.PLAIN, 99));
	}

	private void setContentPane() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}

	private JLabel newLblTitle() {
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(50, 205, 50));
		lblTitle.setForeground(new Color(255, 255, 240));
		return lblTitle;
	}

}
