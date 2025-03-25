package adamasmaca;
/*
 * @author akifsy
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdamAsmaca {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Menu(); //Invokes Menu method.
             
    }
        
    private static final List<String> words = new ArrayList<>(); //List for word adding.
    private static final List<Integer> scores = new ArrayList<>(); //List for score adding.
    
    public static void Menu() {
        Scanner s = new Scanner(System.in);

        
        System.out.println("1-Add new word");
        System.out.println("2-New game");
        System.out.println("3-Show the scores");
        System.out.println("4-Exit");
        System.out.println();

        System.out.print("Choose an option: ");

        int num = s.nextInt();
        s.nextLine();

        switch (num) { //switch-case method to choose any option.
            case 1: //Redirects to "Add new word" section.
                System.out.print("Add a word: ");
                String wordAdding = s.nextLine();
                addWord(wordAdding);
                break;

            case 2: //Redirects to "New game" section. Initiate the game.
                newGame();
                break;

            case 3: //Redirects to "Show the scores" section.
                showScores();
                break;

            case 4: //Redirects to "Exit" section.
                Exit();
                break;
                        
            default:
                System.out.println("Please enter a valid value!");
                System.out.println();
        }
    }
        
    
    
    //This method checks if the word contain integer value.
    public static boolean checkNumber(String newWord) {
        //Iterate every char in string.
        for (char c : newWord.toCharArray()) {
            //Check if the char is integer.
            if (Character.isDigit(c)) {
                //If any integer value found, return false.
                return false;
            }
        }
        //If no integer value found, return true.
        return true;
    }
    
    //This method checks if the list "words" contain the word.
    public static boolean checkList(String newWord){
        
        for(int i = 0; i<words.size(); i++){
            if(newWord.equals(words.get(i))){ //If any element of the list "words" equals the word, return false.
                return false;
            }
        }
        
        return true; //If not, return true.
    }
    
    
    //This method adds a new word to list.
    public static void addWord(String newWord){
        
        if(newWord.length()<3 && !checkNumber(newWord)){
            System.out.println("Entered word must have at least 3 characters and not contain integer value!");
        }        
        else if(newWord.length()<3){
            System.out.println("Entered word must have at least 3 characters!");
        }
        
        else if(!checkNumber(newWord)){
            System.out.println("Entered word must not contain integer value!");
        }
        
        else if(!checkList(newWord)){
           System.out.println("This word was already entered!"); 
        }
        
        else{
            newWord = newWord.toLowerCase(); //To eliminate case sensitivity, the entered word is converted to lowercase.
            words.add(newWord);
            System.out.println("The word successfully added.");   
        }
        
        System.out.println();
        Menu(); //Redirect to the Menu.
    }
    
    
    //This method initiate a new game.
    public static void newGame(){
        Scanner s = new Scanner(System.in);
    
        //Checks if the list "words" is empty.
        if (!words.isEmpty()) {
            
            Random random = new Random();

            //Selecta a random number between 0 and the number of elements in the list "words". 
            int randomIndex = random.nextInt(words.size());

            //Take random element from the list.
            String randomElement = words.get(randomIndex);

            //Print the random element. This line is just for checking.
            //System.out.println("Random Element: " + randomElement);

            String alt = ""; //A string holding underscores "_" equal to the character count of randomElement.
            
            int rights = (randomElement.length())/2; //Number of the rights.
            
            for(int i=0; i<randomElement.length(); i++){  //Appends underscores "_" to the "alt" variable equal to the character count of randomElement.
            
                alt += "_";           
            }

            char[] charArray = alt.toCharArray(); //Stores each element of "alt" at an index in the array, enabling access to the specific character at an index of "alt".

            String modified = alt; //Stores the version of the underscore "_" character replaced with a letter.
        
            List<Character> preChars = new ArrayList<>(); //List for holding previous entered chars.
            
            while(rights > 0){ // While you have a right, this block keeps running.
            
                System.out.println("You have " + rights + " rights.");
                int numOfChar = 0;
                System.out.print("Select one char: ");
                char harf = s.next().charAt(0);
                harf = Character.toLowerCase(harf); //Converts the entered char to lowercase.
                
                if(preChars.contains(harf)==false){ //Checks if the char was previously entered.
                    boolean found = false;
           
                    for(int i=0; i<alt.length(); i++){
                
                        if(randomElement.charAt(i)==harf){    
                            charArray[i] = harf;
                            modified = new String(charArray);
                            numOfChar++;
                            found = true;//Ensures that your rights do not diminish.
                        }        
                    }
                
                    if(found==false) {
                    rights--; // Diminishes the "rights" value.
                    }
                        
                    preChars.add(harf); //Adds the entered char to preChars list.
                    System.out.println("There is " + numOfChar + " \"" + harf + "\".");
                    System.out.println(separate(modified));
                    System.out.println();
                
                }
            
                else{ //This else block operates in the case where the letter has been entered before.
                
                    System.out.println("You enetered this character previously!");
                    System.out.println(separate(modified));
                    System.out.println();
                }
            
         
                if(!modified.contains("_")){
                    System.out.println("You win.");
                    System.out.println("Your point is 10."); //Adds 10 points to "scores" list.
                    scores.add(10);
                    break;
                }                  
            }
            
            if(rights==0 && modified.contains("_")){ // If the user ran out of rights, earns no point.
                
                System.out.println("You lost.");
                System.out.println("Your point is 0."); //Adds 0 point to "scores" list.
                scores.add(0);
                //break;    
            }
            
        }
             
        else { //This else block operates when no word has been entered.
            System.out.println("You didn't enter any word!" );
        }
        
        System.out.println();
        
        Menu(); //Redirects to the Menu.     
     
    }
    
    
    //This method print the string with one space for every char. This is for output to look understandable. 
    public static String separate(String word){
        
        String result = "";
        
        for(int i = 0; i<word.length(); i++){
            
            if(i != word.length()-1){ //To avoid adding a space for the last character.
                result += word.charAt(i);
                result += " ";
            }
            
            else{ //To avoid adding a space for the last character.
                
                result += word.charAt(i);
            }    
        
        }
        return result;
    }
    
    //This method shows the scores.
    public static void showScores(){
        
        int numOfIndex = 1; //To specify the score of the game being played.
        
        if(!scores.isEmpty()){ //Checks if the "score" list is empty.
        
            for(int i = 0; i<scores.size(); i++){ //A for loop that prints the scores from the "scores" list along with their corresponding game indices.
                
                System.out.println(numOfIndex + ". game's score: " + scores.get(i));
                numOfIndex++;
                
            }
            numOfIndex = 1; //To start from 1 when playing a new game.
        }
        
        else{ //This else block operates in the case where no games have been played.
            System.out.println("No Score.");
            
        }
        
        System.out.println();
        Menu(); //Redirects to the Menu.
        
    }
    
    //This method exit the game.
    public static void Exit(){
        
        System.out.println("You exited game.");
        
    }
}

