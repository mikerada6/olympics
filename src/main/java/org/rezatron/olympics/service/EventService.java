package org.rezatron.olympics.service;

import org.rezatron.olympics.domain.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    List<Event> getAllEvents();
    Optional<Event> getEventById(UUID id);
    Event createEvent(Event event);
    Event updateEvent(UUID id, Event event);
    void deleteEvent(UUID id);
}