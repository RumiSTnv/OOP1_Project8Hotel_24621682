package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.FileErrorException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsUnavailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.UnavailablePeriod;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsAvailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCommands {
    protected boolean isOpen;
    protected String filePath;
    protected BufferedReader bufferedReader;

    RoomsAvailability freeRooms = new RoomsAvailability();
    ReservationsManaging reservations = new ReservationsManaging();
    RoomsUnavailability unavailableRooms = new RoomsUnavailability();

    public RoomsAvailability getFreeRooms() {
        return freeRooms;
    }

    public ReservationsManaging getReservations() {
        return reservations;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open(String path, RoomManaging roomManaging, RoomsUnavailability roomsUnavailability) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("New file created: " + path);
                } else {
                    throw new FileErrorException("Failed to create new file: " + path);
                }
            }

            this.filePath = path;
            isOpen = true;

            reservations.clearReservations();
            freeRooms.clearFreeRooms();
            roomsUnavailability.clearUnavailableRooms();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            int section = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    section++;
                    continue;
                }

                if (section == 0) {

                    String[] parts = line.split(" ");
                    int roomNumber = Integer.parseInt(parts[0]);
                    int beds = Integer.parseInt(parts[1]);
                    Room room = roomManaging.findRoomsByRoomNumber(roomNumber);

                    if (room == null) {
                        room = new Room(roomNumber, beds);
                        roomManaging.getAllRooms().add(room);
                    }

                    freeRooms.addFreeRoom(room);
                }

                else if (section == 1) {

                    String[] s = line.split(" ", 5);
                    int roomNumber = Integer.parseInt(s[0]);
                    Date start = dateFormat.parse(s[1]);
                    Date end = dateFormat.parse(s[2]);
                    String note = s[3];
                    int guests = Integer.parseInt(s[4]);

                    reservations.addReservation(
                            new Reservation(roomNumber, start, end, note, guests));
                }

                else if (section == 2) {

                    String[] s = line.split(" ", 4);
                    int roomNumber = Integer.parseInt(s[0]);
                    Date start = dateFormat.parse(s[1]);
                    Date end = dateFormat.parse(s[2]);
                    String note = s[3];

                    roomsUnavailability.addUnavailableRooms(
                            new UnavailablePeriod(roomNumber, start, end, note));
                }
            }

            reader.close();

            System.out.println("File opened");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void close(){
        if(!isOpen){
            return;
        }
        try{
            if(bufferedReader != null){
                bufferedReader.close();

            }
        }catch(IOException e){
            throw new InvalidDataException("Invalid file!");
        }

        filePath=null;
        isOpen=false;
        System.out.println("File closed");
    }

    public void save(){
        if(!isOpen){
            return;
        }

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for(Room room : freeRooms.getFreeRooms()){
                writer.write(room.getRoomNumber() + " " + room.getNumberOfBeds());
                writer.newLine();
            }

            writer.newLine();

            for(Reservation res : reservations.getReservations()){
                writer.write(res.getRoomNumber() + " "
                        + dateFormat.format(res.getStartDate()) + " "
                        + dateFormat.format(res.getEndDate()) + " "
                        + res.getNote() + " "
                        + res.getGuests());
                writer.newLine();
            }

            writer.newLine();

            for (UnavailablePeriod un : unavailableRooms.getUnavailableRooms()) {
                writer.write(un.getRoomNumber() + " " +
                                dateFormat.format(un.getStartDate()) + " " +
                                dateFormat.format(un.getEndDate()) + " " +
                                un.getNote());

                writer.newLine();
            }

            writer.close();
        }catch(IOException e){
            throw new InvalidDataException("Invalid file!");
        }
    }

    public void saveAs(String newPath){
        if(!isOpen){
            return;
        }

        try{
            filePath = newPath;
            File file = new File(newPath);
            if(!file.exists()){
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("New file created: " + newPath);
                } else {
                    throw new FileErrorException("Failed to create new file: " + newPath);
                }
            }

            save();
            System.out.println("File saved");
        }catch(IOException e){
            throw new InvalidDataException("Invalid file!");
        }

    }

    public void help(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The following commands are supported\n");
        stringBuilder.append("open <file> - opens a file\n");
        stringBuilder.append("close - closes currently opened file\n");
        stringBuilder.append("save - saves currently opened file\n");
        stringBuilder.append("save as <file> - saves currently opened file in <file>\n");
        stringBuilder.append("help - show commands\n");
        stringBuilder.append("exit - exit program\n");
        stringBuilder.append("checkin <room> <from> <to> <note> [<guests>] - creates a reservation\n");
        stringBuilder.append("availability [<date>] - show free rooms\n");
        stringBuilder.append("checkout <room> - removes a rezervation\n");
        stringBuilder.append("report <from> <to> - shows the occupied rooms\n");
        stringBuilder.append("find <beds> <from> <to> - finds a suitable room\n");
        stringBuilder.append("find! <beds> <from> <to> - finds a suitable room for an important guest\n");
        stringBuilder.append("unavailable <room> <from> <to> <note> - marks the room as unavailable\n");
        System.out.println(stringBuilder.toString());
    }
}
