public class Char{
    String image;
	int x=0;
	int y=0;
    
    public Char(String url){
        image=url;
    }
    
    public void changeX(int gx){
        x+=gx;
    }
    public void changeY(int gy){
        y+=gy;
    }
}