import java.util.ArrayList;
import java.util.Random;

public class Game{
    private final int CLOUD_DELAY = 25;
    private int targetDelay;
    private int enemyDelay;
    private int cloudDelay;
    public int leftHP;
    public int score;

    private Handler handler;
    private Level level;
    public Chicken chicken;

    public Boolean gameOver;

    public ArrayList<Target> targets;
    public ArrayList<Enemy> enemies;
    public ArrayList<Cloud> clouds;

    public Game(Level level) {
        this.level = level;
        gameOver = false;
        chicken = new Chicken();
        enemies = new ArrayList<>();
        targets = new ArrayList<>();
        clouds = new ArrayList<>();
        targetDelay = level.getTargetDelay();
        enemyDelay = level.getEnemyDelay();
        cloudDelay = CLOUD_DELAY;
        leftHP = 3;
        score = 0;
    }

    public void update() {
        handler = new Handler(targets, chicken.eggs, enemies, clouds);
        createTarget();
        createEnemy();
        createCloud();
        chicken.update();
        handler.move();
        handler.objectCleaner();
        collisionTestEgg();
        collisionTestChicken();

        if(leftHP <= 0) gameOver = true;
    }

    public void createEnemy(){
        enemyDelay--;

        if(enemyDelay < 0) {
            Random random = new Random();
            int chance = random.nextInt(100);
            enemyDelay = level.getEnemyDelay();

            if(chance < level.getEnemyRandom()) {
                Random coordinate = new Random();
                int x = coordinate.nextInt(850) + 200;
                Enemy enemy = new Enemy(x);
                enemy.setVelY(level.getEnemySpeed());
                enemies.add(enemy);
            }
        }
    }

    public void createTarget(){
        targetDelay--;

        if(targetDelay < 0) {
            Random random = new Random();
            int number = random.nextInt(level.getTargetRandom());
            targetDelay = level.getTargetDelay();

            if(number % 6 == 0) {
                Target target = new Target("Right","1");
                target.setVelY(level.getTargetSpeed());
                targets.add(target);
            }
            else if(number % 6 == 1) {
                Target target = new Target("Right","2");
                target.setVelY(level.getTargetSpeed());
                targets.add(target);
            }
            else if(number % 6 == 2) {
                Target target = new Target("Right","3");
                target.setVelY(level.getTargetSpeed());
                targets.add(target);
            }
            else if(number % 6 == 3) {
                Target target = new Target("Left","1");
                target.setVelY(level.getTargetSpeed());
                targets.add(target);
            }
            else if(number % 6 == 4) {
                Target target = new Target("Left","2");
                target.setVelY(level.getTargetSpeed());
                targets.add(target);
            }
            else if(number % 6 == 5) {
                Target target = new Target("Left","3");
                target.setVelY(level.getTargetSpeed());
                targets.add(target);
            }
            
        }
    }

    public void createCloud(){
        cloudDelay--;

        if(cloudDelay < 0) {
            Random random = new Random();
            int chance = random.nextInt(100);
            cloudDelay = CLOUD_DELAY;

            if(chance < 30) {
                Random coordinate = new Random();
                int x = coordinate.nextInt(1000) + 50;
                clouds.add(new Cloud(x));
            }
        }
    }

    public void collisionTestEgg(){

        ArrayList<Target> temp1 = new ArrayList<>();
        ArrayList<Egg> temp2 = new ArrayList<>();

        for(Target target: targets){
            for(Egg egg: chicken.eggs){
                if(target.collisonCheck(egg)){
                    score += target.score;
                    SoundEffect.HIT.play();
                    temp1.add(target);
                    temp2.add(egg);
                }
            }
        }

        targets.removeAll(temp1);
        chicken.eggs.removeAll(temp2);
    }

    public void collisionTestChicken(){

        ArrayList<Enemy> temp = new ArrayList<>();

        for(Enemy enemy : enemies){
            if(chicken.collisonCheck(enemy)){
                SoundEffect.HIT.play();
                temp.add(enemy);
                leftHP--;
            }
        }

        enemies.removeAll(temp);
    }

}
