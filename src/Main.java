import java.io.*;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
//import com.opencsv.CSVWriter;


public class Main {
    ArrayList<Offline> offline_list;
    byte bytes[]= null;

    public Main(){
        offline_list = new ArrayList<>();
        try {
//            parseFile("test.txt");
            scanFile("test.txt");
            updateSheet("test.csv");

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void parseFile(String filename) throws IOException {
        double start = System.currentTimeMillis();
        say("The file is "+ filename);
        FileInputStream fis = new FileInputStream(filename);
        bytes = fis.readAllBytes();
        fis.close();

        double end = System.currentTimeMillis();
        double total = (end-start)/1000;
        say("Finished with "+ " "+total);


    }

    private void scanFile(String filename) throws FileNotFoundException {
        int counter = 0;
        double start = System.currentTimeMillis();
        File f = new File(filename);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()){
            String test = s.nextLine();
            if(test.contains("Error ")){
                String[] message = test.split(" ");
//                say(array[0]+" the time is  "+array[1]);
                String tmpdate = message[0];
                String tmptime= message[1];
//                Offline tmp = new Offline(message[0],message[1]);
                while( s.hasNextLine()){
                    test = s.nextLine();
                    if(test.contains("OK")){
                        break;
                    }
                }
                message = test.split(" ");
                Offline tmp = new Offline(tmpdate, tmptime, message[1]);
//                say(message[1]);
                offline_list.add(tmp);
                counter++;
//                say(test);
            }
        }
        s.close();
        double end = System.currentTimeMillis();
        double total = (end-start)/1000;
        say("Finished with "+ " "+total+" "+ "the number of times is "+counter);

    }

    public void updateSheet(String fname) throws IOException {
        File f = new File("fname");
        FileWriter fw = new FileWriter(f);

        for(Offline ofd:offline_list){
//            say("adding");
//            fw.append(ofd.deviceLabel+","+ofd.date+","+ofd.time+","+ofd.duration+"\n");
              say(fw.append(ofd.deviceLabel));

        }
        fw.flush();
        fw.close();
//        CSVWriter cs = new CSVWriter (new FileWriter(f);
    }



    public static void say(Object o){
        System.out.println(o);
    }




    public static void main(String args[]){
        new Main();
    }

}