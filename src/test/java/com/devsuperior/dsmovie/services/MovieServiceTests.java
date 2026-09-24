package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.tests.MovieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {
	
	@InjectMocks
	private MovieService service;

	@Mock
	private MovieRepository repository;

	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private MovieEntity movie;
	private MovieDTO movieDTO;
	private PageImpl page;

	@BeforeEach
	void setUp() {
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;

		movie = MovieFactory.createMovieEntity();
		movieDTO = MovieFactory.createMovieDTO();
		page = new PageImpl<>(List.of(movie));

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(movie));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		Mockito.when(repository.searchByTitle(anyString(), any(Pageable.class))).thenReturn(page);
	}
	
	@Test
	public void findAllShouldReturnPagedMovieDTO() {

		Pageable pageable = PageRequest.of(0 ,12);
		String title = "MovieTest";

		Page<MovieDTO> movies = service.findAll(title, pageable);

		Assertions.assertNotNull(movies);
	}
	
	@Test
	public void findByIdShouldReturnMovieDTOWhenIdExists() {
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void insertShouldReturnMovieDTO() {
	}
	
	@Test
	public void updateShouldReturnMovieDTOWhenIdExists() {
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
	}
}
