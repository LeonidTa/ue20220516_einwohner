import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Server implements Runnable{
    static ArrayList<Einwohner> distinctEinwohner = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try(ServerSocket ss = new ServerSocket(1313)) {
            System.out.println("Waiting for client");
            Socket client = ss.accept();
            System.out.println("Client connected");
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write("Bitte Bundesland mit 'GET Bundesland' oder 'GET <Geburtsjahr> order by name' angeben:");
            bw.newLine();
            bw.flush();

            EinwohnerManager em = new EinwohnerManager();
            ArrayList<Einwohner> einwohner = em.load();


            while (true) {
                String input = br.readLine();
                if (input.startsWith("GET") && input.endsWith("name")) {
                    String [] splitInput = input.split(" ");
                    int gebJahr = Integer.parseInt(splitInput[1]);
                    System.out.println(gebJahr);
                    for (Einwohner einwohner1 : einwohner) {
                        if (einwohner1.geburtsjahr == gebJahr) {
                            addEinwohnerWithGebJahr(einwohner1);
                        }
                    }
                    Collections.sort(distinctEinwohner);
                    for (Einwohner einwohnerDistinct : distinctEinwohner) {
                        bw.write(einwohnerDistinct.name);
                        bw.newLine();
                        bw.flush();
                    }
                    System.out.println(distinctEinwohner.toString());
                    distinctEinwohner.clear();

                } else if (input.startsWith("GET")) {
                    String splitInput = input.substring(4);
                    System.out.println(splitInput);
                    for (Einwohner einwohner1 : einwohner) {
                        if (splitInput.equals(einwohner1.bundesland)) {
                            bw.write(String.valueOf(einwohner1.geburtsjahr));
                            bw.newLine();
                            bw.flush();
                        }
                    }
                }
                else if (input.equals("EXIT")) {
                    client.close();
                } else {
                    bw.write("Wrong input, please try again");
                    bw.newLine();
                    bw.flush();
                }
            }

        }

    }

    public static void addEinwohnerWithGebJahr(Einwohner e) {
        distinctEinwohner.add(e);
    }

    @Override
    public void run() {

    }
}
