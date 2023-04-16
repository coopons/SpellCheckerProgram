package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class SpellCheckFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpellCheckFrame frame = new SpellCheckFrame();
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
	public SpellCheckFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 330);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(211, 211, 211));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][][]"));

		JLabel lblTitle = new JLabel("Spell Checker");
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 33));
		contentPane.add(lblTitle, "cell 0 0,alignx center");

		JTextPane txtExplain = new JTextPane();
		txtExplain.setOpaque(false);
		txtExplain.setFocusable(false);
		txtExplain.setEditable(false);
		txtExplain.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtExplain.setText(
				"Type any word, click submit, and the program will check the spelling of the provided word in the stored dictionary. If the word is spelled correctly, the text will turn green. Otherwise, the text will turn red, and you will be given suggestions of words similar to the provided word.");
		contentPane.add(txtExplain, "cell 0 1,alignx center");

		JLabel lblInputLabel = new JLabel("");
		lblInputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblInputLabel.setVisible(false);

		textField = new JTextField();
		textField.setMinimumSize(new Dimension(150, 25));

		textField.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setOpaque(false);
		btnSubmit.setVisible(false);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isBlank()) {
					
				} else {
					if (StartFrame.isWordInDictionary(textField.getText())) {
						lblInputLabel.setText("Word is spelled correctly");
						textField.setSelectedTextColor(Color.green);
					} else {
						lblInputLabel.setText("Word is spelled wrong");
						textField.setSelectedTextColor(Color.red);
					}
					contentPane.add(lblInputLabel, "flowy,cell 0 1,alignx center,aligny bottom,gaptop 30");
				}

			}
		});

		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(btnContinue);
				contentPane.remove(txtExplain);
				contentPane.repaint();
				contentPane.add(textField, "cell 0 1,alignx center, aligny bottom, gaptop 40");
				contentPane.add(btnSubmit, "cell 0 3,alignx center");
				lblInputLabel.setVisible(true);
				btnSubmit.setVisible(true);
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnContinue.setOpaque(false);
		contentPane.add(btnContinue, "flowx,cell 0 3,alignx center");

	}

}
