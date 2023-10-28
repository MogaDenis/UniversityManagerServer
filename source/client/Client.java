package source.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Client 
{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Scanner scanner;
    private ArrayList<String> mainMenuList;

    public Client(Socket socket)
    {
        this.socket = socket;
        this.scanner = new Scanner(System.in);
        this.mainMenuList = new ArrayList<>();

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

    private void printList(List<?> list)
    {
        for (Object object : list)
            System.out.println(object.toString());
    }

    private void sendStringToServer(String string) throws IOException
    {
        this.bufferedWriter.write(String.valueOf(string));
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();
    }

    private Vector<String> receiveVectorFromServer() throws IOException
    {
        Integer vectorLength = Integer.parseInt(this.bufferedReader.readLine());
        Vector<String> vector = new Vector<>();

        for (int i = 0; i < vectorLength; i++)
        {
            String currentElement = this.bufferedReader.readLine();
            vector.add(currentElement);
        }

        return vector;
    }

    private void execute()
    {
        try 
        {
            String menuSizeString = this.bufferedReader.readLine();
                
            Integer menuSize = Integer.parseInt(menuSizeString);

            for (int i = 0; i < menuSize; i++)
            {
                String mainMenuEntry = this.bufferedReader.readLine();
                mainMenuList.add(mainMenuEntry);
            }

            while (this.socket.isClosed() == false)
            {
                this.printList(mainMenuList);

                System.out.print("\n>> ");

                String choice = this.scanner.nextLine();
                this.sendStringToServer(choice);

                if (choice.equals(ClientHandler.breakConnection))
                {
                    break;
                }
                else if (choice.equals(ClientHandler.showStudents) || choice.equals(ClientHandler.showTeachers) || 
                        choice.equals(ClientHandler.showSubjects))
                {
                    Vector<String> entities = this.receiveVectorFromServer();
                    this.printList(entities);
                }
            }
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
    }

    public static void main(String[] args)
    {
        try 
        {
            Socket socket = new Socket("localhost", 1234);
            Client client = new Client(socket);

            client.execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
