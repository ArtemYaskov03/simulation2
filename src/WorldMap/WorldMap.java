package WorldMap;

import Entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorldMap {
     private final int height;
     private final int width;
    private final Map<Entity, Coordinates>entityCoordinates=new HashMap<>();



public WorldMap(int height,int width){
    this.height=height;
    this.width=width;
}

public void put(Entity entity){
    Random random = new Random();
    Coordinates coordinatesEntity = new Coordinates(random.nextInt(getHeight()), random.nextInt(getWidth()));
    if(entityCoordinates.containsValue(coordinatesEntity)){
        put(entity);
    }else {
    this.entityCoordinates.put(entity,coordinatesEntity);}

}

public Entity whoHere(Coordinates entityCoordinates){
    for (Entity e : getEntityCoordinates().keySet()){
        if(entityCoordinates.equals(getEntityCoordinates().get(e))){
return e;
        }
    }return null;
}

public Map<Entity, Coordinates>getEntityCoordinates(){
    return this.entityCoordinates;
}

    public int getHeight(){
        return height;
    }

    public int getWidth() {
        return width;
    }
}

