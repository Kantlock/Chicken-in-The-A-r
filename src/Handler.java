import java.util.ArrayList;
import java.util.Iterator;

public class Handler {
    public ArrayList<Target> targets;
    public ArrayList<Egg> eggs;
    public ArrayList<Enemy> enemies;
    public ArrayList<Cloud> clouds;

    public Handler(ArrayList<Target> t, ArrayList<Egg> e, ArrayList<Enemy> en, ArrayList<Cloud> cl){
        targets = t;
        eggs = e;
        enemies = en;
        clouds = cl;
    }

    public void move(){
        for(Target t: targets) t.move();
        for(Egg e: eggs) e.move();
        for(Enemy en: enemies) en.move();
        for(Cloud c: clouds) c.move();
    }

    public void objectCleaner(){
        Iterator<Target> t = targets.iterator();
        Iterator<Egg> e = eggs.iterator();
        Iterator<Enemy> en = enemies.iterator();
        ArrayList<Target> temp1 = new ArrayList<>();
        ArrayList<Egg> temp2 = new ArrayList<>();
        ArrayList<Enemy> temp3 = new ArrayList<>();

        while(t.hasNext()){
            Target target = t.next();
            if(target.y < 0) temp1.add(target);
        }

        while(e.hasNext()){
            Egg egg = e.next();
            if(egg.x < 0 || egg.x > 1200) temp2.add(egg);
        }

        while(en.hasNext()){
            Enemy enemy = en.next();
            if(enemy.y < 0) {
                temp3.add(enemy);
            }
        }

        targets.removeAll(temp1);
        eggs.removeAll(temp2);
        enemies.removeAll(temp3);
    }

}
