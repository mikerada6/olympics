package org.rezatron.olympics.service;

import org.rezatron.olympics.domain.Team;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamService {
    List<Team> getAllTeams();

    Optional<Team> getTeamById(UUID id);

    Team createTeam(Team event);

    Team updateTeam(UUID id, Team event);

    void deleteTeam(UUID id);
}
