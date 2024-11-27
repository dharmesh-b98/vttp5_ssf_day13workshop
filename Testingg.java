import java.util.List;
import java.io.*;

public class Testingg {

    public static void main(String[] args) {
        //List<String> idList = new ArrayList<>();
        File dataDirF = new File("data");
        File[] contacts = dataDirF.listFiles();
        for (File file : contacts){
            //String filename = file.getName().split(".")[0];
            //idList.add(filename);
            System.out.println(file.getName().split("\\.")[0]); //split by "."
        }
    }
}
