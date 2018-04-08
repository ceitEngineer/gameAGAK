/*
        * Copyright (c) 2017 All rights reserved.
        */

        import java.io.Serializable;

/**
 * this class is created to design map editor by features that
 * create and design map semantically.
 * @author      Amir&Abolfazl
 * @since       JDK1.8
 */
public class Map implements Serializable{

    public static final long serialVersionUID = 1l;// to make an executable file and save object todo:[Serializable]
    static int width ,height;
    static int mountOfPlayers;
    int type;
    static int season;
    /**this parameter is for
     *  determining Type of tile*/
    int[][] mapType;

    /**this parameter is to
     *  determine Area of players
     *  can put their things*/
    int[][] mapArea;

    public Map(int width,int height,int mountOfPlayers,int type,int season){

        this.width = width;
        this.height = height;
        this.mountOfPlayers = mountOfPlayers;
        this.type = type;
        this.season = season;

        mapType = new int[width][height];
        mapArea = new int[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mapType[i][j] = type;
            }
        }
        //todo: devide the ground
        mapAreaDeterminer(mountOfPlayers);
    }

    public void mapAreaDeterminer(int num){

        if (num == 2){

            //player 1
            for (int i = 0; i < height/2; i++) {
                for (int j = 0; j < width; j++) {
                    mapArea[i][j] = 1;
                }
            }
            //player 2
            for (int i = height/2; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    mapArea[i][j] = 2;
                }
            }

        }

        if (num == 3){

            //player 1
            for (int i = 0; i < height/2; i++) {
                for (int j = 0; j < width/2; j++) {
                    mapArea[i][j] = 1;
                }
            }
            //player 2
            for (int i = 0; i < height/2; i++) {
                for (int j = width/2; j < width; j++) {
                    mapArea[i][j] = 2;
                }
            }
            //player 3
            for (int i = height/2; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    mapArea[i][j] = 3;
                }
            }

        }

        if (num == 4){

            //player 1
            for (int i = 0; i < height/2; i++) {
                for (int j = 0; j < width/2; j++) {
                    mapArea[i][j] = 1;
                }
            }
            //player 2
            for (int i = 0; i < height/2; i++) {
                for (int j = width/2; j < width; j++) {
                    mapArea[i][j] = 2;
                }
            }
            //player 3
            for (int i = height/2; i < height; i++) {
                for (int j = 0; j < width/2; j++) {
                    mapArea[i][j] = 3;
                }
            }
            //player 4
            for (int i = height/2; i < height; i++) {
                for (int j = width/2; j < width; j++) {
                    mapArea[i][j] = 4;
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMountOfPlayers() {
        return mountOfPlayers;
    }

    public void setMountOfPlayers(int mountOfPlayers) {
        this.mountOfPlayers = mountOfPlayers;
    }

    public int[][] getmapType() {
        return mapType;
    }

    public void setmapType(int[][] mapType) {
        this.mapType = mapType;
    }

}
