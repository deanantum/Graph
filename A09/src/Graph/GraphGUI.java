package Graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingConstants;

public class GraphGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static List<Point> points;
	private Dimension height;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphGUI frame = new GraphGUI();
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
	public GraphGUI() {
		
		points = new ArrayList<>();		
		
		try( Scanner reader = new Scanner( GraphGUI.class.getResourceAsStream("plots.csv")) ){
			
			while( reader.hasNextLine() ){
				String[] point = reader.nextLine().split(",");	
				
				if( point != null ){
					points.add( getPoint( point ) );
				}
			}
			
			for( Point p : points ){
				System.out.println(p);
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		height = getSize();
		
		JLabel lblTitle = new JLabel( "Graph" );
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		
	}
	
	public void paint( Graphics g ){
		g.setColor(Color.BLACK);
		
		int intHeight = height.height;
		int holdX = 0;
		int holdY = intHeight;
		
		for( Point p : points ){
			g.drawLine( holdX,holdY,p.getX(),intHeight - p.getY() );
			g.fillOval( p.getX()-4,intHeight - p.getY()-4,10,10 );			
			holdX = p.getX();
			holdY = intHeight - p.getY();
		}
		
	}

	private static Point getPoint( String[] point ) {
		
		int x = Integer.parseInt( point[0] );
		int y = Integer.parseInt( point[1] );
		
		Point temp = new Point( x,y );
		
		return temp;
	}

}
