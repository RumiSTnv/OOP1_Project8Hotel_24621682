package bg.tu_varna.sit.f24621682.OOP1project.Hotel.person;
/**
 * Моделен клас, представляващ гост на хотела.
 * <p>
 * Капсулира името на клиента за целите на регистрацията.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class Guest {
    private String guestName;
    /**
     * Конструира гост със специфично име.
     *
     * @param guestName пълното име на госта
     */
    public Guest(String guestName) {
        this.guestName = guestName;
    }
    /**
     * Връща името на госта.
     *
     * @return текстово представяне на името
     */
    public String getGuestName() {
        return guestName;
    }
    /**
     * Преобразува обекта в низов формат за директно отпечатване.
     *
     * @return името на госта
     */
    @Override
    public String toString() {
        return guestName;
    }
}
