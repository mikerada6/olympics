package org.rezatron.olympics.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rezatron.olympics.domain.Event;
import org.rezatron.olympics.repository.EventRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void testGetAllEvents() {
        // Arrange
        Event event1 = new Event();
        event1.setId(UUID.randomUUID());
        event1.setName("Event 1");

        Event event2 = new Event();
        event2.setId(UUID.randomUUID());
        event2.setName("Event 2");

        List<Event> events = Arrays.asList(event1, event2);
        when(eventRepository.findAll()).thenReturn(events);

        // Act
        List<Event> result = eventService.getAllEvents();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Event 1", result.get(0).getName());
        assertEquals("Event 2", result.get(1).getName());
    }

    @Test
    void testGetEventById() {
        // Arrange
        UUID eventId = UUID.randomUUID();
        Event event = new Event();
        event.setId(eventId);
        event.setName("Test Event");
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        // Act
        Optional<Event> result = eventService.getEventById(eventId);

        // Assert
        assertEquals("Test Event", result.get().getName());
    }

    @Test
    void testCreateEvent() {
        // Arrange
        Event event = new Event();
        event.setName("New Event");
        when(eventRepository.save(any())).thenReturn(event);

        // Act
        Event result = eventService.createEvent(event);

        // Assert
        assertEquals("New Event", result.getName());
    }

    @Test
    void testUpdateEvent() {
        // Arrange
        UUID eventId = UUID.randomUUID();
        Event existingEvent = new Event();
        existingEvent.setId(eventId);
        existingEvent.setName("Existing Event");

        Event updatedEvent = new Event();
        updatedEvent.setId(eventId);
        updatedEvent.setName("Updated Event");

        when(eventRepository.save(any())).thenReturn(updatedEvent);

        // Act
        Event result = eventService.updateEvent(eventId, updatedEvent);

        // Assert
        assertEquals("Updated Event", result.getName());
    }

    @Test
    void testDeleteEvent() {
        // Arrange
        UUID eventId = UUID.randomUUID();

        // Act
        eventService.deleteEvent(eventId);

        // Assert
        verify(eventRepository, times(1)).deleteById(eventId);
    }
}