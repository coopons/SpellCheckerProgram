package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.DropMode;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StartFrame extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
	private SpellCheckFrame frame = new SpellCheckFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
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
	public StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 330);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(211, 211, 211));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("gap 5px 10px", "[grow]", "[][48.00][54.00][30.00][59.00]"));
		
		JLabel lblTitle = new JLabel("Spell Checker Application");
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 33));
		contentPane.add(lblTitle, "cell 0 0,alignx center");
		
		JLabel lblLabel = new JLabel("Add or remove word from dictionary:");
		lblLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		contentPane.add(lblLabel, "cell 0 1,alignx center,aligny bottom");
		
		JLabel lblIsCorrect = new JLabel("Word not in dictionary");
		lblIsCorrect.setVisible(false);
		lblIsCorrect.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		contentPane.add(lblIsCorrect, "flowy,cell 0 2,alignx center");
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setMinimumSize(new Dimension(150, 25));
		textField.setBackground(new Color(255, 255, 255));
		contentPane.add(textField, "cell 0 2,alignx center");
		textField.setColumns(15);
		
		JButton btnAddWord = new JButton("Add");
		btnAddWord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddWord.setOpaque(false);
		btnAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		contentPane.add(btnAddWord, "flowx,cell 0 3,gapx 70px 30px,grow");
		
		JButton btnDelWord = new JButton("Delete");
		btnDelWord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelWord.setOpaque(false);
		contentPane.add(btnDelWord, "cell 0 3,gapx 30px 70px,grow");
		btnDelWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				dispose();
			}
		});
		btnNext.setPreferredSize(new Dimension(120, 30));
		btnNext.setOpaque(false);
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnNext, "cell 0 4,alignx center");
		
	}
	
}
