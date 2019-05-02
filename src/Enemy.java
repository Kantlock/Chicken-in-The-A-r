public class Enemy {
    private int velY;
    public int x;
    public int y;
    public Render render;

    public Enemy(int x){
        render = new Render("./media/cat3.png");

        this.x = x;
        y = 800;
    }

    public void move(){
        y -= velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
