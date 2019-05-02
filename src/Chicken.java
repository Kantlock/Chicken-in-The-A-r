import java.util.ArrayList;

public class Chicken{

    public int velX = 0;
    public int velY = 0;

    public int x;
    public int y;
    public Render render;
    public Render renderLeft;
    public Render renderRight;
    public ArrayList<Egg> eggs;

    public Chicken(){
        eggs = new ArrayList<>();
        renderLeft = new Render("./media/chicken.png");
        renderRight = new Render("./media/chicken2.png");

        render = renderLeft;

        x = App.WIDTH/2 - render.getWidth();
        y = 0;
    }

    public void update(){
        move();
    }

    public void produceRightEgg(){
            Egg e = new Egg(x, y, "Right");
            e.velX = 12;
            eggs.add(e);
            render = renderRight;
    }

    public void produceLeftEgg(){
            Egg e = new Egg(x, y, "Left");
            e.velX = -12;
            eggs.add(e);
            render = renderLeft;
    }

    public void move(){
        if(x + render.getWidth() <= 1000 && x >= 200) x += velX;
        if(y + render.getHeight() <= 600 && y >= 0) y += velY;

        if(x < 200) x = 200;
        if(x + render.getWidth() > 1000) x = 1000 - render.getWidth();
        if(y < 0) y = 0;
        if(y + render.getHeight() > 600) y = 600 - render.getHeight();
    }

    public Boolean collisonCheck(Enemy enemy){
        if(enemy.x <= this.x + this.render.getWidth() && enemy.x >= this.x){
            if(enemy.y >= this.y && enemy.y <= this.y + this.render.getHeight()) return true;
        }

        return false;
    }



}
