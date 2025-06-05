package application;

import java.util.Objects;

public class Movie implements Comparable <Movie> {
	private String title;
	private String description;
	private int releaseYear;
	private double rate;
	
	public Movie () {
		
	}
	public Movie (String title , String description , int releaseYear, double rate ) {
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rate = rate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(title.toLowerCase());
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie o = (Movie) obj;
		return Objects.equals(title.toLowerCase(), o.title.toLowerCase());
	}
	@Override
	public int compareTo(Movie o) {
		 return this.title.compareToIgnoreCase(o.title);
	}
	
	
}
