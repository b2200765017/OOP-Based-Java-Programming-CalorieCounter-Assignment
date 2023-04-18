import java.io.IOException;
public class Main
{
    public static void main(String[] args) throws IOException
    {
        FileOperation.constantFiles();
        FileOperation.commands(args[0]);
    }
}
