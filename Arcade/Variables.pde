//Overall Arcade
boolean game2048 = false;
boolean gameconnect4 = false;
boolean gameMenu2048 = false;
boolean gameMenuconnect4 = false;
boolean instructionsButton2048 = false;
boolean instructionsButtonconnect4 = false;

PImage arcadeRoom;
PImage arcadeMachine2048;
PImage arcadeMachineconnect4;
PImage door;

//2048
int[][] board = new int[4][4];
int[][] pp[] = new int[4][4][3];
int gap;
int squareSize;
int frames = 4;
int boardLength = gap*(frames+1) + squareSize*frames;
int score = 0;
int life = 1;
int screenX;
int screenY;
int aS;
int aL = 10;
int Width;
int Height;
int numberColour = color(0);
int align = CENTER;

PFont font;
PImage play;
String title2048 = "2048";
String restart = "New Game";
String keepGoing = "Continue";
float colour;
PImage screenDesign;
String example = "Game Start Example";

float colourUP;
float colourDOWN;
float colourLEFT;
float colourRIGHT;

int starter = 2;

//connect 4
int h = 7;
int squareWidth;
int squareHeight;
int w;
int player = 1;
int topScreen;
int winnerBackground = 0;

int[][] connect4Board;
String titleconnect4 = "connect 4";

import processing.sound.*;
SoundFile popFile;
SoundFile doorFile;
SoundFile tadaFile;
SoundFile whooshFile;
SoundFile dropCoinFile;
SoundFile arcadePowerUpFile;
SoundFile arcadePowerDownFile;
