public enum Level {
    LEVEL1(100, 40, 80, 40, 4, 4),
    LEVEL2(100, 50, 80, 50, 5, 5),
    LEVEL3(150, 60, 80, 60, 7, 6);

    private int targetDelay;
    private int targetRandom;
    private int enemyDelay;
    private int enemyRandom;
    private int enemySpeed;
    private int targetSpeed;

    Level(int td, int tr, int ed, int er, int es, int ts){
        targetDelay = td;
        targetRandom = tr;
        enemyDelay = ed;
        enemyRandom = er;
        enemySpeed = es;
        targetSpeed = ts;
    }

    public int getTargetDelay() {
        return targetDelay;
    }

    public int getTargetRandom() {
        return targetRandom;
    }

    public int getEnemyDelay() {
        return enemyDelay;
    }

    public int getEnemyRandom() {
        return enemyRandom;
    }

    public int getEnemySpeed() {
        return enemySpeed;
    }

    public int getTargetSpeed() {
        return targetSpeed;
    }


    static void init() {
        values(); // calls the constructor for all the elements
    }
}

