import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public class Todo {

  private static final Path PATH = Paths.get("tasklist.txt");

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      printUsage();
      System.exit(1);
    }
    if ("-l".equals(args[0])) {
      listTasks();
    } else if ("-a".equals(args[0]) && args.length == 2) {
      Files.write(PATH, ("\n" + args[1]).getBytes(), APPEND);
    } else if ("-a".equals(args[0]) && args.length == 1) {
      System.out.println("Unable to add: no task provided");
    } else if ("-r".equals(args[0]) && args.length == 2) {
      removeLine(Integer.parseInt(args[1]) - 1);
    }


  }

  private static void removeLine(int num) throws IOException {
    List<String> lines = Files.readAllLines(PATH);
    lines.remove(num);
    Files.write(PATH, lines, StandardOpenOption.TRUNCATE_EXISTING);
  }

  private static void listTasks() throws IOException {
    List<String> lines = Files.readAllLines(PATH);
    if (lines.isEmpty()) {
      System.out.println("No todos for today! :)");
    } else {
      // AtomicInteger i = new AtomicInteger(1);
      // lines.forEach(s -> System.out.println(i.getAndIncrement() + " - " + s));
      for (int i = 0; i < lines.size(); i++) {
        System.out.println((i+1) + " - " + lines.get(i));
      }
    }
  }

  private static void printUsage() {
    System.out.println("\nCommand Line Todo application");
    System.out.println("=============================\n");
    System.out.println("Command line arguments:");
    System.out.println("\t-l Lists all the tasks");
    System.out.println("\t-a Adds a new task");
    System.out.println("\t-r Removes an task");
    System.out.println("\t-c Completes an task");
  }

}
