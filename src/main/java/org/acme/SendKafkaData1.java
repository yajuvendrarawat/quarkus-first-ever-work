package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class SendKafkaData1 {
    private final Logger logger1 = Logger.getLogger(ReceveKafkaSendKafka.class);
    @Inject @Channel("dmn-event-driven-requests-out")
    Emitter<Record<String, String>> emitter;

    public void sendDataToKafka1(String jsonString) {
        logger1.infof("Got a logger2: %s - %s", "key", jsonString);
        emitter.send(Record.of("key", jsonString));
    }
}
