package org.rezatron.olympics.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rezatron.olympics.domain.Team;
import org.rezatron.olympics.repository.TeamRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    void testGetAllTeams() {
        // Arrange
        Team team1 = new Team();
        team1.setId(UUID.randomUUID());
        team1.setName("Team 1");

        Team team2 = new Team();
        team2.setId(UUID.randomUUID());
        team2.setName("Team 2");

        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.findAll()).thenReturn(teams);

        // Act
        List<Team> result = teamService.getAllTeams();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Team 1", result.get(0).getName());
        assertEquals("Team 2", result.get(1).getName());
    }

    @Test
    void testGetTeamById() {
        // Arrange
        UUID teamId = UUID.randomUUID();
        Team team = new Team();
        team.setId(teamId);
        team.setName("Test Team");
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        // Act
        Optional<Team> result = teamService.getTeamById(teamId);

        // Assert
        assertEquals("Test Team", result.get().getName());
    }

    @Test
    void testCreateTeam() {
        // Arrange
        Team team = new Team();
        team.setName("New Team");
        when(teamRepository.save(any())).thenReturn(team);

        // Act
        Team result = teamService.createTeam(team);

        // Assert
        assertEquals("New Team", result.getName());
    }

    @Test
    void testUpdateTeam() {
        // Arrange
        UUID teamId = UUID.randomUUID();
        Team existingTeam = new Team();
        existingTeam.setId(teamId);
        existingTeam.setName("Existing Team");

        Team updatedTeam = new Team();
        updatedTeam.setId(teamId);
        updatedTeam.setName("Updated Team");

        when(teamRepository.save(any())).thenReturn(updatedTeam);

        // Act
        Team result = teamService.updateTeam(teamId, updatedTeam);

        // Assert
        assertEquals("Updated Team", result.getName());
    }

    @Test
    void testDeleteTeam() {
        // Arrange
        UUID teamId = UUID.randomUUID();

        // Act
        teamService.deleteTeam(teamId);

        // Assert
        verify(teamRepository, times(1)).deleteById(teamId);
    }
}