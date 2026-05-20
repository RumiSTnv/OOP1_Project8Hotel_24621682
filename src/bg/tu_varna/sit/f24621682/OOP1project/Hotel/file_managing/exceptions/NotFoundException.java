package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions;
/**
 * Изключение, сигнализиращо, че изисканият софтуерен ресурс или обект не може
 * да бъде намерен в системната памет.
 * <p>
 * Хвърля се в случаи, когато се търси несъществуваща стая, липсваща резервация
 * при checkout или когато няма налични свободни легла при изпълнение на команда за търсене.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
