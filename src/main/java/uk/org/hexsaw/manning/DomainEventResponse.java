package uk.org.hexsaw.manning;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class DomainEventResponse {

    private UUID uuid;
    private LocalDateTime eventDateTime;
    private String domainType;
    private String eventType;

    // Default no-args constructor is important for JSON serialisation to work
    public DomainEventResponse() {
    }

    public DomainEventResponse(UUID uuid, LocalDateTime eventDateTime, String domainType, String eventType) {
        this.uuid = uuid;
        this.eventDateTime = eventDateTime;
        this.domainType = domainType;
        this.eventType = eventType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEventResponse that = (DomainEventResponse) o;
        return this.getUuid().equals(that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
