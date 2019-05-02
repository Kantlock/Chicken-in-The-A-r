public class Target {
    private int velY;

    public int x;
    public int y;
    public String location;
    public int score;
    public Render render;
    public Render render1;
    public Render render2;
    public Render render3;
    
    public Target(String loc,String random){
        location = loc;
        y = 800;
        render1 = new Render("./media/target.png");
        render2 = new Render("./media/target1.png");
        render3 = new Render("./media/target2.png");
        
        if(location.equals("Right")) x = 1140;
        else if(location.equals("Left")) x = 0;
        
        if(random.equals("1")) {
            render = render1;
            score = 1;
        }
        else if(random.equals("2")) {
            render = render2;
            score = 2;
        }
        else if(random.equals("3")) {
            render = render3;
            score = 3;
        }
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void move(){
        y -= velY;
    }

    public Boolean collisonCheck(Egg egg){
        if(egg.direction.equals("Left")){
            if(egg.x <= this.x + this.render.getWidth() && egg.x >= this.x){
                if(egg.y >= this.y && egg.y <= this.y + this.render.getHeight()) return true;
            }
        }

        else if(egg.direction.equals("Right")){
            if(egg.x + egg.renderRight.getWidth() <= this.x + this.render.getWidth() && egg.x + egg.renderRight.getWidth() >= this.x){
                if(egg.y >= this.y && egg.y <= this.y + this.render.getHeight()) return true;
            }
        }

        return false;
    }
}
