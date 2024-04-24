package org.rezatron.olympics.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rezatron.olympics.domain.Team;
import org.rezatron.olympics.service.TeamService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

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
        when(teamService.getAllTeams()).thenReturn(teams);

        // Act
        List<Team> result = teamController.getAllTeams().getBody();

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
        when(teamService.getTeamById(teamId)).thenReturn(Optional.of(team));

        // Act
        Team result = teamController.getTeamById(teamId).getBody();

        // Assert
        assertEquals("Test Team", result.getName());
    }

    @Test
    void testCreateTeam() {
        // Arrange
        Team team = new Team();
        team.setName("New Team");
        when(teamService.createTeam(any())).thenReturn(team);

        // Act
        Team result = teamController.createTeam(team).getBody();

        // Assert
        assertEquals("New Team", result.getName());
    }

    @Test
    void testUpdateTeam() {
        // Arrange
        UUID teamId = UUID.randomUUID();
        Team updatedTeam = new Team();
        updatedTeam.setId(teamId);
        updatedTeam.setName("Updated Team");
        when(teamService.updateTeam(eq(teamId), any())).thenReturn(updatedTeam);

        // Act
        Team result = teamController.updateTeam(teamId, updatedTeam).getBody();

        // Assert
        assertEquals("Updated Team", result.getName());
    }

    @Test
    void testDeleteTeam() {
        // Arrange
        UUID teamId = UUID.randomUUID();

        // Act
        teamController.deleteTeam(teamId);

        // Assert
        verify(teamService, times(1)).deleteTeam(teamId);
    }
}