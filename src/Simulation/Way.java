package Simulation;

import Entity.*;
import WorldMap.*;

import java.util.*;

public class Way {
    Coordinates coordinates;
    int h, g, f;
    Way parent;

    Way(Coordinates coordinates, Way parent, int g, int h) {
        this.coordinates = coordinates;
        this.parent = parent;
        this.h = h;
        this.g = g;
        this.f = g + h;
    }

}

