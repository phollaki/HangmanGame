package com.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class App {
	private static List<String> beforeGuesses;
	private static List<String> alreadyFound;
	private static boolean endofthegame = false;
	private static int tipp = 0;
	private static int fails = 0;
	private static int found = 0;
	private static int lvl = 0;
	
	
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			beforeGuesses = new ArrayList<String>();
			alreadyFound = new ArrayList<String>();
			lvl = chooseLevel(sc);
			String word = levelOfTheGame(lvl);
			String[] blindword = new String[word.length()]; 
			blindCharacters(blindword,word.length());
			printNormalHangmantree(fails);
			while(!endofthegame) {
				endofthegame = letsplay(word,sc,blindword);
			}
	}
	
	
	public static int chooseLevel(Scanner sc) {
		System.out.println("Choose a level (1-6): ");
		int lvl=-1;
        while(lvl<0){
            while (!sc.hasNextInt()) {
                if(sc.hasNext()){
                    String s = sc.next();
                    System.out.println("You choose a wrong level, your choices 1,2,3,4,5,6:" + s);
                }
            }
            lvl = sc.nextInt();
        }
        return lvl;
	}
	public static String tipp(Scanner sc) {
		System.out.println("Your guess: ");
		String character = "";
        while(!sc.hasNext("c")){
            while (!sc.hasNext()) {
                System.out.println("Your should write a character:" + character);	
            }
            character = sc.next();
        }
        return character;
	}
	public static boolean contains(final int[] arr, final int key) {
	    return Arrays.stream(arr).anyMatch(i -> i == key);
	}
	public static String levelOfTheGame(int level) {
		int randomNum = 0;
		String[] level1 = {"kő","fa","át","be","ki","le","se","és"};
		String[] level2 = {"hát","fej","dob","bot","seb","kés","sál"};
		String[] level3 = {"kettő","tévé","nyolc","hiba","kupa","sapi"};
		String[] level4 = {"kutya","zokni","tevék","gatya","váltó","torta","robot","fagyi"};
		String[] level5 = {"kenter","tartás","király","hajnal","csontos","szellem","termek"};
		String[] level6 = {"fagylalt","komment","vörösbor","vadászat","kalapos","kerekes"};
		switch(level)
	     {
	        case 1:
	        	randomNum = randomNumber(level1.length);
	        	return level1[randomNum];
	        case 2:
	        	randomNum = randomNumber(level2.length);
	        	return level2[randomNum];
	        case 3:
	        	randomNum = randomNumber(level3.length);
	        	return level3[randomNum];
	        case 4:
	        	randomNum = randomNumber(level4.length);
	        	return level4[randomNum];
	        case 5:
	        	randomNum = randomNumber(level5.length);
	        	return level5[randomNum];
	        case 6:
	        	randomNum = randomNumber(level6.length);
	        	return level6[randomNum];
		    default:
		    	randomNum = randomNumber(level1.length);
	        	return level1[randomNum];
		      }
	}
	public static int randomNumber(int lengthOfArray) {
		return (int)(Math.random()*lengthOfArray);
	}
	public static String[] blindCharacters(String[] blindword,int length) {
		for (int i = 0; i < length; i++) {
			blindword[i] = "_";
		}
		return blindword;
	}	
	
	public static boolean letsplay(String word, Scanner sc, String[] blindword) {
		char[] chars = new char[word.length()];
		  for (int i = 0; i < word.length(); i++) {
			  chars[i] = word.charAt(i);
		  }
		  System.out.println("Word: " +  Arrays.deepToString(blindword).replace("[", "").replace(",", " ").replace("]", ""));
		  System.out.println("Your Guess: ");
		  String currentguess = sc.next();
		  while(currentguess.length()>1 || alreadyFound.contains(currentguess)) {
			  System.out.println("Your Guess can only be 1 character and you can not guess 1 character multiple times: ");
			  currentguess = sc.next();
		  }
		  
		  if(currentguess != null) {
			  tipp++;
			  boolean notfound = false;
			  for (int i = 0; i < chars.length; i++) {
				try {
					if(chars[i]==currentguess.charAt(0)) {
						alreadyFound.add(currentguess);
						blindword[i] = currentguess;
						if(!alreadyFound.contains(currentguess)) {
							found++;
							notfound = true;
						}
						if(found == word.length()) {
							System.out.println("You won! New game?(yes/no)");
							System.out.println(Arrays.deepToString(blindword).replace("[", "").replace(",", " ").replace("]", ""));
							return true;
						}
					}
				}catch (StringIndexOutOfBoundsException ex) {
					while(currentguess.length()>1) {
						fails = 0;
						found = 0;
						currentguess = sc.next();
					}
					
				}
			  }
			  if(notfound == false) {
				  beforeGuesses.add(currentguess);
				  fails++;
				  printNormalHangmantree(fails);
				  if(fails == 6) {
					  System.out.println("END! You lost this game! New game? (yes/no)");
					  return true;
				  }
			  }
			  else if(fails != 6){
				  printNormalHangmantree(fails);
			  }

			  System.out.println("Number of your guesses: " + tipp); 
			  System.out.println("Wrong guesses: " + beforeGuesses.toString().replace("[", "").replace("]", ""));
		  }
		return false;
	  }
	public static void printNormalHangmantree(int fails) {
		if(fails == 0) {
		  System.out.println(); 
		  System.out.println("    -----");
		  System.out.println("   |     |"); 
		  System.out.println("         |");
		  System.out.println("         |"); 
		  System.out.println("         |");
		  System.out.println("         |"); 
		  System.out.println("      -------");
		  System.out.println("");
		  System.out.println();
		}
		if(fails == 1) {
			System.out.println(); 
			  System.out.println("    -----");
			  System.out.println("   |     |"); 
			  System.out.println("   O     |");
			  System.out.println("         |"); 
			  System.out.println("         |");
			  System.out.println("         |"); 
			  System.out.println("      -------");
			  System.out.println("");
			  System.out.println();
		}
		if(fails == 2) {
			  System.out.println(); 
			  System.out.println("    -----");
			  System.out.println("   |     |"); 
			  System.out.println("   O     |");
			  System.out.println("   |     |"); 
			  System.out.println("         |");
			  System.out.println("         |"); 
			  System.out.println("      -------");
			  System.out.println("");
			  System.out.println();
		}
		if(fails == 3) {
			System.out.println(); 
			  System.out.println("    -----");
			  System.out.println("   |     |"); 
			  System.out.println("  \\O     |");
			  System.out.println("   |     |"); 
			  System.out.println("         |");
			  System.out.println("         |"); 
			  System.out.println("      -------");
			  System.out.println("");
			  System.out.println();
			}
		if(fails == 4) {
			System.out.println(); 
			  System.out.println("    -----");
			  System.out.println("   |     |"); 
			  System.out.println("  \\O/    |");
			  System.out.println("   |     |"); 
			  System.out.println("         |");
			  System.out.println("         |"); 
			  System.out.println("      -------");
			  System.out.println("");
			  System.out.println();
			}
		if(fails == 5) {
			System.out.println(); 
			  System.out.println("    -----");
			  System.out.println("   |     |"); 
			  System.out.println("  \\O/    |");
			  System.out.println("   |     |"); 
			  System.out.println("  /      |");
			  System.out.println("         |"); 
			  System.out.println("      -------");
			  System.out.println("");
			  System.out.println();
			}
		if(fails == 6) {
			System.out.println(); 
			  System.out.println("    -----");
			  System.out.println("   |     |"); 
			  System.out.println("  \\O/    |");
			  System.out.println("   |     |"); 
			  System.out.println("  / \\    |");
			  System.out.println("         |"); 
			  System.out.println("      -------");
			  System.out.println("");
			  System.out.println();
			}
	  }
}

