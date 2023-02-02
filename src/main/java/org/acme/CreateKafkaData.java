package org.acme;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.jboss.logging.Logger;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreateKafkaData {
  //  private final Logger logger2 = Logger.getLogger(MovieConsumer.class);
    @Inject
    SendKafkaData producer;

    @POST
    public Response send(CsvData data) {
      //logger.infof("Got a movie: %d - %s", record.key(), record.value());
       // logger2.info(movie);
       // logger2.infof("Got a logger2: %s", movie);
        producer.sendDataToKafka(data);
        // Return an 202 - Accepted response.
        return Response.accepted().build();
    }
}
