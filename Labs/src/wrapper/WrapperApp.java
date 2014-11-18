package wrapper;

public class WrapperApp {

	public static void main(String[] args) {

		System.out.printf( "%10s: %,-30d %,-30d\n", "Byte", Byte.MIN_VALUE, Byte.MAX_VALUE );
		System.out.printf( "%10s: %,-30d %,-30d\n", "Short", Short.MIN_VALUE, Short.MAX_VALUE );
		System.out.printf( "%10s: %,-30d %,-30d\n", "Integer", Integer.MIN_VALUE, Integer.MAX_VALUE );
		System.out.printf( "%10s: %,-30d %,-30d\n", "Long", Long.MIN_VALUE, Long.MAX_VALUE );
		
		Valuation( new Character('a') );
		Valuation( new Character('A') );
		Valuation( new Character('#') );
		Valuation( new Character('6') );
	}
	
	private static void Valuation( Character c ){
		System.out.print(c+": ");
		if( Character.isLetter(c)){
			System.out.print("letter ");
		}
		if( Character.isUpperCase(c)){
			System.out.print("uppercase ");
		}
		if( Character.isDigit(c)){
			System.out.print("number ");
		}		
		System.out.println();
	}

}
