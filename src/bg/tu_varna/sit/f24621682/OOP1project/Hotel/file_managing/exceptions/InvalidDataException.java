package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions;
/**
 * Персонализирано изключение, индикиращо въвеждане на некоректни, непълни
 * или невалидни потребителски данни.
 * <p>
 * Задейства се при грешни формати на датите, невалидни числови стойности
 * за номера на стаи или нарушаване на синтактичните правила на командите.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
