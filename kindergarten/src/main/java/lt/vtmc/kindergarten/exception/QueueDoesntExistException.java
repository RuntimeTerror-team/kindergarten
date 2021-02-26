package lt.vtmc.kindergarten.exception;

public class QueueDoesntExistException extends RuntimeException {
    public QueueDoesntExistException(String active_queue_must_exists) {
    }
}
