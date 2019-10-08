package uk.org.hexsaw.manning;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/api/domain-events")
public class DomainEventController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DomainEventResponse> handleDomainEvent(@RequestBody DomainEvent domainEvent) {
        return new ResponseEntity<>(new DomainEventResponse(domainEvent.getUuid(), LocalDateTime.now(),
                domainEvent.getDomainType(), domainEvent.getEventType()), HttpStatus.OK);
    }
}
