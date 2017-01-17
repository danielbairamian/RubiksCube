
public class Corner {

	 public MainRubiksSolver.C color1;
	 public MainRubiksSolver.C color2;
	 public MainRubiksSolver.C color3;
	 
	 
	 
	 public Corner(MainRubiksSolver.C c1, MainRubiksSolver.C c2, MainRubiksSolver.C c3 ){
		 
		 color1 = c1;
		 color2 = c2;
		 color3 = c3;
		 
	 }
	 
	 public void updateCorner(MainRubiksSolver.C c1, MainRubiksSolver.C c2, MainRubiksSolver.C c3 ){
		 color1 = c1;
		 color2 = c2;
		 color3 = c3;
	 }

	 
	
	
	
	
}



