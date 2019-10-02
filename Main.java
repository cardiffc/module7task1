import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();
        Collections.sort(staff, (o1, o2) -> {
            if (o1.getName().compareTo(o2.getName()) <= 0)
            {
                return (o1.getSalary().compareTo(o2.getSalary()) > 0) ? 1 : -1;
            }
            if (o1.getName().compareTo(o2.getName()) > 0)
            {
                return (o1.getSalary().compareTo(o2.getSalary()) >= 0) ? 1 : -1;
            }
            return 0;
        });
        for (Employee emoployees : staff)
        {
            System.out.println(emoployees);
        }
    }


    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}