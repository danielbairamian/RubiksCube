public class Edge {
	
	
	//public enum C {
	//	BLUE, WHITE, RED, ORANGE, GREEN, YELLOW
	//}
	
 public MainRubiksSolver.C color1 ;
 public MainRubiksSolver.C color2;
 
 public MainRubiksSolver.C color1Connected;
 public MainRubiksSolver.C color2Connected;
	  
       //DOMINGO
		//todd is weaker
	
 
	
	public Edge( MainRubiksSolver.C c1 ,  MainRubiksSolver.C c2 ,  MainRubiksSolver.C c1connected , MainRubiksSolver.C c2connected ){
		
		this.color1 = c1; //WHITE
		this.color2 = c2; //RED
		this.color1Connected = c1connected;
		this.color2Connected = c2connected;
		
		
	}
	
	
	
	
	public void updateEdges(){
		
		//color1Connected = c1connected;
		//color2Connected = c2connected;
		
		
		
		//fronttop
		if(MainRubiksSolver.front[0][1] == color1 && MainRubiksSolver.top[2][1] == color2){
			color1Connected = MainRubiksSolver.front[1][1]; 
			color2Connected = MainRubiksSolver.top[1][1];
			//System.out.println("front and top");
		}else if(MainRubiksSolver.front[0][1] == color2 && MainRubiksSolver.top[2][1] == color1){
			color2Connected = MainRubiksSolver.front[1][1]; 
			color1Connected = MainRubiksSolver.top[1][1];
			//System.out.println("front and top");

		//frontright
		}else if(MainRubiksSolver.front[1][2] == color1 && MainRubiksSolver.right[1][0] == color2){
			color1Connected = MainRubiksSolver.front[1][1]; 
			color2Connected = MainRubiksSolver.right[1][1];
			//System.out.println("front and right");

			
		}else if(MainRubiksSolver.front[1][2] == color2 && MainRubiksSolver.right[1][0] == color1){
			color2Connected = MainRubiksSolver.front[1][1]; 
			color1Connected = MainRubiksSolver.right[1][1];
			//System.out.println("front and right");
			
		}		
		//frontleft
		else if(MainRubiksSolver.front[1][0] == color1 && MainRubiksSolver.left[1][2] == color2){
			color1Connected = MainRubiksSolver.front[1][1]; 
			color2Connected = MainRubiksSolver.left[1][1];
			//System.out.println("front and left");

			
		}else if(MainRubiksSolver.front[1][0] == color2 && MainRubiksSolver.left[1][2] == color1){
			color2Connected = MainRubiksSolver.front[1][1]; 
			color1Connected = MainRubiksSolver.left[1][1];	
			//System.out.println("front and left");

		}
		
		//frontbot
		else if(MainRubiksSolver.front[2][1] == color1 && MainRubiksSolver.bottom[2][1] == color2){
			color1Connected = MainRubiksSolver.front[1][1]; 
			color2Connected = MainRubiksSolver.bottom[1][1];
			//System.out.println("front and bottom");

		}	
		else if(MainRubiksSolver.front[2][1] ==  color2 && MainRubiksSolver.bottom[2][1] == color1){
			color2Connected = MainRubiksSolver.front[1][1]; 
			color1Connected = MainRubiksSolver.bottom[1][1];
			//System.out.println("front and bottom");

		}
		
		//MIDDLE SHIT
		
		else if(MainRubiksSolver.right[2][1] == color1 && MainRubiksSolver.bottom[1][0] == color2){
					color1Connected = MainRubiksSolver.right[1][1]; 
					color2Connected = MainRubiksSolver.bottom[1][1];
					//System.out.println("right and bottom");

					
				}else if(MainRubiksSolver.right[2][1] == color2 && MainRubiksSolver.bottom[1][0] == color1){
					color2Connected = MainRubiksSolver.right[1][1]; 
					color1Connected = MainRubiksSolver.bottom[1][1];
					//System.out.println("right and bottom");

				}		
				
				
				
				//lefttop
				else if(MainRubiksSolver.left[0][1] == color1 && MainRubiksSolver.top[1][0] == color2){
					color1Connected = MainRubiksSolver.left[1][1]; 
					color2Connected = MainRubiksSolver.top[1][1];
					//System.out.println("left and top");

				}else if(MainRubiksSolver.left[0][1] == color2 && MainRubiksSolver.top[1][0] == color1){
					color2Connected = MainRubiksSolver.left[1][1]; 
					color1Connected = MainRubiksSolver.top[1][1];	
					//System.out.println("left and top");

				}
				
				//leftbot
				else if(MainRubiksSolver.left[2][1] == color1 && MainRubiksSolver.bottom[1][2] == color2){
					color1Connected = MainRubiksSolver.left[1][1]; 
					color2Connected = MainRubiksSolver.bottom[1][1];
					//System.out.println("left and bottom");

					
				}	
				//INCONSISTENCY????   left[2][1] and left [1][0]
				else if(MainRubiksSolver.left[2][1] ==  color2 && MainRubiksSolver.bottom[1][2] == color1){
					color2Connected = MainRubiksSolver.left[1][1]; 
					color1Connected = MainRubiksSolver.bottom[1][1];	
					//System.out.println("left and bottom");

				}
		
		
	
				
				
				
				else if(MainRubiksSolver.back[0][1] == color1 && MainRubiksSolver.top[0][1] == color2){
					color1Connected = MainRubiksSolver.back[1][1]; 
					color2Connected = MainRubiksSolver.top[1][1];
					//System.out.println("back and top");

				}else if(MainRubiksSolver.back[0][1] == color2 && MainRubiksSolver.top[0][1] == color1){
					color2Connected = MainRubiksSolver.back[1][1]; 
					color1Connected = MainRubiksSolver.top[1][1];
					//System.out.println("back and top");

				//frontright
				}else if(MainRubiksSolver.back[1][2] == color1 && MainRubiksSolver.left[1][0] == color2){
					color1Connected = MainRubiksSolver.back[1][1]; 
					color2Connected = MainRubiksSolver.left[1][1];
					//System.out.println("back and left");

					
				}else if(MainRubiksSolver.back[1][2] == color2 && MainRubiksSolver.left[1][0] == color1){
					color2Connected = MainRubiksSolver.back[1][1]; 
					color1Connected = MainRubiksSolver.left[1][1];
					//System.out.println("back and left");

					
				}		
				//frontleft
				else if(MainRubiksSolver.back[1][0] == color1 && MainRubiksSolver.right[1][2] == color2){
					color1Connected = MainRubiksSolver.back[1][1]; 
					color2Connected = MainRubiksSolver.right[1][1];
					//System.out.println("back and right");

					
				}else if(MainRubiksSolver.back[1][0] == color2 && MainRubiksSolver.right[1][2] == color1){
					color2Connected = MainRubiksSolver.back[1][1]; 
					color1Connected = MainRubiksSolver.right[1][1];	
					//System.out.println("back and right");

				}
				
				//frontbot
				else if(MainRubiksSolver.back[2][1] == color1 && MainRubiksSolver.bottom[0][1] == color2){
					color1Connected = MainRubiksSolver.back[1][1]; 
					color2Connected = MainRubiksSolver.bottom[1][1];
					//System.out.println("back and bottom");

					
				}	
				else if(MainRubiksSolver.back[2][1] ==  color2 && MainRubiksSolver.bottom[0][1] == color1){
					color2Connected = MainRubiksSolver.back[1][1]; 
					color1Connected = MainRubiksSolver.bottom[1][1];	
					//System.out.println("back and bottom");

				}
				
 
		//pighttop
		else if(MainRubiksSolver.right[0][1] == color1 && MainRubiksSolver.top[1][2] == color2){
		//	//System.out.println("Initializing this edge with " + MainRubiksSolver.right[0][1] + " and " + MainRubiksSolver.top[1][2] + " where " + MainRubiksSolver.right[0][1] + "is connected to " + MainRubiksSolver.right[1][1] + " and " + MainRubiksSolver.top[1][2] +" is connected to " + MainRubiksSolver.top[1][1] );
					color1Connected = MainRubiksSolver.right[1][1]; 
					color2Connected = MainRubiksSolver.top[1][1];
				}else if(MainRubiksSolver.right[0][1] == color2 && MainRubiksSolver.top[1][2] == color1){
					color2Connected = MainRubiksSolver.right[1][1]; 
					color1Connected = MainRubiksSolver.top[1][1];
					//System.out.println("Initializing this edge with " + MainRubiksSolver.right[0][1] + " and " + MainRubiksSolver.top[1][2] + " where " + MainRubiksSolver.right[0][1] + "is connected to " + MainRubiksSolver.right[1][1] + " and " + MainRubiksSolver.top[1][2] +" is connected to " + MainRubiksSolver.top[1][1] );

				//rightbot
				}
				else{
					//System.out.println("THIS SHOULD NOT HAPPEN");
				}
		
		
	}

}


