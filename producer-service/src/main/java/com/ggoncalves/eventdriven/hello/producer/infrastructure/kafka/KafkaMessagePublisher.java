package com.ggoncalves.eventdriven.hello.producer.infrastructure.kafka;

import com.ggoncalves.eventdriven.hello.producer.infrastructure.MessagePublisher;
import com.ggoncalves.eventdriven.hello.shared.domain.HelloWorldEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "messaging.provider", havingValue = "kafka")
public class KafkaMessagePublisher implements MessagePublisher {

  private final KafkaTemplate<String, HelloWorldEvent> kafkaTemplate;

  @Override
  public void publishHelloMessage(HelloWorldEvent message) {
    log.info("Publishing message: {}", message);
    kafkaTemplate.send(KafkaConfig.HELLO_TOPIC, message.getId(), message);
  }
}