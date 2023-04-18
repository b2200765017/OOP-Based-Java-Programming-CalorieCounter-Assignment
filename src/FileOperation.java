import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperation
{
    static ArrayList<Human> humans = new ArrayList<>();
    static ArrayList<Food> foods = new ArrayList<>();
    static ArrayList<Sport> sports = new ArrayList<>();
    static ArrayList<Human> listhumans = new ArrayList<>();

    public static void constantFiles() throws FileNotFoundException
    {
        File PEOPLE = new File("people.txt");
        File SPORT = new File("sport.txt");
        File FOOD = new File("food.txt");

        Scanner scanHuman = new Scanner(PEOPLE);
        while (scanHuman.hasNext())
        {
            String[] arr = scanHuman.nextLine().split("\t", 6);
            int personId = Integer.parseInt(arr[0]);
            String name = arr[1];
            String gender = arr[2];
            int weight = Integer.parseInt(arr[3]);
            int height = Integer.parseInt(arr[4]);
            int dateOfBirth = Integer.parseInt(arr[5]);
            Human newHuman = new Human(personId, name, gender, weight, height, dateOfBirth);
            humans.add(newHuman);
        }
        scanHuman.close();

        Scanner scanFood = new Scanner(FOOD);
        while (scanFood.hasNext())
        {
            String[] arr = scanFood.nextLine().split("\t", 3);
            int foodId = Integer.parseInt(arr[0]);
            String name = arr[1];
            int calorie = Integer.parseInt(arr[2]);
            Food newfood = new Food(foodId, name, calorie);
            foods.add(newfood);
        }
        scanFood.close();

        Scanner scanSport = new Scanner(SPORT);
        while (scanSport.hasNext())
        {
            String[] arr = scanSport.nextLine().split("\t", 3);
            int sportId = Integer.parseInt(arr[0]);
            String name = arr[1];
            int calorieBurned = Integer.parseInt(arr[2]);
            Sport newSport = new Sport(sportId, name, calorieBurned);
            sports.add(newSport);
        }
        scanSport.close();
    }
    public static void commands(String commandFile) throws IOException
    {
        FileWriter fileWriter = new FileWriter("monitoring.txt");
        File file = new File(commandFile);
        Scanner commands = new Scanner(file);
        while (commands.hasNext())
        {
            String command = commands.nextLine();
            if (command.startsWith("1"))
            {
                String[] arr = command.split("\t", 3);
                int personId = Integer.parseInt(arr[0]);
                Human person = Human.findObject(personId);
                if (!listhumans.contains(person))
                {
                    listhumans.add(person);
                }
                if (arr[1].startsWith("1"))
                {
                    int f = Integer.parseInt(arr[1]);
                    int amount = Integer.parseInt(arr[2]); // food eaten
                    Food food = Food.findObject(f);
                    int calorie = food.getCalorie() * amount;
                    person.setCalorieTaken(calorie + person.getCalorieTaken());
                    fileWriter.write(Food.printFood(food.getname(), personId, calorie));

                }
                else
                {
                    int s = Integer.parseInt(arr[1]);
                    int amount = Integer.parseInt(arr[2]); // sport done
                    Sport sport = Sport.findObject(s);
                    int calorie = sport.getCalorieBurn() * amount / 60;
                    person.setCalorieBurned(calorie + person.getCalorieBurned());
                    fileWriter.write(Sport.printSport(sport.getName(), personId, calorie));
                }
            }
            else
            {
                if (command.substring(5).startsWith("("))
                {
                    int personId = Integer.parseInt(command.substring(6, 11)); // print id of this person
                    Human person = Human.findObject(personId);
                    fileWriter.write(person.printHuman());
                }
                else if (command.substring(5).startsWith("L"))
                {
                    for (Human listhuman : listhumans)
                    {
                        fileWriter.write(listhuman.printHuman());
                    }
                }
                else
                {

                    ArrayList<String> printList = (Human.printWarn(listhumans));
                    for (String i: printList)
                    {
                        fileWriter.write(i);
                    }
                    // It should print the person who take more than daily calorie
                }
            }
            if (commands.hasNext())
            {
                fileWriter.write("***************\n");
            }
        }
        fileWriter.close();
    }
}
