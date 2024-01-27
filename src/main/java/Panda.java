import java.util.Scanner;

public class Panda {

    private static boolean running = false;
    private static Task[] tlist;
    private static int idx = 0;

    private static void startUp() {
        System.out.println(
            "Hello! I'm Panda\n" + 
            "What can I do for you?"
        );
        running = true;
        tlist = new Task[100];
    }

    private static void shutDown() {
        System.out.println(
            "Bye. Hope to see you again soon!"
        );
    }

    private static void printTlist() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < idx; i++) {
            System.out.println((i + 1) + "." + tlist[i]);
        }
    }

    private static void comm (String userInput) throws PandaException {
        if(userInput.equals("bye")) {
            running = false;
            return;
        }
        if(userInput.equals(("list"))) {
            Panda.printTlist();
            return;
        }
        if(userInput.split(" ")[0].equals("mark")) {
            int target = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            if(target >= idx) {
                throw new OutOfBoundsException();
            }
            tlist[target].mark();
            System.out.println("Nice! I've marked this task as done:\n  " + tlist[target]);
            return;
        }
        if(userInput.split(" ")[0].equals("unmark")) {
            int target = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            if(target >= idx) {
                throw new OutOfBoundsException();
            }
            tlist[target].unmark();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tlist[target]);
            return;
        }
        if(userInput.split(" ")[0].equals("todo")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyTodoException();
            }
            tlist[idx] = new Todo(splitted[1].trim());
            idx = idx + 1;
            System.out.println("Got it. I've added this task:\n  " + tlist[idx - 1] + "\nNow you have " + idx + " tasks in the list.");
            return;
        }
        if(userInput.split(" ")[0].equals("deadline")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyDeadlineException("desc");
            }
            String[] args = splitted[1].split("/by");
            if(args.length < 2) {
                throw new EmptyDeadlineException("date");
            }
            tlist[idx] = new Deadline(args[0].trim(), args[1].trim());
            idx = idx + 1;
            System.out.println("Got it. I've added this task:\n  " + tlist[idx - 1] + "\nNow you have " + idx + " tasks in the list.");
            return;
        }
        if(userInput.split(" ")[0].equals("event")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyEventException("desc");
            }
            String[] args = splitted[1].split("/from");
            if(args.length < 2 || args[1].split("/to").length < 2) {
                throw new EmptyEventException("date");
            }
            tlist[idx] = new Event(args[0].trim(), args[1].split("/to")[0].trim(), args[1].split("/to")[1].trim());
            idx = idx + 1;
            System.out.println("Got it. I've added this task:\n  " + tlist[idx - 1] + "\nNow you have " + idx + " tasks in the list.");
            return;
        }
        throw new UnknownCommandException();
    }

    public static void main(String[] args) {
        Panda.startUp();
        Scanner myObj  = new Scanner(System.in);
        while(running) {
            System.out.print("> ");
            String userInput = myObj.nextLine();
            try {
                Panda.comm(userInput);
            }
            catch (PandaException e) {
                System.out.println(e.getMessage());
            }
        }
        myObj.close();
        Panda.shutDown();
    }
}
