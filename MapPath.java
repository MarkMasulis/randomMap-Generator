import java.util.*;
public class MapPath
{
    /*
    ' ' = Path
    'X' = Wall
    'E' = Entrance
    'V' = Exit
    */
    private char[][] map;
    private Random r = new Random();
    private int length;
    private int width;
    public MapPath(int length, int width){
        this.length = length;
        this.width = width;
        map = new char[length][width];
        //Create random maps until a desired map is created.
        do{
            for(int i = 0; i < length; i++){
                for(int j = 0; j < width; j++){
                    setTiles(i,j);
                }
            }
            int entrancePosition = r.nextInt(3);
            setEntranceExit('E', entrancePosition);
            int exitPosition = r.nextInt(3);
            setEntranceExit('V', exitPosition);
        }while(!goodPath());
    }
    private boolean goodPath(){
        boolean playable = true;
        return playable;
    }
    private boolean hasEntranceAndExit(){
        boolean hasEntrance = false;
        boolean hasExit = false;
        
        return hasEntrance && hasExit;
    }
    
    /*
    Takes distance from tile to closest corner. Makes random number equal to some distance from any point within the grid.
    if distance from closest corner is less than the random number, a wall is created. This leads to more open space towards center.
    */
    private void setTiles(int l, int w){
        if(l == 0 || l == map.length-1 || w == 0 || w == map[l].length-1){
            map[l][w] = 'X';
        }else{
            int shortestLength = Math.min(l, length-l);
            int shortestWidth = Math.min(w, width -w);
            double pointDistance = Math.sqrt(Math.pow(shortestLength, 2) + Math.pow(shortestWidth, 2));
            double randomDistance = r.nextDouble()
                *Math.sqrt(Math.pow(r.nextInt(length-1), 2) + Math.pow(r.nextInt(width-1), 2));
            if(randomDistance > pointDistance){
                map[l][w] = 'X';
            }else{
                map[l][w] = ' ';
            }
        }
    }
    private void setEntranceExit(char type, int rValue){
        switch(rValue){
                case 0: map[r.nextInt(length-1)][0] = type;
                break;
                case 1: map[0][r.nextInt(width-1)] = type;
                break;
                case 2: map[length-1][r.nextInt(width-1)] = type;
                break;
                case 3: map[r.nextInt(length-1)][width-1] = type;
                break;
            }
    }
    
    public String toString(){
        String str = "";
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j< map[i].length; j++){
                str += map[i][j] + " ";
            }
            str += "\n";
        }
        
        return str;
    }
}