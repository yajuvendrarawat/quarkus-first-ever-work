package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
public class ReceveKafkaSendKafka {

    private final Logger logger = Logger.getLogger(ReceveKafkaSendKafka.class);
    @Inject
    SendKafkaData1 producer1;
    
    @Incoming("csvData-in")
    public void receive(Record<String, String> record) {
        logger.infof("Got the Data: %s - %s", record.key(), record.value());
        String elements = record.value();
       // logger.infof("List %s",elements);
        //csvToJson(elements);
        logger.infof("Json %s",csvToSingtelDMFormatJson(elements));
        producer1.sendDataToKafka1(csvToSingtelDMFormatJson(elements));
    }

    private String csvToSingtelDMFormatJson(String csv){

        String[] value = csv.split(",");
        String[] name = {"Date","Event Logger Number","IMSI","MCC-MNC"};
        //get all rows
        //StringBuilder json = new StringBuilder("[\n");
        StringBuilder json = new StringBuilder("{\r\n");

        json.append("\"")
            .append("specversion")
            .append("\" : \"")
            .append("1.0")
            .append("\",\r\n"); //comma-1

        json.append("\"")
            .append("id")
            .append("\" : \"")
            .append("a89b61a2-5644-487a-8a86-144855c5dce8")
            .append("\",\r\n"); //comma-1

        json.append("\"")
            .append("source")
            .append("\" : \"")
            .append("SomeEventSource")
            .append("\",\r\n"); //comma-1

        json.append("\"")
            .append("type")
            .append("\" : \"")
            .append("DecisionRequest")
            .append("\",\r\n"); //comma-1

        json.append("\"")
            .append("subject")
            .append("\" : \"")
            .append("TheSubject")
            .append("\",\r\n"); //comma-1

        json.append("\"")
            .append("kogitodmnmodelname")
            .append("\" : \"")
            .append("Roaming Evaluation")
            .append("\",\r\n"); //comma-1
        
        json.append("\"")
            .append("kogitodmnmodelnamespace")
            .append("\" : \"")
            .append("https://github.com/kiegroup/drools/kie-dmn/_A4BCA8B8-CF08-433F-93B2-A2598F19ECFF")
            .append("\",\r\n"); //comma-1
        
        json.append("\"")
            .append("data")
            .append("\" : {");

        //json.append("\t{\n");
        //logger.infof("Length %d",value.length);
        for(int i = 0; i < value.length; i++){
            json.append("\"")
            .append(name[i])
            .append("\" : \"")
            .append(value[i])
            .append("\",\r\n"); //comma-1
        }
        json.replace(json.lastIndexOf(","), json.length(), "\n");

        json.append("}},"); //comma-2

        //remove comma-2
        json.replace(json.lastIndexOf(","), json.length(), "");

       // json.append("]");

        return json.toString();

    }

}
