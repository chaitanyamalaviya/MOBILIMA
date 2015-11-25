package my.mobilima;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.text.DateFormatSymbols;
public class Sales {

		public void generateSales(){
			
		int choice1,choice2,numDate1,numMonth,thatSale;
		int i,j,k,counter,movieShowed;
		int maxMovie = 20, numCinp = 3;
		String currentLine;
		Scanner sc=new Scanner(System.in);
		Calendar cal = Calendar.getInstance();
		DateFormatSymbols dateFormat = new DateFormatSymbols();
		String[] monthNames = dateFormat.getMonths();
		
		
		
		do{
			System.out.println("Enter your choice:");
			System.out.println("(1)By movie");
			System.out.println("(2)By cineplex");
			System.out.println("(3)Quit");
			choice1 = sc.nextInt();
			
			switch(choice1)
			{
			
			case 1:
			
			{
				System.out.println("Enter your choice for period selection : ");
				System.out.println("(1) By day--first day of the month till current day");
				System.out.println("(2) By month--from January till current month");
				choice2 = sc.nextInt();
				
				switch(choice2)
				{
				
				case 1:
				{
					numDate1 = cal.get(Calendar.DAY_OF_MONTH);
					int[][][] parSale = new int[numDate1][maxMovie][numCinp];
					String[] movName = new String[maxMovie];
					String[] actDate = new String[numDate1];
					String[] cinName = new String[numCinp];
					cinName[0] = "cineplex 1";
					cinName[1] = "cineplex 2";
					cinName[2] = "cineplex 3";
					movieShowed = 0;
				
					for(i=0;i<numDate1;i++){
//						actDate[i] = (Integer.toString(date1 + i)) + "/" + "11";
						actDate[i] = (Integer.toString(i+1)) +  "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
					}
					
					for(i=0;i<numDate1;i++){
						for(j=0;j<maxMovie;j++){
							for(k=0;k<numCinp;k++)
								parSale[i][j][k] = 0;
						}
					}
					File file = new File("Sales.txt");
					
					try{
						Scanner scStream = new Scanner(file);
						while((currentLine = scStream.nextLine()) != null){
							for(i=0;i<numDate1;i++){
								while(currentLine.contains(actDate[i])){//for a particular date
									currentLine = scStream.nextLine();
							
									for(j=0;j<maxMovie;j++){//for a particular movie
										if(movName[j] == null){//if no entry of that movie in the array and that movie has not been stored
											movName[j] = currentLine;
											currentLine = scStream.nextLine();
											for(k=0;k<numCinp;k++){
												if(currentLine == cinName[k]){
													currentLine = scStream.nextLine();
													thatSale = Character.getNumericValue(currentLine.charAt(0));
													parSale[i][j][k] += thatSale;
													break;
												}
											}
											movieShowed++;
											
											break;
										}
										else if(currentLine == movName[j]){//if that movie has been stored in the list
											   currentLine = scStream.nextLine();
											   for(k=0;k<numCinp;k++){
												   if(currentLine == cinName[k]){
													   currentLine = scStream.nextLine();
													   thatSale = Character.getNumericValue(currentLine.charAt(0));
													   parSale[i][j][k] += thatSale;
													   break;
												   }
											   }
											   break;
									
										}
								
									}
									currentLine = scStream.nextLine();
								}
							}
						}
						
					}catch(IOException ex){
						ex.printStackTrace();
					}
					
					for(i=0;i<movieShowed;i++){
						System.out.println("Movie "+i+":"+movName[i]);
						for(j=0;j<numDate1;j++){
							System.out.println("On "+actDate[j]+":");
							for(k=0;k<numCinp;k++){
								if(parSale[j][i][k] != 0)
									System.out.println("In cineplex "+k+", the sale is "+parSale[j][i][k]+" dollars.");
							}
						}
					}
					break;
				}
				
				case 2:
				
				{
					numMonth = cal.get(Calendar.MONTH) + 1;
					int[][][] parSale = new int[numMonth][maxMovie][numCinp];
					String[] movName = new String[maxMovie];
					String[] actMonth = new String[numMonth];
					String[] cinName = new String[numCinp];
					cinName[0] = "cineplex 1";
					cinName[1] = "cineplex 2";
					cinName[2] = "cineplex 3";
					for(i=0;i<numMonth;i++){
						actMonth[i] = (Integer.toString(i+1)) + "/" + cal.get(Calendar.YEAR);
					}
					for(i=0;i<numMonth;i++){
						for(j=0;j<maxMovie;j++){
							for(k=0;k<numCinp;k++)
								parSale[i][j][k] = 0;
						}
					}
					movieShowed = 0;
					File file = new File("Sales.txt");
					try{
						Scanner scStream = new Scanner(file);
						while((currentLine = scStream.nextLine()) != null){
							for(i=0;i<numMonth;i++){
								while(currentLine.contains(actMonth[i])){//for a particular date
									currentLine = scStream.nextLine();
							
									for(j=0;j<maxMovie;j++){//for a particular movie
										if(movName[j] == null){//if no entry of that movie in the array and that movie has not been stored
											movName[j] = currentLine;
											currentLine = scStream.nextLine();
											for(k=0;k<numCinp;k++){
												if(currentLine == cinName[k]){
													currentLine = scStream.nextLine();
													thatSale = Character.getNumericValue(currentLine.charAt(0));
													parSale[i][j][k] += thatSale;
													break;
												}
											}
											movieShowed++;
											
											break;
										}
										else if(currentLine == movName[j]){//if that movie has been stored in the list
											   currentLine = scStream.nextLine();
											   for(k=0;k<numCinp;k++){
												   if(currentLine == cinName[k]){
													   currentLine = scStream.nextLine();
													   thatSale = Character.getNumericValue(currentLine.charAt(0));
													   parSale[i][j][k] += thatSale;
													   break;
												   }
											   }
											   break;
									
										}
								
									}
									currentLine = scStream.nextLine();
								}
							}
						}
						
					}catch(IOException ex){
						ex.printStackTrace();
					}
					for(i=0;i<movieShowed;i++){
						System.out.println("Movie "+i+":"+movName[i]);
						for(j=0;j<numMonth;j++){
							System.out.println("In "+ monthNames[j] + " " + cal.get(Calendar.YEAR));
							for(k=0;k<numCinp;k++){
								if(parSale[j][i][k] != 0)
									System.out.println("In cineplex "+k+", the sale is "+parSale[j][i][k]+" dollars.");
							}
						}
					}
					break;
				}
				}
				}
			
			case 2:
			{
				System.out.println("Enter your choice for period selection:");
				System.out.println("(1)By day--first day of the month till current day");
				System.out.println("(2)By month--from January till current month");
				choice2 = sc.nextInt();
				switch(choice2){
				case 1:{
					numDate1 = cal.get(Calendar.DAY_OF_MONTH);
					int[][][] parSale = new int[numDate1][maxMovie][numCinp];
					String[] movName = new String[maxMovie];
					String[] actDate = new String[numDate1];
					String[] cinName = new String[numCinp];
					cinName[0] = "cineplex 1";
					cinName[1] = "cineplex 2";
					cinName[2] = "cineplex 3";
					for(i=0;i<numDate1;i++){
//						actDate[i] = (Integer.toString(date1 + i)) + "/" + "11";
						actDate[i] = (Integer.toString(i+1)) +  "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
					}
					for(i=0;i<numDate1;i++){
						for(j=0;j<maxMovie;j++){
							for(k=0;k<numCinp;k++)
								parSale[i][j][k] = 0;
						}
					}
					movieShowed =0;
					File file = new File("Sales.txt");
					try{
						Scanner scStream = new Scanner(file);
						while((currentLine = scStream.nextLine()) != null){
							for(i=0;i<numDate1;i++){
								while(currentLine.contains(actDate[i])){//for a particular date
									currentLine = scStream.nextLine();
							
									for(j=0;j<maxMovie;j++){//for a particular movie
										if(movName[j] == null){//if no entry of that movie in the array and that movie has not been stored
											movName[j] = currentLine;
											currentLine = scStream.nextLine();
											for(k=0;k<numCinp;k++){
												if(currentLine == cinName[k]){
													currentLine = scStream.nextLine();
													thatSale = Character.getNumericValue(currentLine.charAt(0));
													parSale[i][j][k] += thatSale;
													break;
												}
											}
											movieShowed++;
											
											break;
										}
										else if(currentLine == movName[j]){//if that movie has been stored in the list
											   currentLine = scStream.nextLine();
											   for(k=0;k<numCinp;k++){
												   if(currentLine == cinName[k]){
													   currentLine = scStream.nextLine();
													   thatSale = Character.getNumericValue(currentLine.charAt(0));
													   parSale[i][j][k] += thatSale;
													   break;
												   }
											   }
											   break;
									
										}
								
									}
									currentLine = scStream.nextLine();
								}
							}
						}
						
					}catch(IOException ex){
						ex.printStackTrace();
					}
					for(i=0;i<numCinp;i++){
						System.out.println("In cineplex "+(i+1)+":");
						for(j=0;j<numDate1;j++){
							System.out.println("On "+actDate[j]+":");
							for(k=0;k<movieShowed;k++){
								if(parSale[j][k][i] != 0)
									System.out.println("For movie: "+movName[k]+", the sale is "+parSale[j][k][i]+" dollars.");
							}
						}
					}
					break;
				}
				
				case 2:
				
				{
					numMonth = cal.get(Calendar.MONTH) + 1;
					int[][][] parSale = new int[numMonth][maxMovie][numCinp];
					String[] movName = new String[maxMovie];
					String[] actMonth = new String[numMonth];
					String[] cinName = new String[numCinp];
					cinName[0] = "cineplex 1";
					cinName[1] = "cineplex 2";
					cinName[2] = "cineplex 3";
					for(i=0;i<numMonth;i++){
						actMonth[i] = (Integer.toString(i+1)) + "/" + cal.get(Calendar.YEAR);
					}
					for(i=0;i<numMonth;i++){
						for(j=0;j<maxMovie;j++){
							for(k=0;k<numCinp;k++)
								parSale[i][j][k] = 0;
						}
					}
					movieShowed = 0;
					File file = new File("Sales.txt");
					try{
						Scanner scStream = new Scanner(file);
						while((currentLine = scStream.nextLine()) != null){
							for(i=0;i<numMonth;i++){
								while(currentLine.contains(actMonth[i])){//for a particular date
									currentLine = scStream.nextLine();
							
									for(j=0;j<maxMovie;j++){//for a particular movie
										if(movName[j] == null){//if no entry of that movie in the array and that movie has not been stored
											movName[j] = currentLine;
											currentLine = scStream.nextLine();
											for(k=0;k<numCinp;k++){
												if(currentLine == cinName[k]){
													currentLine = scStream.nextLine();
													thatSale = Character.getNumericValue(currentLine.charAt(0));
													parSale[i][j][k] += thatSale;
													break;
												}
											}
											movieShowed++;
											
											break;
										}
										else if(currentLine == movName[j]){//if that movie has been stored in the list
											   currentLine = scStream.nextLine();
											   for(k=0;k<numCinp;k++){
												   if(currentLine == cinName[k]){
													   currentLine = scStream.nextLine();
													   thatSale = Character.getNumericValue(currentLine.charAt(0));
													   parSale[i][j][k] += thatSale;
													   break;
												   }
											   }
											   break;
									
										}
								
									}
									currentLine = scStream.nextLine();
								}
							}
						}
						
					}catch(IOException ex){
						ex.printStackTrace();
					}
					for(i=0;i<numCinp;i++){
						System.out.println("In cineplex "+(i+1)+":");
						for(j=0;j<numMonth;j++){
							System.out.println("In "+ monthNames[j] + " " + cal.get(Calendar.YEAR));
							for(k=0;k<movieShowed;k++){
								if(parSale[j][k][i] != 0)
									System.out.println("For movie "+movName[k]+", the sale is "+parSale[j][k][i]+" dollars.");
							}
						}
					}
					break;
				}
				}
				}
				
			}
			
			
		    }while(choice1 != 3);
				
				
				
}
}	


