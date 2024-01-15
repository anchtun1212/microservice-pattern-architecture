package com.course.microservice.googlecloud.api.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.course.microservice.googlecloud.api.response.PlainMessage;
import com.google.cloud.Identity;
import com.google.cloud.Policy;
import com.google.cloud.spring.pubsub.PubSubAdmin;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageClass;
import com.google.cloud.storage.StorageRoles;

@RestController
@RequestMapping(value = "/api")
public class GoogleCloudApi {

	private static final Logger LOG = LoggerFactory.getLogger(GoogleCloudApi.class);

	@Autowired
	private Storage storage;

	@Autowired
	private PubSubAdmin pubSubAdmin;

	@Autowired
	private PubSubTemplate pubSubTemplate;

	@PostMapping(value = "/log/{level}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> logging(@PathVariable(name = "level") String level) {
		var logString = String.format("This is a log sample for level %s, random string : %s", level,
				RandomStringUtils.randomAlphanumeric(8));

		switch (level.toLowerCase()) {
		case "trace":
			LOG.trace(logString);
			break;
		case "debug":
			LOG.debug(logString);
			break;
		case "info":
			LOG.info(logString);
			break;
		case "warn":
			LOG.warn(logString);
			break;
		case "error":
			LOG.error(logString);
			break;
		default:
			logString = String.format(
					"I don't know what is log level '%s', please use one of : trace / debug / info / warn / error.",
					level);
			break;
		}

		var responseBody = new PlainMessage(logString);
		return ResponseEntity.ok().body(responseBody);
	}

	@PostMapping(value = "/pubsub/{topicName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> pubsubCreateTopic(@PathVariable(name = "topicName") String topicName) {
		var createdTopic = pubSubAdmin.createTopic(topicName);
		var responseBody = new PlainMessage("Created pub/sub topic : " + createdTopic.getName());

		return ResponseEntity.ok().body(responseBody);
	}

	@GetMapping(value = "/pubsub/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> pubsubListTopics() {
		var topicNames = new ArrayList<String>();

		pubSubAdmin.listTopics().forEach(t -> topicNames.add(t.getName()));

		return ResponseEntity.ok().body(topicNames);
	}

	@PostMapping(value = "/pubsub/{topicName}/publish", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<PlainMessage> pubsubPublishToTopic(@PathVariable(name = "topicName") String topicName,
			@RequestBody String message) throws InterruptedException, ExecutionException {
		pubSubTemplate.publish(topicName, message).get();
		var str = String.format("Message published to : %s, with value : %s", topicName, message);
		var responseBody = new PlainMessage(str);

		return ResponseEntity.ok().body(responseBody);
	}

	@PostMapping(value = "/pubsub/{topicName}/subscription/{subscriptionName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> pubsubSubscriptionFromTopic(@PathVariable(name = "topicName") String topicName,
			@PathVariable(name = "subscriptionName") String subscriptionName) {
		var subscribtion = pubSubAdmin.createSubscription(subscriptionName, topicName);

		var responseBody = new PlainMessage("Created subscription : " + subscribtion.getName());
		return ResponseEntity.ok().body(responseBody);
	}

	@PostMapping(value = "/pubsub/subscribe/{subscriptionName}/pull", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> pubsubPullFromTopic(
			@PathVariable(name = "subscriptionName") String subscriptionName,
			@RequestParam(defaultValue = "10", name = "count") int messageCount) {
		var pulledMessagesContent = new ArrayList<String>();
		var pulledMessages = pubSubTemplate.pull(subscriptionName, messageCount, false);

		pulledMessages.forEach(m -> pulledMessagesContent.add(m.getPubsubMessage().getData().toStringUtf8()));
		pubSubTemplate.ack(pulledMessages);

		return ResponseEntity.ok().body(pulledMessagesContent);
	}

	@PostMapping(value = "/storage/{bucketName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> storageUploadFile(@PathVariable(name = "bucketName") String bucketName,
			@RequestParam("file") MultipartFile file) throws IOException {
		var existingBucket = storage.get(bucketName);

		if (existingBucket == null || !existingBucket.exists()) {
			var bucketInfo = BucketInfo.newBuilder(bucketName).setStorageClass(StorageClass.STANDARD)
					.setLocation("asia-southeast2").setVersioningEnabled(false).build();
			existingBucket = storage.create(bucketInfo);

			// public read
			// TODO set to viewer only
			storage.setIamPolicy(bucketName,
					Policy.newBuilder().addIdentity(StorageRoles.objectAdmin(), Identity.allUsers()).build());
		}

		var blobInfo = BlobInfo.newBuilder(existingBucket, file.getOriginalFilename())
				.setContentType(file.getContentType()).build();
		var createdBlob = storage.create(blobInfo, file.getBytes());

		var responseBody = new PlainMessage(
				"File " + createdBlob.getName() + " available on " + createdBlob.getMediaLink());

		return ResponseEntity.ok().body(responseBody);
	}

	@GetMapping(value = "/storage/{bucketName}/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> storageListFiles(@PathVariable(name = "bucketName") String bucketName)
			throws IOException {
		var blobNames = new ArrayList<String>();
		storage.list(bucketName).getValues().forEach(blob -> blobNames.add(blob.getName()));

		return ResponseEntity.ok().body(blobNames);
	}

}
