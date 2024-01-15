package com.course.microservice.googlecloud.api.server;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.googlecloud.api.client.MockbinClient;
import com.course.microservice.googlecloud.api.response.ChainResponse;
import com.course.microservice.googlecloud.api.response.PlainMessage;

@RestController
@RequestMapping("/api/chain")
public class ChainApi {

	@Autowired
	private MockbinClient mockbinClient;

	private static final List<Integer> STATUS = List.of(HttpStatus.OK.value(), HttpStatus.BAD_REQUEST.value(),
			HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.ACCEPTED.value(), HttpStatus.UNAUTHORIZED.value(),
			HttpStatus.NOT_IMPLEMENTED.value());

	@GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChainResponse> chainStatus() {
		var status = STATUS.get(ThreadLocalRandom.current().nextInt(0, STATUS.size()));
		var reason = String.format("Reason generated at UTC %s", Instant.now());

		var mockbinResponse = mockbinClient.status(status, reason).getBody();

		var chainResponse = new ChainResponse();
		chainResponse.setMessage("A random string for chainResponse : " + RandomStringUtils.randomAlphanumeric(8));
		chainResponse.setMockbin(mockbinResponse);

		return ResponseEntity.status(status).body(chainResponse);
	}

	@GetMapping(value = "/delay/{millis}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChainResponse> chainDelay(@PathVariable(name = "millis") long millis) {
		var status = STATUS.get(ThreadLocalRandom.current().nextInt(0, STATUS.size()));
		var mockbinResponse = mockbinClient.delay(millis).getBody();

		var chainResponse = new ChainResponse();
		chainResponse.setMessage("A random string for chainResponse : " + RandomStringUtils.randomAlphanumeric(8));
		chainResponse.setMockbin(mockbinResponse);

		return ResponseEntity.status(status).body(chainResponse);
	}

	@GetMapping(value = "/multi/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> chainDelay(@PathVariable(name = "count") int count) {
		if (count < 0 || count > 10) {
			count = 10;
		}

		int totalMillis = 0;
		var str = new ArrayList<String>();
		var status = STATUS.get(ThreadLocalRandom.current().nextInt(0, STATUS.size()));

		for (int i = 0; i < count; i++) {
			var millis = ThreadLocalRandom.current().nextInt(50, 1000);
			mockbinClient.delay(millis).getBody();

			str.add(String.format("Loop %d, delay %d", i, millis));
			totalMillis += millis;
		}

		str.add(String.format("Total delay is %d millis", totalMillis));

		var response = new PlainMessage(String.join(" | ", str));
		return ResponseEntity.status(status).body(response);
	}

}
