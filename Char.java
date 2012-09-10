public class Char{
    String image;
	int x=0;
	int y=0;
	int speed=1;
    
    public Char(String url){
        image=url;
    }
    
    public void changeX(){
        x+=(2*speed);
    }
    public void changeY(){
        y+=speed;
    }
    public void changeSpeed(int s){
        speed=s;
    }
}