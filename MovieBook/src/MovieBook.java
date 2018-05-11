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
    public static List<String> movieList = new ArrayList<>();
    public static List<Cinema> cineList = new ArrayList<>();
    public static User usr = new User();
    public static Map<String, List<Cinema>> movieCineMap = new HashMap<>();
    public static Map<String, String> userPswd = new HashMap<>();

    public static class Movie{
        public String name;
        public String time;
        public String date;
        public Integer seats;
//        public List<Cinema> cineList = new ArrayList<>();
        public Movie() {};
        public Movie(String name, String time, String date, Integer seats) {
            this.name = name;
            this.time = time;
            this.date = date;
            this.seats = seats;
        }
    }

    public static class Cinema{
        public String name;
        public List<Movie> cineMovieList = new ArrayList<>();
        public Cinema(String name) {
            this.name = name;
        }
        public Movie getMovie(String movName) {
            Movie movie = new Movie();
            for (Movie mv: cineMovieList) {
                if (movName.compareTo(mv.name) == 0) {
//                    System.out.println("Find "+movName);
                    movie = mv;
                }
            }
            return movie;
        }
    }

    public static class User{
        public String name;
        public String password;
    }

    public static void FetchInfoFromTxt() {
        // read login info
        String fileName="./login.txt";
        StringBuilder jsonStr = ReadJson(fileName);

        try{
            JSONObject json = new JSONObject(jsonStr.toString());
            JSONArray users = json.getJSONArray("login");
            for(int i=0;i<users.length();i++) {
                JSONObject user = (JSONObject) users.get(i);
                String usrName = (String) user.get("name"); //获取JSON对象的键值对
                String pswd = (String) user.get("password");
                userPswd.put(usrName, pswd);
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }

        // read cineplex movie info
        fileName="./cineplex.txt";
        jsonStr = ReadJson(fileName);

        //将json字符串转化为JSON对象，并读取内容
        try{
            JSONObject json = new JSONObject(jsonStr.toString());
            JSONArray cineplex = json.getJSONArray("cineplex");
            for(int i=0;i<cineplex.length();i++) {
                JSONObject cinema = (JSONObject) cineplex.get(i);
                String cineName = (String) cinema.get("name"); //获取JSON对象的键值对
                Cinema cine = new Cinema(cineName);
                cineList.add(cine);
                JSONArray movies = cinema.getJSONArray("movies");
                //System.out.println("***Name: " + cineName+"***");
                for (int j = 0; j < movies.length(); j++) {
                    JSONObject movie = (JSONObject) movies.get(j);
                    String movName = (String) movie.get("name");
                    String movTime = (String) movie.get("time");
                    String movDate = (String) movie.get("date");
                    Integer movSeats = (Integer) movie.get("seats");
                    Movie mv = new Movie(movName, movTime, movDate, movSeats);
                    if (movieCineMap.containsKey(movName)) {
                        movieCineMap.get(movName).add(cine);
                    } else {
                        List<Cinema> cl = new ArrayList<>();
                        cl.add(cine);
                        movieCineMap.put(movName, cl);
                        movieList.add(movName);
                    }
                    cine.cineMovieList.add(mv);
//                    Movie m = cine.getMovie(movName);
//                    System.out.println(movName+" "+m.name);
                }
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }

    public static StringBuilder ReadJson(String fileName) {
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
        return jsonStr;
    }

    public static void UserLogin() {
        System.out.print("User ID: ");
        Scanner sc = new Scanner(System.in);
        String userName = String.valueOf(sc.nextLine());

        while (!userPswd.containsKey(userName)) {
            System.out.println("No match. Please try again.");
            System.out.print("User ID: ");
            userName = String.valueOf(sc.nextLine());
        }
        usr.name = userName;
        System.out.print("Password: ");
        String password = String.valueOf(sc.nextLine());
        while (password.compareTo(userPswd.get(userName)) != 0) {
            System.out.println("Password incorrect. Please try again.");
            System.out.print("Password: ");
            password = String.valueOf(sc.nextLine());
        }
    }

/*
========================
== Search by Movie ==
========================
*/
    public static void ShowAllMovies() {
        int i = 1;
        for (String movName: movieList) {
            System.out.println(i+" : "+movName);
            i++;
        }
        System.out.print("Please select one movie or enter 0 to return to main menu:");
        Scanner sc = new Scanner(System.in);
        String choice = String.valueOf(sc.nextLine());
        ShowAllMovieCineplex(movieList.get(Integer.valueOf(choice)-1));
    }

    // show all cineplex playing selected movie
    public static void ShowAllMovieCineplex(String movName) {
        Scanner sc = new Scanner(System.in);
        String[] choices;
        List<Cinema> cineList = movieCineMap.get(movName);

        boolean proceed = true;
        while (proceed) {
            int i = 1;
            System.out.println("***Movie: " + movName + "***");
            for (Cinema cine : cineList) {
                Movie mv = cine.getMovie(movName);
                System.out.println(i + " : " + cine.name + " on " + mv.time + " on " + mv.date + " with Available seats: " + mv.seats);
                i++;
            }
            System.out.print("Please select one movie to book or enter 0 to return to main menu:");
            try {
                choices = String.valueOf(sc.nextLine()).split(" ");
                if (choices.length == 1) {
                    System.out.println(Integer.valueOf(choices[0]));
                    int input = Integer.valueOf(choices[0]);
                    if (input < 0 || input > i - 1) {
                        continue;
                    } else {
                        if (input != 0) {
                            System.out.print("Please select one movie to book or enter 0 to return to main menu:");
                            BookMoviesPerCine(movName, cineList, choices);
                        }
                        break;
                    }
                } else {
                    BookMoviesPerCine(movName, cineList, choices);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }
        }
        MainEntry();
    }

    public static void BookMoviesPerCine(String movName, List<Cinema> cineList, String[] choices) {
        int maxId = cineList.size();

        List<Movie> validMovies = new ArrayList<>();
        for (String s: choices) {
            try {
                int movId = Integer.valueOf(s)-1;
                if (movId < 0 || movId > maxId) {
                    System.out.println("Invalid choice "+s+", skip.");
                    continue;
                }
                validMovies.add(cineList.get(movId).getMovie(movName));
            } catch (Exception e) {
                System.out.println("Invalid choice "+s+", skip.");
            }
        }
        System.out.println("Confirm to book below movies:");
        for (Movie mv: validMovies) {
            System.out.println(mv.name+" on " + mv.time+" on " + mv.date+" with Available seats: " + mv.seats);
        }
        System.out.println("Yes/No: ");
        Scanner sc = new Scanner(System.in);
        String choice = String.valueOf(sc.nextLine());
        if (choice.compareTo("Yes") == 0) {
            for (Movie mv: validMovies) {
                if (mv.seats > 0) {
                    System.out.println("Book Movie: "+mv.name+" success.");
                    mv.seats--;
                } else {
                    System.out.println("Book Movie: "+mv.name+" fail, there is no more available seats");
                }
            }
        } else {
            ShowAllMovieCineplex(movName);
        }
    }
/*
========================
== Search by Cineplex ==
========================
*/

    public static void ShowAllCineplex() {
        int i = 1;
        for (Cinema cine: cineList) {
            System.out.println(i+" : "+cine.name);
            i++;
        }
        System.out.print("Please select one cineplex or enter 0 to return to main menu:");
        Scanner sc = new Scanner(System.in);
        String choice = String.valueOf(sc.nextLine());
        ShowAllCineplexMovie(cineList.get(Integer.valueOf(choice)-1));
    }

    // show all movies in selected cineplex
    public static void ShowAllCineplexMovie(Cinema cine) {
        Scanner sc = new Scanner(System.in);
        String[] choices;
        boolean proceed = true;
        while (proceed) {
            int i = 1;
            System.out.println("***Cinema: "+cine.name+"***");
//        System.out.println(cine.cineMovieList.size());
            for (Movie mv: cine.cineMovieList) {
//            Movie mv1 = cine.getMovie(mv.name);
                System.out.println(i+" : " + mv.name+" on " + mv.time+" on " + mv.date+" with Available seats: " + mv.seats);
                i++;
            }
            System.out.print("Please select one movie to book or enter 0 to return to main menu:");
            try {
                choices = String.valueOf(sc.nextLine()).split(" ");
                if (choices.length == 1) {
                    System.out.println(Integer.valueOf(choices[0]));
                    int input = Integer.valueOf(choices[0]);
                    if (input < 0 || input >i - 1) {
                        continue;
                    } else {
                        if (input != 0) {
                            System.out.print("Please select one movie to book or enter 0 to return to main menu:");
                            BookMovies(cine, cine.cineMovieList, choices);
                        }
                        break;
                    }
                } else {
                    BookMovies(cine, cine.cineMovieList, choices);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }
        }
        MainEntry();
    }

    public static void BookMovies(Cinema cine, List<Movie> movieList, String[] choices) {
        int maxId = movieList.size();
        List<Movie> validMovies = new ArrayList<>();
        for (String s: choices) {
            try {
                int movId = Integer.valueOf(s)-1;
                if (movId < 0 || movId > maxId) {
                    System.out.println("Invalid choice "+s+", skip.");
                    continue;
                }
                validMovies.add(movieList.get(movId));
            } catch (Exception e) {
                System.out.println("Invalid choice "+s+", skip.");
            }
        }
        System.out.println("Confirm to book below movies:");
        for (Movie mv: validMovies) {
            System.out.println(mv.name+" on " + mv.time+" on " + mv.date+" with Available seats: " + mv.seats);
        }
        System.out.println("Yes/No: ");
        Scanner sc = new Scanner(System.in);
        String choice = String.valueOf(sc.nextLine());
        if (choice.compareTo("Yes") == 0) {
            for (Movie mv: validMovies) {
                if (mv.seats > 0) {
                    System.out.println("Book Movie: "+mv.name+" success.");
                    mv.seats--;
                } else {
                    System.out.println("Book Movie: "+mv.name+" fail, there is no more available seats");
                }
            }
        } else {
            ShowAllCineplexMovie(cine);
        }
    }


    public static void MainEntry() {
        System.out.println("Welcome "+usr.name+" !\n****************Menu****************");
        System.out.println("Please select what you want:\n1: Search to book (By Movie)\n2: Search to book (By Cineplex)\n3: Quit\nPlease input your choice: ");
        Scanner sc = new Scanner(System.in);
        String choice = String.valueOf(sc.nextLine());
        while (choice.compareTo("1") != 0 && choice.compareTo("2") != 0 && choice.compareTo("3") != 0) {
            System.out.println("Please choose either 1 or 2.\nPlease input your choice:");
            choice = String.valueOf(sc.nextLine());
        }
        if (choice.compareTo("1") == 0) {
            System.out.println("Show All Movies");
            ShowAllMovies();
        } else if (choice.compareTo("2") == 0) {
            System.out.println("Show All Cineplex Theatres");
            ShowAllCineplex();
        }
        return;
    }

    public static void main(String[] args) {
        FetchInfoFromTxt();
        System.out.println("===========================================\n===== Welcome to movie booking system =====\n===========================================");
        UserLogin();
        MainEntry();
    }
}
