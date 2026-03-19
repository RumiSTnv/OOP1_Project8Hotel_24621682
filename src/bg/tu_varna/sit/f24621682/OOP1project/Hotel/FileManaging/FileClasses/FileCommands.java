package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public abstract class FileCommands {
    protected boolean isOpen;
    protected String filePath;
    protected BufferedReader bufferedReader;

    public boolean isOpen() {
        return isOpen;
    }

    public abstract void open(String path);

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

    public abstract void save();

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

}
