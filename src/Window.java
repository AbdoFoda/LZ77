import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;


public class Window extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JFileChooser fc;
	private JFileChooser dc;
	private JButton btnAddFile;
	private JButton btnSelectDirectory;
	private JButton Compress;
	private JButton deCompress;
	private JLabel defaultNote;
	private JLabel note;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
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
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("LZ77 Compression");
		frame.setBounds(200, 200, 777, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel header = new JLabel("Compression and deCompression Program :)");
		header.setBounds(10, 10, 500, 100);
		frame.getContentPane().add(header);

		JLabel lblChooseAFile = new JLabel("choose A file       :");
		lblChooseAFile.setBounds(10, 50, 200, 100);
		frame.getContentPane().add(lblChooseAFile);

		JLabel lblChooseADirectory = new JLabel("choose directory:");
		lblChooseADirectory.setBounds(10, 100, 200, 100);
		frame.getContentPane().add(lblChooseADirectory);

		textField = new JTextField();
		textField.setBounds(150, 90, 400, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(150, 140, 400, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		btnAddFile = new JButton("Add File");
		btnAddFile.setBounds(600, 90, 150, 25);
		frame.getContentPane().add(btnAddFile);
		btnAddFile.addActionListener(this);

		btnSelectDirectory = new JButton("Select Directory");
		btnSelectDirectory.setBounds(600, 140, 150, 25);
		frame.getContentPane().add(btnSelectDirectory);
		btnSelectDirectory.addActionListener(this);

		Compress = new JButton("Compress");
		Compress.setBounds(600, 200, 150, 25);
		Compress.setEnabled(false);
		Compress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (Main.validFileAndDirectory(textField.getText(), textField_1.getText())
						&& fileExtension(textField.getText()).equals("txt")) {
					try {
						ArrayList<Key> keys = Encryption.encrypt(File.readTextFile(textField.getText()));
						String output=textField_1.getText()+"/";
						int start=textField.getText().lastIndexOf('/')+1 , end =textField.getText().lastIndexOf('.');
						output+=textField.getText().substring(start,end)+".lz77" ;
						Main.newFile(output);
						File.writeToKeysFile(output, keys);
						JOptionPane.showMessageDialog(null, "Process is done succefully");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "An error has been occured with the file");
					return;
				}
			}

		});
		frame.getContentPane().add(Compress);

		deCompress = new JButton("deCompress");
		deCompress.setBounds(430, 200, 150, 25);
		deCompress.setEnabled(false);
		deCompress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (Main.validFileAndDirectory(textField.getText(), textField_1.getText())
						&& fileExtension(textField.getText()).equals("lz77")) {
					try {
						String text = Decryption.decrypt(File.readKeysFile(textField.getText()));
						String output=textField_1.getText()+"/";
						int start=textField.getText().lastIndexOf('/')+1 , end =textField.getText().lastIndexOf('.');
						output+=textField.getText().substring(start,end)+"1.txt" ;
						Main.newFile(output);
						File.writeToTextFile(output, text);
						JOptionPane.showMessageDialog(null, "Process is done succefully");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "An error has been occured with the file");
					return;
				}
			}
			
		});
		frame.getContentPane().add(deCompress);

		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		dc = new JFileChooser();
		dc.setAcceptAllFileFilterUsed(false);
		dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		defaultNote = new JLabel();
		note = new JLabel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnAddFile) {
			int ret = fc.showOpenDialog(Window.this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				java.io.File textFile = fc.getSelectedFile();
				String ext = fileExtension(textFile.getName());
				if (!"txt".equals(ext) && !"lz77".equals(ext)) {
					JOptionPane.showMessageDialog(null, "Invalid file extension :" + ext);
					return;
				}
				if ("txt".equals(ext)) {
					Compress.setEnabled(true);
					deCompress.setEnabled(false);
				} else {
					Compress.setEnabled(false);
					deCompress.setEnabled(true);
				}
				textField.setText(textFile.getAbsolutePath());
				textField_1.setText(textFile.getParentFile().getAbsolutePath());
				defaultNote.setText(" the default directory is the current directory of the file,wanna change it ?!");
				note.setText("OK, " + ext + " file "
						+ (("txt".equals(ext)) ? "you can compress it " : "you cane decompress it ") + ":) ");
				note.setBounds(150, 150, 600, 100);
				defaultNote.setBounds(150, 130, 600, 100);
				frame.getContentPane().add(note);
				frame.getContentPane().add(defaultNote);
				frame.revalidate();
				frame.repaint();

			}
		} else if (e.getSource() == btnSelectDirectory) {
			int ret = dc.showOpenDialog(null);
			if (ret == JFileChooser.APPROVE_OPTION) {
				textField_1.setText(dc.getCurrentDirectory().getAbsolutePath());
			}
		}
	}

	

	private static String fileExtension(String filename) {
		int dotIndex = filename.lastIndexOf('.');
		return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
	}
}

