package WorldMap;
import Entity.*;


public class Renderer {

    public static final String COW = "\uD83D\uDC04";
    public static final String WOLF = "\uD83D\uDC3A";
    public static final String GRASS = "\uD83C\uDF3F";
    public static final String ROCK = "\uD83D\uDDFF";
    public static final String TREE = "\uD83C\uDF33";
    public static final String FREE_CELL = "* ";
    public static final String LINE = "=======================================";


    public void printBord(WorldMap worldMap){
        int height = worldMap.getHeight();
        int width = worldMap.getWidth();
        System.out.println(LINE);
        for (int h=0;h<height;h++){
            System.out.println(" ");
            for (int w = 0;w<width;w++){
                if (worldMap.whoHere(new Coordinates(h,w)) instanceof Cow){
                    System.out.print(COW);
                } else if (worldMap.whoHere(new Coordinates(h,w)) instanceof Wolf) {
                    System.out.print(WOLF);
                } else if (worldMap.whoHere(new Coordinates(h,w)) instanceof Grass) {
                    System.out.print(GRASS);
                } else if (worldMap.whoHere(new Coordinates(h,w)) instanceof Rock) {
                    System.out.print(ROCK);
                } else if (worldMap.whoHere(new Coordinates(h,w)) instanceof Tree) {
                    System.out.print(TREE);
                }else {
                    System.out.print(FREE_CELL);
                }

            }
        }
        System.out.println(" ");
    }
}
