package Simulation;

import Entity.*;
import WorldMap.*;

import java.util.*;

public class Actions {
private int enterHeight(){
    System.out.print("Введите высоту поля (мин 10 макс 100):");
    Scanner height = new Scanner(System.in);
   if (height.hasNextInt()){
       int h = height.nextInt();
       if (h<10||h>100){
           System.out.println("Введите корректное число!");
           enterHeight();
       }else return h;
   }else {
       System.out.println("Введите корректное число!");
       enterHeight();
   }return 0;
}

private int enterWidth(){
    System.out.print("Введите длину поля (мин 10 макс 100):");
    Scanner width = new Scanner(System.in);
    if (width.hasNextInt()){
        int w = width.nextInt();
        if (w<10||w>100){
            System.out.println("Введите корректное число!");
            enterWidth();
        }else return w;
    }else {
        System.out.println("Введите корректное число!");
        enterWidth();
    }return 0;
}
    WorldMap worldMap = new WorldMap(enterHeight(),enterWidth());
    int square = worldMap.getHeight()*worldMap.getWidth();
    int countCow = square/50;
    int countWolf = square/100;
    int countGrass = square/20;
    int countRock = square/25;
    int countTree = square/25;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public void startingGame(){
        for(int numCow = 0 ;numCow<countCow;numCow++){
            worldMap.put(new Cow());
        }
        for (int numWolf = 0;numWolf<countWolf;numWolf++){
            worldMap.put(new Wolf());
        }
        for (int numGrass = 0;numGrass<countGrass;numGrass++){
            worldMap.put(new Grass());
        }
        for (int numRock=0;numRock<countRock;numRock++){
            worldMap.put(new Rock());
        }
        for (int numTree = 0; numTree<countTree;numTree++){
            worldMap.put(new Tree());
        }
    }


    public void oneStepSimulation(){
        Renderer w = new Renderer();

        int countCow = 0;
        int countGrass =0;
        List<Entity> entity = new ArrayList<>(worldMap.getEntityCoordinates().keySet());
        for (Entity entity1 : entity) {
            if (worldMap.getEntityCoordinates().containsKey(entity1)){
            if (entity1 instanceof Creature) {
                ((Creature) entity1).makeMove(worldMap);

            }
        }
        }
        for (Entity e:worldMap.getEntityCoordinates().keySet()){
            if(e instanceof Cow){
                countCow++;
            }if (e instanceof Grass){
                countGrass++;
            }
        }
        if (countGrass<this.countGrass){
            for (int i = countGrass;i<this.countGrass;i++){
                worldMap.put(new Grass());
            }
        }
        if (countCow<this.countCow){
            for (int i = countCow;i<this.countCow;i++){
                worldMap.put(new Cow());
            }
        } statistika();
        w.printBord(worldMap);

    }
private int numStepsSimulations = 1;
    private void statistika() {
        System.out.println("Steps "+ numStepsSimulations);
        numStepsSimulations++;
        for (Entity e : worldMap.getEntityCoordinates().keySet()){
            if (e instanceof Cow){
                System.out.print("Cow: ");
                System.out.print("Hp: "+((Cow) e).hp + " ");
                System.out.println("Coordinates: " + worldMap.getEntityCoordinates().get(e));
            } else if (e instanceof Wolf) {
                System.out.print("Wolf: ");
                System.out.println("Coordinates: " + worldMap.getEntityCoordinates().get(e));
            }
        }
    }

    public void startSimulation() {

        while (running){
            if (!paused){
                oneStepSimulation();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
   private void pause(){
        paused=true;
        System.out.println("Пауза");
    }
    private void stop(){
        running=false;
        System.out.println("Завершение");
    }
    private void resume(){
        paused=false;
        System.out.println("Старт");
    }
    public void commanding(){
        startingGame();
        System.out.println("Команды: ");
        System.out.print("Запустить симуляцию: play; Сделать один ход: one; Cелать паузу: pause; Возобновить симуляцию: resume; Завершить: stop; ");
        Scanner scanner = new Scanner(System.in);
        Thread thread = new Thread(this::startSimulation);
        boolean a = true;
        while (a){
            String command = scanner.nextLine();
            switch (command){
                case "play":
                    thread.start();
                    break;
                case "pause":
                    pause();
                    break;
                case "resume":
                    resume();
                    break;
                case "stop":
                    stop();
                    a = false;
                    break;
                case "one":
                 oneStepSimulation();
                    break;
                default:
                    System.out.println("hz");
            }
        }
    }



}


