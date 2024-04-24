package org.rezatron.olympics.service;

import org.rezatron.olympics.domain.Team;
import org.rezatron.olympics.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamServiceImpl  implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> getTeamById(UUID id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(UUID id, Team team) {
        team.setId(id); // Ensure the ID matches the path variable
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(UUID id) {
        teamRepository.deleteById(id);
    }
}
