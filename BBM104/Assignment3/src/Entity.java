import java.util.*;

public abstract class Entity {
    public final String ID;
    public final int AP;
    public final int MAXHP;
    public final int MAXMOVE;
    private int HP;
    private int[] coords;


    public Entity(String id, int ap, int hp, int maxmove) {
        ID = id;
        AP = ap;
        MAXHP = hp;
        this.HP = hp;
        MAXMOVE = maxmove;
    }


    public abstract void move(Entity[][] board, String[] moves) throws OutOfBoundaries;


    public abstract void attack(Entity[][] board);


    // Gets to Entity and returns the stronger one.
    public static Entity fightToDeath(Entity e1, Entity e2) {
        int damage = Math.min(e1.HP, e2.HP);
        e1.setHP(e1.getHP() - damage);
        e2.setHP(e2.getHP() - damage);
        if (e1.HP == e2.HP) return null;
        else return (e1.HP > e2.HP) ? e1 : e2;
    }


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if (HP > MAXHP) {
            this.HP = MAXHP;
        }
        else if (HP < 0) {
            this.HP = 0;
        }
        else {
            this.HP = HP;
        }
    }


    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }


    @Override
    public String toString() {
        return "Entity{" +
                "ID='" + ID + '\'' +
                ", AP=" + AP +
                ", MAXHP=" + MAXHP +
                ", MAXMOVE=" + MAXMOVE +
                ", HP=" + HP +
                ", coords=" + Arrays.toString(coords) +
                '}';
    }
}
