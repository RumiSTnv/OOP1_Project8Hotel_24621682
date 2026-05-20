package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions;
/**
 * Неконтролирано изключение (Unchecked Exception), хвърляно при възникване на
 * критични грешки, свързани с файловата система.
 * <p>
 * Използва се за сигнализиране при невъзможност за създаване, четене или
 * персистентен запис на данните върху диска.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class FileErrorException extends RuntimeException {
    public FileErrorException(String message) {
        super(message);
    }
}
