package source.client;

import java.util.ArrayList;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import source.controller.Controller;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ArrayList<String> mainMenu;
    private Controller controller;

    private ArrayList<String> getMainMenuList() 
    {
        ArrayList<String> mainMenu = new ArrayList<>();

        mainMenu.add("1 - Show the students.");
        mainMenu.add("2 - Show the teachers.");
        mainMenu.add("3 - Show the subjects.");
        mainMenu.add("4 - Add a student.");
        mainMenu.add("5 - Add a teacher.");
        mainMenu.add("6 - Add a subject.");
        mainMenu.add("0 - Close the application.");

        return mainMenu;
    }

    public ClientHandler(Socket socket, Controller controller) 
    {
        this.socket = socket;
        this.mainMenu = this.getMainMenuList();
        this.controller = controller;

        try 
        {
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } 
        catch (IOException e) 
        {
            this.closeConnection();
            e.printStackTrace(System.out);
        }
    }

    private void closeConnection() 
    {
        try 
        {
            if (this.socket != null)
                this.socket.close();

            if (this.bufferedReader != null)
                this.bufferedReader.close();

            if (this.bufferedWriter != null)
                this.bufferedWriter.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        System.out.println("A client has disconnected.");
    }

    private void sendStringToClient(String string) throws IOException
    {
        this.bufferedWriter.write(String.valueOf(string));
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();
    }

    @Override
    public void run() 
    {
        try 
        {
            Integer menuSize = this.mainMenu.size();
            this.sendStringToClient(String.valueOf(menuSize));

            for (String menuEntry : this.mainMenu) 
                this.sendStringToClient(menuEntry);
            

            while (this.socket.isClosed() == false) 
            {
                String clientChoice = this.bufferedReader.readLine();
                System.out.println(clientChoice);

                if (clientChoice.equals(String.valueOf(0))) 
                {
                    this.closeConnection();
                    break;
                }

                this.sendStringToClient("answer");;
            }
        } 
        catch (IOException e) 
        {
            this.closeConnection();
            e.printStackTrace();
        }
    }
}
