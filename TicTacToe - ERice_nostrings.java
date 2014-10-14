import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

	public class TicTacToe extends JFrame implements ActionListener{
		
		//0 is computer (O)
		//1 is user     (X)
		//unitGrid is an array for the tic tac toe arrangement:  0  1  2
        	//                                                       3  4  5
        	//                                                       6  7  8                                                                                                              
		
		
		//1.  I think I put way too much effort into this project. At the end, I feel like my code isn't very clever. My code is way too long.  
		//2.  I really want to be a student at 8th Light.  I really want to be a better coder.
		//3.  If you find errors, will I have the opportunity to fix it?
		
		private JRadioButton firstTurn = new JRadioButton("FirstTurn");//previously static
		private JRadioButton secondTurn = new JRadioButton("SecondTurn");//previously static
	    	private int unitGrid[] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
	    
		public TicTacToe(){
		
			//radiobutton for first player
			firstTurn = new JRadioButton("move first");
			firstTurn.setSelected(true);
			
			//radiobutton for second player
			secondTurn = new JRadioButton("move second");
			
			//button ends game and resets screen
			JButton button = new JButton("Start"); 
			button.addActionListener(this);  
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(firstTurn);
			bg.add(secondTurn);
			bg.add(button);
				
			Container cp = getContentPane();
		    cp.setLayout(new FlowLayout());
		    cp.add(firstTurn);
		    cp.add(secondTurn);
		    cp.add(button);
		}
		
		//getters and setters
		public JRadioButton getFirstTurn(){
	    	return firstTurn;
	    }
	    public JRadioButton getSecondTurn(){
	    	return secondTurn;
	    }
	    public int[] getUnitGrid(){
	    	return unitGrid;
	    }
	    public void setFirstTurn(JRadioButton firstTurn){
	    	this.firstTurn = firstTurn;
	    }
	    public void setSecondTurn(JRadioButton secondTurn){
	    	this.secondTurn = secondTurn;
	    }
	    public void setUnitGrid(int[] unitGrid){
	    	this.unitGrid = unitGrid;
	    }
	    
	    
		//clears the screen (actionPerormed by Start button)
		public void actionPerformed(ActionEvent e){ 
			unitGrid[0] = -1;
			unitGrid[1] = -1;
			unitGrid[2] = -1;
			unitGrid[3] = -1;
			unitGrid[4] = -1;
			unitGrid[5] = -1;
			unitGrid[6] = -1;
			unitGrid[7] = -1;
			unitGrid[8] = -1;
			repaint();
			
			//if computer is first take corner
			if (secondTurn.isSelected()){
				unitGrid[0] = 0;
			}
		 }
		
		//movement for "X" (person) is in mousePressed function
		//movement for "O" (computer) is here
		public void computerMove(){  
			
		    //fill space in horizontal tic tac toe row  when two X's or two O's are already there
			for (int n = 0; n < 2; n++){ //n for "X" then "O"
				for (int j = 0; j <= 6; j = j + 3){ 
					if (unitGrid[j] == n && unitGrid[j + 1] == n && unitGrid[j + 2] == -1){
						unitGrid[j + 2] = 0;  // 0 | 0 | empty
						return;
					}
					else if (unitGrid[j] == n && unitGrid[j + 2] == n && unitGrid[j + 1] == -1 ){
						unitGrid[j + 1] = 0;  // 0 | empty | 0
						return; 
					}
					else if (unitGrid[j + 1] == n && unitGrid[j + 2] == n && unitGrid[j] == -1){
						unitGrid[j] = 0;  // empty | 0 | 0
						return;
					}
				}
			
				//fill space in vertical tic tac toe when two X's or two O's are already there
				for (int m = 0; m < 3; m++){
					if (unitGrid[m] == n && unitGrid[m + 3] == n && unitGrid[m + 6] == -1){  
						unitGrid[m + 6] = 0; // 0 / 0 / empty  <--diagram (sideways column) 
						return;
					}
					else if (unitGrid[m] == n && unitGrid[m + 6] == n && unitGrid[m + 3] == -1){
						unitGrid[m + 3] = 0;  //  0 / empty / 0
						return;
					}
					else if (unitGrid[m + 3] == n && unitGrid[m + 6] == n && unitGrid[m] == -1){
						unitGrid[m] = 0;  // empty / 0 / 0
						return;
					}
				}
			}

			//fill space in diagonal tic tac toe
			if (unitGrid[0] == unitGrid[8] && unitGrid[0] != -1 && unitGrid[4] == -1){
				unitGrid[4] = 0;	
				return;
			}	
			else if (unitGrid[0] == unitGrid[4] && unitGrid[0] != -1 && unitGrid[8] == -1){
				unitGrid[8] = 0;
				return;
			}
			else if (unitGrid[4] == unitGrid[8] && unitGrid[4] != -1 && unitGrid[0] == -1){
				unitGrid[0] = 0;
				return;
			}
			else if (unitGrid[2] == unitGrid[4] && unitGrid[2] != -1 && unitGrid[6] == -1){
				unitGrid[6] = 0;
				return;
			}
			else if (unitGrid[2] == unitGrid[6] && unitGrid[2] != -1 && unitGrid[4] == -1){
				unitGrid[4] = 0;
				return;
			}
			else if (unitGrid[4] == unitGrid[6] && unitGrid[4] != -1 && unitGrid[2] == -1){
				unitGrid[2] = 0;
				return;
			}	
			
			//catches forks that are not caught by the following forks function
			else if ((unitGrid[0] == 1 && unitGrid[1] == -1 && unitGrid[2] == -1 && unitGrid[3] == -1 && unitGrid[4] == 0 && 
					unitGrid[5] == -1 && unitGrid[6] == -1 && unitGrid[7] == -1 && unitGrid[8] == 1) ||
					(unitGrid[0] == -1 && unitGrid[1] == -1 && unitGrid[2] == 1 && unitGrid[3] == -1 && unitGrid[4] == 0 && 
					unitGrid[5] == -1 && unitGrid[6] == 1 && unitGrid[7] == -1 && unitGrid[8] == -1)){
						unitGrid[1] = 0;
						return;
			}
			
			int a = forks();
			if (a != -1 ){// if there is a valid fork value 
					unitGrid[a] = 0;
					return;
			}
			
			//fill center, then fill opposite corners, then corners, and then sides 
			else if (unitGrid[4] == -1){ 
				unitGrid[4] = 0;
			else if (unitGrid[0] != -1 && unitGrid[8] == -1){
				unitGrid[8] = 0;
			else if (unitGrid[0] == -1 && unitGrid[8] != -1)
				unitGrid[0] = 0;
			else if (unitGrid[2] != -1 && unitGrid[6] == -1)
				unitGrid[6] = 0;
			else if (unitGrid[7] == -1 && unitGrid[6] != -1)
				unitGrid[2] = 0;
			
		    else if (unitGrid[0] == -1) 
				unitGrid[0] = 0;
			else if (unitGrid[2] == -1)
				unitGrid[2] = 0;
			else if (unitGrid[6] == -1)
				unitGrid[6] = 0;
			else if (unitGrid[8] == -1)
				unitGrid[8] = 0;
			else if (unitGrid[1] == -1)
				unitGrid[1] = 0;
			else if (unitGrid[3] == -1)
				unitGrid[3] = 0;
			else if (unitGrid[5] == -1)
				unitGrid[5] = 0;
			else if (unitGrid[7] == -1) 
				unitGrid[7] = 0;
		}
	
		
		public static void main(String[] args) {
			TicTacToe frame = new TicTacToe();
			frame.setSize(435,450);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me){  
		        int boxPos = -1;
		        boolean twice = false;//clicked twice on
		        //first row
				if (me.getX() > 50 && me.getX() < 150 && me.getY() < 212 && me.getY() > 113){
					//below lines needed to keep "0"s from appearing when double-clicking on the same "X" (fixed bug)
					if (frame.getUnitGrid()[0] != -1)
						twice = true;
					else
						boxPos = 0;
				}
				else if (me.getX() > 150 && me.getX() < 250 && me.getY() < 212 && me.getY() > 113){
					if (frame.getUnitGrid()[1] != -1)
						twice = true;
					else
						boxPos = 1;
				}
				else if (me.getX() > 250 && me.getX() < 349 && me.getY() < 212 && me.getY() > 113){
					if (frame.getUnitGrid()[2] != -1)
						twice = true;
					else
						boxPos = 2;
				}
				
				//second row
				else if (me.getX() > 50 && me.getX() < 150 && me.getY() < 311 && me.getY() > 212){
					if (frame.getUnitGrid()[3] != -1)
						twice = true;
					else
						boxPos = 3;			
				}
				else if (me.getX() > 150 && me.getX() < 250 && me.getY() < 311 && me.getY() > 212){
					if (frame.getUnitGrid()[4] != -1)
						twice = true;
					else
						boxPos = 4;
				}
				else if (me.getX() > 250 && me.getX() < 349 && me.getY() < 311 && me.getY() > 212){
					if (frame.getUnitGrid()[5] != -1)
						twice = true;
					else
						boxPos = 5;
				}
				
				//third row
				else if (me.getX() > 50 && me.getX() < 150 && me.getY() < 411 && me.getY() > 311){
					if (frame.getUnitGrid()[6] != -1)
						twice = true;
					else
						boxPos = 6;
				}
				else if (me.getX() > 150 && me.getX() < 249 && me.getY() < 411 && me.getY() > 311){
					if (frame.getUnitGrid()[7] != -1)
						twice = true;
					else
						boxPos = 7;
				}
				else if (me.getX() > 250 && me.getX() < 349 && me.getY() < 411 && me.getY() > 311){
					if (frame.getUnitGrid()[8] != -1)
						twice = true;
					else
						boxPos = 8;
				}
				
				//keeps from array[-1]
				if (boxPos == -1)
					return;
				else
					frame.unitGrid[boxPos] = 1;	
				
				frame.repaint();//changes in unitGrid call for repaint
				if (frame.checkWin() == 1 || frame.checkWin() == 2 || frame.checkWin() == 3){
					return;
				}
				if (!twice){
					frame.computerMove();//if didn't click on same square twice, run computerMove()
					frame.repaint();
				}
				frame.checkWin();
			}});
		
		JPanel panel2 = new JPanel() {
		@Override
		public void paintComponent(Graphics g) { //automatically redraws with repaint
		       super.paintComponent(g);            	
		       setBackground(Color.white);
		       g.setColor(Color.BLUE);
		       g.drawString("Computer Tic Tac Toe (user is X)", 100, 40);
		    				
		       //grid outline
		       g.drawRect(50, 50, 300, 300);
		    			
		       //horizontal tic tac toe lines
		       g.drawLine(50, 150, 350, 150);
		       g.drawLine(50, 250, 350, 250);
		    			
		       //vertical tic tac toe lines
		       g.drawLine(150, 50, 150, 350);
		       g.drawLine(250, 50, 250, 350);
		    			
		       //draws "O"s and "X"s from unitGrid
		       if (frame.unitGrid[0] == 0){
					g.drawString("O", 90, 110);
				}
				else if (frame.unitGrid[0] == 1 ){
					g.drawString("X", 90, 110);
				}
		    	if (frame.unitGrid[1] == 0){
					g.drawString("O", 190, 110);
				}
				else if (frame.unitGrid[1] == 1 ){
					g.drawString("X", 190, 110);
				}
		    	if (frame.unitGrid[2] == 0){
					g.drawString("O", 290, 110);
				}
				else if (frame.unitGrid[2] == 1 ){
					g.drawString("X", 290, 110);
				}
		    	if (frame.unitGrid[3] == 0){
					g.drawString("O", 90, 210);
				}						
				else if (frame.unitGrid[3] == 1 ){
					g.drawString("X", 90, 210);
				}
		    	if (frame.unitGrid[4] == 0){
					g.drawString("O", 190, 210);
				}
				else if (frame.unitGrid[4] == 1 ){
					g.drawString("X", 190, 210);
				}
		    	if (frame.unitGrid[5] == 0){
					g.drawString("O", 290, 210);
				}
				else if (frame.unitGrid[5] == 1 ){
					g.drawString("X", 290, 210);
				}
		    	if (frame.unitGrid[6] == 0){
					g.drawString("O", 90, 310);
				}
				else if (frame.unitGrid[6] == 1 ){
					g.drawString("X", 90, 310);
				}
		    	if (frame.unitGrid[7] == 0){
					g.drawString("O", 190, 310);
				}
				else if (frame.unitGrid[7] == 1 ){
					g.drawString("X", 190, 310);
				}
		    	if (frame.unitGrid[8] == 0){
					g.drawString("O", 290, 310);
				}
				else if (frame.unitGrid[8] == 1 ){
					g.drawString("X",290, 310);
				}
		    	g.setFont(new Font("Arial", Font.BOLD, 50));
		    	
		    	if (frame.checkWin() == 2){
		    		g.setFont(new Font("Arial", Font.BOLD, 30));
		    		g.drawString("COMPUTER WINS! :(", 50, 200);
		    	}
		    	else if (frame.checkWin() == 1){ //hopefully never called
		    		g.drawString("YOU WIN! :)", 40, 200);
		    	}
		    	 	else if (frame.checkWin() == 3)
		    		g.drawString("TIE!  :|", 110, 200);	
		        }    
			};
				
		panel2.setPreferredSize(new Dimension(400, 450));//maintains size of window
		frame.add(panel2);
		//frame.repaint(); 
	}
		
		//outcome of game		
		public int checkWin(){
			    int d = -1;
		    	if ((unitGrid[0] == 1 && unitGrid[1] == 1 && unitGrid[2] == 1) ||
		    		(unitGrid[3] == 1 && unitGrid[4] == 1 && unitGrid[5] == 1) ||
		    		(unitGrid[6] == 1 && unitGrid[7] == 1 && unitGrid[8] == 1) ||
		    		(unitGrid[0] == 1 && unitGrid[3] == 1 && unitGrid[6] == 1) ||
		    		(unitGrid[1] == 1 && unitGrid[4] == 1 && unitGrid[7] == 1) ||
		    		(unitGrid[2] == 1 && unitGrid[5] == 1 && unitGrid[8] == 1) ||
		    		(unitGrid[0] == 1 && unitGrid[4] == 1 && unitGrid[8] == 1) ||
		    		(unitGrid[2] == 1 && unitGrid[4] == 1 && unitGrid[6] == 1))
		    		{ d = 1; }
		    	else if ((unitGrid[0] == 0 && unitGrid[1] == 0 && unitGrid[2] == 0) ||
			    		(unitGrid[3] == 0 && unitGrid[4] == 0 && unitGrid[5] == 0) ||
			    		(unitGrid[6] == 0 && unitGrid[7] == 0 && unitGrid[8] == 0) ||
			    		(unitGrid[0] == 0 && unitGrid[3] == 0 && unitGrid[6] == 0) ||
			    		(unitGrid[1] == 0 && unitGrid[4] == 0 && unitGrid[7] == 0) ||
			    		(unitGrid[2] == 0 && unitGrid[5] == 0 && unitGrid[8] == 0) ||
			    		(unitGrid[0] == 0 && unitGrid[4] == 0 && unitGrid[8] == 0) ||
			    		(unitGrid[2] == 0 && unitGrid[4] == 0 && unitGrid[6] == 0))
						{ d = 2; }
				else if (unitGrid[0] != -1 && unitGrid[1] != -1 && unitGrid[2] != -1 && 
					unitGrid[3] != -1 && unitGrid[4] != -1 && unitGrid[5] != -1 &&
					unitGrid[6] != -1 && unitGrid[7] != -1 && unitGrid[8] != -1)
					{ d = 3; }
		    	return d;
		}	
			
		
////////////////////////////////////////////////////////////////////////////////////
//Fork theory:  Examine vertical, horizontal, and diagonal lines.
//If there are two lines that that contain an X or O, a blank spot, and a spot for the same fork value, the fork value is 0.
//really convoluted and not very clever, kind of brute forcing it...tell me if you want me to redo it...

	 int forks(){
		 int lines;
		 int d;
		 for (int n = 1; n >= 0; n--){//Y rotation (n = 0) occurs after X rotation(n = 1) so that filling a winning fork occurs before a blocking fork
			 for (d = 0; d < 9; d++){
				 lines = 0;
			
				//HORIZONTAL
				if (unitGrid[d] == -1){ //look at empty spaces
					for (int j = 0; j <= 6; j = j + 3){ 
						if (((d == 0 && j == 0) ||(d == 3 && j == 3) ||  (d == 6 && j == 6)) && unitGrid[j + 1] == n && unitGrid[j + 2] == -1) //fork | 0 | empty 
							lines++;
						else if (((d == 0 && j == 0) ||(d == 3 && j == 3) ||( d == 6 && j == 6)) && unitGrid[j + 1] == -1 && unitGrid[j + 2] == n)//fork| empty | 0
							lines++;
						else if  (unitGrid[j] == -1 && ((d == 1 && j == 0)|| (d == 4 && j == 3) || (d == 7 && j == 6)) && unitGrid[j + 2] == n //empty | fork | 0
							lines++;
						else if (unitGrid[j] == n && ((d == 1 && j == 0)|| (d == 4 && j == 3) || (d == 7 && j == 6)) && unitGrid[j + 2] == -1) //0 | fork | empty 
							lines++;
						else if (unitGrid[j] == n && unitGrid[j + 1] == -1 && ((d == 2 && j == 0) || (d == 5 & j == 3)|| (d == 8 && j == 6)))//0 | empty | fork
							lines++;
						else if (unitGrid[j] == -1 && unitGrid[j + 1] == n && ((d == 2 && j == 0) || (d == 5 & j == 3)|| (d == 8 && j == 6)))//empty | 0 | fork
							lines++;
					}  // j iteration
					
					//VERTICAL
					for (int m = 0; m < 3; m++){
						if (((d == 0 && m == 0)|| (d == 1 && m == 1) || (d == 2 && m == 2)) && unitGrid[m + 3] == n && unitGrid[m + 6] == -1)  //fork / 0 / empty  <--diagram (sideways column) 
							lines++;
						else if (((d == 0 && m == 0) || (d == 1 && m == 1) || (d == 2 && m == 2)) && unitGrid[m + 3] == -1 && unitGrid[m + 6] == n)//fork / empty / 0
							lines++;
						else if (unitGrid[m] == -1 && ((d == 6 && m == 0) || (d == 7 && m == 1) || (d == 8 && m == 2)) && unitGrid[m + 6] == n)//empty / fork / 0
							lines++;
						if (unitGrid[m] == n && ((d == 3 && m == 0) || (d == 4 && m == 1) || (d == 5 && m == 2)) && unitGrid[m + 6] == -1)  //0 / fork / empty  <--diagram (sideways column) 
							lines++;
						else if (unitGrid[m + 3] == -1 && unitGrid[m] == n && ((d == 6 && m == 0) || (d == 7 && m == 1) || (d == 8 && m == 2))) // 0 / empty / fork
							lines++;
						else if (unitGrid[m] == -1 && unitGrid[m + 3] == n && ((d == 6 && m == 0) || (d == 7 && m == 1) || (d == 8 && m == 2)))//empty / 0 / fork
							lines++;
					} // m iteration
					
					//DIAGONAL
					if (d == 0 && unitGrid[8] == n && unitGrid[4] == -1)	
						lines++;
			    	else if (unitGrid[0] == n && unitGrid[4] == -1 && d == 8)	
			    		lines++;
					else if (unitGrid[0] == n && d == 4 && unitGrid[8] == -1)
						lines++;
					else if (d == 0 && unitGrid[4] == n && unitGrid[8] == -1)
						lines++;
					else if (d == 4 && unitGrid[8] == n && unitGrid[0] == -1)
						lines++;
					else if (unitGrid[4] == n && d == 8 && unitGrid[0] == -1)
						lines++; 
					if (d == 2 && unitGrid[4] == n && unitGrid[6] == -1)
						lines++; 
					else if (unitGrid[2] == n && d == 4 && unitGrid[6] == -1)
						lines++; 
					else if (d == 2 && unitGrid[6] == n && unitGrid[4] == -1)
						lines++;
					else if (unitGrid[2] == n && d == 6 && unitGrid[4] == -1)
						lines++; 
					else if (d == 4 && unitGrid[6] == n && unitGrid[2] == -1)
						lines++;
					else if (unitGrid[4] == n && d == 6  && unitGrid[2] == -1)
						lines++;       
					
					if (lines >= 2)                                                                                                                                              
						return d;      
				} //if loop
		 } //for loop
	   }  //for loop
	   return -1;
	} //forks function
} 
	