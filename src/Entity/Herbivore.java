package Entity;

import Simulation.*;
import WorldMap.*;

import java.util.List;

public class Herbivore extends Creature {

    @Override
    public void   makeMove(WorldMap worldMap) {
        Astar a = new Astar(worldMap, this);
        List<Coordinates> steps = a.A();
        Coordinates newCoord = steps.get(Math.min(this.speed,steps.size()-1));
    if (worldMap.whoHere(newCoord)==null&&steps.size()>this.speed){
        worldMap.getEntityCoordinates().remove(this);
        worldMap.getEntityCoordinates().put(this,newCoord);
    } else {
        worldMap.getEntityCoordinates().remove(worldMap.whoHere(steps.getLast()));
        worldMap.getEntityCoordinates().remove(this);
        worldMap.getEntityCoordinates().put(this,steps.getLast());

    }

    }
    public void newHp(int hp){
        this.hp=hp;
    }
    }

