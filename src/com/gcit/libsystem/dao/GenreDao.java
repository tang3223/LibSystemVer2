package com.gcit.libsystem.dao;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.Genre;

public class GenreDao extends BaseDao {
	
	public GenreDao(Connection conn) {
		super(conn);
	}
	
	public void addGenre(Genre genre) throws SQLException {
		String  addGenre  = "INSERT INTO tbl_genre (genre_name) VALUE (?)";
		List<?> genreInfo = Arrays.asList(genre.getGenreName());
		save(addGenre, genreInfo);
	}
	
	public void updateGenre(Genre genre) throws SQLException{
		String  updateGenre = "UPDATE tbl_genre SET genre_name=? WHERE genre_id=?";
		List<?> genreInfo   = Arrays.asList(genre.getGenreName(), genre.getGenreId());
		save(updateGenre, genreInfo);
	}
	
	public void deleteGenre(Genre genre) throws SQLException{
		String  deleteGenre = "DELETE FROM tbl_genre WHERE genre_id=?";
		List<?> genreInfo   = Arrays.asList(genre.getGenreId());
		save(deleteGenre, genreInfo);
	}
	
	public List<Genre> readAllGenre() throws SQLException{
		String readGenre = "SELECT * FROM tbl_genre";
		return read(readGenre, null);
	}
	
	public Genre readGenre(Integer genreID) throws SQLException{
		String  readGenre  = "SELECT * FROM tbl_genre WHERE genre_id=?";
		List<?> genreInfo  = Arrays.asList(genreID);
		List<Genre> genres = read(readGenre, genreInfo);
		if(genres!=null && !genres.isEmpty()){
			return genres.get(0);
		}
		return null;
	}
	
	public List<Genre> readGenre(String genreName) throws SQLException{
		String  readGenre = "SELECT * FROM tbl_genre WHERE genre_name LIKE ?";
		List<?> genreInfo = Arrays.asList(genreName);
		return read(readGenre,genreInfo);
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		BookDao bookDao = new BookDao(conn);
		List<Genre> genres = new ArrayList<>();
		String readBook = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_genres WHERE genre_id=?)";
		while(rs.next()){
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			List<?> genreInfo = Arrays.asList(genre.getGenreId());
			genre.setBooks(bookDao.readOnly(readBook,genreInfo));
			genres.add(genre);
		}
		return genres;
	}

	@Override
	public List<Genre> extractDataReadOnly(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}
	
	@Override
	protected <T> T extractSingleData(ResultSet rs) throws SQLException {
		return null;
	}
	
}
