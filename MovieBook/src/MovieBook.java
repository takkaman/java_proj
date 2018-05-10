import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class MovieBook {
    public static class User{
        public String name;
        public User(String name) {
            this.name = name;
        }
    }

    public static User UserLogin() {
        User usr = new User("Leo");
        return usr;
    }

    public static void ShowMovies() {
        String fileName="./rank.txt";
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {
                line = in.readLine();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static void ShowCineplex() {
        String fileName="./cineplex.txt";
        StringBuilder jsonStr = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String temp;
            while((temp = reader.readLine())!= null)
            jsonStr.append(temp);
            reader.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        //将json字符串转化为JSON对象，并读取内容
        try{
            JSONObject json = new JSONObject(jsonStr.toString());
            JSONArray cineplex = json.getJSONArray("cineplex");
            for(int i=0;i<cineplex.length();i++) {
                JSONObject cinema = (JSONObject) cineplex.get(i);
                String cineName = (String) cinema.get("name"); //获取JSON对象的键值对
                JSONArray movies = cinema.getJSONArray("movies");
                System.out.println("***Name: " + cineName+"***");
                for (int j = 0; j < movies.length(); j++) {
                    JSONObject movie = (JSONObject) movies.get(j);
                    String movName = (String) movie.get("name");
                    String movTime = (String) movie.get("time");
                    String movDate = (String) movie.get("date");
                    Integer movSeats = (Integer) movie.get("seats");
                    System.out.println(j+1+" : " + movName+" on " + movTime+" on " + movDate+" with Available seats: " + movSeats);
                }
                System.out.print("\n");
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }

        return;
    }



    public static void MainEntry(User usr) {
        System.out.println("Welcome "+usr.name+" !\n****************Menu****************");
        System.out.println("Please select what you want:\n1: Show all movies\n2: Show all cineplexes\nPlease input your choice: ");
        Scanner sc = new Scanner(System.in);
        String choice = String.valueOf(sc.nextLine());
        while (choice.compareTo("1") != 0 && choice.compareTo("2") != 0) {
            System.out.println("Please choose either 1 or 2.\nPlease input your choice:");
            choice = String.valueOf(sc.nextLine());
        }
        if (choice.compareTo("1") == 0) {
            System.out.println("Show movies");
            ShowMovies();
        } else if (choice.compareTo("2") == 0) {
            System.out.println("Show Cineplex Theatres");
            ShowCineplex();
        }
        return;
    }

    public static void main(String[] args) {
        User usr = UserLogin();
        MainEntry(usr);
        // SearchByMovie();
        // SearchByCineplex();
    }
}
