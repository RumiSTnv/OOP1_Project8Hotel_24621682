package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.FileErrorException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.person.Guest;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.UnavailablePeriod;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomActivity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Команда за отваряне на текстов файл и зареждане на базата данни в паметта.
 * <p>
 * Извършва селективно парсване на редовете по блокове (ROOMS, RESERVATIONS, UNAVAILABLE_ROOMS),
 * възстановява обектите и инициализира работното състояние.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class OpenCommand extends Command {
    private HotelState state;
    /**
     * Конструира команда за отваряне на файл.
     *
     * @param state текущото споделено състояние, което ще бъде запълнено с данни
     */
    public OpenCommand(HotelState state) {

        super("open", "Opens file");

        this.state = state;
    }
    /**
     * Изпълнява проверката на файла и неговото линейно парсване чрез BufferedReader.
     *
     * @param input пътят до файла на диска
     */
    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split("\\s+");

            if (parts.length != 2) {
                throw new InvalidDataException("Usage: open <file>");
            }

            File file = new File(parts[1]);

            if (!file.exists()) {
                boolean created = file.createNewFile();

                if (created) {
                    System.out.println("New file created: " + parts[1]);
                } else {
                    throw new FileErrorException("Failed to create file");
                }

                state.setOpen(true);
                state.setFilePath(parts[1]);
                return;
            }

            state.setFilePath(parts[1]);
            state.setOpen(true);

            state.getReservations().clearReservations();
            state.getFreeRooms().clearFreeRooms();
            state.getFreeRooms().getFreeRooms().clear();
            state.getRoomManaging().getAllRooms().clear();
            state.getUnavailableRooms().clearUnavailableRooms();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    if (line.equals("ROOMS")) {
                        while (!(line = reader.readLine().trim()).equals("END_ROOMS")) {

                            String[] roomParts = line.split("\\s+");
                            int roomNumber = Integer.parseInt(roomParts[0]);
                            int beds = Integer.parseInt(roomParts[1]);
                            Room room = state.getRoomManaging().findRoomsByRoomNumber(roomNumber);

                            if (room == null) {

                                room = new Room(roomNumber, beds);
                                state.getRoomManaging().getAllRooms().add(room);
                            }

                            state.getFreeRooms().addFreeRoom(room);
                        }
                    }

                    else if (line.equals("RESERVATIONS")) {
                        while ((line = reader.readLine()) != null && !line.trim().equals("END_RESERVATIONS")) {
                            String[] reservationParts = line.trim().split("\\s+");

                            if (reservationParts.length < 5) continue;

                            int roomNumber = Integer.parseInt(reservationParts[0]);
                            Date start = dateFormat.parse(reservationParts[1]);
                            Date end = dateFormat.parse(reservationParts[2]);
                            String note = reservationParts[3];
                            int guestsCount = Integer.parseInt(reservationParts[4]);

                            Reservation reservation = new Reservation(roomNumber, start, end, note, guestsCount);

                            int currentIndex = 5;
                            for (int i = 0; i < guestsCount && currentIndex + 1 < reservationParts.length; i++) {

                                String fullName = reservationParts[currentIndex] + " " + reservationParts[currentIndex + 1];
                                reservation.addGuest(new Guest(fullName));
                                currentIndex += 2;
                            }

                            while (currentIndex < reservationParts.length) {
                                String raw = reservationParts[currentIndex].toUpperCase();

                                try {
                                    RoomActivity type = RoomActivity.valueOf(raw);
                                    reservation.addActivity(new Activity(type, start, end));
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid activity: " + raw);
                                }
                                currentIndex++;
                            }

                            state.getReservations().addReservation(reservation);
                        }
                    }

                    else if (line.equals("UNAVAILABLE_ROOMS")) {

                        while (!(line = reader.readLine().trim()).equals("END_UNAVAILABLE_ROOMS")) {
                            String[] unavailableParts = line.split("\\s+", 4);
                            int roomNumber = Integer.parseInt(unavailableParts[0]);
                            Date start = dateFormat.parse(unavailableParts[1]);
                            Date end = dateFormat.parse(unavailableParts[2]);
                            String note = unavailableParts[3];
                            UnavailablePeriod unavailable = new UnavailablePeriod(roomNumber, start, end, note);

                            state.getUnavailableRooms().addUnavailableRooms(unavailable);
                        }
                    } else if (line.equals("ACTIVITY_RESULTS")) {

                        while (!(line = reader.readLine().trim()).equals("END_ACTIVITY_RESULTS")) {
                            state.getActivityManaging().addResult(line);
                        }
                    } else if (line.equals("ROOM_PROGRAM_RESULTS")) {

                        while (!(line = reader.readLine().trim()).equals("END_ROOM_PROGRAM_RESULTS")) {
                            state.getRoomProgramManaging().addToProgram(line);
                        }
                    }
                }
            }
            if(state.getRoomManaging().getAllRooms().isEmpty()){
                state.getRoomManaging().addData();
            }

            System.out.println(successfulExecutionMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
