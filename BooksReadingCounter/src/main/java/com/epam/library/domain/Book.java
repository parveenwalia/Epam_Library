package com.epam.library.domain;

public class Book {
	private int id;
    private String title;
    private String author;
    private String brief;
    private int publishYear;

    public Book() {

    }

    public Book(int id, String title, String author, String brief, int publishYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.brief = brief;
        this.publishYear = publishYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + id;
		result = prime * result + publishYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Book other = (Book) obj;
		
		if (author == null) {
			if (other.author != null)
				return false;
		} 
		else if (!author.equals(other.author))
			return false;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} 
		else if (!brief.equals(other.brief))
			return false;
		
		if (id != other.id)
			return false;
		
		if (publishYear != other.publishYear)
			return false;
		
		if (title == null) {
			if (other.title != null)
				return false;
		} 
		else if (!title.equals(other.title))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", brief=" + brief + ", publishYear="
				+ publishYear + "]";
	}
	
    
}
