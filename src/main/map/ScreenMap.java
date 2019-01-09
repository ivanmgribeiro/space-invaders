package main.map;

import main.entity.Entity;
import main.entity.EntityAlien;
import main.entity.EntityLaser;

import java.util.ArrayList;
import java.util.List;

public class ScreenMap {
    private List<Entity> entityList = new ArrayList<>();

    public List<Entity> getEntityList() {
        return new ArrayList<Entity>(entityList);
    }
    public void addEntity(Entity ent) {
        entityList.add(ent);
    }
}
