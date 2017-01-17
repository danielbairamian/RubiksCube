
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import javax.swing.JButton;

public class GUI {

	public static JPanel panel = new JPanel();
	public static JButton button0 = new JButton("");
	public static JButton button1 = new JButton("");
	public static JButton button2 = new JButton("");
	public static JButton button3 = new JButton("");
	public static JButton button4 = new JButton("");
	public static JButton button5 = new JButton("");
	public static JButton button6 = new JButton("");
	public static JButton button7 = new JButton("");
	public static JButton button8 = new JButton("");
	public static JButton next = new JButton("Next");
	public static JButton done = new JButton("Done");
	public static JFrame window = new JFrame();
	public static JLabel sideLabel = new JLabel("");
	public static int side = 0;
	public static int Cube_colors[][] = new int[6][9];

	public static int holder[] = new int[9];

	public static void main(String[] args) {
		// TODO code application logic here
		// <editor­fold defaultstate="collapsed" desc="Window">
		final int WINDOW_WIDTH = 350;
		final int WINDOW_HEIGHT = 208;
		// create a window
		// set the title
		window.setTitle("Cncr");
		// set the sizse of the window
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// Specify what happens when you close the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// display the window
		window.setVisible(true);
		// make it not resizable
		window.setResizable(false);
		// </editor­fold>
		addStuff();
		// System.out.println("THIS");
		// you have to run the code 6 times one for each side

	}

	public static void addStuff() {
		panel.setLayout(null);
		window.add(panel);
		// add the text to tell the user what side is being worked on
		sideLabel.setText("This is the front");
		sideLabel.setLocation(178, 20);
		panel.add(sideLabel);
		sideLabel.setSize(100, 14);

		// add the buttons and put them in the right place

		// <editor­fold defaultstate="collapsed" desc="comment">

		panel.add(button0);
		button0.setBounds(10, 10, 46, 46);
		panel.add(button3);
		button3.setBounds(10, 66, 46, 46);
		panel.add(button6);
		button6.setBounds(10, 122, 46, 46);
		panel.add(button1);
		button1.setBounds(66, 10, 46, 46);
		panel.add(button4);
		button4.setBounds(66, 66, 46, 46);
		panel.add(button7);
		button7.setBounds(66, 122, 46, 46);
		panel.add(button2);
		button2.setBounds(122, 10, 46, 46);
		panel.add(button5);
		button5.setBounds(122, 66, 46, 46);
		panel.add(button8);
		button8.setBounds(122, 122, 46, 46);
		panel.add(next);
		next.setBounds(178, 98, 100, 30);
		panel.add(done);
		done.setBounds(178, 138, 100, 30);

		// </editor­fold>
		// chaneging the colors of the buttons and stuff
		// <editor­fold defaultstate="collapsed" desc="comment">

		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(0, button0);
			}

		});

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(1, button1);
			}

		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(2, button2);
			}

		});

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(3, button3);
			}

		});

		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(4, button4);
			}

		});

		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(5, button5);
			}

		});

		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(6, button6);
			}

		});

		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(7, button7);
			}

		});

		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(8, button8);
			}

		});

		

		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printThings();
			}
		});

		
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("clicked nextt");
				if (side == 5) {
					side = 0;
				} else {
					side++;
				}

				switch (side) {
				case 0:
					sideLabel.setText("This is the front");
					break;
				case 1:
					sideLabel.setText("This is the left side");
					break;
				case 2:
					sideLabel.setText("This is the right side");
					break;
				case 3:
					sideLabel.setText("This is the back");
					break;
				case 4:
					sideLabel.setText("This is the top");
					break;
				case 5:
					sideLabel.setText("This is the bottom");
					break;

				}

				updateSides();
			}
		});

	}

	public static void onClick(int bnr, JButton x) {
		System.out.println("You clicked button8");
		if (Cube_colors[side][bnr] == 5) {
			System.out.println("Cube_colors[" + side + "][bnr] WAS: " + Cube_colors[side][bnr]);
			Cube_colors[side][bnr] = 0;
			System.out.println("Cube_colors[" + side + "][bnr] IS NOW: " + Cube_colors[side][bnr]);
		} else {
			System.out.println("Cube_colors[" + side + "][bnr] WAS: " + Cube_colors[side][bnr]);
			Cube_colors[side][bnr]++;
			System.out.println("Cube_colors[" + side + "][bnr] IS NOW: " + Cube_colors[side][bnr]);
		}
		
		switch (Cube_colors[side][bnr]) {
		case 0:
			x.setBackground(Color.WHITE);
			break;
		case 1:
			x.setBackground(Color.YELLOW);
			break;
		case 2:
			x.setBackground(Color.ORANGE);
			break;
		case 3:
			x.setBackground(Color.RED);
			break;
		case 4:
			x.setBackground(Color.GREEN);
			break;
		case 5:
			x.setBackground(Color.BLUE);
			break;
		}
		/*
		if (bnr == 0) {
			switch (Cube_colors[side][0]) {
			case 0:
				x.setBackground(Color.WHITE);
				break;
			case 1:
				x.setBackground(Color.YELLOW);
				break;
			case 2:
				x.setBackground(Color.ORANGE);
				break;
			case 3:
				x.setBackground(Color.RED);
				break;
			case 4:
				x.setBackground(Color.GREEN);
				break;
			case 5:
				x.setBackground(Color.BLUE);
				break;
			}
		}
		

		if (bnr == 8) {
			switch (Cube_colors[side][8]) {
			case 0:
				button8.setBackground(Color.WHITE);
				break;
			case 1:
				button8.setBackground(Color.YELLOW);
				break;
			case 2:
				button8.setBackground(Color.ORANGE);
				break;
			case 3:
				button8.setBackground(Color.RED);
				break;
			case 4:
				button8.setBackground(Color.GREEN);
				break;
			case 5:
				button8.setBackground(Color.BLUE);
				break;
			}
		}
		

		if (bnr == 8) {
			switch (Cube_colors[side][8]) {
			case 0:
				button8.setBackground(Color.WHITE);
				break;
			case 1:
				button8.setBackground(Color.YELLOW);
				break;
			case 2:
				button8.setBackground(Color.ORANGE);
				break;
			case 3:
				button8.setBackground(Color.RED);
				break;
			case 4:
				button8.setBackground(Color.GREEN);
				break;
			case 5:
				button8.setBackground(Color.BLUE);
				break;
			}
		}
		

		if (bnr == 8) {
			switch (Cube_colors[side][8]) {
			case 0:
				button8.setBackground(Color.WHITE);
				break;
			case 1:
				button8.setBackground(Color.YELLOW);
				break;
			case 2:
				button8.setBackground(Color.ORANGE);
				break;
			case 3:
				button8.setBackground(Color.RED);
				break;
			case 4:
				button8.setBackground(Color.GREEN);
				break;
			case 5:
				button8.setBackground(Color.BLUE);
				break;
			}
		}
		

		if (bnr == 8) {
			switch (Cube_colors[side][8]) {
			case 0:
				button8.setBackground(Color.WHITE);
				break;
			case 1:
				button8.setBackground(Color.YELLOW);
				break;
			case 2:
				button8.setBackground(Color.ORANGE);
				break;
			case 3:
				button8.setBackground(Color.RED);
				break;
			case 4:
				button8.setBackground(Color.GREEN);
				break;
			case 5:
				button8.setBackground(Color.BLUE);
				break;
			}
		}
		

		if (bnr == 8) {
			switch (Cube_colors[side][8]) {
			case 0:
				button8.setBackground(Color.WHITE);
				break;
			case 1:
				button8.setBackground(Color.YELLOW);
				break;
			case 2:
				button8.setBackground(Color.ORANGE);
				break;
			case 3:
				button8.setBackground(Color.RED);
				break;
			case 4:
				button8.setBackground(Color.GREEN);
				break;
			case 5:
				button8.setBackground(Color.BLUE);
				break;
			}
		}
		

		if (bnr == 8) {
			switch (Cube_colors[side][8]) {
			case 0:
				button8.setBackground(Color.WHITE);
				break;
			case 1:
				button8.setBackground(Color.YELLOW);
				break;
			case 2:
				button8.setBackground(Color.ORANGE);
				break;
			case 3:
				button8.setBackground(Color.RED);
				break;
			case 4:
				button8.setBackground(Color.GREEN);
				break;
			case 5:
				button8.setBackground(Color.BLUE);
				break;
			}
		}
		

		if (bnr == 8) {
			switch (Cube_colors[side][8]) {
			case 0:
				button8.setBackground(Color.WHITE);
				break;
			case 1:
				button8.setBackground(Color.YELLOW);
				break;
			case 2:
				button8.setBackground(Color.ORANGE);
				break;
			case 3:
				button8.setBackground(Color.RED);
				break;
			case 4:
				button8.setBackground(Color.GREEN);
				break;
			case 5:
				button8.setBackground(Color.BLUE);
				break;
			}
		}
		

		if (bnr == 8) {
			switch (Cube_colors[side][8]) {
			case 0:
				button8.setBackground(Color.WHITE);
				break;
			case 1:
				button8.setBackground(Color.YELLOW);
				break;
			case 2:
				button8.setBackground(Color.ORANGE);
				break;
			case 3:
				button8.setBackground(Color.RED);
				break;
			case 4:
				button8.setBackground(Color.GREEN);
				break;
			case 5:
				button8.setBackground(Color.BLUE);
				break;
			}
		}*/
	}

	public static void updateSides() {

		switch (Cube_colors[side][0]) {
		case 0:
			button0.setBackground(Color.WHITE);
			break;
		case 1:
			button0.setBackground(Color.YELLOW);
			break;
		case 2:
			button0.setBackground(Color.ORANGE);
			break;
		case 3:
			button0.setBackground(Color.RED);
			break;
		case 4:
			button0.setBackground(Color.GREEN);
			break;
		case 5:
			button0.setBackground(Color.BLUE);
			break;
		}

		switch (Cube_colors[side][1]) {
		case 0:
			button1.setBackground(Color.WHITE);
			break;
		case 1:
			button1.setBackground(Color.YELLOW);
			break;
		case 2:
			button1.setBackground(Color.ORANGE);
			break;
		case 3:
			button1.setBackground(Color.RED);
			break;
		case 4:
			button1.setBackground(Color.GREEN);
			break;
		case 5:
			button1.setBackground(Color.BLUE);
			break;
		}

		switch (Cube_colors[side][2]) {
		case 0:
			button2.setBackground(Color.WHITE);
			break;
		case 1:
			button2.setBackground(Color.YELLOW);
			break;
		case 2:
			button2.setBackground(Color.ORANGE);
			break;
		case 3:
			button2.setBackground(Color.RED);
			break;
		case 4:
			button2.setBackground(Color.GREEN);
			break;
		case 5:
			button2.setBackground(Color.BLUE);
			break;
		}

		switch (Cube_colors[side][3]) {
		case 0:
			button3.setBackground(Color.WHITE);
			break;
		case 1:
			button3.setBackground(Color.YELLOW);
			break;
		case 2:
			button3.setBackground(Color.ORANGE);
			break;
		case 3:
			button3.setBackground(Color.RED);
			break;
		case 4:
			button3.setBackground(Color.GREEN);
			break;
		case 5:
			button3.setBackground(Color.BLUE);
			break;
		}

		switch (Cube_colors[side][4]) {
		case 0:
			button4.setBackground(Color.WHITE);
			break;
		case 1:
			button4.setBackground(Color.YELLOW);
			break;
		case 2:
			button4.setBackground(Color.ORANGE);
			break;
		case 3:
			button4.setBackground(Color.RED);
			break;
		case 4:
			button4.setBackground(Color.GREEN);
			break;
		case 5:
			button4.setBackground(Color.BLUE);
			break;
		}

		switch (Cube_colors[side][5]) {
		case 0:
			button5.setBackground(Color.WHITE);
			break;
		case 1:
			button5.setBackground(Color.YELLOW);
			break;
		case 2:
			button5.setBackground(Color.ORANGE);
			break;
		case 3:
			button5.setBackground(Color.RED);
			break;
		case 4:
			button5.setBackground(Color.GREEN);
			break;
		case 5:
			button5.setBackground(Color.BLUE);
			break;
		}

		switch (Cube_colors[side][6]) {
		case 0:
			button6.setBackground(Color.WHITE);
			break;
		case 1:
			button6.setBackground(Color.YELLOW);
			break;
		case 2:
			button6.setBackground(Color.ORANGE);
			break;
		case 3:
			button6.setBackground(Color.RED);
			break;
		case 4:
			button6.setBackground(Color.GREEN);
			break;
		case 5:
			button6.setBackground(Color.BLUE);
			break;
		}

		switch (Cube_colors[side][7]) {
		case 0:
			button7.setBackground(Color.WHITE);
			break;
		case 1:
			button7.setBackground(Color.YELLOW);
			break;
		case 2:
			button7.setBackground(Color.ORANGE);
			break;
		case 3:
			button7.setBackground(Color.RED);
			break;
		case 4:
			button7.setBackground(Color.GREEN);
			break;
		case 5:
			button7.setBackground(Color.BLUE);
			break;
		}

		switch (Cube_colors[side][8]) {
		case 0:
			button8.setBackground(Color.WHITE);
			break;
		case 1:
			button8.setBackground(Color.YELLOW);
			break;
		case 2:
			button8.setBackground(Color.ORANGE);
			break;
		case 3:
			button8.setBackground(Color.RED);
			break;
		case 4:
			button8.setBackground(Color.GREEN);
			break;
		case 5:
			button8.setBackground(Color.BLUE);
			break;
		}
	}

	public static void printThings(){
			
		System.out.print("public static void INITNAME(){ \n"
				+ "front = new C[3][3]; \nback = new C[3][3]; \ntop = new C[3][3]; \nbottom = new C[3][3]; \nleft = new C[3][3]; \nright = new C[3][3]; \n \n");
				
		for(int i = 0; i < 6; i++){
			if(false && i == 5) {
				System.out.println("THIS IS BOTTOM: ");
				for(int j = 8; j >= 0; j--){
					if(i == 0) System.out.print("front[");
					else if(i == 1) System.out.print("left[");
					else if(i == 2) System.out.print("right[");
					else if(i == 3) System.out.print("back[");
					else if(i == 4) System.out.print("top[");
					else if(i == 5) System.out.print("bottom[");


					if(j < 3) System.out.print("0]");
					else if( j < 6) System.out.print("1]");
					else if( j < 9) System.out.print("2]");
					
					if(j < 3) System.out.print("[" + j + "]");
					else if( j < 6) System.out.print("[" + (j-3) + "]");
					else if( j < 9) System.out.print("[" + (j-6) + "]");
					
					System.out.print(" = C." );
					
					if(Cube_colors[i][j] == 0) System.out.print("WHITE; \n");
					else if(Cube_colors[i][j] == 1) System.out.print("YELLOW; \n");
					else if(Cube_colors[i][j] == 2) System.out.print("ORANGE; \n");
					else if(Cube_colors[i][j] == 3) System.out.print("RED; \n");
					else if(Cube_colors[i][j] == 4) System.out.print("GREEN; \n");
					else if(Cube_colors[i][j] == 5) System.out.print("BLUE; \n");
					
					//System.out.print();
				}
			}else{
			  for(int j = 0; j < 9; j++){
				if(i == 0) System.out.print("front[");
				else if(i == 1) System.out.print("left[");
				else if(i == 2) System.out.print("right[");
				else if(i == 3) System.out.print("back[");
				else if(i == 4) System.out.print("top[");
				else if(i == 5) System.out.print("bottom[");


				if(j < 3) System.out.print("0]");
				else if( j < 6) System.out.print("1]");
				else if( j < 9) System.out.print("2]");
				
				if(j < 3) System.out.print("[" + j + "]");
				else if( j < 6) System.out.print("[" + (j-3) + "]");
				else if( j < 9) System.out.print("[" + (j-6) + "]");
				
				System.out.print(" = C." );
				
				if(Cube_colors[i][j] == 0) System.out.print("WHITE; \n");
				else if(Cube_colors[i][j] == 1) System.out.print("YELLOW; \n");
				else if(Cube_colors[i][j] == 2) System.out.print("ORANGE; \n");
				else if(Cube_colors[i][j] == 3) System.out.print("RED; \n");
				else if(Cube_colors[i][j] == 4) System.out.print("GREEN; \n");
				else if(Cube_colors[i][j] == 5) System.out.print("BLUE; \n");
				
				//System.out.print();
			}
			}
		}
		
		
		/*
		 * public static void allMethodsTwiceInitCube() {
		// static Color[][] front, back , top , bot , left , right;
		front = new C[3][3];
		back = new C[3][3];
		top = new C[3][3];
		bottom = new C[3][3];
		left = new C[3][3];
		right = new C[3][3];
		 */
	}
}