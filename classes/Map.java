import java.util.*;

class Map {

	List<Location> explored = new ArrayList<Location>();
	int location;

	public Map() {}


	public void mapAdd(Location here){
		explored.add(here);
	}

	public String dispBuff(int longest,String name,boolean addArrow){

		int toFill = longest-name.length()+4;
		int firstBuff = Math.max(2,((int) (toFill/2)));
		int secondBuff= Math.max(2,toFill-firstBuff);

		if (toFill>0){
			StringBuffer frontBuffer = new StringBuffer(firstBuff);
			for (int i = 0; i < firstBuff; i++){frontBuffer.append(" ");}
	   		
	   		StringBuffer endBuffer = new StringBuffer(secondBuff);
			for (int i = 0; i < secondBuff; i++){endBuffer.append(" ");}
			if (addArrow){
   				return "->"+frontBuffer.toString().substring(2)+name+endBuffer.toString().substring(2) +"<-";
   			}else{
   				return frontBuffer.toString()+name+endBuffer.toString();
   			}
   		}else{
   			return name;
   		}
	}





	public void display(int here){
		int longestName = 0;
		for( Location place : explored){
			if (place.type=="Town" && place.name.length()>longestName){
				longestName=place.name.length()+2;
			}
		}
		for( Location place : explored){
			if (place.type=="Town"){
				if (place == explored.get(here)){
					System.out.println(dispBuff(longestName,place.name,true));
				}else{
					System.out.println(dispBuff(longestName,place.name,false));
				}
			}else{
				if (place == explored.get(here)){
					System.out.println(dispBuff(longestName,"X",true));
				}else{
					System.out.println(dispBuff(longestName,"|",false));
				};
			}
		}
		System.out.println(dispBuff(longestName,"?",false));
		System.out.println();
	}



	public static void main(String[] args){
		Map test = new Map();
		Location test0 = new Location(0,5);
		Location test1 = new Location(1,5);
		Location test2 = new Location(2,5);
		Location test3 = new Location(3,5);
		Location test4 = new Location(4,5);
		Location test5 = new Location(5,5);
		Location test6 = new Location(6,5);
		Location test7 = new Location(7,5);
		test.mapAdd(test0);
		test.mapAdd(test1);
		test.mapAdd(test2);
		test.mapAdd(test3);
		test.mapAdd(test4);
		test.mapAdd(test5);
		test.mapAdd(test6);
		test.mapAdd(test7);
		test.display(0);
		test.display(1);
		test.display(2);
		test.display(3);
		test.display(4);
		test.display(5);
		test.display(6);
		test.display(7);
	}
}