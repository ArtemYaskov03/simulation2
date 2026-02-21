package Entity;

import Simulation.*;
import WorldMap.*;

import java.util.List;

public class Predator extends Creature {
    private int damage;

    @Override
    public void makeMove(WorldMap worldMap) {
        Astar a=new Astar(worldMap,this);
        List<Coordinates> steps=a.A();
        Coordinates newCoord = steps.get(Math.min(this.speed,steps.size()-1));
        if (worldMap.whoHere(steps.getLast())instanceof Herbivore && worldMap.whoHere(newCoord)==null) {
        worldMap.getEntityCoordinates().remove(this);
        worldMap.getEntityCoordinates().put(this,newCoord);
        } else if (((Creature) worldMap.whoHere(steps.getLast())).hp <= this.damage) {
            worldMap.getEntityCoordinates().remove(worldMap.whoHere(steps.getLast()));
            worldMap.getEntityCoordinates().remove(this);
            worldMap.getEntityCoordinates().put(this, steps.getLast());
        } else {
                ((Herbivore) worldMap.whoHere(steps.getLast())).newHp(hit(((Creature) worldMap.whoHere(steps.getLast()))));
        }


    }
    public int hit( Creature food){
        return food.hp-this.damage;

    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
