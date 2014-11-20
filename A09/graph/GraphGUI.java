package graph;

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
import javax.swing.border.EtchedBorder;

public class GraphGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Point> points;
	private List<Point> newPoints;
	private Dimension height;
	//private JPanel drawPanel;
	private JLabel lblTitle;
	private String fileName;

	//Comment from David to test GIT stuff
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		readFile();
		
		newTitle();
		contentPane.add( lblTitle, BorderLayout.NORTH );
		
		//GraphBox graph = new GraphBox();
		JPanel graphPanel = new GraphBox();
		graphPanel.setBorder(new EtchedBorder( EtchedBorder.LOWERED, null, null ));
		contentPane.add(graphPanel, BorderLayout.CENTER);		
		
		//drawPanel = new JPanel();
		//drawPanel.setBorder(new EtchedBorder( EtchedBorder.LOWERED, null, null ));
		//drawPanel.add( graphPanel );
		//contentPane.add( drawPanel, BorderLayout.CENTER );
	}

	private void readFile() {
		points = new ArrayList<>();		
		height = getSize();
		
		try( Scanner reader = new Scanner( GraphGUI.class.getResourceAsStream("plots.csv")) ){
			while( reader.hasNextLine() ){
				String[] point = reader.nextLine().split(",");	
				
				if( point != null ){
					points.add( getPoint( point ) );
				}
			}
			
			/*for( Point p : points ){
				System.out.println(p);
			}*/
		}
	}

	private void newTitle() {
		lblTitle = new JLabel( "Graph" );
		lblTitle.setHorizontalAlignment( SwingConstants.CENTER );
		lblTitle.setFont(new Font( "Tahoma", Font.PLAIN, 15 ));
	}

	private static Point getPoint( String[] point ) {
		
		int x = Integer.parseInt( point[0] );
		int y = Integer.parseInt( point[1] );
		
		Point temp = new Point( x,y );
		
		return temp;
	}
	
	class GraphBox extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		GraphBox(){
			setPreferredSize( new Dimension( 200,200 ) );
		}
		
		@Override
		public void paintComponent( Graphics g ){
			
			super.paintComponent(g);
			
			g.setColor(Color.BLACK);
			
			if( toScale() ){			
				newDraw( newPoints,g );	
			}else{
				newDraw( points,g );				
			}
		}

		private void newDraw( List<Point> drawPoints, Graphics g ) {
			//g.drawLine( 0,120,640,120 );
			int intHeight = height.height - 80;
			int holdX = 0;
			int holdY = intHeight;
			//System.out.println("Height: "+ intHeight );
			for( Point p : drawPoints ){
				g.drawLine( holdX,holdY,p.getX(),intHeight - p.getY() - 80 );
				g.fillOval( p.getX()-4,intHeight - p.getY() - 4 - 80,10,10 );			
				holdX = p.getX();
				holdY = intHeight - p.getY() - 80;
			}
		}
		
		private boolean toScale(){
			
			boolean over = false;
			int maxY = 0;
			
			//Finds the highest Y value and see if it goes over 200
			for( Point p : points ){
				if( p.getY() > 200 ){
					//System.out.println("Y: "+ p.getY());
					maxY = p.getY();
					over = true;
				}
			}

			//If the Y value is over 200, it will go over the alloted space, scale down
			if( over ){
				newPoints = new ArrayList<>(points);
				
				float denominator = 200;
				float newScale = maxY / denominator;
				//System.out.printf("Scale - maxY: %d newScale: %.01f%n", maxY, newScale);
				
				if( newScale > 2.0 ){
					while( newScale > 2.0 ){
						newScale = maxY/denominator;
						//ystem.out.println ("newScale: "+ newScale );
						if(newScale > 2.0 ){
							denominator += 5;
						}
					}
				}

				//Enter the new scaled down points
				for( Point p : newPoints ){							
					//System.out.println("2nd: MaxY="+ maxY +" Y: "+ p.getY());
					float put = p.getY() / newScale;
					p.setY( (int) put );
					//System.out.println("new Y: "+ p.getY());
				}
			}
			
			/*for( Point p : newPoints ){							
				System.out.printf("X: %d Y: %d%n", p.getX(), p.getY());
			}*/
			
			return over;
		}
		
	}

}
