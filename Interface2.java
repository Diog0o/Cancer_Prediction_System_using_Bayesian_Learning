package app;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.border.Border;

import amostra.Amostra;
import exp.Exp;
import redes_bayes.BN;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class Interface2 {
	
	BN bn;

	private JFrame frame;
	private JTextField txtInput;
	private JTextField txtOutput;
	private JLabel lblNewLabel_1;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	private static class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface2 window = new Interface2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setForeground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 606, 363);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		//ler a rede de bayes do disco
		
		 JButton btnreset = new JButton("");
		 btnreset.setBounds(490, 0,90, 90);
		 btnreset.setIcon(new ImageIcon(Interface2.class.getResource("/imagens/reset (2) (1).png")));
		 btnreset.setBackground(new Color(224, 255, 255));
		 btnreset.setBorder(null); //10 is the radius
		 btnreset.setForeground(new Color(224, 255, 255));
		btnreset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				txtInput.setText("Input"); //pomos a string entre aspas
				txtOutput.setText("Output");
			}
		});
		
		//btnreset.setBounds(491, 28, 89, 23);
		frame.getContentPane().add(btnreset);
		
		
		 JButton btnNewButton = new JButton("");
		 btnNewButton.setBounds(34, 123,90, 90);
		 btnNewButton.setIcon(new ImageIcon(Interface2.class.getResource("/imagens/open-book (1).png")));
		 btnNewButton.setBackground(new Color(224, 255, 255));
		 btnNewButton.setBorder(null); //10 is the radius
		 btnNewButton.setForeground(new Color(224, 255, 255));
		
		//JButton btnNewButton = new JButton("Read Object");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser2 = new JFileChooser();
				fileChooser2.setDialogTitle("Object to open");   

				int r = fileChooser2.showOpenDialog((Component)e.getSource());

				if (r == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser2.getSelectedFile();
					FileInputStream fileIn; //o que e isto
					try {
						fileIn = new FileInputStream(fileToSave);
						ObjectInputStream objectIn = new ObjectInputStream(fileIn);
						bn = (BN)objectIn.readObject();
						objectIn.close();
						fileIn.close();
						lblNewLabel_3.setText("FILE SELECTED");

					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblNewLabel.setText("Sucess");
				}
				else lblNewLabel.setText("Error");
			}
		});
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(265, 222, 176, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		frame.getContentPane().add(btnNewButton);
		
		 JButton btnrun = new JButton("");
		 btnrun.setBounds(455, 123,90, 90);
		 btnrun.setIcon(new ImageIcon(Interface2.class.getResource("/imagens/classified (1).png")));
		 btnrun.setBackground(new Color(224, 255, 255));
		 btnrun.setBorder(null); //10 is the radius
		 btnrun.setForeground(new Color(224, 255, 255));
		//JButton btnrun = new JButton("Calculate");
		btnrun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//String dm = txtInput.getText();
				String tb = txtInput.getText();
				
				ArrayList<int[]> opcoes = new ArrayList<int[]>();
				
				for (int i=0; i < bn.d_classe(); i++) {
					//String b = Integer.toString(i);
					String a = tb.concat(",").concat(Integer.toString(i));
					//int[] aa = Arrays.stream(a.split(",")).mapToInt(Integer::parseInt).toArray();
					opcoes.add(Arrays.stream(a.split(",")).mapToInt(Integer::parseInt).toArray());
				}
				
				//String exp0 = tb.concat(",0");
				//String exp1 = tb.concat(",1");
				
				//txtOutput.setText(exp);
				
				
				//int[] tb1 = Arrays.stream(exp0.split(",")).mapToInt(Integer::parseInt).toArray();
				//int[] dm1 = Arrays.stream(exp1.split(",")).mapToInt(Integer::parseInt).toArray();
				
				//mostrar probabilidade
				
				double max = 0;
				int classe = -1;
				
				for (int i=0; i < opcoes.size();i++) {
					if (bn.prob(opcoes.get(i)) > max) {
						max = bn.prob(opcoes.get(i));
						classe= i;
					}
				}
				
				
				/* if (bn.prob(dm1)>bn.prob(tb1)) {
					txtOutput.setText("Ups");
				}
				else{
					txtOutput.setText("Tass bem");
				}
				
				*/
				double d = 0;
				
				for (int i=0; i < opcoes.size();i++) {
					d+=bn.prob(opcoes.get(i));
				}
				
				double res = bn.prob(opcoes.get(classe))/d *100 ;
				
				
				
				txtOutput.setText("The class is " + Integer.toString(classe));
				lblNewLabel_1.setText("With a " +  df.format(res) + "%" + " chance");
				
				
				
			}
		});
		//btnrun.setBounds(445, 166, 89, 23);
		frame.getContentPane().add(btnrun);
		
		
		
		txtInput = new JTextField();
		txtInput.setBackground(new Color(255, 255, 255));
		txtInput.setToolTipText("Insert values between commas (eg: \"0,0,0,0\")");
		//como e que sabe o tamanho do vetor que tem que receber?
		//le a amostra antes?
		//como le a amostra?
		txtInput.setBounds(221, 152, 166, 37);
		txtInput.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
		frame.getContentPane().add(txtInput);
		txtInput.setColumns(10);
		
		txtOutput = new JTextField();
		txtOutput.setText("Output");
		txtOutput.setBounds(265, 265, 86, 20);
		frame.getContentPane().add(txtOutput);
		txtOutput.setColumns(10);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Mongolian Baiti", Font.PLAIN, 11));
		lblNewLabel_2.setIcon(new ImageIcon(Interface2.class.getResource("/imagens/wallpaper_IST_Branco_2560-removebg-preview (1).png")));
		lblNewLabel_2.setBounds(24, 11, 198, 98);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("SELECT FILE");
		lblNewLabel_3.setFont(new Font("Mongolian Baiti", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(44, 220, 69, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("CLASSIFY");
		lblNewLabel_4.setFont(new Font("Mongolian Baiti", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(476, 222, 69, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("INSERT DATA");
		lblNewLabel_5.setFont(new Font("Mongolian Baiti", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(265, 133, 86, 14);
		frame.getContentPane().add(lblNewLabel_5);
	}
}
