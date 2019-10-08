package uk.org.hexsaw.manning;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DomainEventControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String DOMAIN_TYPE = "Book";
    private static final String EVENT_TYPE = "BookUpdated";
    private static final String SOURCE = "Test Service";

    private static final DomainEvent DOMAIN_EVENT = new DomainEvent(UUID.randomUUID(), LocalDateTime.now(), DomainEventControllerTest.DOMAIN_TYPE,
            DomainEventControllerTest.EVENT_TYPE, DomainEventControllerTest.SOURCE );

    @Test
    public void thatWeGetAHttp200ForBasicRequestWithDomainEvent() {
        HttpEntity<DomainEvent> request = new HttpEntity<>(DomainEventControllerTest.DOMAIN_EVENT);
        ResponseEntity<DomainEventResponse> response = this.restTemplate.postForEntity("/api/domain-events", request, DomainEventResponse.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void thatWeGetAValidDomainEventResponse() {
        HttpEntity<DomainEvent> request = new HttpEntity<>(DomainEventControllerTest.DOMAIN_EVENT);
        ResponseEntity<DomainEventResponse> response = this.restTemplate.postForEntity("/api/domain-events", request, DomainEventResponse.class);
        DomainEventResponse domainEventResponse = response.getBody();
        assertThat(domainEventResponse.getUuid(), equalTo(DomainEventControllerTest.DOMAIN_EVENT.getUuid()));
        assertThat(domainEventResponse.getEventDateTime().isAfter(DomainEventControllerTest.DOMAIN_EVENT.getEventDateTime()), equalTo(true));
        assertThat(domainEventResponse.getDomainType(), equalTo(DomainEventControllerTest.DOMAIN_EVENT.getDomainType()));
        assertThat(domainEventResponse.getEventType(), equalTo(DomainEventControllerTest.DOMAIN_EVENT.getEventType()));
    }


}
