import java.util.List;


public class MoveEnemy {
	private List<Block> enemyList;
	private boolean direct;
	private double speed;
	
    public MoveEnemy(List<Block> enemyList, double speed){
    	this.enemyList = enemyList;
    	this.speed = speed;
    	this.direct = true;
    }
    
    
    public int getLeftX(){
    	if(this.enemyList == null){
    		return -1;
    	}
    	int min = (int) this.enemyList.get(0).getCollisionRectangle().getUpperLeft().getX();
    	for(int i =0; i<this.enemyList.size(); i++){
    		int temp = (int) this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getX();
    		if(temp<min){
    			min = temp;
    		}
    	}
    	return min;
    }
    
    public int getRightX(){
    	if(this.enemyList.size() == 0){
    		return -1;
    	}
    	int max = (int) this.enemyList.get(0).getCollisionRectangle().getUpperRight().getX();
    	for(int i =0; i<this.enemyList.size(); i++){
    		int temp = (int) this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getX();
    		if(temp>max){
    			max = temp;
    		}
    	}
    	return max;
    }
    
    public int getUpY(){
    	if(this.enemyList.size() == 0){
    		return -1;
    	}
    	int min = (int) this.enemyList.get(0).getCollisionRectangle().getUpperRight().getY();
    	
    	for(int i =0; i<this.enemyList.size(); i++){
    		int temp = (int) this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getY();
    		if(temp<min){
    			min = temp;
    		}
    	}
    	return min;
    }
    
    
    public void move(){
    	
    	if(this.direct){

    		
    		if (this.getRightX() <= 750) {
	            for(int i = 0; i<this.enemyList.size(); i++){
	            	int x= (int) (this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getX() + this.speed);
	            	int y= (int) this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getY();
	            	this.enemyList.get(i).setXY(x, y);
	            }
	        }else{
	        	this.direct = false;
	        	for(int i = 0; i<this.enemyList.size(); i++){
	            	int x= (int) this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getX();
	            	int y= (int) (this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getY() + 20);
	            	this.enemyList.get(i).setXY(x, y);
	            }
	        	
	        }

    		
    		
    	}else{


    		if (this.getLeftX() >= 20) {
	            for(int i = 0; i<this.enemyList.size(); i++){
	            	int x= (int) (this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getX() - this.speed);
	            	int y= (int) this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getY();
	            	
	            	this.enemyList.get(i).setXY(x, y);
	            }
	        }else{
	        	this.direct = true;
	        	for(int i = 0; i<this.enemyList.size(); i++){
	            	int x= (int) this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getX();
	            	int y= (int) (this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getY() + 20);
	            	
	            	this.enemyList.get(i).setXY(x, y);
	            }
	        	
	        }
    		
    	}
    	
    }
    
    public void setList(List<Block> newlist){
    	this.enemyList = newlist;
    }


	public void moveUp() {

		while(true){
			
		if (this.getUpY() >= 40) {
            for(int i = 0; i<this.enemyList.size(); i++){
            	int x= (int) (this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getX());
            	int y=  (int) (this.enemyList.get(i).getCollisionRectangle().getUpperLeft().getY()-this.speed);
            	this.enemyList.get(i).setXY(x, y);
            }
        }else{
        	break;
            }
        	
        }
		
		//left
		this.direct = false;
		while(!this.direct){
			this.move();
			
	        	
	        }
		
	}


	public void moveToStart() {
		// TODO Auto-generated method stub
		
	}

}
