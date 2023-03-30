package com.tngtech.architecture.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.tngtech.architecture.api.db.TicketRepository;
import com.tngtech.architecture.api.model.Ticket;
import com.tngtech.architecture.api.model.TicketStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TicketApiTest {

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
		String response = restTemplate.getForObject("http://localhost:" + port + "/ticket",
				String.class);
		
		//then
		assertThat(response).isEqualTo("[]");
	}

	@Test
	public void gettingExistingTicketsShouldReturnTicketList() throws Exception {
		//given
		// ticketRepository.store(new Ticket("42", "Unclear answer", "Answer was given, but cannot be understood", TicketStatus.OPEN));
		Ticket storedTicket = new Ticket("42", "Unclear answer", "Answer was given, but cannot be understood", TicketStatus.OPEN);
		when(ticketRepository.getAllTickets()).thenReturn(Collections.singletonList(storedTicket));

		//when		
		String response = restTemplate.getForObject("http://localhost:" + port + "/ticket",
				String.class);
		
		//then
		assertThat(response).contains("42", "Unclear answer");
	}
}