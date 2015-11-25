/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

/**
 *
 * @author Tram Anh
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class GUI_SearchShowtime extends JFrame {

    private JPanel contentPane;
    JTextArea textArea;
    JScrollPane jscrollPane;

    /**
     * Launch the application.
     */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI_SearchShowtime frame = new GUI_SearchShowtime();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
    /**
     * Create the frame.
     */
    public GUI_SearchShowtime(ArrayList<Movie> movies, ArrayList<Cineplex> cineplexes) {
        final ArrayList<Movie> movieUI = movies;
        final ArrayList<Cineplex> cineplexUI = cineplexes;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 760, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textArea = new JTextArea();
        textArea.setBounds(31, 128, 573, 261);
        //contentPane.add(textArea);
        
        jscrollPane = new JScrollPane(textArea);
        jscrollPane.setBounds(31, 128, 573, 261);
        contentPane.add(jscrollPane);

        final JComboBox comboBox = new JComboBox();
        comboBox.setBounds(10, 52, 382, 23);
        listMoviesUI(movies, comboBox);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
//                System.out.println("Selected: " + comboBox.getSelectedItem());
//                System.out.println(", Position: " + comboBox.getSelectedIndex());
                textArea.setText(null);
                int selected = comboBox.getSelectedIndex();
                String movieName = movieUI.get(selected).getName();
                redirectSystemStreams();
                listMovieShowtime(movieName, cineplexUI);
                
                
            }
        };
        comboBox.addActionListener(actionListener);

        contentPane.add(comboBox);



//        JButton btnSearch = new JButton("Search");
//        btnSearch.setBounds(443, 51, 89, 23);
//        contentPane.add(btnSearch);

        JLabel lblSearchMovieShowtime = new JLabel("Search Movie Showtime");
        lblSearchMovieShowtime.setBounds(31, 27, 162, 14);
        contentPane.add(lblSearchMovieShowtime);

        
    }

    //this function is copied from CreateUpdateMovie
    public void listMoviesUI(ArrayList<Movie> movies, JComboBox comboBox) {
//        System.out.println("List of movies: ");
        String comboBoxList;
        if (!movies.isEmpty()) {
            for (int i = 0; i < movies.size(); i++) {
                if (!movies.get(i).getStatus().equalsIgnoreCase("End Of Showing")) {
//                    System.out.println(i + ". " + movies.get(i).getName() + " (" + movies.get(i).getType() + ") - " + movies.get(i).getStatus());
                    comboBoxList = (i+1) + ". " + movies.get(i).getName() + " (" + movies.get(i).getType() + ") - " + movies.get(i).getStatus();
                    comboBox.addItem(comboBoxList);
                }
            }
        } else {
            comboBox.addItem("There is no movie in the database");
        }
    }

    //This function is copied from CreateUpdateMovie
   public static boolean listMovieShowtime(String movieName, ArrayList<Cineplex> cineplexes) {
        //return boolean so that we will know if there is any for this showtime at all
        boolean showtimeFlag = false;
        boolean cineplexFlag = false;
        for (int i = 0; i < 3; i++) {
            System.out.println("Cineplex " + (i + 1)+". "+ cineplexes.get(i).getCineplexName());
            for (int j = 0; j < cineplexes.get(i).cinemas.size(); j++) {
                if (cineplexes.get(i).cinemas.get(j).showOccupiedSlot(movieName)) {
                    System.out.println("    Cinema " + (j + 1) + " (" + cineplexes.get(i).cinemas.get(j).getCinemaType() + ")");
                    showtimeFlag = true;
                    cineplexFlag = true;
                }
            }
            if(cineplexFlag == false)
                System.out.println("No showtime for this cineplex.");
            else
                cineplexFlag = false;
            System.out.println("");
        }
        return showtimeFlag;
    }

    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.append(text);
            }
        });
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }
}
