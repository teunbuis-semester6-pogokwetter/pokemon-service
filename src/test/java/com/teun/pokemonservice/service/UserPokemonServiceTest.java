package com.teun.pokemonservice.service;

import com.teun.pokemonservice.dto.UserPokemonDTO;
import com.teun.pokemonservice.models.UserPokemon;
import com.teun.pokemonservice.repo.UserPokemonRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserPokemonServiceTest {
    @Mock
    private UserPokemonRepo repo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache cache;

    @InjectMocks
    private UserPokemonService userPokemonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUserPokemonToDatabase() {
        // Arrange
        UserPokemonDTO userPokemonDTO = new UserPokemonDTO();
        UserPokemon toSave = new UserPokemon();
        UserPokemon savedUserPokemon = new UserPokemon();
        UserPokemonDTO expectedDto = new UserPokemonDTO();

        when(modelMapper.map(userPokemonDTO, UserPokemon.class)).thenReturn(toSave);
        when(repo.save(toSave)).thenReturn(savedUserPokemon);
        when(modelMapper.map(savedUserPokemon, UserPokemonDTO.class)).thenReturn(expectedDto);

        // Act
        UserPokemonDTO result = userPokemonService.saveUserPokemon(userPokemonDTO, 123);

        // Assert
        verify(modelMapper, times(1)).map(userPokemonDTO, UserPokemon.class);
        verify(repo, times(1)).save(toSave);
        verify(modelMapper, times(1)).map(savedUserPokemon, UserPokemonDTO.class);
        assertEquals(expectedDto, result);
    }

    @Test
    void testFindAllFromDataBase() {
        // Arrange
        List<UserPokemon> mockUserPokemonList = new ArrayList<>();
        mockUserPokemonList.add(new UserPokemon());
        when(repo.findAll()).thenReturn(mockUserPokemonList);

        List<UserPokemonDTO> expectedDtoList = new ArrayList<>();
        expectedDtoList.add(new UserPokemonDTO());
        when(modelMapper.map(mockUserPokemonList, List.class)).thenReturn(expectedDtoList);

        // Act
        List<UserPokemonDTO> result = userPokemonService.findAll();

        // Assert
        verify(repo, times(1)).findAll();
        verify(modelMapper, times(1)).map(mockUserPokemonList, List.class);
        assertEquals(expectedDtoList, result);
    }

    @Test
    void testFindAllByUserIdFromDatabase() {
        // Arrange
        long userId = 123;
        List<UserPokemon> mockUserPokemonList = new ArrayList<>();
        mockUserPokemonList.add(new UserPokemon());
        when(repo.findByUserId(userId)).thenReturn(mockUserPokemonList);

        List<UserPokemonDTO> expectedDtoList = new ArrayList<>();
        expectedDtoList.add(new UserPokemonDTO());
        when(modelMapper.map(mockUserPokemonList, List.class)).thenReturn(expectedDtoList);

        // Act
        List<UserPokemonDTO> result = userPokemonService.findAllByUserId(userId);

        // Assert
        verify(repo, times(1)).findByUserId(userId);
        verify(modelMapper, times(1)).map(mockUserPokemonList, List.class);
        assertEquals(expectedDtoList, result);
    }

    @Test
    void testUpdateCache() {
        // Arrange
        String cacheKey = "userId123";
        when(cacheManager.getCache("userpokemoncache")).thenReturn(cache);

        // Act
        userPokemonService.updateCache(cacheKey);

        // Assert
        verify(cacheManager, times(1)).getCache("userpokemoncache");
        verify(cache, times(1)).clear();
        verify(cache, times(1)).evict(cacheKey);
        // Verify the logger output if necessary
    }
}