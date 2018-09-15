package com.myco;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.TransactionManagingInterceptor;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.axoniq.axondb.client.AxonDBConfiguration;
import io.axoniq.axondb.client.axon.AxonDBEventStore;

@Configuration
public class AxonConfig {

  @Bean
  public Serializer eventSerializer() {
    return new JacksonSerializer();
  }

  @Autowired
  public void defaultEventHandlingInitialization(EventProcessingConfiguration eventProcConfig) {
    // TODO: determine if there is any reason to NOT use tracking processors by default, as this configuration does
    eventProcConfig.usingTrackingProcessors();
  }

  @Bean
  public BeanValidationInterceptor<Message<?>> beanValidationInteceptor() {
    return new BeanValidationInterceptor<>();
  }

  @Bean(destroyMethod = "shutdown")
  public AsynchronousCommandBus commandBus(TransactionManager transactionManager) {
    AsynchronousCommandBus cb = new AsynchronousCommandBus();
    cb.registerDispatchInterceptor(beanValidationInteceptor());
    cb.registerHandlerInterceptor(new TransactionManagingInterceptor<>(transactionManager));
    return cb;
  }

  @Bean
  public Cache cache() {
    return new WeakReferenceCache();
  }

  @Bean
  public SpringAggregateSnapshotter snapshotter(ParameterResolverFactory parameterResolverFactory,
      EventStore eventStore, TransactionManager transactionManager) {
    Executor executor = Executors.newSingleThreadExecutor();
    SpringAggregateSnapshotter snapshotter =
        new SpringAggregateSnapshotter(eventStore, parameterResolverFactory, executor, transactionManager);
    return snapshotter;
  }

  @Bean(name = "eventBus")
  public EventStore eventStore(AxonDBConfiguration axonDBConfiguration, Serializer eventSerializer) {
    return new AxonDBEventStore(axonDBConfiguration, eventSerializer);
  }

  @Bean
  public AxonDBConfiguration axonDBConfiguration() {
    return new AxonDBConfiguration();
  }
}
