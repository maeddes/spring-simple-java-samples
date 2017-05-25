package de.maeddes.springredispubsub;

public interface MessagePublisher {

    void publish(final String message);
}