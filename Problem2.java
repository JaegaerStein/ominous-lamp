import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Problem2 {
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        int height = in.nextInt();
        int width = in.nextInt();

        String[][] map = new String[height][width];

        for (int i = 0; i < height; i++){
            for (int d = 0; d < width; d++){
                map[i][d] = "O";
            }
        }
        in.nextLine();
        String directions = in.nextLine();
        String[] pairs = directions.split(" ");
        for (int i = 0; i < pairs.length; i ++){
            String[] coordinates = pairs[i].split(",");
            int x = parseInt(coordinates[0]);
            int y = parseInt(coordinates[1]);
            map[x][y] = "X";
        }
        for (int i = 0; i < height; i++){
            for (int d = 0; d < width; d++){
                if (map[i][d].equals("O")){
                    map[i][d] = in.next();
                }
            }
        }
        
        int numberOfBottles = in.nextInt();

        ArrayList<Bottle> bottle = new ArrayList<>();

        for (int i = 0; i < numberOfBottles; i++){

            int x = in.nextInt();
            int y = in.nextInt();
            String bottleName = in.nextLine().trim();
            String message = in.nextLine();
            bottle.add(new Bottle(bottleName, message, x, y));
            System.out.println(bottleName + ": starting at (" + x + "," + y + ")");
        }
        int moveCount = 0;


        while(true) {
            boolean bottleLanded = true;

            for (Bottle o : bottle) {
                int x = o.getX();
                int y = o.getY();
                String position = map[x][y];
                System.out.print(moveCount + o.toString());
                if (position.equals("X")) {
                    System.out.println("LANDED!");
                    System.out.println("<<" + o.getMessage() + ">>");
                } else {
                    System.out.println("In ocean, current taking it " + position + ".");
                    bottleLanded = false;
                }
                if (position.equals("N")) {
                    o.moveNorth(o.getX());
                }
                if (position.equals("S")) {
                    o.moveSouth(o.getX());
                }
                if (position.equals("E")) {
                    o.moveEast(o.getY());
                }
                if (position.equals("W")) {
                    o.moveWest(o.getY());
                }
            }
            moveCount++;
            if (bottleLanded) {
                break;
            }
        }
    }
    public static class Bottle{

        private int x;
        private int y;
        private String name;
        private String message;

        public Bottle(String name, String message, int x, int y){
            this.name = name;
            this.message = message;
            this.x = x;
            this.y = y;
        }
        public String getName(){
            return name;
        }
        public String getMessage(){
            return message;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        public void setName(String name){
            this.name = name;
        }
        public void setMessage(String message){
            this.message = message;
        }
        public void moveEast(int y){
            this.y = y--;
        }
        public void moveWest(int y){
            this.y = y++;
        }
        public void moveNorth(int x){
            this.x = x++;
        }
        public void moveSouth(int x){
            this.x = x--;
        }
        public String toString(){
            return ": " + name + " at " + "(" + x + "," + y + "): ";
        }
    }
}