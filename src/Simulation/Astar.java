package Simulation;

import Entity.*;
import WorldMap.*;

import java.util.*;

public class Astar{
   WorldMap worldMap;
   Entity entity;
   final int HEIGHT;
    final int WIDTH;
   public Astar(WorldMap worldMap, Entity entity){
       this.worldMap= worldMap;
       this.entity=entity;
       this.HEIGHT = worldMap.getHeight();
       this.WIDTH = worldMap.getWidth();
   }

public boolean withinMap(Coordinates coordinates){
    return coordinates.high() < HEIGHT && coordinates.width() < WIDTH;
}
public int searchH(Coordinates myCoordinates, Coordinates finishCoordinates){
    return Math.abs(myCoordinates.high()- finishCoordinates.high())+Math.abs(myCoordinates.width()- finishCoordinates.width());
}
public ArrayList<Coordinates> getNeighbors(Coordinates coordinates){
    int h = coordinates.high();
    int w = coordinates.width();
    ArrayList<Coordinates>neighbors = new ArrayList<>();
    List<Coordinates> potentialNeighbor=new ArrayList<>();
    potentialNeighbor.add(new Coordinates(h,w+1));
    potentialNeighbor.add(new Coordinates(h,w-1));
    potentialNeighbor.add(new Coordinates(h+1,w));
    potentialNeighbor.add(new Coordinates(h-1,w));
    for (Coordinates neighbor : potentialNeighbor) {
        if (withinMap(neighbor)) {
            neighbors.add(neighbor);
        }
    }

    return neighbors;
}

public ArrayList<Entity> typeFood(Entity entity){
    ArrayList<Entity>typeFoodList = new ArrayList<>();
    if (entity instanceof Herbivore){
       for(Entity e: worldMap.getEntityCoordinates().keySet()){
           if(e instanceof Grass){
               typeFoodList.add(e);
           }

           }return typeFoodList;
       }if (entity instanceof Predator){
        for(Entity e:worldMap.getEntityCoordinates().keySet()){
            if(e instanceof Herbivore){
                typeFoodList.add(e);
            }

        }
        return typeFoodList;
    }return typeFoodList;
}
public Coordinates typeFoodCoordinates(Entity entity){
       HashMap<Integer,Entity> minSteps = new HashMap<>();
    for (Entity e:typeFood(entity)){
        int steps =Math.abs(worldMap.getEntityCoordinates().get(entity).high()-worldMap.getEntityCoordinates().get(e).high())+Math.abs(worldMap.getEntityCoordinates().get(entity).width()-worldMap.getEntityCoordinates().get(e).width());
        minSteps.put(steps,e);
    }
    int min = 10000000;
    for(int i:minSteps.keySet()){
        if (i < min ){
            min = i;
        }
    }
    return worldMap.getEntityCoordinates().get(minSteps.get(min));
}






public List<Coordinates>A(){
Coordinates start = worldMap.getEntityCoordinates().get(entity);
Coordinates finish = typeFoodCoordinates(entity);
Map<Coordinates,Way> openList=new HashMap<>();
Map<Coordinates,Way>closeList=new HashMap<>();
for (Entity e:worldMap.getEntityCoordinates().keySet()) {
    if (e instanceof Items) {
        closeList.put(worldMap.getEntityCoordinates().get(e), new Way(worldMap.getEntityCoordinates().get(e), null, 0, searchH(worldMap.getEntityCoordinates().get(e), finish)));
    }
}

openList.put(start,new Way(start,null,0,searchH(start,finish)));

while (!openList.isEmpty()){
    Way myWayNow = null;
    for (Way w:openList.values()) {
        if (myWayNow == null || w.f < myWayNow.f) {
            myWayNow = w;
        }
    }
        if (myWayNow.coordinates.equals(finish)){
            List<Coordinates>stepsCoordinates = new ArrayList<>();
            while (myWayNow!=null){
              stepsCoordinates.add(myWayNow.coordinates);
              myWayNow=myWayNow.parent;
            }
            Collections.reverse(stepsCoordinates);

            return stepsCoordinates;
        }
    openList.remove(myWayNow.coordinates);
        closeList.put(myWayNow.coordinates,myWayNow);

        for (Coordinates neighbor:getNeighbors(myWayNow.coordinates)){
            if (closeList.containsKey(neighbor)){
                continue;
            }
            int g = myWayNow.g+1;
            Way myWayNext = openList.get(neighbor);
            if (myWayNext==null) {
                openList.put(neighbor,new Way(neighbor,myWayNow,g,searchH(neighbor,finish)));
            } else if (g< myWayNext.g) {
                myWayNext.parent=myWayNow;
                myWayNext.g = g;
                myWayNext.f = g + searchH(neighbor,finish);


            }
        }



    }
return null;
}
}
