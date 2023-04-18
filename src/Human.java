import java.util.ArrayList;

public class Human
{
    private final int personId;
    private final String name;
    private final int age;
    private final int calorieNeed;
    private int calorieTaken = 0;
    private int calorieBurned = 0;

    public Human(int personId, String name, String gender, int weight, int height, int dateOfBirth)
    {
        this.personId = personId;
        this.name = name;
        this.age = 2022 - dateOfBirth;
        this.calorieNeed = dailyCalorieNeed(weight, height, age, gender);
    }
    public static int dailyCalorieNeed(int weight, int height, int age, String gender)
    {
        float calorie;
        if (gender.equals("male"))
        {
            calorie = (float) (66 + (13.75 * weight) + (5 * height) - (6.8 * age));
        }
        else
        {
            calorie = (float) (665 + (9.6 * weight) + (1.7 * height) - (4.7 * age));
        }
        return Math.round(calorie);
    }
    public static Human findObject(int personId) {
        Human human = null;
        for (int i = 0; i < FileOperation.humans.size(); i++) {
            if (FileOperation.humans.get(i).personId == personId) {
                human = FileOperation.humans.get(i);
                return human;
            }
        }
        return human;
    }
    public String printHuman()
    {
        int result = Math.round((this.calorieNeed - this.calorieTaken + this.calorieBurned));
        if (result > 0)
        {
            return (this.name + "\t" + this.age + "\t" + this.calorieNeed + "kcal\t" + this.calorieTaken + "kcal\t" + this.calorieBurned
                    + "kcal\t" + -result + "kcal\n");
        }
        else if (result == 0)
        {
            return (this.name + "\t" + this.age + "\t" + this.calorieNeed + "kcal\t" + this.calorieTaken + "kcal\t" + this.calorieBurned
                    + "kcal\t" + result + "kcal \n");
        }
        else
        {
            return (this.name + "\t" + this.age + "\t" + this.calorieNeed + "kcal\t" + this.calorieTaken + "kcal\t" + this.calorieBurned
                    + "kcal\t" + "+" + -result + "kcal\n");
        }
    }
    public static ArrayList<String> printWarn(ArrayList<Human> listhumans)
    {
        ArrayList<String> printList = new ArrayList<>();
        int calorieWarn = 0;
        for (Human human : listhumans) {
            if (human.calorieNeed - human.calorieTaken + human.calorieBurned < 0) {
                printList.add(human.printHuman());
                calorieWarn++;
            }
        }
        if (calorieWarn == 0)
        {
            printList.add("there\tis\tno\tsuch\tperson\n");
        }
        return printList;
    }

    public int getCalorieTaken() {return calorieTaken;}
    public void setCalorieTaken(int calorieTaken) {this.calorieTaken = calorieTaken;}
    public int getCalorieBurned() {return calorieBurned;}
    public void setCalorieBurned(int calorieBurned) {this.calorieBurned = calorieBurned;}

}
