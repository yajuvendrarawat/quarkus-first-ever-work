package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class SendKafkaData {
    private final Logger logger1 = Logger.getLogger(ReceveKafkaSendKafka.class);
    @Inject @Channel("csvData-out")
    Emitter<Record<String, String>> emitter;

    public void sendDataToKafka(CsvData csvdata) {
        logger1.infof("Got a logger1: %s - %s", csvdata.name, csvdata.string);
        emitter.send(Record.of(csvdata.name, csvdata.string));
    }
}
