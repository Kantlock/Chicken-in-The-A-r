public class Egg {
    public int x;
    public int y;
    public static Render renderLeft;
    public static Render renderRight;
    public String direction;

    public int velX;

    public Egg(int x, int y, String direction){
        this.x = x;
        this.y = y;

        if(direction.equals("Left")) {
            renderLeft = new Render("./media/eggleft.png");
            this.direction = direction;
        }

        else if(direction.equals("Right")) {
            renderRight = new Render("./media/eggright.png");
            this.direction = direction;
        }
    }

    public void move(){
        x += velX;
    }
}
