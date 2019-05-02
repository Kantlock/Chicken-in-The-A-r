public class Cloud {
    public int x;
    public int y;
    private int velY;
    public Render render;

    public Cloud(int x){
        render = new Render("./media/cloud.png");
        this.x = x;

        y = 800;
        velY = 3;
    }
    public void move() {
        y -= velY;
    }

}