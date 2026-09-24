package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.ScoreFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {
	
	@InjectMocks
	private ScoreService service;

	@Mock
	private ScoreRepository scoreRepository;

	@Mock
	private UserService userService;

	@Mock
	private MovieRepository movieRepository;

	private Long existingMovieId;
	private Long nonExistingMovieId;
	private UserEntity user;
	private ScoreEntity score;
	private MovieEntity movie;
	private ScoreDTO scoreDTO;
	private ScoreDTO nonExistingScoreDTO;

	@BeforeEach
	void setUp() {
		existingMovieId = 1L;
		nonExistingMovieId = 2L;

		score = ScoreFactory.createScoreEntity();
		movie = score.getId().getMovie();
		user = UserFactory.createUserEntity();
		scoreDTO = ScoreFactory.createScoreDTO();
		nonExistingScoreDTO = new ScoreDTO(nonExistingMovieId, ScoreFactory.scoreValue);

		Mockito.when(userService.authenticated()).thenReturn(user);
		Mockito.when(movieRepository.findById(existingMovieId)).thenReturn(Optional.of(movie));
		Mockito.when(movieRepository.findById(nonExistingMovieId)).thenReturn(Optional.empty());
		Mockito.when(scoreRepository.saveAndFlush(any())).thenReturn(score);
		Mockito.when(movieRepository.save(any())).thenReturn(movie);
	}

	@Test
	public void saveScoreShouldReturnMovieDTO() {

		MovieDTO result = service.saveScore(scoreDTO);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(existingMovieId, result.getId());
		Assertions.assertEquals(ScoreFactory.scoreValue, result.getScore());
		Assertions.assertEquals(1, result.getCount());
	}
	
	@Test
	public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.saveScore(nonExistingScoreDTO);
		});
	}
}
