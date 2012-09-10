public class Char{
    String image;
	int x=0;
	int y=0;
	int speed=5;
    
    public Char(String url){
        image=url;
    }
    
    public void changeX(int gx){
        x+=gx;
    }
    public void changeY(int gy){
        y+=gy;
    }
    public void changeSpeed(int s){
        speed=s;
    }
}