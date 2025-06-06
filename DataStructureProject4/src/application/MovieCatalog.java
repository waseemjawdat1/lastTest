package application;

public class MovieCatalog {

	public MovieCatalog() {
		
	}
	public static boolean searchByTitle (String title) {
		   Movie temp = new Movie();
	       temp.setTitle(title);
	      if ( Main.hash.get(temp) == null ) return false;
	      else return true;
	}
	public static Movie getByTitle (String title) {
		   Movie temp = new Movie();
	       temp.setTitle(title);
	       return Main.hash.get(temp);  
	}
	
	public static void earseByTitle (String title) {
		Movie temp = new Movie();
	    temp.setTitle(title);
	    Main.hash.delete(temp); 
	}
}
