/**
 * 
 */
package de.schule.madnx.server.game.map;

/**
 * @author xgadscj
 *
 */
public class FourPlayerMap implements Map{
	private int[][] map = { { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 3, 4 }, { 2, 4 }, { 1, 4 }, { 1, 5 }, { 1, 6 },
			{ 1, 7 }, { 1, 8 }, { 2, 8 }, { 3, 8 }, { 4, 8 }, { 4, 9 }, { 4, 10 }, { 4, 11 }, { 5, 11 }, { 6, 11 },
			{ 7, 11 }, { 8, 11 }, { 8, 10 }, { 8, 9 }, { 8, 8 }, { 9, 8 }, { 10, 8 }, { 11, 8 }, { 11, 7 }, { 11, 6 },
			{ 11, 5 }, { 11, 4 }, { 10, 4 }, { 9, 4 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 8, 1 }, { 7, 1 }, { 6, 1 },
			{ 5, 1 } };
	private int[][] redSpawn = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
	private int[][] blueSpwan = { { 0, 11 }, { 0, 12 }, { 1, 11 }, { 1, 12 } };
	private int[][] greenSpawn = { { 11, 11 }, { 11, 12 }, { 12, 11 }, { 12, 12 } };
	private int[][] yellowSpawn = { { 11, 0 }, { 12, 0 }, { 11, 1 }, { 12, 1 } };
	private int[][] redCorner = { { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 } };
	private int[][] blueCorner = { { 2, 6 }, { 3, 6 }, { 4, 6 }, { 5, 6 } };
	private int[][] greenCorner = { { 6, 7 }, { 6, 8 }, { 6, 9 }, { 6, 10 } };
	private int[][] yellowCorner = { { 7, 6 }, { 8, 6 }, { 9, 6 }, { 10, 6 } };
	private int[][] redStart = { { 4, 1 } };
	private int[][] blueStart = { { 1, 8 } };
	private int[][] greenStart = { { 8, 11 } };
	private int[][] yellowStart = { { 11, 4 } };
}
