package source.server;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

import source.client.ClientHandler;
import source.controller.Controller;
import source.model.student.Student;
import source.model.teacher.Teacher;
import source.model.subject.Subject;
import source.repository.Repository;
import source.repository.StudentsRepository;
import source.repository.SubjectsRepository;
import source.repository.TeachersRepository;

public class Server 
{
    private ServerSocket serverSocket;
    private Controller controller;
    
    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;

        Repository<Student> students = new StudentsRepository();
        Repository<Teacher> teachers = new TeachersRepository();
        Repository<Subject> subjects = new SubjectsRepository();

        this.controller = new Controller(students, teachers, subjects);
    }

    private void closeConnection()
    {
        try
        {
            if (this.serverSocket != null)
                this.serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }

    public void startServer()
    {
        try 
        {
            System.out.println("Server started\n");
            while (this.serverSocket.isClosed() == false)
            {
                Socket socket = this.serverSocket.accept();
                System.out.println("New client has joined.");

                ClientHandler clientHandler = new ClientHandler(socket, controller);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }
        catch (IOException e)
        {
            this.closeConnection();
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);

        server.startServer();
    }
}
