import java.util.*;

public class Elevator {

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.run();
    }

    Scanner sc = new Scanner(System.in);
    final int topFloor = 12;
    final int ground = 0;
    int currentFloor = 0;
    List<Integer> requiredFloorS = new ArrayList<Integer>();

    void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {
        }
    }

    void goUp(int closestFloor) {
        int startingFloor = currentFloor;
        while (currentFloor < closestFloor) {
            currentFloor++;
            System.out.println("Elevator is going up.    from floor: " + startingFloor + "  | current floor: " + currentFloor + "|    to floor: " + closestFloor);
            pause(500);
        }
    }

    void goDown(int closestFloor) {
        while (currentFloor > closestFloor) {
            currentFloor--;
            System.out.println("Elevator is going down.    CF: " + currentFloor + "  |   RF: " + closestFloor);
            pause(500);
        }
    }

    public int closestFloor() {
        int closestFloor = 0;
        int compare = Integer.MAX_VALUE;
        for (int i = 0; i < requiredFloorS.size(); i++) {
            if (Math.abs(currentFloor - requiredFloorS.get(i)) < compare) {
                compare = Math.abs(currentFloor - requiredFloorS.get(i));
                closestFloor = requiredFloorS.get(i);
            } else {
                compare = Math.abs(compare - requiredFloorS.get(i));
            }
        }
        return closestFloor;
    }

    void run() {
        int counter = 0;
        int upcomingFloor;

        System.out.println("Current Floor is: " + currentFloor + "       Please enter destination floor: ");
        try {
            while (true) {
                int nextFloor = sc.nextInt();
                if (nextFloor == -1) {
                    System.out.println("Selected floors are: " + requiredFloorS);
                    break;
                }
                if (nextFloor < ground || nextFloor > topFloor || nextFloor == currentFloor) {
                    System.out.println("You have selected floor that doesnt exist or you are already on it, please select different floor");
                } else {
                    requiredFloorS.add(nextFloor);
                    counter++;
                }
                if(counter >= 3 ){
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("You entered unfamiliar symbols or something unexpected happend. Please enter something from range of integers" +
                    " 0-12 or -1 to end sequence of floors after you restart the application.");
            System.exit(0);
        }

        for (int i = 0; i < counter; i++) {
            System.out.println("Doors are closing....");
            pause(1000);

            upcomingFloor = closestFloor();
            if (upcomingFloor < currentFloor) {
                goDown(upcomingFloor);
            } else if (upcomingFloor > currentFloor) {
                goUp(upcomingFloor);
            }
            requiredFloorS.removeAll(Arrays.asList(upcomingFloor));
            System.out.println("Doors are opening...");
        }
        run();
    }
}