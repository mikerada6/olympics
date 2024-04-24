package org.rezatron.olympics.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rezatron.olympics.domain.Event;
import org.rezatron.olympics.service.EventService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

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
        when(eventService.getAllEvents()).thenReturn(events);

        // Act
        List<Event> result = eventController.getAllEvents().getBody();

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
        when(eventService.getEventById(eventId)).thenReturn(Optional.of(event));

        // Act
        Event result = eventController.getEventById(eventId).getBody();

        // Assert
        assertEquals("Test Event", result.getName());
    }

    @Test
    void testCreateEvent() {
        // Arrange
        Event event = new Event();
        event.setName("New Event");
        when(eventService.createEvent(any())).thenReturn(event);

        // Act
        Event result = eventController.createEvent(event).getBody();

        // Assert
        assertEquals("New Event", result.getName());
    }

    @Test
    void testUpdateEvent() {
        // Arrange
        UUID eventId = UUID.randomUUID();
        Event updatedEvent = new Event();
        updatedEvent.setId(eventId);
        updatedEvent.setName("Updated Event");
        when(eventService.updateEvent(eq(eventId), any())).thenReturn(updatedEvent);

        // Act
        Event result = eventController.updateEvent(eventId, updatedEvent).getBody();

        // Assert
        assertEquals("Updated Event", result.getName());
    }

    @Test
    void testDeleteEvent() {
        // Arrange
        UUID eventId = UUID.randomUUID();

        // Act
        eventController.deleteEvent(eventId);

        // Assert
        verify(eventService, times(1)).deleteEvent(eventId);
    }
}