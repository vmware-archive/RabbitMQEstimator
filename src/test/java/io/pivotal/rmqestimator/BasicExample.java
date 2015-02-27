package io.pivotal.rmqestimator;

import java.net.URISyntaxException;
import java.util.Collection;

import com.google.common.base.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.pivotal.rmqestimator.rest.httpclient.BasicAuthHttpClientProvider;
import io.pivotal.rmqestimator.rest.mgmt.model.*;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;


public class BasicExample {

    private static final Logger logger = LoggerFactory.getLogger(BasicExample.class);

    public static void main(String[] args) throws URISyntaxException {

        String hostname = (args != null && args.length > 0)? args[0] : "localhost";

        BasicAuthHttpClientProvider provider = new BasicAuthHttpClientProvider("guest", "guest");

        /*
            Intializing the service is easy.  The defaults == guest:guest@localhost:15672
         */
        RabbitNodeMgmtService mgmt = RabbitNodeMgmtService.builder().build();

        /* Or you can override defaults by using the builder. */
        mgmt = RabbitNodeMgmtService.builder().host(hostname).port(15672).credentials("guest", "guest").build();

        exchangesExample(mgmt);

        queueExample(mgmt);

        publishConsumeExample(mgmt);

        connectionExample(mgmt);
    }

    public static void exchangesExample(RabbitNodeMgmtService mgmt){

        Exchange ex = new Exchange("my.exchange");
        log(mgmt.exchanges()
                .create(ex)
                .get("my.exchange"));

        mgmt.exchanges().delete("my.exchange");
    }

    public static void queueExample(RabbitNodeMgmtService mgmt){

        Queue q = new Queue("my.queue");

        log(mgmt.queues()
                .create(q)
                .get("my.queue"));

        mgmt.queues().delete("my.queue");
    }

    public static void publishConsumeExample(RabbitNodeMgmtService mgmt){

        mgmt.queues()
                .create(new Queue("q1"))
            .and()
            .exchanges()
                .create(new Exchange("ex1"))
            .and()
            .bindings()
                .create(new Binding("ex1", "q1", "topic1"));

        mgmt.exchanges().publish("ex1", Message.builder().routingKey("topic1").payload("Hello!").build());

        Optional<Collection<ReceivedMessage>> messages =
                mgmt.queues().consume("q1", ConsumeOptions.builder().requeueMessage(false).build());

        log(messages);
    }

    public static void connectionExample(RabbitNodeMgmtService mgmt){

        Collection<Connection> all = mgmt.connections().all();

        log(all);
    }

    public static void log(String template, Object... args) {

        logger.info(template, args);
    }

    public static void log(Object obj) {

        logger.info("{}", obj);
    }
}
