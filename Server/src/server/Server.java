package server;

import dataBase.Authorizer;
import dataBase.BookSearcher;
import request.Book;
import request.BookText;
import request.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * * @author Светлана
 */
final class Server {
    private final ExecutorService threadPool = Executors.newFixedThreadPool(5);
    private final Authorizer authorizer = new Authorizer();
    private final BookSearcher bookSearcher = new BookSearcher();

    public void start() {

        System.out.println("Server is running.");
        try {
            int PORT = 7777;
            ServerSocket listener = new ServerSocket(PORT);

            try {

                while (true) {
                    //запускается обработчик клиента в отдельном потоке до тех пор, пока не отключу сервер, он будет ждать нового клиента
                    threadPool.execute(new Handler(listener.accept()));
                }
            } catch (IOException exception) {
                System.err.println("Error when client connected");
            } finally {
                listener.close();
                threadPool.shutdown();
                threadPool.awaitTermination(100, TimeUnit.MILLISECONDS);
                threadPool.shutdownNow();

            }
        } catch (IOException exception) {
            System.err.println("Error when server start");
        } catch (InterruptedException ex) {
            System.out.println("Error when shutdown threads");
        }
    }

    private class Handler extends Thread {
        private final Socket socket;
        private User user;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        public Handler(Socket socket) {
            this.socket = socket;
        }


        public void run() {
            try {
                System.out.println("New user accepted " + socket);
                //сервер создает входящий поток
                in = new ObjectInputStream(socket.getInputStream());
                //сервер создает выходящий поток
                out = new ObjectOutputStream(socket.getOutputStream());

                user = (User) in.readObject();

                while (!user.getAuthorized()) {
                    if (!user.getRegistration()) {
                        user.setAuthorized(authorizer.authorize(user));
                    } else {
                        user.setAuthorized(authorizer.register(user));
                        //System.out.println("register");
                    }
                    //ответ клиенту в формате user
                    out.writeObject(user);
                    out.flush();
                    user = (User) in.readObject();
                }


                while (true) {
                    // ожидается запрос в строковом формате
                    String request = in.readUTF();
                    if (!"".equals(request)) {
                        if ("TEXT_REQUEST".equals(request)) {
                            //получаем запрос в формате book
                            Book book = (Book) in.readObject();
                            //out.writeUTF(bookSearcher.foundText(book));
                            BookText bookText = bookSearcher.foundText(book);
                            out.writeObject(bookText);
                            out.flush();

                        } else if ("DELETE_REQUEST".equals(request)) {
                            Book book = (Book) in.readObject();
                            bookSearcher.deleteBook(book);
                        } else if ("SEND_REQUEST".equals(request)) {
                            Book book = (Book) in.readObject();
                            BookText text = (BookText) in.readObject();
                            bookSearcher.bookAdded(book, text);
                        } else {
                            out.writeObject(bookSearcher.search(request));
                        }
                    }

                }
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {

                System.err.println("Object not found" + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Cant close socket");
                } finally {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
