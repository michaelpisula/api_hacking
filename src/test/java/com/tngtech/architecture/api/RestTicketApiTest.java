package com.tngtech.architecture.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.tngtech.architecture.api.db.TicketRepository;
import com.tngtech.architecture.api.model.Ticket;
import com.tngtech.architecture.api.model.TicketStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTicketApiTest {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private TicketRepository ticketRepository;

	@Test
	public void gettingTicketsShouldReturnEmptyList() throws Exception {
		//given

		//when		
		String response = restTemplate.getForObject("http://localhost:" + port + "/tickets",
				String.class);
		
		//then
		assertThat(response).isEqualTo("[]");
	}

	@Test
	public void gettingExistingTicketsShouldReturnTicketList() throws Exception {
		//given
		Ticket storedTicket = new Ticket("42", "Unclear answer", "Answer was given, but cannot be understood", TicketStatus.OPEN);
		when(ticketRepository.getAllTickets()).thenReturn(Collections.singletonList(storedTicket));

		//when		
		String response = restTemplate.getForObject("http://localhost:" + port + "/tickets",
				String.class);
		
		//then
		assertThat(response).contains("42", "Unclear answer");
	}

	
	@Test
	public void gettingExistingTicketsShouldReturnSingleTicket() throws Exception {
		//given
		String ticketId = "42";
		Ticket storedTicket = new Ticket(ticketId, "Unclear answer", "Answer was given, but cannot be understood", TicketStatus.OPEN);
		when(ticketRepository.getTicket(ticketId)).thenReturn(storedTicket);

		//when		
		String response = restTemplate.getForObject("http://localhost:" + port + "/tickets/" + ticketId,
				String.class);
		
		//then
		assertThat(response).contains(ticketId, "Unclear answer");
	}

		
	@Test
	public void addTicket() throws Exception {
		//given
		Ticket storedTicket = new Ticket("", "Unclear answer", "Answer was given, but cannot be understood", TicketStatus.OPEN);
		HttpEntity<Ticket> request = new HttpEntity<>(storedTicket);

		//when		
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/tickets", request, String.class);

		
		//then
		assertThat(response.getBody()).contains("Unclear answer");
		verify(ticketRepository, times(1)).store(any(Ticket.class));
	}
}