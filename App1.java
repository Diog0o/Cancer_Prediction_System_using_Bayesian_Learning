package app2;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import amostra.Amostra;
import floresta.Forest;
import grafo.Grafoo;
import redes_bayes.BN;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class App1 implements Serializable{
	private static final long serialVersionUID = 1L;

		Amostra A;
		BN bn;
		Forest f;
		Grafoo gr;

		private JFrame frame;
		private JTextComponent textArea; //variavel que guarda a amostra em string

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						App1 window = new App1();
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
		public App1() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 690, 470);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			/*textArea = new JTextArea();
			textArea.setBounds(29, 59, 391, 139);
			frame.getContentPane().add(textArea);
			((JTextArea) textArea).setColumns(10);
			*/

			JFileChooser fileChooser = new JFileChooser();
			JButton btnNewButton = new JButton("Choose File ");
			btnNewButton.addActionListener(new ActionListener() {
				

				@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e) {
					int r = fileChooser.showOpenDialog((Component)e.getSource());
					if (r==fileChooser.APPROVE_OPTION){
						A = new Amostra(fileChooser.getSelectedFile().getAbsolutePath());
						//textArea.setText(A.toString().replace("],", "], \n "));
					}
			}
			});
			
			
			btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			btnNewButton.setBounds(74, 143, 217, 44);
			frame.getContentPane().add(btnNewButton);
			
			

			JButton btnNewButton_1 = new JButton("Learn");
			btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Grafoo g = new Grafoo(A.nr_var());
					gr= Grafoo.g_completo(A);
					//Forest f= new Forest (A.nr_var());
					f= gr.max_spanning_tree1(); //Temos que fazer a max spanning tree devolver uma floresta
					bn = new BN(f,A,0.5);				
					}
			});
			btnNewButton_1.setBounds(58, 320, 266, 72);
			frame.getContentPane().add(btnNewButton_1);
			
			JFileChooser fileChooser2 = new JFileChooser();

			JButton btnNewButton_2 = new JButton("Save");
			btnNewButton_2.addActionListener(new ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e) {
					int r = fileChooser2.showSaveDialog((Component)e.getSource());
					if (r==fileChooser2.APPROVE_OPTION){
						try {
							FileOutputStream fileOut = new 
									FileOutputStream(
											fileChooser2.getSelectedFile().getAbsolutePath());
							ObjectOutputStream objectOut = 
									new ObjectOutputStream(fileOut);
							objectOut.writeObject(bn); 
							objectOut.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			});
			btnNewButton_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			
			
			btnNewButton_2.setBounds(352, 320, 266, 72);
			frame.getContentPane().add(btnNewButton_2);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(App1.class.getResource("/imagens/wallpaper_IST_Branco_2560-removebg-preview (1).png")));
			lblNewLabel.setBounds(32, 11, 202, 91);
			frame.getContentPane().add(lblNewLabel);

		}
	}