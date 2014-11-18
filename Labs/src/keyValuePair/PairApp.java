package keyValuePair;

public class PairApp {

	public static void main(String[] args) {
		
		KeyValuePair<String,Integer> p1 = new KeyValuePair<>( "SLC",189899 );
		KeyValuePair<String,Integer> p2 = new KeyValuePair<>( "NY",8244910 );
		
		System.out.println( "p1: "+ p1 );
		System.out.println( "p2: "+ p2 );
		
		System.out.println("p1.equals(p2):");
		System.out.println(p1.equals( p2 ) ? "true" : "false" );

		System.out.println();
		
		p1 = p2;
		
		System.out.println( "p1: "+ p1 );
		System.out.println( "p2: "+ p2 );
		
		System.out.println("p1.equals(p2):");
		System.out.println(p1.equals( p2 ) ? "true" : "false" );

		System.out.println();
	}

}
