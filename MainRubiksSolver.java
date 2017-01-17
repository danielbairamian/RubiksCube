/*
 * Last update: 8.5.16
 * 
 * 	Developers: Tord Jon
 * 				Daniel Bairamian
 * 
 * 
 * 
 * 
 */
public class MainRubiksSolver {
	static C[][] front, back, top, bottom, left, right;
	public static Edge[] edges = new Edge[12];
	public static Corner[] corners = new Corner[8];
	public static String userAlgorithm = "";
	public static boolean printAlgorithms = true;
	private static long startTime;
	public enum C {
		BLUE, WHITE, RED, ORANGE, GREEN, YELLOW, CYAN;
	}
	
	
	public static void finishCube(){
		refreshCorners();
		boolean topIsComplete = false;
		int counter3 = 0; 
		int counter = 0; 
		int counter2 = 0;
		while(!topIsComplete){
			refreshCorners();
			
			if(top[2][2] != top[1][1] ){  //right[0][0] != right[1][1] && front[0][2] != front[1][1] && top[2][2] != top[1][1]){
				while(!topIsComplete){
					if(counter3 == 30   ||  (     (front[0][2] == front[1][1] && top[2][2] == top[1][1])  && (right[0][2] == right[1][1] && top[0][2] == top[1][1])
							&& (left[0][0] == left[1][1] && top[0][0] == top[1][1])    ) && (front[0][0] == front[1][1] && top[2][0] == top[1][1])	){
						topIsComplete = true;
					}
				
					while(top[2][2] != top[1][1]){ //right[0][0] != right[1][1] && front[0][2] != front[1][1] && top[2][2] != top[1][1]){
						rightBotSequence();
					}
					
					rotateTopCounterClockwise(false);
				}
			//while()
			}else{
			//rotateTopCounterClockwise(false);
				turnCubeLeft();
			}
			
			//if the cube is solved
			if(counter3 == 30   ||  (     (front[0][2] == front[1][1] && top[2][2] == top[1][1])  && (right[0][2] == right[1][1] && top[0][2] == top[1][1])
					&& (left[0][0] == left[1][1] && top[0][0] == top[1][1])    ) && (front[0][0] == front[1][1] && top[2][0] == top[1][1])	){
				topIsComplete = true;
			}
			counter3++;
			System.out.println(counter3);
		}
		
		while(front[0][1] != front[1][1] ){
			rotateTopClockwise(true);
		}
		
		
		
	}
	
	public static void clarify(){
		System.out.println(" RS :  T R T’ R’ T’ F’ T F  \n TS:  T’ L’ T L T F T’ F’  \n FRURUF:  F R T R’ T’ F’  \n RU:  R T R’ T R T T R’ T  \n rurururu: T R T’ L’ T R’ T’ L \n  RB: R’ Bot R Bot' ");

	}
	public static void lulGorithm(){
		userAlgorithm += "\n ruruururuur ";
		rotateTopClockwise(false);
		rotateRightClockwise(false);
		rotateTopCounterClockwise(false);
		rotateLeftCounterClockwise(false);
		rotateTopClockwise(false);
		rotateRightCounterClockwise(false);
		rotateTopCounterClockwise(false);
		rotateLeftClockwise(false);
	}
	
	public static void positionTopCorners(){
		
		boolean topCornersPositioned = false;
		
		if (((front[0][0] == front[1][1] || front[0][0] == top[1][1] || front[0][0] == left[1][1]) && (left[0][2] == left[1][1] || left[0][2] == front[1][1] || left[0][2] == top[1][1]) && (top[2][0] == top[1][1] || top[2][0] == left[1][1] || top[2][0] == front[1][1]))
				&&		((back[0][2] == back[1][1] || back[0][2] == top[1][1] || back[0][2] == left[1][1]) && (left[0][0] == left[1][1] || left[0][0] == back[1][1] || left[0][0] == top[1][1]) && (top[0][0] == top[1][1] || top[0][0] == left[1][1] || top[0][0] == back[1][1])) &&
				((back[0][0] == back[1][1] || back[0][0] == top[1][1] || back[0][0] == right[1][1]) && (right[0][2] == right[1][1] || right[0][2] == back[1][1] || right[0][2] == top[1][1]) && (top[0][2] == top[1][1] || top[0][2] == right[1][1] || top[0][2] == back[1][1]))) {
			topCornersPositioned = true;
		}
		
		while(!topCornersPositioned){ //while theyre not all fixed
			//is one in the right spot?   
			boolean foundOne = false;
			for(int x = 0; x < 4; x++){    
				if(!  // (front[0][2] == front[1][1] &&  right[0][0] == right[1][1] && top[2][2] == top[1][1])) {  //corners[0].color1 == 
					((front[0][2] == front[1][1] ||  front[0][2] == right[1][1] 	|| front[0][2] == top[1][1])
					&& (right[0][0] == front[1][1] ||  right[0][0] == right[1][1] 	|| right[0][0] == top[1][1])
					&& (top[2][2] == front[1][1] ||  top[2][2] == right[1][1] 	|| top[2][2] == top[1][1]))
					){
					
					turnCubeLeft();
				} else{
					foundOne = true;
					break;
				}
			}
			lulGorithm();
			
				//topfrontleft
				if(   
						((front[0][0] == front[1][1] ||  front[0][0] == top[1][1] 	|| front[0][0] == left[1][1])  && (left[0][2] == left[1][1]  ||  left[0][2] == front[1][1] 	|| left[0][2] == top[1][1]) 
								&& (top[2][0] == top[1][1] || top[2][0] == left[1][1] || top[2][0] == front[1][1]))    
				
						&&
				
						( (back[0][2] == back[1][1] ||  back[0][2] == top[1][1] 	|| back[0][2] == left[1][1]) && (left[0][0] == left[1][1]  ||  left[0][0] == back[1][1] 	|| left[0][0] == top[1][1]) 
								&&  (top[0][0] == top[1][1]	   ||  top[0][0] == left[1][1] 		|| top[0][0] == back[1][1])) 
						&&  
				
						((back[0][0] == back[1][1] ||  back[0][0] == top[1][1] 	|| back[0][0] == right[1][1]) && (right[0][2] == right[1][1]  ||  right[0][2] == back[1][1] 	|| right[0][2] == top[1][1])
								&& (top[0][2] == top[1][1] || top[0][2] == right[1][1] || top[0][2] == back[1][1] ))
						){
			
				topCornersPositioned = true;
			
				}
		}
	
	}
	
	public static void solveLastCross(){
	//	System.out.println("doing last cross");
		refreshEdges();
		
		
	if(top[0][1] == top[1][1] && top[1][0] == top[1][1] && top[1][2] == top[1][1] && top[2][1] == top[1][1]){
		System.out.println("do nothing mfers");
	}else{
		//if NONE of them are == top[1][1] -> do FRURUF
		if((top[0][1] != top[1][1] && top[1][0]  != top[1][1] && top[1][2]  != top[1][1] && top[2][1]  != top[1][1])){
			fruruf();
		}
		
		//while 2 of those == top[1][1]
		//if ( 2 of those == top[1][1]) , we need to know if its an L or a line.
		if(top[1][2] == top[1][1] && top[0][1] == top[1][1]){
			turnCubeLeft();
			turnCubeLeft();
			turnCubeLeft();
			fruruf();

		}
		else if(top[1][2] == top[1][1] && top[2][1] == top[1][1]){
			turnCubeLeft();
			turnCubeLeft();
			fruruf();
		}
		else if(top[1][0] == top[1][1] && top[2][1] == top[1][1]){
			turnCubeLeft();
			fruruf();
		}
		else if(top[0][1] == top[1][1] && top[1][0] == top[1][1]){
			fruruf();
		}
		
		
		
		if(top[0][1] == top[1][1] && top[2][1] == top[1][1]  && top[1][0] != top[1][1]){
			turnCubeLeft();
			fruruf();
		}else if(top[1][0] == top[1][1] && top[1][2] == top[1][1]  && top[0][1] != top[1][1]){
			fruruf();
		}
		
		
		// top[0][1],  top[1][0], top[1][2], top[2][1]      == top[1][1]
		
	}
		
		//make sure the edges are in the right spots:
		boolean topCrossSolved = false;
		if( front[0][1] == front[1][1] && left[0][1] == left[1][1] && right[0][1] == right[1][1] && back[0][1] == back[1][1] ){
			topCrossSolved = true;

		}else{
		
		
		while(!topCrossSolved){	 				
			rotateTopCounterClockwise(false);//are they already? Do nothing    ){
			if( front[0][1] != front[1][1] && left[0][1] != left[1][1] && right[0][1] != right[1][1] && back[0][1] != back[1][1] ){
			//if(! ( front[0][1] == front[1][1] && left[0][1] == left[1][1] && right[0][1] == right[1][1] && back[0][1] == back[1][1] )){
				RightUpSequenece();
				//we only have the middle up.
			}
			
			if(front[0][1] == front[1][1] && back[0][1] == back[1][1]){
				RightUpSequenece();

			}else if(left[0][1] == left[1][1] && right[0][1] == right[1][1]){
				turnCubeLeft();
				RightUpSequenece();

			}
			/* we are retarded.
			if(top[0][1] == top[1][1] && top[2][1] == top[1][1]){
				System.out.println("11111111111111111111111111111111");
				turnCubeLeft();
				ebolaGorithm();
			}else if(top[1][0] == top[1][1] && top[1][2] == top[1][1]){
				ebolaGorithm();
				System.out.println("222222222222222");

			}*/
			
			if(front[0][1] == front[1][1] && right[0][1] == right[1][1]){
				turnCubeLeft();
				turnCubeLeft();
				turnCubeLeft();
				RightUpSequenece();
			}
			else if(right[0][1] == right[1][1] && back[0][1] == back[1][1]){
				//nothing
				RightUpSequenece();
			}
			else if(back[0][1] == back[1][1] && left[0][1] == left[1][1]){
				turnCubeLeft();
				RightUpSequenece();

			}
			else if(front[0][1] == front[1][1] && left[0][1] == left[1][1]){
				turnCubeLeft();
				turnCubeLeft();
				RightUpSequenece();
				
			} 
			

		
		
			if( front[0][1] == front[1][1] && left[0][1] == left[1][1] && right[0][1] == right[1][1] && back[0][1] == back[1][1] ){
				topCrossSolved = true;
			}
		}
		

		
		}
		
	}
	
	public static void RightUpSequenece(){
		//R U Rí U R U U Rí U
		userAlgorithm += "RU";
		 rotateRightClockwise(false);
		 rotateTopClockwise(false);
		 rotateRightCounterClockwise(false);
		 rotateTopClockwise(false);
		 rotateRightClockwise(false);
		 rotateTopClockwise(false);
		 rotateTopClockwise(false);
		 rotateRightCounterClockwise(false);
		 rotateTopClockwise(false);
		 
	}
	
	public static void fruruf(){
		userAlgorithm += " FRURUF";
		rotateFrontClockwise(false);
		rotateRightClockwise(false);
		rotateTopClockwise(false);
		rotateRightCounterClockwise(false);
		rotateTopCounterClockwise(false);
		rotateFrontCounterClockwise(false);
	}
	
	public static void firstfullsolvetest(){ 
		front = new C[3][3]; 
		back = new C[3][3]; 
		top = new C[3][3]; 
		bottom = new C[3][3]; 
		left = new C[3][3]; 
		right = new C[3][3]; 
		 
		front[0][0] = C.ORANGE; 
		front[0][1] = C.BLUE; 
		front[0][2] = C.GREEN; 
		front[1][0] = C.ORANGE; 
		front[1][1] = C.RED; 
		front[1][2] = C.BLUE; 
		front[2][0] = C.BLUE; 
		front[2][1] = C.BLUE; 
		front[2][2] = C.YELLOW; 
		left[0][0] = C.ORANGE; 
		left[0][1] = C.ORANGE; 
		left[0][2] = C.BLUE; 
		left[1][0] = C.ORANGE; 
		left[1][1] = C.GREEN; 
		left[1][2] = C.WHITE; 
		left[2][0] = C.RED; 
		left[2][1] = C.RED; 
		left[2][2] = C.RED; 
		right[0][0] = C.YELLOW; 
		right[0][1] = C.GREEN; 
		right[0][2] = C.YELLOW; 
		right[1][0] = C.ORANGE; 
		right[1][1] = C.BLUE; 
		right[1][2] = C.BLUE; 
		right[2][0] = C.ORANGE; 
		right[2][1] = C.RED; 
		right[2][2] = C.RED; 
		back[0][0] = C.ORANGE; 
		back[0][1] = C.GREEN; 
		back[0][2] = C.WHITE; 
		back[1][0] = C.RED; 
		back[1][1] = C.ORANGE; 
		back[1][2] = C.YELLOW; 
		back[2][0] = C.GREEN; 
		back[2][1] = C.WHITE; 
		back[2][2] = C.BLUE; 
		top[0][0] = C.GREEN; 
		top[0][1] = C.YELLOW; 
		top[0][2] = C.BLUE; 
		top[1][0] = C.GREEN; 
		top[1][1] = C.WHITE; 
		top[1][2] = C.RED; 
		top[2][0] = C.WHITE; 
		top[2][1] = C.WHITE; 
		top[2][2] = C.RED; 
		bottom[0][0] = C.WHITE; 
		bottom[0][1] = C.GREEN; 
		bottom[0][2] = C.WHITE; 
		bottom[1][0] = C.WHITE; 
		bottom[1][1] = C.YELLOW; 
		bottom[1][2] = C.YELLOW; 
		bottom[2][0] = C.GREEN; 
		bottom[2][1] = C.YELLOW; 
		bottom[2][2] = C.YELLOW; 
	}
	
	
	public static void solveLayerTwo() {
		  turnCubeUpsideDown();
		  refreshEdges();
		  int counter3 = 0;
		  int counter = 0;
		  int counter2 = 0;
		  boolean layerTwoIsComplete = false;
		  if (((front[1][2] == front[1][1] && right[1][0] == right[1][1]) && (front[1][0] == front[1][1] && left[1][2] == left[1][1]) && (back[1][0] == back[1][1] && right[1][2] == right[1][1])) && (back[1][2] == back[1][1] && left[1][0] == left[1][1])) {
		   System.out.println("NEVER STARTED BECAUSE IT WAS ALREADY FIXED");
		   layerTwoIsComplete = true;
		  }
		  
		  while (!layerTwoIsComplete) {
		   counter = 0;

		   if ((top[0][1] != top[1][1] || top[1][2] != top[1][1] || top[1][0] != top[1][1] || top[2][1] != top[1][1]      || front[0][1] != top[1][1] || left[0][1] != top[1][1] || right[0][1] != top[1][1] || back[0][1] == top[1][1] )) { // != top[1][1] || left[0][0] != top[1][1] || left[0][2] != top[1][1] || back[0][0] != top[1][1] || back[0][2] != top[1][1])) {
		    for (int i = 0; i < 4; i++) {
		     if (right[1][0] != right[1][1] || front[1][2] != front[1][1]) {
		      topRightSequence();
		      break;
		     }
		     turnCubeLeft();
		     //userAlgorithm += "DOINGTHISCUSPIECEISSTUCK";
		    }

		   }
		   boolean foundSomething = false;

		   while (counter < 4 ) { 
		    refreshEdges();
		    //System.out.println("Rotating top clockwise");
		    rotateTopClockwise(false);
		    counter++;
		    refreshEdges();
		    // System.out.println(counter);
		    if( !(front[0][1] == front[1][1] && top[2][1] != top[1][1]) ){
		     foundSomething = true;
		    }
		    
		    if (counter == 4) { // skip this cus we didnt find the piece();
		     
		    } else {
		     
		     
		     
		     if (top[2][1] == right[1][1]) {
		      topRightSequence();
		      if(counter == 1){
		       userAlgorithm += " -T";
		      }else if(counter == 2){
		       userAlgorithm += " -T-T";
		      }else if(counter == 3){
		       userAlgorithm += " -T'";
		      }else{
		       System.out.println("THIS SHYOULD HAPPEN, COUNTER SHOULD BE 1, 2 or 3");
		      }
		      // U R U’ R’ U’ F’ U F
		     } else if (top[2][1] == left[1][1]) {
		      if(counter == 1){
		       userAlgorithm += " T";
		      }else if(counter == 2){
		       userAlgorithm += " T T";
		      }else if(counter == 3){
		       userAlgorithm += " T'";
		      }else{
		       System.out.println("THIS SHYOULD HAPPEN, COUNTER SHOULD BE 1, 2 or 3");
		      }
		      // do cancergorithm2
		      // Left: U’ L’ U L U F U’ F
		      topLeftSequence();
		     } else {
		      // System.out.print(aidsCounter);
		
		     }

		    }

		   }
		   

		  
		   counter3++;
		   if (counter3 == 100) {
		    System.out.println("Stopped because counter was too big");
		    layerTwoIsComplete = true;
		   } else if (((front[1][2] == front[1][1] && right[1][0] == right[1][1]) && (front[1][0] == front[1][1] && left[1][2] == left[1][1]) && (back[1][0] == back[1][1] && right[1][2] == right[1][1])) && (back[1][2] == back[1][1] && left[1][0] == left[1][1])) {

		    //System.out.println("Stopped because we think we found a solution");
		    layerTwoIsComplete = true;
		   } else {
		    turnCubeLeft();
		   }
		  }
		 }
	public static void topRightSequence(){
		//U R UÌ RÌ UÌ FÌ U F
		
		
		rotateTopClockwise(false);
		rotateRightClockwise(false);
		rotateTopCounterClockwise(false);
		rotateRightCounterClockwise(false);
		rotateTopCounterClockwise(false);
		rotateFrontCounterClockwise(false);
		rotateTopClockwise(false);
		rotateFrontClockwise(false);
		userAlgorithm += " RS";
		
		
	}
	
	public static void topLeftSequence(){
		//Left: UÌ LÌ U L U F UÌ F'
		//System.out.println("running cancerGorithm2");
/*		rotateTopCounterClockwise(true);
		rotateLeftCounterClockwise(true);
		rotateTopClockwise(true);
		rotateLeftClockwise(true);
		rotateTopClockwise(true);
		rotateFrontClockwise(true);
		rotateTopCounterClockwise(true);
		rotateFrontCounterClockwise(true);
		*/
		rotateTopCounterClockwise(false);
		rotateLeftCounterClockwise(false);
		rotateTopClockwise(false);
		rotateLeftClockwise(false);
		rotateTopClockwise(false);
		rotateFrontClockwise(false);
		rotateTopCounterClockwise(false);
		rotateFrontCounterClockwise(false);
		
		userAlgorithm += " TS";
	}
	
	public static void turnCubeLeft(){
	//	System.out.println("Turning cube left");
		userAlgorithm += " TurnLeft";   
		rotateTopClockwise(false);
		rotateMidCounterClockwise(false);
		rotateMidCounterClockwise(false);
		rotateMidCounterClockwise(false);
		rotateBotClockwise(false);  // was 	rotateBotCounterClockwise(false);
	}
	
	public static void turnCubeUpsideDown(){
		userAlgorithm += " UpsideDown";
		rotateMidUp(false);
		rotateMidUp(false);
		rotateLeftCounterClockwise(false);
		rotateLeftCounterClockwise(false);
		rotateRightClockwise(false);
		rotateRightClockwise(false);
		
	}
	
	public static void initDansCube(){
		  front = new C[3][3];
		  back = new C[3][3];
		  top = new C[3][3];
		  bottom = new C[3][3];
		  left = new C[3][3];
		  right = new C[3][3];

		  top[0][0] = C.GREEN;
		  top[0][1] = C.RED;
		  top[0][2] = C.WHITE;
		  top[1][0] = C.ORANGE;
		  top[1][1] = C.ORANGE;
		  top[1][2] = C.WHITE;
		  top[2][0] = C.RED;
		  top[2][1] = C.GREEN;
		  top[2][2] = C.ORANGE;

		  front[0][0] = C.BLUE;
		  front[0][1] = C.YELLOW;
		  front[0][2] = C.BLUE;
		  front[1][0] = C.GREEN;
		  front[1][1] = C.WHITE;
		  front[1][2] = C.YELLOW;
		  front[2][0] = C.ORANGE;
		  front[2][1] = C.BLUE; //ORANGE
		  front[2][2] = C.GREEN;

		  bottom[0][0] = C.BLUE;
		  bottom[0][1] = C.YELLOW;
		  bottom[0][2] = C.YELLOW;
		  bottom[1][0] = C.BLUE;
		  bottom[1][1] = C.RED;
		  bottom[1][2] = C.GREEN;
		  bottom[2][0] = C.RED;
		  bottom[2][1] = C.ORANGE; //C.GREEN;
		  bottom[2][2] = C.GREEN;

		  right[0][0] = C.YELLOW;
		  right[0][1] = C.BLUE;
		  right[0][2] = C.RED;
		  right[1][0] = C.ORANGE;
		  right[1][1] = C.BLUE;
		  right[1][2] = C.GREEN;
		  right[2][0] = C.WHITE;
		  right[2][1] = C.RED;
		  right[2][2] = C.WHITE;

		  left[0][0] = C.ORANGE;
		  left[0][1] = C.WHITE;
		  left[0][2] = C.YELLOW;
		  left[1][0] = C.RED;
		  left[1][1] = C.GREEN;
		  left[1][2] = C.ORANGE;
		  left[2][0] = C.RED;
		  left[2][1] = C.RED;
		  left[2][2] = C.YELLOW;

		  back[0][0] = C.BLUE;
		  back[0][1] = C.WHITE;
		  back[0][2] = C.WHITE;
		  back[1][0] = C.WHITE;
		  back[1][1] = C.YELLOW;
		  back[1][2] = C.YELLOW;
		  back[2][0] = C.ORANGE;
		  back[2][1] = C.BLUE;
		  back[2][2] = C.GREEN;
	}
	public static void main(String[] args) {		// Main Method
		
		startTime = System.currentTimeMillis();
		printAlgorithms = false;
		initCube();
		initEdges();
		initCorners();
		
		printRubiks();
		userAlgorithm = "";

		rotateFrontClockwise(true);
		rotateFrontClockwise(true);
		rotateBotCounterClockwise(true);
		rotateRightClockwise(true);
		rotateRightClockwise(true);
		rotateBotClockwise(true);
		rotateBotClockwise(true);
		rotateFrontClockwise(true);
		rotateFrontClockwise(true);
		rotateFrontClockwise(true);
		rotateFrontClockwise(true);
		rotateBotCounterClockwise(true);
		rotateRightClockwise(true);
	
		
		solveCross();
		printRubiks();
		
		
		System.out.print("-----------------------------------");
		solveTopCorners();
		
		
		printRubiks();
		System.out.print("-----------------------------------");
		System.out.println("above this is the top solved.");
		refreshEdges();
		solveLayerTwo();
        printRubiks();	
        System.out.print("-----------------------------------");
		
		
		
		solveLastCross();
		printRubiks();
		System.out.print("-----------------------------------");
	
		System.out.println("positioning top corners");
		positionTopCorners();
		
		finishCube();
		
		printRubiks();
		System.out.println(userAlgorithm);
		
		System.out.println("------------------------------------------------------");
		System.out.println("This might be useful: ");
		clarify();

		long endTime = System.currentTimeMillis();
		System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}
	
	public static void INITNAME(){ 
		front = new C[3][3]; 
		back = new C[3][3]; 
		top = new C[3][3]; 
		bottom = new C[3][3]; 
		left = new C[3][3]; 
		right = new C[3][3]; 
		 
		front[0][0] = C.BLUE; 
		front[0][1] = C.WHITE; 
		front[0][2] = C.WHITE; 
		front[1][0] = C.WHITE; 
		front[1][1] = C.WHITE; 
		front[1][2] = C.WHITE; 
		front[2][0] = C.WHITE; 
		front[2][1] = C.WHITE; 
		front[2][2] = C.WHITE; 
		left[0][0] = C.ORANGE; 
		left[0][1] = C.WHITE; 
		left[0][2] = C.WHITE; 
		left[1][0] = C.WHITE; 
		left[1][1] = C.ORANGE; 
		left[1][2] = C.WHITE; 
		left[2][0] = C.WHITE; 
		left[2][1] = C.WHITE; 
		left[2][2] = C.WHITE; 
		right[0][0] = C.RED; 
		right[0][1] = C.WHITE; 
		right[0][2] = C.RED; 
		right[1][0] = C.WHITE; 
		right[1][1] = C.RED; 
		right[1][2] = C.WHITE; 
		right[2][0] = C.WHITE; 
		right[2][1] = C.WHITE; 
		right[2][2] = C.WHITE; 
		back[0][0] = C.YELLOW; 
		back[0][1] = C.WHITE; 
		back[0][2] = C.YELLOW; 
		back[1][0] = C.WHITE; 
		back[1][1] = C.YELLOW; 
		back[1][2] = C.WHITE; 
		back[2][0] = C.WHITE; 
		back[2][1] = C.WHITE; 
		back[2][2] = C.WHITE; 
		top[0][0] = C.BLUE; 
		top[0][1] = C.WHITE; 
		top[0][2] = C.BLUE; 
		top[1][0] = C.WHITE; 
		top[1][1] = C.BLUE; 
		top[1][2] = C.WHITE; 
		top[2][0] = C.ORANGE; 
		top[2][1] = C.WHITE; 
		top[2][2] = C.BLUE; 
		bottom[0][0] = C.WHITE; 
		bottom[0][1] = C.WHITE; 
		bottom[0][2] = C.WHITE; 
		bottom[1][0] = C.WHITE; 
		bottom[1][1] = C.WHITE; 
		bottom[1][2] = C.WHITE; 
		bottom[2][0] = C.WHITE; 
		bottom[2][1] = C.WHITE; 
		bottom[2][2] = C.WHITE; 
		
	}
	
	public static void solveTopCorners(){
		refreshCorners();
		boolean topIsComplete = false;
		int counter3 = 0; 
		int counter = 0; 
		int counter2 = 0;
		while(!topIsComplete){
			refreshCorners();
			
			if(!  ( (front[2][2] == top[1][1]) || front[2][0] == top[1][1]  || right[2][0] == top[1][1] || right[2][2] == top[1][1]
				    || back[2][0] == top[1][1]  || back[2][2] == top[1][1] || left[2][0] == top[1][1] || left[2][2] == top[1][1] 
				    || bottom[0][0] == top[1][1] || bottom[2][0] == top[1][1] || bottom[0][2] == top[1][1] || bottom[2][2] == top[1][1]) ){
				
				for(int i = 0; i < 4; i++){	//this for loop is fixing stuck pieces 
					if(((right[1][1] == right[0][0] || right[1][1] == front [0][2] || right[1][1] == top[2][2] )
							  &&  (front[1][1] == right[0][0] || front[1][1] == front [0][2] || front[1][1] == top[2][2]) 
							   && (top[1][1] == right[0][0] || top[1][1] == front[0][2] || top[1][1] == top[2][2])          &&   !(right[1][1] == right[0][0] && front[1][1] == front[0][2] && top[1][1] == top[2][2]))){
						
						
						while(!(right[1][1] == right[0][0] && front[1][1] == front[0][2] && top[1][1] == top[2][2])){
							refreshCorners();
							rightBotSequence();
							refreshCorners();
						}
						
					}else if(   (right[0][0] == top[1][1] || front [0][2] == top[1][1] || top[2][2] == top[1][1]) /*COULD BE WRONG SPOT*/    &&   !(right[1][1] == right[0][0] && front[1][1] == front[0][2] && top[1][1] == top[2][2])  ){
						rightBotSequence();
					}
					turnCubeLeft();
				}
			}
			

			
			

			
			
			//WE SHOULD DO THIS AT SOME POINT, doing aidsGorithm til its done like in #XD
			//put the while no top corners are at the bottom loop here
			//IF(the cube is in the right position, but not facing the right sides, do aidsgorithm til its done like in #XD)  /   IF the corner at the TOP RIGHT FRONT IS NOT CORRECT, but HAS top[1][1] aidsgorithm x1 
			// maybe dont use this   !(right[1][1] == right[0][0] && front[1][1] == front[0][2] && top[1][1] == top[2][2])
			
			
			
			while(  counter < 4    &&  !((right[1][1] == right[2][0] || right[1][1] == front [2][2] || right[1][1] == bottom[2][0] )
					  &&  (front[1][1] == right[2][0] || front[1][1] == front [2][2] || front[1][1] == bottom[2][0]) 
					   && (top[1][1] == right[2][0] || top[1][1] == front [2][2] || top[1][1] == bottom[2][0])   ) 	){
					refreshCorners();
					rotateBotClockwise(true);    //fix this for better output
					counter++;
					refreshCorners();		
					// System.out.println(counter);
			}
			
			
			if(counter == 4){ //skip this cus we didnt find the piece();
			
			}else{

				counter2 = 0;
				while(!(right[1][1] == right[0][0] && front[1][1] == front[0][2] && top[1][1] == top[2][2]) && counter2 < 6){  // #XD
					refreshCorners();
					rightBotSequence();
					counter2++;
					refreshCorners();
				}
				
			}
			
			counter = 0;
			//System.out.println(aidsCounter);
			turnCubeLeft();
			
			
			if(counter3 == 15    ||  (     (front[0][2] == front[1][1] && top[2][2] == top[1][1])  && (right[0][2] == right[1][1] && top[0][2] == top[1][1])
					&& (left[0][0] == left[1][1] && top[0][0] == top[1][1])    ) && (front[0][0] == front[1][1] && top[2][0] == top[1][1])	){
				topIsComplete = true;
			}
			counter3++;
		}
		
		
		
	}
	
	public static void initCorners() {
		corners[0] = new Corner(MainRubiksSolver.top[2][0], MainRubiksSolver.front[0][0], MainRubiksSolver.left[0][2]);
		corners[1] = new Corner(MainRubiksSolver.top[2][2], MainRubiksSolver.front[0][2], MainRubiksSolver.right[0][0]);
		corners[2] = new Corner(MainRubiksSolver.top[0][2], MainRubiksSolver.right[0][2], MainRubiksSolver.back[0][0]);
		corners[3] = new Corner(MainRubiksSolver.top[0][0], MainRubiksSolver.left[0][0], MainRubiksSolver.back[0][2]);
		corners[4] = new Corner(MainRubiksSolver.front[2][0], MainRubiksSolver.bottom[2][2], MainRubiksSolver.left[2][2]);
		corners[5] = new Corner(MainRubiksSolver.front[2][2], MainRubiksSolver.bottom[2][0], MainRubiksSolver.right[2][0]);
		corners[6] = new Corner(MainRubiksSolver.right[2][2], MainRubiksSolver.bottom[0][0], MainRubiksSolver.back[2][0]);
		corners[7] = new Corner(MainRubiksSolver.back[2][2], MainRubiksSolver.bottom[0][2], MainRubiksSolver.left[2][0]);
		//if corners [4 / 5 / 6/ 7] !CNTAINS red. check color1, color2, color3
	}
	public static void refreshCorners(){
		corners[0].updateCorner(MainRubiksSolver.top[2][0], MainRubiksSolver.front[0][0], MainRubiksSolver.left[0][2]);
		corners[1].updateCorner(MainRubiksSolver.top[2][2], MainRubiksSolver.front[0][2], MainRubiksSolver.right[0][0]);
		corners[2].updateCorner(MainRubiksSolver.top[0][2], MainRubiksSolver.right[0][2], MainRubiksSolver.back[0][0]);
		corners[3].updateCorner(MainRubiksSolver.top[0][0], MainRubiksSolver.left[0][0], MainRubiksSolver.back[0][2]);
		corners[4].updateCorner(MainRubiksSolver.front[2][0], MainRubiksSolver.bottom[2][2], MainRubiksSolver.left[2][2]);
		corners[5].updateCorner(MainRubiksSolver.front[2][2], MainRubiksSolver.bottom[2][0], MainRubiksSolver.right[2][0]);
		corners[6].updateCorner(MainRubiksSolver.right[2][2], MainRubiksSolver.bottom[0][0], MainRubiksSolver.back[2][0]);
		corners[7].updateCorner(MainRubiksSolver.back[2][2], MainRubiksSolver.bottom[0][2], MainRubiksSolver.left[2][0]);
	}
	
	public static void printCorners(){
		for(int i = 0; i < 8; i++){
			System.out.println("Corner " + i + ": " + corners[i].color1 + " / " + corners[i].color2 + " / " + corners[i].color3);
		}
	}
	
	//right counterclockwise -> bottom counterclockwise -> right clockwise  -> bottom counterclockwise
	public static void rightBotSequence(){
		//System.out.println("Doing right counterclockwise -> bottom clockwise -> right clockwise -> bottom counterclockwise");
		rotateRightCounterClockwise(false);
		rotateBotClockwise(false);
		rotateRightClockwise(false);
		rotateBotCounterClockwise(false);
		userAlgorithm += " RB";
		
	}
	
	public static void newSolveTopFront(int i) {
		//assume we're working with the top right edge here:
		//if ONE (of two) color is connected to TOP and has THE SAME color as top (2nd isnt same as right, if so we skip) AAANDD the other color has the same color as front. 
		//	if(  [i].color1 == top[2][1] && color2 == front[0][1]    OR ||    [i].color2 == top[2][1] && color1 == front[0][1]{   
		//		if(top[2][1] == top[1][1] {color is facing top} else {color is facing front} 

		//TOP FRONT
		if (((edges[i].color1 == top[2][1] && edges[i].color2 == front[0][1]) || (edges[i].color2 == top[2][1] && edges[i].color1 == front[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (top[2][1] == top[1][1]) { // the top color is on top

				//DO NOTHING AS THIS IS WHAT WE WANT

			} else { //the top color is on front

				//Front counterclock -> top clock -> left counterclock -> top counterclock
				rotateFrontCounterClockwise(true);
				rotateTopClockwise(true);
				rotateLeftCounterClockwise(true);
				rotateTopCounterClockwise(true);

			}
		}
		//TOP LEFT     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		else if (((edges[i].color1 == top[1][0] && edges[i].color2 == left[0][1]) || (edges[i].color2 == top[1][0] && edges[i].color1 == left[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (top[1][0] == top[1][1]) { // the top color is on top
				rotateLeftCounterClockwise(true);
				rotateLeftCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				//left x2 -> bot counterclock -> front x2
			} else { //the top color is on front
				rotateLeftClockwise(true);
				rotateFrontClockwise(true);

				//left clock -> front clock
			}
			
		}
		//TOP RIGHT
		else if (((edges[i].color1 == top[1][2] && edges[i].color2 == right[0][1]) || (edges[i].color2 == top[1][2] && edges[i].color1 == right[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (top[1][2] == top[1][1]) { // the top color is on top
				rotateRightClockwise(true);
				rotateRightClockwise(true);
				rotateBotClockwise(true);
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				//right x2 -> bot clock -> front x2
			} else { //the top color is on front

				rotateRightCounterClockwise(true);
				rotateFrontCounterClockwise(true);

				// rightcounterclock -> front counterclock	
			}
		}
		//TOP BACK
		else if (((edges[i].color1 == top[0][1] && edges[i].color2 == back[0][1]) || (edges[i].color2 == top[0][1] && edges[i].color1 == back[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (top[0][1] == top[1][1]) { // the top color is on top

				rotateBackClockwise(true);
				rotateBackClockwise(true);
				rotateBotCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				//back x2 -> bot x 2 -> front x2
			} else { //the top color is on back
				//back x2 -> bot clockwise -> right clockwise -> front counterclock -> right counterclock

				rotateBackClockwise(true);
				rotateBackClockwise(true);

				rotateBotClockwise(true);
				
				rotateRightClockwise(true);

				rotateFrontCounterClockwise(true);

				rotateRightCounterClockwise(true);

			}
		}
		/* --------------------------------------------- ABOVE THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE TOP LAYER ------------------------------------------------------- */
		/* --------------------------------------------- BELOW THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE MID LAYER ------------------------------------------------------- */

		//FRONT LEFT
		if (((edges[i].color1 == front[1][0] && edges[i].color2 == left[1][2]) || (edges[i].color2 == front[1][0] && edges[i].color1 == left[1][2])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (front[1][0] == top[1][1]) { // the top color is on the front

				rotateTopClockwise(true);

				rotateLeftCounterClockwise(true);

				rotateTopCounterClockwise(true);

				//top clock -> left counterclock -> top counterclock
			} else { //the top color is on the left side
				rotateFrontClockwise(true);
			}
		} //FRONT RIGHT
		else if (((edges[i].color1 == front[1][2] && edges[i].color2 == right[1][0]) || (edges[i].color2 == front[1][2] && edges[i].color1 == right[1][0])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (front[1][2] == top[1][1]) { // the top color is the front

				rotateTopCounterClockwise(true);

				rotateRightClockwise(true);

				rotateTopClockwise(true);

				//rotateRightClockwise(true);
				//rotateRightClockwise(true);
				//rotateRightClockwise(true);

			} else {
				rotateFrontCounterClockwise(true);
			}
		}
		//BACK RIGHT
		else if (((edges[i].color1 == back[1][0] && edges[i].color2 == right[1][2]) || (edges[i].color2 == back[1][0] && edges[i].color1 == right[1][2])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (back[1][0] == top[1][1]) { // the top color is on the back

				rotateRightClockwise(true);
				rotateBotClockwise(true);
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				rotateRightCounterClockwise(true);

			} else { //the top color is on the right

				rotateTopCounterClockwise(true);
				rotateTopCounterClockwise(true);
				rotateBackCounterClockwise(true);
				rotateTopCounterClockwise(true);
				rotateTopCounterClockwise(true);

				// top x2 -> back counterclock -> top 2x  //??questionable?
			}
		}
		//BACK LEFT           !!!!!!!!!!1
		else if (((edges[i].color1 == back[1][2] && edges[i].color2 == left[1][0]) || (edges[i].color2 == back[1][2] && edges[i].color1 == left[1][0])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (back[1][2] == top[1][1]) { // the top color is on the back
				rotateTopClockwise(true);
				rotateLeftClockwise(true);
				rotateTopCounterClockwise(true);
			} else { //the top color is on the left
				rotateTopCounterClockwise(true);
				rotateTopCounterClockwise(true);
				rotateBackClockwise(true);
				rotateTopCounterClockwise(true);
				rotateTopCounterClockwise(true);
				// top x2 --> back clockwise --> top x2

			}
		}

		//  --------------------------------------------- ABOVE THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE MID LAYER ------------------------------------------------------- /
		//  ------------------------------------------- BELOW THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE BOTTOM LAYER ------------------------------------------------------ /

		//BOTTOM FRONT
		if (((edges[i].color1 == bottom[2][1] && edges[i].color2 == front[2][1]) || (edges[i].color2 == bottom[2][1] && edges[i].color1 == front[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			
			
			if (bottom[2][1] == top[1][1]) { // the top color is on bot

				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				
				//front x2
			} else { //the top color is on front
				System.out.println("CANCCCCCCCCCCCCCCCCCCER KILL LEB SIS PLZ");
				System.out.println(front[2][1]);
				rotateFrontCounterClockwise(true);
				rotateTopCounterClockwise(true);
				rotateRightClockwise(true);
				rotateTopClockwise(true);
				//rotateRightCounterClockwise(true);  fuck you cuntface
				//front counterclock -> top counterclock -> right clock -> top clock -> right counterclock   <--- show dan maybe
			}
		}
		//BOTTOM LEFT
		else if (((edges[i].color1 == bottom[1][2] && edges[i].color2 == left[2][1]) || (edges[i].color2 == bottom[1][2] && edges[i].color1 == left[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (bottom[1][2] == top[1][1]) { // the top color is on bottom
				rotateBotCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
			} else { //the top color is on left

				rotateLeftCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateLeftClockwise(true);
				// left counterclock -> front clock -> left clock
			}
		}
		//BOTTOM RIGHT
		else if (((edges[i].color1 == bottom[1][0] && edges[i].color2 == right[2][1]) || (edges[i].color2 == bottom[1][0] && edges[i].color1 == right[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (bottom[1][0] == top[1][1]) { // the top color is on the bottom
				rotateBotClockwise(true);
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				//bottom clock -> front x2
			} else { //the top color is on the right

				rotateRightClockwise(true);
				rotateFrontCounterClockwise(true);
				rotateRightCounterClockwise(true);
				// right clock -> front counterclock -> right counterclock

			}
		}
		//BOTTOM BACK
		else if (((edges[i].color1 == bottom[0][1] && edges[i].color2 == back[2][1]) || (edges[i].color2 == bottom[0][1] && edges[i].color1 == back[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == front[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == front[1][1]))) {
			if (bottom[0][1] == top[1][1]) { // the top color is on the bottom
				rotateBotCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);

				//bottom x2 -> front x2

			} else { //the top color is on the back
				rotateBackClockwise(true);
				rotateTopClockwise(true);
				rotateLeftClockwise(true);
				rotateTopCounterClockwise(true);
				rotateBackCounterClockwise(true);
				// back clockwise --> top clockwise --> left clockwise --> top counterclockwise --> back counterclockwise 
			}
		}

		else {
			//System.out.println("why the fuck did we land here????");   - we're supposed to you dumbfuck
		}

	}
	public static void alterInitCube() {
		// static Color[][] front, back , top , bot , left , right;
		front = new C[3][3];
		back = new C[3][3];
		top = new C[3][3];
		bottom = new C[3][3];
		left = new C[3][3];
		right = new C[3][3];

		top[0][0] = C.YELLOW;
		top[0][1] = C.RED;
		top[0][2] = C.WHITE;
		top[1][0] = C.YELLOW;
		top[1][1] = C.RED;
		top[1][2] = C.WHITE;
		top[2][0] = C.GREEN;
		top[2][1] = C.GREEN;
		top[2][2] = C.ORANGE;

		front[0][0] = C.RED;
		front[0][1] = C.RED;
		front[0][2] = C.GREEN;
		front[1][0] = C.YELLOW;
		front[1][1] = C.YELLOW;
		front[1][2] = C.YELLOW;
		front[2][0] = C.BLUE;
		front[2][1] = C.ORANGE;
		front[2][2] = C.ORANGE;

		bottom[0][0] = C.YELLOW;
		bottom[0][1] = C.ORANGE;
		bottom[0][2] = C.WHITE;
		bottom[1][0] = C.YELLOW;
		bottom[1][1] = C.ORANGE;
		bottom[1][2] = C.WHITE;
		bottom[2][0] = C.BLUE;
		bottom[2][1] = C.BLUE;
		bottom[2][2] = C.RED;

		right[0][0] = C.YELLOW;
		right[0][1] = C.GREEN;
		right[0][2] = C.GREEN;
		right[1][0] = C.BLUE;
		right[1][1] = C.GREEN;
		right[1][2] = C.GREEN;
		right[2][0] = C.WHITE;
		right[2][1] = C.ORANGE;
		right[2][2] = C.ORANGE;

		left[0][0] = C.RED;
		left[0][1] = C.RED;
		left[0][2] = C.WHITE;
		left[1][0] = C.BLUE;
		left[1][1] = C.BLUE;
		left[1][2] = C.GREEN;
		left[2][0] = C.BLUE;
		left[2][1] = C.BLUE;
		left[2][2] = C.YELLOW;

		back[0][0] = C.RED;
		back[0][1] = C.WHITE;
		back[0][2] = C.BLUE;
		back[1][0] = C.RED;
		back[1][1] = C.WHITE;
		back[1][2] = C.ORANGE;
		back[2][0] = C.GREEN;
		back[2][1] = C.WHITE;
		back[2][2] = C.ORANGE;

	}


	public static void initEdges() {

		edges[0] = new Edge(MainRubiksSolver.front[0][1], MainRubiksSolver.top[2][1], MainRubiksSolver.front[1][1], MainRubiksSolver.top[1][1]);
		edges[1] = new Edge(MainRubiksSolver.front[1][2], MainRubiksSolver.right[1][0], MainRubiksSolver.front[1][1], MainRubiksSolver.right[1][1]);
		edges[2] = new Edge(MainRubiksSolver.front[1][0], MainRubiksSolver.left[1][2], MainRubiksSolver.front[1][1], MainRubiksSolver.left[1][1]);
		edges[3] = new Edge(MainRubiksSolver.front[2][1], MainRubiksSolver.bottom[2][1], MainRubiksSolver.front[1][1], MainRubiksSolver.bottom[1][1]);
		
		edges[4] = new Edge(MainRubiksSolver.right[0][1], MainRubiksSolver.top[1][2], MainRubiksSolver.right[1][1], MainRubiksSolver.top[1][1]);

		//edges[4] = new Edge(MainRubiksSolver.right[0][1], MainRubiksSolver.top[1][2], MainRubiksSolver.right[1][1], MainRubiksSolver.top[1][1]);
		//edges[4] = new Edge(C.CYAN,C.BLUE , C.CYAN,C.BLUE );
		//pighttop

		edges[5] = new Edge(MainRubiksSolver.right[2][1], MainRubiksSolver.bottom[1][0], MainRubiksSolver.right[1][1], MainRubiksSolver.bottom[1][1]);

		edges[6] = new Edge(MainRubiksSolver.left[0][1], MainRubiksSolver.top[1][0], MainRubiksSolver.left[1][1], MainRubiksSolver.top[1][1]);
		edges[7] = new Edge(MainRubiksSolver.left[2][1], MainRubiksSolver.bottom[1][2], MainRubiksSolver.left[1][1], MainRubiksSolver.bottom[1][1]);

		edges[8] = new Edge(MainRubiksSolver.back[0][1], MainRubiksSolver.top[0][1], MainRubiksSolver.back[1][1], MainRubiksSolver.top[1][1]);
		edges[9] = new Edge(MainRubiksSolver.back[1][2], MainRubiksSolver.left[1][0], MainRubiksSolver.back[1][1], MainRubiksSolver.left[1][1]);
		edges[10] = new Edge(MainRubiksSolver.back[1][0], MainRubiksSolver.right[1][2], MainRubiksSolver.back[1][1], MainRubiksSolver.right[1][1]);
		edges[11] = new Edge(MainRubiksSolver.back[2][1], MainRubiksSolver.bottom[0][1], MainRubiksSolver.back[1][1], MainRubiksSolver.bottom[1][1]);

	}

	public static void refreshEdges() {
		for (int i = 0; i < 12; i++) {
			edges[i].updateEdges();
		}
		 

	}

	public static void printEdges() {
		for (int i = 0; i < 12; i++) {
			System.out.println(edges[i].color1 + " / " + edges[i].color2 + " edge : " + edges[i].color1 + " is connected to " + edges[i].color1Connected + " AND, " + edges[i].color2 + " is connected to " + edges[i].color2Connected);
		}
	}

	public static void solveCross() {
		if (false && top[0][1] == top[1][1] && top[1][0] == top[1][1] && top[1][2] == top[1][1] && top[2][1] == top[1][1]) {
			System.out.println("cross is already fixed");
		} else {
			//solveRight
			for (int i = 0; i < 12; i++) {
				newSolveTopRight(i);
			}
			for (int i = 0; i < 12; i++) {
				//System.out.println("Done topright doing topfront");
				newSolveTopFront(i);
			}
			
			for (int i = 0; i < 12; i++) {
			//	System.out.println("Done topfront doing topleft");
				newSolveTopLeft(i);
			}
			for (int i = 0; i < 12; i++) {
			//	System.out.println("Done topleft doing topback");
				newSolverTopBack(i);

			}

		}

	}

	public static void newSolveTopRight(int i) {
		//assume we're working with the top right edge here:
		//if ONE (of two) color is connected to TOP and has THE SAME color as top (2nd isnt same as right, if so we skip) AAANDD the other color has the same color as front. 
		//	if(  [i].color1 == top[2][1] && color2 == front[0][1]    OR ||    [i].color2 == top[2][1] && color1 == front[0][1]{   
		//		if(top[2][1] == top[1][1] {color is facing top} else {color is facing front} 

		//TOP FRONT
		if (((edges[i].color1 == top[2][1] && edges[i].color2 == front[0][1]) || (edges[i].color2 == top[2][1] && edges[i].color1 == front[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (top[2][1] == top[1][1]) { // the top color is on top
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				rotateBotCounterClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			} else { //the top color is on front
				rotateFrontClockwise(true);
				rotateRightClockwise(true);
			}
		}
		//TOP LEFT
		else if (((edges[i].color1 == top[1][0] && edges[i].color2 == left[0][1]) || (edges[i].color2 == top[1][0] && edges[i].color1 == left[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (top[1][0] == top[1][1]) { // the top color is on top
				rotateLeftCounterClockwise(true);
				rotateLeftCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			} else { //the top color is on front
				rotateLeftClockwise(true);
				rotateFrontCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			}
		}
		//TOP RIGHT
		else if (((edges[i].color1 == top[1][2] && edges[i].color2 == right[0][1]) || (edges[i].color2 == top[1][2] && edges[i].color1 == right[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (top[1][2] == top[1][1]) { // the top color is on top
				//DO NOTHING because this is the result we want
			} else { //the top color is on front
				rotateRightCounterClockwise(true);
				rotateTopClockwise(true);
				rotateFrontCounterClockwise(true);
				rotateTopCounterClockwise(true);
			}
		}
		//TOP BACK
		else if (((edges[i].color1 == top[0][1] && edges[i].color2 == back[0][1]) || (edges[i].color2 == top[0][1] && edges[i].color1 == back[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (top[1][0] == top[1][1]) { // the top color is on top
				rotateBackClockwise(true);
				rotateBackClockwise(true);
				rotateBotClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			} else { //the top color is on front
				rotateBackClockwise(true);
				rotateRightCounterClockwise(true);
			}
		}
		/* --------------------------------------------- ABOVE THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE TOP LAYER ------------------------------------------------------- */
		/* --------------------------------------------- BELOW THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE MID LAYER ------------------------------------------------------- */

		//FRONT LEFT
		if (((edges[i].color1 == front[1][0] && edges[i].color2 == left[1][2]) || (edges[i].color2 == front[1][0] && edges[i].color1 == left[1][2])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (front[1][0] == top[1][1]) { // the top color is on the front
				rotateLeftClockwise(true);
				rotateBotCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			} else { //the top color is on the left side
				rotateFrontCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
				rotateFrontClockwise(true);

			}
		}
		//FRONT RIGHT
		else if (((edges[i].color1 == front[1][2] && edges[i].color2 == right[1][0]) || (edges[i].color2 == front[1][2] && edges[i].color1 == right[1][0])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (front[1][2] == top[1][1]) { // the top color is the front
				rotateRightClockwise(true);
			} else { //the top color is on the right
				//topClockW -> frontCounter -> topCounterClock
				rotateTopClockwise(true);
				rotateFrontCounterClockwise(true);
				rotateTopCounterClockwise(true);
			}
		}
		//BACK RIGHT  !!!!!!!!!!!!!!!!???????????????????????????? still not solved idk
		else if (((edges[i].color1 == back[1][0] && edges[i].color2 == right[1][2]) || (edges[i].color2 == back[1][0] && edges[i].color1 == right[1][2])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (back[1][0] == top[1][1]) { // the top color is on the back
				//rotateRightClockwise(true);
				//rotateRightClockwise(true);
				//rotateRightClockwise(true);
				//rotateBotCounterClockwise(true);
				//rotateBotCounterClockwise(true);
				//rotateRightClockwise(true);
				//rotateRightClockwise(true);
				//rotateRightClockwise(true);
				rotateRightCounterClockwise(true);

			} else { //the top color is on the right
				rotateBackClockwise(true);
				rotateBotClockwise(true);
				rotateBackCounterClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			}
		}
		//BACK LEFT
		else if (((edges[i].color1 == back[1][2] && edges[i].color2 == left[1][0]) || (edges[i].color2 == back[1][2] && edges[i].color1 == left[1][0])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (back[1][2] == top[1][1]) { // the top color is on the back
				rotateTopClockwise(true);
				rotateTopClockwise(true);
				rotateLeftClockwise(true);
				rotateTopClockwise(true);
				rotateTopClockwise(true);				
			} else { //the top color is on the left
				rotateTopCounterClockwise(true);
				rotateBackClockwise(true);
				rotateTopClockwise(true);
			}
		}

		/* --------------------------------------------- ABOVE THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE MID LAYER ------------------------------------------------------- */
		/* ------------------------------------------- BELOW THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE BOTTOM LAYER ------------------------------------------------------ */

		//BOTTOM FRONT
		if (((edges[i].color1 == bottom[2][1] && edges[i].color2 == front[2][1]) || (edges[i].color2 == bottom[2][1] && edges[i].color1 == front[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (bottom[2][1] == top[1][1]) { // the top color is on bot
				//bottom counterclock -> right x 2
				rotateBotCounterClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			} else { //the top color is on front
				//front counterclock -> right x2 -> front clockwise
				rotateFrontCounterClockwise(true);
				rotateRightClockwise(true);
				rotateFrontClockwise(true);
			}
		}
		//BOTTOM LEFT
		else if (((edges[i].color1 == bottom[1][2] && edges[i].color2 == left[2][1]) || (edges[i].color2 == bottom[1][2] && edges[i].color1 == left[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (bottom[1][2] == top[1][1]) { // the top color is on bottom
				rotateBotCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);

			} else { //the top color is on left
				rotateBotCounterClockwise(true);
				rotateFrontCounterClockwise(true);
				rotateRightClockwise(true);
				rotateFrontClockwise(true);
			}
		}
		//BOTTOM RIGHT
		else if (((edges[i].color1 == bottom[1][0] && edges[i].color2 == right[2][1]) || (edges[i].color2 == bottom[1][0] && edges[i].color1 == right[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (bottom[1][0] == top[1][1]) { // the top color is on the bottom
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			} else { //the top color is on the right
				rotateBotClockwise(true);
				rotateFrontCounterClockwise(true);
				rotateRightClockwise(true);
				rotateFrontClockwise(true);

			}
		}
		//BOTTOM BACK
		else if (((edges[i].color1 == bottom[0][1] && edges[i].color2 == back[2][1]) || (edges[i].color2 == bottom[0][1] && edges[i].color1 == back[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == right[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == right[1][1]))) {
			if (bottom[0][1] == top[1][1]) { // the top color is on the bottom
				rotateBotClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			} else { //the top color is on the back
				//rotate back counterclock x1 -> rotateright counterclock -> rotate backk clock

				rotateBackCounterClockwise(true);
				rotateRightCounterClockwise(true);
				rotateBackClockwise(true);

			}
		}

		else {
			//System.out.println("why the fuck did we land here????");   - we're supposed to you dumbfuck
		}

	}

	public static void newSolveTopLeft(int i) {

		//assume we're working with the top right edge here:
		//if ONE (of two) color is connected to TOP and has THE SAME color as top (2nd isnt same as right, if so we skip) AAANDD the other color has the same color as front. 
		// if(  [i].color1 == top[2][1] && color2 == front[0][1]    OR ||    [i].color2 == top[2][1] && color1 == front[0][1]{   
		//  if(top[2][1] == top[1][1] {color is facing top} else {color is facing front} 

		//TOP FRONT
		if (((edges[i].color1 == top[2][1] && edges[i].color2 == front[0][1]) || (edges[i].color2 == top[2][1] && edges[i].color1 == front[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (top[2][1] == top[1][1]) { // the top color is on top
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				rotateBotClockwise(true);
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);
				//front x2 -> bottom clockwise -> left x2

			} else { //the top color is on front
				//front counterclock -> left counterclock
				rotateFrontCounterClockwise(true);
				rotateLeftCounterClockwise(true);

			}
		}
		//TOP LEFT
		else if (((edges[i].color1 == top[1][0] && edges[i].color2 == left[0][1]) || (edges[i].color2 == top[1][0] && edges[i].color1 == left[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (top[1][0] == top[1][1]) { // the top color is on top
				//DO NOTHING THIS IS WHAT WE WANT
			} else { //the top color is on front
				//left clockwise -> top counterclock -> front clockwise -> top clock -
				rotateLeftClockwise(true);
				rotateTopCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateTopClockwise(true);

			}
		}
		//TOP RIGHT
		else if (((edges[i].color1 == top[1][2] && edges[i].color2 == right[0][1]) || (edges[i].color2 == top[1][2] && edges[i].color1 == right[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (top[1][2] == top[1][1]) { // the top color is on top
				// right x2 -> bot x2 -> left x2
				rotateRightClockwise(true);
				rotateRightClockwise(true);
				rotateBotClockwise(true);
				rotateBotClockwise(true);
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);

			} else { //the top color is on front
				//right x2 -> bot clockwise -> front clock -> left counterclock -> fron counterclock
				rotateRightClockwise(true);
				rotateRightClockwise(true);
				rotateBotClockwise(true);
				rotateFrontClockwise(true);
				rotateLeftCounterClockwise(true);
				rotateFrontCounterClockwise(true);
			}
		}
		//TOP BACK
		else if (((edges[i].color1 == top[0][1] && edges[i].color2 == back[0][1]) || (edges[i].color2 == top[0][1] && edges[i].color1 == back[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (top[0][1] == top[1][1]) { // the top color is on top
				//back x2 -> bot counterclock -> right x2
				rotateBackClockwise(true);
				rotateBackClockwise(true);
				rotateBotCounterClockwise(true);
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);

			} else { //the top color is on back
				//back counterclock -> left clockwise  
				rotateBackCounterClockwise(true);
				rotateLeftClockwise(true);

			}
		}
		/* --------------------------------------------- ABOVE THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE TOP LAYER ------------------------------------------------------- */
		/* --------------------------------------------- BELOW THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE MID LAYER ------------------------------------------------------- */

		//FRONT LEFT
		if (((edges[i].color1 == front[1][0] && edges[i].color2 == left[1][2]) || (edges[i].color2 == front[1][0] && edges[i].color1 == left[1][2])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (front[1][0] == top[1][1]) { // the top color is on the front
				//left counterclock x1
				rotateLeftCounterClockwise(true);
			} else { //the top color is on the left side
				//top counterclock -> front clock -> top clock
				rotateTopCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateTopClockwise(true);
			}
		}
		//FRONT RIGHT
		else if (((edges[i].color1 == front[1][2] && edges[i].color2 == right[1][0]) || (edges[i].color2 == front[1][2] && edges[i].color1 == right[1][0])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (front[1][2] == top[1][1]) { // the top color is the front
				//front x2 -> left counterclock -> front x2

				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				rotateLeftCounterClockwise(true);
				rotateFrontClockwise(true);
				rotateFrontClockwise(true);

			} else { //the top color is on the right
				//top counterclock -> front counterclock -> top clock
				rotateTopCounterClockwise(true);
				rotateFrontCounterClockwise(true);
				rotateTopClockwise(true);

			}
		}
		//BACK RIGHT
		else if (((edges[i].color1 == back[1][0] && edges[i].color2 == right[1][2]) || (edges[i].color2 == back[1][0] && edges[i].color1 == right[1][2])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (back[1][0] == top[1][1]) { // the top color is on the back
				//right clock -> bot x2 -> right counterclock -> left x2

				rotateRightClockwise(true);
				rotateBotClockwise(true);
				rotateBotClockwise(true);
				rotateRightCounterClockwise(true);
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);

			} else { //the top color is on the right
				//top clock -> back counterclock -> top counterclock
				rotateTopClockwise(true);
				rotateBackCounterClockwise(true);
				rotateTopCounterClockwise(true);

			}
		}
		//BACK LEFT
		else if (((edges[i].color1 == back[1][2] && edges[i].color2 == left[1][0]) || (edges[i].color2 == back[1][2] && edges[i].color1 == left[1][0])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (back[1][2] == top[1][1]) { // the top color is on the back
				//left clock
				rotateLeftClockwise(true);
			} else { //the top color is on the left
				// top clock -> back clock -> top counterclock
				rotateTopClockwise(true);
				rotateBackClockwise(true);
				rotateTopCounterClockwise(true);
			}
		}

		/* --------------------------------------------- ABOVE THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE MID LAYER ------------------------------------------------------- */
		/* ------------------------------------------- BELOW THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE BOTTOM LAYER ------------------------------------------------------ */

		//BOTTOM FRONT
		if (((edges[i].color1 == bottom[2][1] && edges[i].color2 == front[2][1]) || (edges[i].color2 == bottom[2][1] && edges[i].color1 == front[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (bottom[2][1] == top[1][1]) { // the top color is on bot
				//bot clockwise -> left x2
				rotateBotClockwise(true);
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);

			} else { //the top color is on front
				//front clockwise -> left counterclock -> front counterclock
				rotateFrontClockwise(true);
				rotateLeftCounterClockwise(true);
				rotateFrontCounterClockwise(true);
			}
		}
		//BOTTOM LEFT
		else if (((edges[i].color1 == bottom[1][2] && edges[i].color2 == left[2][1]) || (edges[i].color2 == bottom[1][2] && edges[i].color1 == left[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (bottom[1][2] == top[1][1]) { // the top color is on bottom
				//left x 2 
				rotateLeftCounterClockwise(true);
				rotateLeftCounterClockwise(true);

			} else { //the top color is on left
				//left clock -> top clock -> back clock -> top counterclock
				rotateLeftClockwise(true);
				rotateTopClockwise(true);
				rotateBackClockwise(true);
				rotateTopCounterClockwise(true);
			}
		}
		//BOTTOM RIGHT
		else if (((edges[i].color1 == bottom[1][0] && edges[i].color2 == right[2][1]) || (edges[i].color2 == bottom[1][0] && edges[i].color1 == right[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (bottom[1][0] == top[1][1]) { // the top color is on the bottom
				//bot x2 -> left x2 
				rotateBotClockwise(true);
				rotateBotClockwise(true);
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);

			} else { //the top color is on the right
				// bot clockwise -> front clock -> left counterclock -> front counterclock
				rotateBotClockwise(true);
				rotateFrontClockwise(true);
				rotateLeftCounterClockwise(true);
				rotateFrontCounterClockwise(true);
			}
		}
		//BOTTOM BACK
		else if (((edges[i].color1 == bottom[0][1] && edges[i].color2 == back[2][1]) || (edges[i].color2 == bottom[0][1] && edges[i].color1 == back[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == left[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == left[1][1]))) {
			if (bottom[0][1] == top[1][1]) { // the top color is on the bottom    
				//bot counterclock -> left x2
				rotateBotCounterClockwise(true);
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);

			} else { //the top color is on the back
				//back clock -> left clock -> back counterclock

				rotateBackClockwise(true);
				rotateLeftClockwise(true);
				rotateBackCounterClockwise(true);

			}
		} else {
			//should not rly have to do anything
		}

	}

	public static void newSolverTopBack(int i) {
		//assume we're working with the top right edge here:
		//if ONE (of two) color is connected to TOP and has THE SAME color as top (2nd isnt same as right, if so we skip) AAANDD the other color has the same color as front. 
		//	if(  [i].color1 == top[2][1] && color2 == front[0][1]    OR ||    [i].color2 == top[2][1] && color1 == front[0][1]{   
		//		if(top[2][1] == top[1][1] {color is facing top} else {color is facing front} 
		//TOP FRONT
		if (((edges[i].color1 == top[2][1] && edges[i].color2 == front[0][1]) || (edges[i].color2 == top[2][1] && edges[i].color1 == front[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (top[2][1] == top[1][1]) { // the top color is on top

				rotateFrontClockwise(true);
				rotateFrontClockwise(true);
				rotateBotClockwise(true);
				rotateBotClockwise(true);
				rotateBackClockwise(true);
				rotateBackClockwise(true);
				//front x2 -> bot x2 -> back x2
			} else { //the top color is on front
				//rotate front clock -> rotate top clock -> rotate right -> rotate top counterclock -> right counterclock -> front counterclock
				System.out.println("WE ARE NEVER GONNA SEE THIS PRINT EVER - Todd 2k16");
				rotateFrontClockwise(true);
				rotateTopClockwise(true);
				rotateRightClockwise(true);
				rotateTopCounterClockwise(true);
				rotateRightCounterClockwise(true);

			}
		}
		//TOP LEFT
		else if (((edges[i].color1 == top[1][0] && edges[i].color2 == left[0][1]) || (edges[i].color2 == top[1][0] && edges[i].color1 == left[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (top[1][0] == top[1][1]) { // the top color is on top
				//left x2 -> bot clock -> back x2
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);
				rotateBotClockwise(true);
				rotateBackClockwise(true);
				rotateBackClockwise(true);

			} else { //the top color is on front
				//left counterclock -> back clock
				rotateLeftCounterClockwise(true);
				rotateBackClockwise(true);

			}
		}
		//TOP RIGHT
		else if (((edges[i].color1 == top[1][2] && edges[i].color2 == right[0][1]) || (edges[i].color2 == top[1][2] && edges[i].color1 == right[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {
			
			if (top[1][2] == top[1][1]) { // the top color is on top
				//right x2 -> bot coounterclock -> back x2

				rotateRightClockwise(true);
				rotateRightClockwise(true);
				rotateBotCounterClockwise(true);
				rotateBackClockwise(true);
				rotateBackClockwise(true);

			} else { //the top color is on front
				//right -> back counterclock
				rotateRightClockwise(true);
				rotateBackCounterClockwise(true);
			}
		}
		//TOP BACK
		else if (((edges[i].color1 == top[0][1] && edges[i].color2 == back[0][1]) || (edges[i].color2 == top[0][1] && edges[i].color1 == back[0][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (top[0][1] == top[1][1]) { // the top color is on top
				//NOTHING THIS IS WHJAT WE WANT
			} else { //the top color is on back
				//back counterclock -> top counterlock > left clock -> top clock 
				rotateBackCounterClockwise(true);
				rotateTopCounterClockwise(true);
				rotateLeftClockwise(true);
				rotateTopClockwise(true);
			}
		}
		/* --------------------------------------------- ABOVE THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE TOP LAYER ------------------------------------------------------- */
		/* --------------------------------------------- BELOW THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE MID LAYER ------------------------------------------------------- */

		//FRONT LEFT
		if (((edges[i].color1 == front[1][0] && edges[i].color2 == left[1][2]) || (edges[i].color2 == front[1][0] && edges[i].color1 == left[1][2])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (front[1][0] == top[1][1]) { // the top color is on the front
				//left clock -> bot clock -> back x2 -> left counterclock
				rotateLeftClockwise(true);
				rotateBotClockwise(true);
				rotateBackClockwise(true);
				rotateBackClockwise(true);
				rotateLeftCounterClockwise(true);
			} else { //the top color is on the left side
				//left x2 -> back clock -> left x2
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);
				rotateBackClockwise(true);
				rotateLeftClockwise(true);
				rotateLeftClockwise(true);
			}
		}
		//FRONT RIGHT
		else if (((edges[i].color1 == front[1][2] && edges[i].color2 == right[1][0]) || (edges[i].color2 == front[1][2] && edges[i].color1 == right[1][0])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (front[1][2] == top[1][1]) { // the top color is the front
				//right counterclock -> bot counterclock -> back x2 -> right clock 
				rotateRightCounterClockwise(true);
				rotateBotCounterClockwise(true);
				rotateBackCounterClockwise(true);
				rotateBackCounterClockwise(true);
				rotateRightClockwise(true);
			} else { //the top color is on the right
				rotateRightClockwise(true);
				rotateRightClockwise(true);
				rotateBackCounterClockwise(true);
				rotateRightClockwise(true);
				rotateRightClockwise(true);
			}
		}
		//BACK RIGHT
		else if (((edges[i].color1 == back[1][0] && edges[i].color2 == right[1][2]) || (edges[i].color2 == back[1][0] && edges[i].color1 == right[1][2])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (back[1][0] == top[1][1]) { // the top color is on the back
				//top clock -> right counterclock -> top counterclock
				rotateTopClockwise(true);
				rotateRightCounterClockwise(true);
				rotateTopCounterClockwise(true);
			} else { //the top color is on the right
				//back counterclock
				System.out.println("XDDDDDDDDDD");
				rotateBackCounterClockwise(true);
			}
		}
		//BACK LEFT
		else if (((edges[i].color1 == back[1][2] && edges[i].color2 == left[1][0]) || (edges[i].color2 == back[1][2] && edges[i].color1 == left[1][0])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (back[1][2] == top[1][1]) { // the top color is on the back
				//top counterclock -> left clock -> top clock
				rotateTopCounterClockwise(true);
				rotateLeftClockwise(true);
				rotateTopClockwise(true);
			} else { //the top color is on the left
				//back clock
				rotateBackClockwise(true);
			}
		}

		/* --------------------------------------------- ABOVE THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE MID LAYER ------------------------------------------------------- */
		/* ------------------------------------------- BELOW THIS IS SOLVING IF THE TOP RIGHT PIECE IS IN THE BOTTOM LAYER ------------------------------------------------------ */

		//BOTTOM FRONT
		if (((edges[i].color1 == bottom[2][1] && edges[i].color2 == front[2][1]) || (edges[i].color2 == bottom[2][1] && edges[i].color1 == front[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (bottom[2][1] == top[1][1]) { // the top color is on bot
				//bot x2 -> back x2
				rotateBotClockwise(true);
				rotateBotClockwise(true);
				rotateBackClockwise(true);
				rotateBackClockwise(true);
			} else { //the top color is on front
				//bot counterclock -> right counterclock -> back counterclock -> right clock
				rotateBotCounterClockwise(true);
				rotateRightCounterClockwise(true);
				rotateBackCounterClockwise(true);
				rotateRightClockwise(true);
			}
		}
		//BOTTOM LEFT
		else if (((edges[i].color1 == bottom[1][2] && edges[i].color2 == left[2][1]) || (edges[i].color2 == bottom[1][2] && edges[i].color1 == left[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {
			if (bottom[1][2] == top[1][1]) { // the top color is on bottom
				//bot clock -> back x2
				rotateBotClockwise(true);
				rotateBackClockwise(true);
				rotateBackClockwise(true);
			} else { //the top color is on left
				//left clock -> back clock -> left counterclock
				rotateLeftClockwise(true);
				rotateBackClockwise(true);
				rotateLeftCounterClockwise(true);
			}
		}
		//BOTTOM RIGHT
		else if (((edges[i].color1 == bottom[1][0] && edges[i].color2 == right[2][1]) || (edges[i].color2 == bottom[1][0] && edges[i].color1 == right[2][1])) && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {

			if (bottom[1][0] == top[1][1]) { // the top color is on the bottom
				//bot counterclock -> back x2 
				rotateBotCounterClockwise(true);
				rotateBackClockwise(true);
				rotateBackClockwise(true);
			} else { //the top color is on the right
				//right counterclock -> back counterclock -> right clock
				rotateRightCounterClockwise(true);
				rotateBackCounterClockwise(true);
				rotateRightClockwise(true);
			}
		}
		//BOTTOM BACK
		else if (((edges[i].color1 == bottom[0][1] && edges[i].color2 == back[2][1]) || (edges[i].color2 == bottom[0][1] && edges[i].color1 == back[2][1])) 
				 && ((edges[i].color1 == top[1][1] && edges[i].color2 == back[1][1]) || (edges[i].color2 == top[1][1] && edges[i].color1 == back[1][1]))) {
			

			//System.out.println("AIDS: " + edges[i].color1 + "  / " +edges[i].color2);
			if (bottom[0][1] == top[1][1]) { // the top color is on the bottom				
				//back x2
				rotateBackClockwise(true);
				rotateBackClockwise(true);
			} else { //the top color is on the back
				//back clock -> top counterclock -> left counterclock -> top clock 
				rotateBackClockwise(true);
				rotateTopCounterClockwise(true);
				rotateLeftClockwise(true);
				rotateTopClockwise(true);
				//back counterclockwise  ->  top counterclockwise -> left clockwise -> top clockwise
				//rotateBackCounterClockwise(true);

			}
		} else {
		}
	}

	public static void solvedInitCube() {
		// static Color[][] front, back , top , bot , left , right;
		front = new C[3][3];
		back = new C[3][3];
		top = new C[3][3];
		bottom = new C[3][3];
		left = new C[3][3];
		right = new C[3][3];

		top[0][0] = C.BLUE;
		top[0][1] = C.BLUE;
		top[0][2] = C.BLUE;
		top[1][0] = C.BLUE;
		top[1][1] = C.BLUE;
		top[1][2] = C.BLUE;
		top[2][0] = C.BLUE;
		top[2][1] = C.BLUE;
		top[2][2] = C.BLUE;

		front[0][0] = C.WHITE;
		front[0][1] = C.WHITE;
		front[0][2] = C.WHITE;
		front[1][0] = C.WHITE;
		front[1][1] = C.WHITE;
		front[1][2] = C.WHITE;
		front[2][0] = C.WHITE;
		front[2][1] = C.WHITE;
		front[2][2] = C.WHITE;

		bottom[0][0] = C.GREEN;
		bottom[0][1] = C.GREEN;
		bottom[0][2] = C.GREEN;
		bottom[1][0] = C.GREEN;
		bottom[1][1] = C.GREEN;
		bottom[1][2] = C.GREEN;
		bottom[2][0] = C.GREEN;
		bottom[2][1] = C.GREEN;
		bottom[2][2] = C.GREEN;

		left[0][0] = C.ORANGE;
		left[0][1] = C.ORANGE;
		left[0][2] = C.ORANGE;
		left[1][0] = C.ORANGE;
		left[1][1] = C.ORANGE;
		left[1][2] = C.ORANGE;
		left[2][0] = C.ORANGE;
		left[2][1] = C.ORANGE;
		left[2][2] = C.ORANGE;

		right[0][0] = C.RED;
		right[0][1] = C.RED;
		right[0][2] = C.RED;
		right[1][0] = C.RED;
		right[1][1] = C.RED;
		right[1][2] = C.RED;
		right[2][0] = C.RED;
		right[2][1] = C.RED;
		right[2][2] = C.RED;

		back[0][0] = C.YELLOW;
		back[0][1] = C.YELLOW;
		back[0][2] = C.YELLOW;
		back[1][0] = C.YELLOW;
		back[1][1] = C.YELLOW;
		back[1][2] = C.YELLOW;
		back[2][0] = C.YELLOW;
		back[2][1] = C.YELLOW;
		back[2][2] = C.YELLOW;

	}
	
	
	public static void topSolvedInitCube() {
		// static Color[][] front, back , top , bot , left , right;
		front = new C[3][3];
		back = new C[3][3];
		top = new C[3][3];
		bottom = new C[3][3];
		left = new C[3][3];
		right = new C[3][3];

		top[0][0] = C.BLUE;
		top[0][1] = C.BLUE;
		top[0][2] = C.WHITE;
		top[1][0] = C.BLUE;
		top[1][1] = C.BLUE;
		top[1][2] = C.WHITE;
		top[2][0] = C.RED;
		top[2][1] = C.RED;
		top[2][2] = C.YELLOW;

		front[0][0] = C.BLUE;
		front[0][1] = C.WHITE;
		front[0][2] = C.ORANGE;
		front[1][0] = C.BLUE;
		front[1][1] = C.WHITE;
		front[1][2] = C.WHITE;
		front[2][0] = C.WHITE;
		front[2][1] = C.WHITE;
		front[2][2] = C.WHITE;

		bottom[0][0] = C.GREEN;
		bottom[0][1] = C.GREEN;
		bottom[0][2] = C.GREEN;
		bottom[1][0] = C.GREEN;
		bottom[1][1] = C.GREEN;
		bottom[1][2] = C.GREEN;
		bottom[2][0] = C.GREEN;
		bottom[2][1] = C.GREEN;
		bottom[2][2] = C.GREEN;

		left[0][0] = C.WHITE;
		left[0][1] = C.RED;
		left[0][2] = C.YELLOW;
		left[1][0] = C.ORANGE;
		left[1][1] = C.ORANGE;
		left[1][2] = C.YELLOW;
		left[2][0] = C.ORANGE;
		left[2][1] = C.ORANGE;
		left[2][2] = C.ORANGE;

		right[0][0] = C.BLUE;
		right[0][1] = C.BLUE;
		right[0][2] = C.RED;
		right[1][0] = C.ORANGE;
		right[1][1] = C.RED;
		right[1][2] = C.RED;
		right[2][0] = C.RED;
		right[2][1] = C.RED;
		right[2][2] = C.RED;

		back[0][0] = C.BLUE;
		back[0][1] = C.ORANGE;
		back[0][2] = C.ORANGE;
		back[1][0] = C.YELLOW;
		back[1][1] = C.YELLOW;
		back[1][2] = C.YELLOW;
		back[2][0] = C.YELLOW;
		back[2][1] = C.YELLOW;
		back[2][2] = C.YELLOW;

	}
	
	public static void rotateRightClockwise(boolean print) {
		if(print)
			userAlgorithm += " R";
		// CLOCKWISE
		if(printAlgorithms)
		System.out.println("Rotatng RIGHT CLOCKWISE");
		/*
		 * ^ | * * * * * * * * *
		 * 
		 * 
		 */

		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = front[i][2];
			FirstColor = top[i][2];
			top[i][2] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = back[2 - i][0];
			back[2 - i][0] = FirstColor;

			FirstColor = bottom[2 - i][0];
			bottom[2 - i][0] = SecondColor;
			front[i][2] = FirstColor;
		}

		// IS Kò
		FirstColor = right[0][2];
		right[0][2] = right[0][0];
		SecondColor = right[2][2];
		right[2][2] = FirstColor;
		FirstColor = right[2][0];
		right[2][0] = SecondColor;
		right[0][0] = FirstColor;

		FirstColor = right[1][2];
		right[1][2] = right[0][1];
		SecondColor = right[2][1];
		right[2][1] = FirstColor;
		FirstColor = right[1][0];
		right[1][0] = SecondColor;
		right[0][1] = FirstColor;

		refreshEdges();
	}

	public static void rotateRightCounterClockwise(boolean print) {
		if(print)
		userAlgorithm += " R'";

		rotateRightClockwise(false);
		rotateRightClockwise(false);
		rotateRightClockwise(false);

	}

	public static void rotateLeftCounterClockwise(boolean print) {
		if(print)
		userAlgorithm += " L'";
		
		if(printAlgorithms)
		System.out.println("Rotatng LEFT COUNTERCLOCKWISE");

		/*
		 * ^ | * * * * * * * * *
		 * 
		 * 
		 */

		// COUNTER-CLOCKWISE
		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = front[i][0];
			FirstColor = top[i][0];
			top[i][0] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = back[2 - i][2];
			back[2 - i][2] = FirstColor;

			FirstColor = bottom[2 - i][2];
			bottom[2 - i][2] = SecondColor;
			front[i][0] = FirstColor;
		}

		// IS Kò

		FirstColor = left[0][0];
		left[0][0] = left[0][2];
		SecondColor = left[2][0];
		left[2][0] = FirstColor;
		FirstColor = left[2][2];
		left[2][2] = SecondColor;
		left[0][2] = FirstColor;

		FirstColor = left[1][0];
		left[1][0] = left[0][1];
		SecondColor = left[2][1];
		left[2][1] = FirstColor;

		FirstColor = left[1][2];
		left[1][2] = SecondColor;
		left[0][1] = FirstColor;
		refreshEdges();
	}

	public static void rotateLeftClockwise(boolean print) {
		
		if(print)
			userAlgorithm += " L";

		rotateLeftCounterClockwise(false);
		rotateLeftCounterClockwise(false);
		rotateLeftCounterClockwise(false);

	}

	public static void rotateMidUp(boolean print) {
		if(print)
		userAlgorithm += " MU";
		
		if(printAlgorithms)
		System.out.println("Rotating MID UP (whatever the fuck that means)");

		/*
		 * ^ | * * * * * * * * *
		 * 
		 * 
		 */

		// CLOCKWISE
		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = front[i][1];
			FirstColor = top[i][1];
			top[i][1] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = back[2 - i][1];
			back[2 - i][1] = FirstColor;

			FirstColor = bottom[2 - i][1];
			bottom[2 - i][1] = SecondColor;
			front[i][1] = FirstColor;
		}
		refreshEdges();
	}

	public static void rotateMidDown(boolean print) {
		if(print)
			userAlgorithm += " MD";
	
		rotateMidUp(false);
		rotateMidUp(false);
		rotateMidUp(false);

	}

	public static void rotateTopCounterClockwise(boolean print) {
		if(print)
			userAlgorithm += " T'";

		if(printAlgorithms)
		System.out.println("Rotating TOP COUNTERCLOCKWISE");

		/*
		 * * * * --> * * * * * *
		 * 
		 * 
		 */

		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = front[0][i];
			FirstColor = right[0][i];
			right[0][i] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = back[0][i];
			back[0][i] = FirstColor;

			FirstColor = left[0][i];
			left[0][i] = SecondColor;
			front[0][i] = FirstColor;
		}

		FirstColor = top[0][0];
		top[0][0] = top[0][2];
		SecondColor = top[2][0];
		top[2][0] = FirstColor;
		FirstColor = top[2][2];
		top[2][2] = SecondColor;
		top[0][2] = FirstColor;

		FirstColor = top[1][0];
		top[1][0] = top[0][1];
		SecondColor = top[2][1];
		top[2][1] = FirstColor;

		FirstColor = top[1][2];
		top[1][2] = SecondColor;
		top[0][1] = FirstColor;

		refreshEdges();
	}

	public static void rotateTopClockwise(boolean print) {
		if(print)
			userAlgorithm += " T";
		
		rotateTopCounterClockwise(false);
		rotateTopCounterClockwise(false);
		rotateTopCounterClockwise(false);

	}

	public static void rotateMidCounterClockwise(boolean print) {
		if(print)
			userAlgorithm += " MCC";
		
		if(printAlgorithms)
		System.out.println("Rotating MID COUNTERCLOCKWISE");

		/*
		 * * * * * * * --> * * *
		 * 
		 * 
		 */

		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = front[1][i];
			FirstColor = right[1][i];
			right[1][i] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = back[1][i];
			back[1][i] = FirstColor;

			FirstColor = left[1][i];
			left[1][i] = SecondColor;
			front[1][i] = FirstColor;
		}

		refreshEdges();
	}

	public static void rotateBotCounterClockwise(boolean print) {
		if(print)
			userAlgorithm += " Bot'";
		
		if(printAlgorithms)
		System.out.println("Rotating BOT COUNTERCLOCKWISE");

		/*
		 * * * * * * * * * * -->
		 * 
		 * 
		 */

		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = front[2][i];
			FirstColor = right[2][i];
			right[2][i] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = back[2][i];
			back[2][i] = FirstColor;

			FirstColor = left[2][i];
			left[2][i] = SecondColor;
			front[2][i] = FirstColor;
		}

		FirstColor = bottom[0][2];
		bottom[0][2] = bottom[0][0];
		SecondColor = bottom[2][2];
		bottom[2][2] = FirstColor;
		FirstColor = bottom[2][0];
		bottom[2][0] = SecondColor;
		bottom[0][0] = FirstColor;

		FirstColor = bottom[1][2];
		bottom[1][2] = bottom[0][1];
		SecondColor = bottom[2][1];
		bottom[2][1] = FirstColor;
		FirstColor = bottom[1][0];
		bottom[1][0] = SecondColor;
		bottom[0][1] = FirstColor;

		refreshEdges();
	}

	public static void rotateBotClockwise(boolean print) {
		if(print)
			userAlgorithm += " Bot";
		
		rotateBotCounterClockwise(false);
		rotateBotCounterClockwise(false);
		rotateBotCounterClockwise(false);

	}

	public static void rotateFrontClockwise(boolean print) {
		if(print)
			userAlgorithm += " F";
		
		if(printAlgorithms)
		System.out.println("Rotating FRONT CLOCKWISE");

		/*
		 * -------> * * *| * * *| * * *| V
		 * 
		 */

		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = top[2][i];
			FirstColor = right[i][0];
			right[i][0] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = bottom[2][i];
			bottom[2][i] = FirstColor;

			FirstColor = left[2 - i][2];

			left[2 - i][2] = SecondColor;
			top[2][i] = FirstColor;
		}

		FirstColor = front[0][2];
		front[0][2] = front[0][0];
		SecondColor = front[2][2];
		front[2][2] = FirstColor;
		FirstColor = front[2][0];
		front[2][0] = SecondColor;
		front[0][0] = FirstColor;

		FirstColor = front[1][2];
		front[1][2] = front[0][1];
		SecondColor = front[2][1];
		front[2][1] = FirstColor;
		FirstColor = front[1][0];
		front[1][0] = SecondColor;
		front[0][1] = FirstColor;

		refreshEdges();
	}

	public static void rotateFrontCounterClockwise(boolean print) {
		if(print)
			userAlgorithm += " F'";
		rotateFrontClockwise(false);
		rotateFrontClockwise(false);
		rotateFrontClockwise(false);

	}

	public static void rotateMidClockwise(boolean print) {
		if(print)
			userAlgorithm += " M";
		if(printAlgorithms)
		System.out.println("Rotating MID CLOCKWISE");

		/*
		 * -------> * * *| * * *| * * *| V
		 * 
		 */

		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = top[1][i];
			FirstColor = right[i][1];
			right[i][1] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = bottom[1][i];
			bottom[1][i] = FirstColor;

			FirstColor = left[2 - i][1];
			left[2 - i][1] = SecondColor;
			top[1][i] = FirstColor;
		}
		refreshEdges();
	}

	public static void rotateBackClockwise(boolean print) {
		if(print)
			userAlgorithm += " B";
		// looking from the front
		if(printAlgorithms)
		System.out.println("Rotating BACK CLOCKWISE");

		/*
		 * -------> * * *| * * *| * * *| V
		 * 
		 */

		C FirstColor;
		C SecondColor;
		for (int i = 0; i < 3; i++) {
			SecondColor = top[0][i];
			FirstColor = right[i][2];
			right[i][2] = SecondColor;

			// we dont want to mirror on first rotation, BUT ON the 2nd time,
			// NOT on the 3rd time AND ON THE 4th time
			// WE ONLY MIRROR WHEN BACK GETS ITS VALUE FROM TOP AND WHEN FRONT
			// GETS ITS VALUE FROM BOTTOM

			SecondColor = bottom[0][i];
			bottom[0][i] = FirstColor;

			FirstColor = left[2 - i][0];
			left[2 - i][0] = SecondColor;
			top[0][i] = FirstColor;
		}

		FirstColor = back[0][0];
		back[0][0] = back[0][2];
		SecondColor = back[2][0];
		back[2][0] = FirstColor;
		FirstColor = back[2][2];
		back[2][2] = SecondColor;
		back[0][2] = FirstColor;

		FirstColor = back[1][0];
		back[1][0] = back[0][1];
		SecondColor = back[2][1];
		back[2][1] = FirstColor;

		FirstColor = back[1][2];
		back[1][2] = SecondColor;
		back[0][1] = FirstColor;

		refreshEdges();
	}

	public static void rotateBackCounterClockwise(boolean print) {
		if(print)
			userAlgorithm += " B'";
		rotateBackClockwise(false);
		rotateBackClockwise(false);
		rotateBackClockwise(false);

	}

	public static void initCube() {
		// static Color[][] front, back , top , bot , left , right;
		front = new C[3][3];
		back = new C[3][3];
		top = new C[3][3];
		bottom = new C[3][3];
		left = new C[3][3];
		right = new C[3][3];

		top[0][0] = C.RED;
		top[0][1] = C.BLUE;
		top[0][2] = C.RED;
		top[1][0] = C.GREEN;
		top[1][1] = C.RED;
		top[1][2] = C.WHITE;
		top[2][0] = C.GREEN;
		top[2][1] = C.RED;
		top[2][2] = C.BLUE;

		front[0][0] = C.RED;
		front[0][1] = C.YELLOW;
		front[0][2] = C.RED;
		front[1][0] = C.ORANGE;
		front[1][1] = C.WHITE;
		front[1][2] = C.BLUE;
		front[2][0] = C.BLUE;
		front[2][1] = C.YELLOW;
		front[2][2] = C.ORANGE;

		bottom[0][0] = C.WHITE;
		bottom[0][1] = C.ORANGE;
		bottom[0][2] = C.YELLOW;
		bottom[1][0] = C.RED;
		bottom[1][1] = C.ORANGE;
		bottom[1][2] = C.YELLOW;
		bottom[2][0] = C.GREEN;
		bottom[2][1] = C.ORANGE; 
		bottom[2][2] = C.ORANGE;

		right[0][0] = C.YELLOW;
		right[0][1] = C.ORANGE;
		right[0][2] = C.WHITE;
		right[1][0] = C.WHITE;
		right[1][1] = C.GREEN;
		right[1][2] = C.YELLOW;
		right[2][0] = C.WHITE;
		right[2][1] = C.WHITE;
		right[2][2] = C.ORANGE;

		left[0][0] = C.WHITE;
		left[0][1] = C.WHITE;
		left[0][2] = C.YELLOW;
		left[1][0] = C.RED;
		left[1][1] = C.BLUE;
		left[1][2] = C.GREEN;
		left[2][0] = C.GREEN;
		left[2][1] = C.BLUE;
		left[2][2] = C.YELLOW;

		back[0][0] = C.GREEN;
		back[0][1] = C.RED;
		back[0][2] = C.BLUE;
		back[1][0] = C.GREEN;
		back[1][1] = C.YELLOW;
		back[1][2] = C.GREEN;
		back[2][0] = C.BLUE;
		back[2][1] = C.BLUE;
		back[2][2] = C.ORANGE;

	}
	
	public static void initTestCube() {
		// static Color[][] front, back , top , bot , left , right;
		front = new C[3][3];
		back = new C[3][3];
		top = new C[3][3];
		bottom = new C[3][3];
		left = new C[3][3];
		right = new C[3][3];

		top[0][0] = C.WHITE;
		top[0][1] = C.ORANGE;
		top[0][2] = C.RED;
		top[1][0] = C.RED;
		top[1][1] = C.RED;
		top[1][2] = C.YELLOW;
		top[2][0] = C.BLUE;
		top[2][1] = C.WHITE;
		top[2][2] = C.RED;

		front[0][0] = C.YELLOW;
		front[0][1] = C.GREEN;
		front[0][2] = C.BLUE;
		front[1][0] = C.BLUE;
		front[1][1] = C.WHITE;
		front[1][2] = C.YELLOW;
		front[2][0] = C.ORANGE;
		front[2][1] = C.WHITE; 
		front[2][2] = C.ORANGE;

		bottom[0][0] = C.RED;
		bottom[0][1] = C.WHITE;
		bottom[0][2] = C.BLUE;
		bottom[1][0] = C.GREEN;
		bottom[1][1] = C.ORANGE;
		bottom[1][2] = C.ORANGE;
		bottom[2][0] = C.YELLOW;
		bottom[2][1] = C.BLUE; //C.GREEN;
		bottom[2][2] = C.YELLOW;

		right[0][0] = C.WHITE;
		right[0][1] = C.RED;
		right[0][2] = C.WHITE;
		right[1][0] = C.GREEN;
		right[1][1] = C.GREEN;
		right[1][2] = C.GREEN;
		right[2][0] = C.GREEN;
		right[2][1] = C.ORANGE;
		right[2][2] = C.YELLOW;

		left[0][0] = C.GREEN;
		left[0][1] = C.WHITE;
		left[0][2] = C.RED;
		left[1][0] = C.RED;
		left[1][1] = C.BLUE;
		left[1][2] = C.YELLOW;
		left[2][0] = C.ORANGE;
		left[2][1] = C.YELLOW;
		left[2][2] = C.BLUE;

		back[0][0] = C.GREEN;
		back[0][1] = C.BLUE;
		back[0][2] = C.ORANGE;
		back[1][0] = C.RED;
		back[1][1] = C.YELLOW;
		back[1][2] = C.BLUE;
		back[2][0] = C.GREEN;
		back[2][1] = C.ORANGE;
		back[2][2] = C.WHITE;

	}
	
	public static void initCrossCube() {
		// static Color[][] front, back , top , bot , left , right;
		front = new C[3][3];
		back = new C[3][3];
		top = new C[3][3];
		bottom = new C[3][3];
		left = new C[3][3];
		right = new C[3][3];

		top[0][0] = C.YELLOW;
		top[0][1] = C.RED;
		top[0][2] = C.YELLOW;
		top[1][0] = C.RED;
		top[1][1] = C.RED;
		top[1][2] = C.RED;
		top[2][0] = C.RED;
		top[2][1] = C.RED;
		top[2][2] = C.WHITE;

		front[0][0] = C.WHITE;
		front[0][1] = C.WHITE;
		front[0][2] = C.ORANGE;
		front[1][0] = C.ORANGE;
		front[1][1] = C.WHITE;
		front[1][2] = C.WHITE;
		front[2][0] = C.RED;
		front[2][1] = C.ORANGE; 
		front[2][2] = C.WHITE;

		bottom[0][0] = C.RED;
		bottom[0][1] = C.GREEN;
		bottom[0][2] = C.BLUE;
		bottom[1][0] = C.BLUE;
		bottom[1][1] = C.ORANGE;
		bottom[1][2] = C.ORANGE;
		bottom[2][0] = C.BLUE;
		bottom[2][1] = C.WHITE;
		bottom[2][2] = C.YELLOW;

		right[0][0] = C.GREEN;
		right[0][1] = C.GREEN;
		right[0][2] = C.BLUE;
		right[1][0] = C.BLUE;
		right[1][1] = C.GREEN;
		right[1][2] = C.YELLOW;
		right[2][0] = C.ORANGE;
		right[2][1] = C.ORANGE;
		right[2][2] = C.GREEN;

		left[0][0] = C.ORANGE;
		left[0][1] = C.BLUE;
		left[0][2] = C.BLUE;
		left[1][0] = C.GREEN;
		left[1][1] = C.BLUE;
		left[1][2] = C.GREEN;
		left[2][0] = C.YELLOW;
		left[2][1] = C.YELLOW;
		left[2][2] = C.GREEN;

		back[0][0] = C.RED;
		back[0][1] = C.YELLOW;
		back[0][2] = C.GREEN;
		back[1][0] = C.BLUE;
		back[1][1] = C.YELLOW;
		back[1][2] = C.YELLOW;
		back[2][0] = C.WHITE;
		back[2][1] = C.WHITE;
		back[2][2] = C.ORANGE;

	}
	
	
	public static void notepadCube(){
		front = new C[3][3];
		back = new C[3][3];
		top = new C[3][3];
		bottom = new C[3][3];
		left = new C[3][3];
		right = new C[3][3];

		top[0][0] = C.GREEN;
		top[0][1] = C.ORANGE;
		top[0][2] = C.WHITE;
		top[1][0] = C.RED;
		top[1][1] = C.BLUE;
		top[1][2] = C.BLUE;
		top[2][0] = C.GREEN;
		top[2][1] = C.YELLOW;
		top[2][2] = C.GREEN;

		front[0][0] = C.WHITE;
		front[0][1] = C.ORANGE;
		front[0][2] = C.WHITE;
		front[1][0] = C.YELLOW;
		front[1][1] = C.WHITE;
		front[1][2] = C.BLUE;
		front[2][0] = C.BLUE;
		front[2][1] = C.RED; 
		front[2][2] = C.YELLOW;

		bottom[0][0] = C.ORANGE;
		bottom[0][1] = C.RED;
		bottom[0][2] = C.WHITE;
		bottom[1][0] = C.YELLOW;
		bottom[1][1] = C.GREEN;
		bottom[1][2] = C.WHITE;
		bottom[2][0] = C.BLUE;
		bottom[2][1] = C.WHITE;
		bottom[2][2] = C.ORANGE;

		right[0][0] = C.ORANGE;
		right[0][1] = C.ORANGE;
		right[0][2] = C.BLUE;
		right[1][0] = C.RED;
		right[1][1] = C.RED;
		right[1][2] = C.WHITE;
		right[2][0] = C.RED;
		right[2][1] = C.BLUE;
		right[2][2] = C.GREEN;

		left[0][0] = C.RED;
		left[0][1] = C.GREEN;
		left[0][2] = C.RED;
		left[1][0] = C.ORANGE;
		left[1][1] = C.ORANGE;
		left[1][2] = C.GREEN;
		left[2][0] = C.RED;
		left[2][1] = C.BLUE;
		left[2][2] = C.YELLOW;

		back[0][0] = C.ORANGE;
		back[0][1] = C.WHITE;
		back[0][2] = C.YELLOW;
		back[1][0] = C.GREEN;
		back[1][1] = C.YELLOW;
		back[1][2] = C.GREEN;
		back[2][0] = C.YELLOW;
		back[2][1] = C.YELLOW;
		back[2][2] = C.BLUE;

		
	}
	
	public static void printRubiks(){

		System.out.print("front ");
		printCube(front);
		System.out.println("\n---------");

		System.out.print("left ");
		printCube(left);
		System.out.println("\n---------");

		System.out.print("right ");
		printCube(right);
		System.out.println("\n---------");

		System.out.print("top ");
		printCube(top);
		System.out.println("\n---------");

		System.out.print("bottom ");
		printBotAndBack(bottom); 
		System.out.println("\n---------");

		System.out.print("back ");
		printCube(back);
		System.out.println("\n---------");

	}

	public static void printBotAndBack(C[][] cube) {
		for (int i = 2; i >= 0; i--) {
			System.out.println("");
			for (int j = 2; j >= 0; j--) {
				System.out.print(cube[i][j] + " ");
			}
		}
	}

	public static void printCube(C[][] cube) {
		for (int i = 0; i < 3; i++) {
			System.out.println("");
			for (int j = 0; j < 3; j++) {
				System.out.print(cube[i][j] + " ");
			}
		}

	}
	
}


