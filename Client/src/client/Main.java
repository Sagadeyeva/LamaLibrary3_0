package client;

import reader.Registration;


class Main {
    public static void main(String[] ar) {
        Client client = new Client();
        client.connect();
        Registration.show(client);
    }
}
