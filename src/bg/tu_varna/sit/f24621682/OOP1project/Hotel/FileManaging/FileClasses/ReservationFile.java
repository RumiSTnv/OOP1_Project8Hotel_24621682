package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Reservation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationFile extends HotelFile{
    private List<Reservation> reservations = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public void open(String path) {
        try{
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
                System.out.println("File created");
            }
            this.filePath = path;
            isOpen = true;

            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = bufferedReader.readLine()) != null){
                String [] splitting = line.split(" ");
            }

           // bufferedReader.close();


            System.out.println("File opened");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkIn(Scanner scanner, Room room){
        if(!isOpen){
            System.out.println("Please enter the room number");
            return;
        }
        String roomNumber = scanner.next();
        String startDate = scanner.next();
        String endDate = scanner.next();
        String note = scanner.nextLine();;
        int guestNumber = room.getNumberOfBeds();

        Reservation reservation = new Reservation(roomNumber, startDate, endDate, note);
        reservations.add(reservation);
    }

    @Override
    public void save() {
        if(!isOpen){
            return;
        }

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for(Reservation reservation : reservations){
                writer.write(reservation.getRoomNumber() + " "
                        + reservation.getStartDate() + " "
                + reservation.getEndDate());
                writer.newLine();
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");

        }catch(Exception e){
            System.out.println("Error writing to the file.");
        }
    }
}
