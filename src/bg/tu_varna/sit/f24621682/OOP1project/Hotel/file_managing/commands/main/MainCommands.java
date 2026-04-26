package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomsAvailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCommands {
    protected boolean isOpen;
    protected String filePath;
    protected BufferedReader bufferedReader;

    RoomsAvailability freeRooms = new RoomsAvailability();
    ReservationsManaging reservations = new ReservationsManaging();

    public RoomsAvailability getFreeRooms() {
        return freeRooms;
    }

    public ReservationsManaging getReservations() {
        return reservations;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open(String path, RoomManaging roomManaging){
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created");
            }

            this.filePath = path;
            isOpen = true;
            reservations.clearReservations();
            freeRooms.clearFreeRooms();


            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((line = reader.readLine()) != null) {
                try {
                    String[] s = line.split(" ", 5);

                    int roomNumber = Integer.parseInt(s[0]);
                    Date start = dateFormat.parse(s[1]);
                    Date end = dateFormat.parse(s[2]);

                    String note = s[3];
                    int guests = Integer.parseInt(s[4]);

                    reservations.addReservation(
                            new Reservation(roomNumber, start, end, note, guests)
                    );

                } catch (Exception e) {
                    System.out.println("Invalid line: " + line);
                }
            }

            reader.close();

            for (Room room : roomManaging.getAllRooms()) {
                boolean isReserved = false;

                for (Reservation res : reservations.getReservations()) {
                    if (res.getRoomNumber() == room.getRoomNumber()) {
                        isReserved = true;
                        break;
                    }
                }

                if (!isReserved) {
                    room.setRoomStatus(RoomStatus.AVAILABLE);
                    freeRooms.addFreeRoom(room);
                }
            }

            save();

            reader.close();
            System.out.println("File opened");

        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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

            for(Reservation res : reservations.getReservations()){
                writer.write(res.getRoomNumber() + " "
                        + dateFormat.format(res.getStartDate()) + " "
                        + dateFormat.format(res.getEndDate()) + " "
                        + res.getNote() + " "
                        + res.getGuests());
                writer.newLine();
            }

            writer.close();
        }catch(IOException e){
            e.printStackTrace();
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
                file.createNewFile();
            }

            save();
            System.out.println("File saved");
        }catch(IOException e){
            e.printStackTrace();
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
