package main.map;

import main.entity.Entity;
import main.entity.EntityAlien;
import main.entity.EntityLaser;
import main.entity.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class ScreenMap {
    private List<EntityPlayer> playerList = new ArrayList<>();
    private List<EntityLaser> laserList = new ArrayList<>();
    private List<EntityAlien> alienList = new ArrayList<>();

    public synchronized List<Entity> getEntityList() {
        ArrayList<Entity> entityList = new ArrayList<>(laserList);
        entityList.addAll(playerList);
        entityList.addAll(alienList);
        return entityList;
    }

    public synchronized List<EntityPlayer> getPlayerList() {
        return new ArrayList<>(playerList);
    }

    public synchronized List<EntityLaser> getLaserList() {
        return new ArrayList<>(laserList);
    }

    public synchronized List<EntityAlien> getAlienList() {
        return new ArrayList<>(alienList);
    }

    public synchronized List<Entity> getNonLaserList() {
        ArrayList<Entity> returnList = new ArrayList<>(alienList);
        returnList.addAll(playerList);
        return returnList;
    }

    public synchronized void addEntity(Entity ent) {
        if (ent instanceof EntityLaser) {
            laserList.add((EntityLaser) ent);
        } else if (ent instanceof EntityAlien) {
            alienList.add((EntityAlien) ent);
        } else if (ent instanceof EntityPlayer) {
            playerList.add((EntityPlayer) ent);
        }
    }

    public synchronized void destroyEntity(Entity ent) {
        if (ent instanceof EntityLaser) {
            laserList.remove(ent);
        } else if (ent instanceof EntityAlien) {
            alienList.remove(ent);
        } else if (ent instanceof EntityPlayer) {
            playerList.remove(ent);
        }
    }
}
