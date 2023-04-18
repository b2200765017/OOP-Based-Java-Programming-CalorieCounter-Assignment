public class Food
{
    private final int foodId;
    private final String name;
    private final int calorie;

    public Food(int id, String name, int calorie)
    {
        this.foodId = id;
        this.name = name;
        this.calorie = calorie;
    }
    public static Food findObject(int foodId) {
        Food food = null;
        for (int i = 0; i < FileOperation.foods.size(); i++) {
            if (FileOperation.foods.get(i).foodId == foodId) {
                food = FileOperation.foods.get(i);
                return food;
            }
        }
        return food;
    }
    public static String printFood(String food, int personId, int calorie)
    {
        return (personId + "\t" + "has\ttaken" + "\t" + calorie + "kcal" + "\t" + "from"  + "\t" + food + "\n");
    }
    public String getname() {return name;}
    public int getCalorie() {return calorie;}
}
