public class Sport {
    private final int id;
    private final String name;
    private final int calorieBurn;

    public Sport(int id, String name, int calorieBurned) {
        this.id = id;
        this.name = name;
        this.calorieBurn = calorieBurned;
    }
    public static Sport findObject(int sportId)
    {
        Sport sport = null;
        for (int i = 0; i < FileOperation.sports.size(); i++)
        {
            if (FileOperation.sports.get(i).id == sportId)
            {
                sport = FileOperation.sports.get(i);
                return sport;
            }
        }
        return sport;
    }

    public static String printSport(String sport, int personId, int calorie)
    {
        return (personId + "\t" + "has\tburned" + "\t" + calorie + "kcal" + "\t" + "thanks"  + "\t" +
                "to" + "\t" + sport + "\n");
    }

    public String getName() {return name;}
    public int getCalorieBurn() {return calorieBurn;}
}
