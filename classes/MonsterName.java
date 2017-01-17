import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
import java.io.*;

//Return random noun

class MonsterName {

	String name;

	public MonsterName() {
		try{
			Path pathToMyTextFile = Paths.get("./Names/randMonsters.txt");
			List<String> linesInFile = Files.readAllLines(pathToMyTextFile,StandardCharsets.UTF_8);
			Random randomUtil = new Random();
			int max = linesInFile.size() - 1;
			int min = 0;
			int randomIndexForWord = randomUtil.nextInt((max - min + 1)) + min;
			String randomWord = linesInFile.get(randomIndexForWord);
			name = randomWord;
		}catch(IOException ioe){
    		//Handle exception here, most of the time you will just log it.
    		System.out.println("wtf");
    		System.out.println(ioe);

		}
	}

	public static void main(String[] args){
		MonsterName test = new MonsterName();
		System.out.println(test.name);
	}

}
