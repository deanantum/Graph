package labFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MountainApp {
	public static void main(String[] args) {
		
		List<Mountain> mountainList = new LinkedList<>();
		
		String line = null;
		
		try( Scanner reader = new Scanner( MountainApp.class.getResourceAsStream( "Mountains.csv" )) ){			

			while( reader.hasNextLine() ){
					
				line = reader.nextLine();
				
				Mountain newMountain = getMountain( line );
				
				if( newMountain != null ){
					mountainList.add( newMountain );
				}				
			}
			
		}catch( NumberFormatException | ArrayIndexOutOfBoundsException e ){				
			System.out.println( line +" .. could not be read in as a mountain.");
			System.out.println();
		}
		
		for( Mountain m : mountainList ){
			System.out.println(m);
		}
	}

	private static Mountain getMountain( String nextLine ){
		
		String[] el = nextLine.split(",");
		
		String name = el[0];
		int height = Integer.parseInt( el[1] );
		boolean glacier = Boolean.parseBoolean( el[2] );
		
		Mountain temp = new Mountain( name,height,glacier );
		
		return temp;
	}
}
