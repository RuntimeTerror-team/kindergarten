package lt.vtmc.kindergarten.service;

public class QueueDoesntExistException extends RuntimeException {
    public QueueDoesntExistException(String active_queue_must_exists) {
    }
}
