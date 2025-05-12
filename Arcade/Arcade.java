import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Arcade extends PApplet {

public void setup(){
  
  restart2048();
  restartconnect4();
  
  squareWidth = 250 * width/3000;
  squareHeight = 248 * height/2000;
  w = width/squareWidth;
  h = 7;
  topScreen = squareHeight + 20 * width/3000;
  connect4Board = new int[h][w];
  
  gap = 10 * width/3000;
  squareSize = 300 * width/3000;
  screenX = 875 * width/3000;
  screenY = 350 * height/2000;
  
  whooshFile = new SoundFile(this, "Whoosh.wav");
  doorFile = new SoundFile(this, "Door.wav");
  doorFile.play();
  arcadePowerUpFile = new SoundFile(this, "Arcade Power Up.wav");
  arcadePowerDownFile = new SoundFile(this, "Arcade Power Down.wav");
}

public void draw(){
  if(gameMenu2048 == false && gameMenuconnect4 == false){
    arcade();
  }
  
  if(gameMenu2048 == true && gameMenuconnect4 == false){
    menu2048();
  }
  
  if(gameMenu2048 == false && gameMenuconnect4 == true){
    menuconnect4();
  }
  
  if(game2048 == true && gameconnect4 == false){
    mainGame2048();
  }
  
  if(game2048 == false && gameconnect4 == true){
    mainGameconnect4();
  }
  
  if(instructionsButton2048 == true && instructionsButtonconnect4 == false){
    instructions2048();
  }
  
  if(instructionsButton2048 == false && instructionsButtonconnect4 == true){
    instructionsconnect4();
  }
}
public void restartButton(){
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  textSize(100 * width/3000);
  if(mouseX >= width/2 - textWidth(restart)/2 - 50 * width/3000 && mouseX <= width/2 + textWidth(restart)/2 + 50 * width/3000 && mouseY <= 200 * height/2000 && mouseY >= 75 * height/2000){
    colour = 255;
  }else{
    colour = 0;
  }
  rectt(width/2 - textWidth(restart)/2 - 50 * width/3000, 50 * height/2000, textWidth(restart) + 100 * width/3000, 125 * height/2000, 10, color(colour));
  textt(restart, width/2 - textWidth(restart)/2, 75 * height/2000, width, height, color(255 - colour), 100 * width/3000, LEFT);
}

public void buttons(){
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  textAlign(LEFT);
  
  //quitting the game button
  textSize(200 * width/3000);
  if(mouseX >= width-textWidth("QUIT")-10 * width/3000 && mouseX <= width-10 * width/3000 && mouseY <= 200 * height/2000 && mouseY >= 10 * height/2000){
    fill(255);
  }else{
    fill(161, 229, 76);
  }
  text("QUIT", width-textWidth("QUIT")-10 * width/3000, 200 * height/2000);
  
  //back a screen
  textSize(200 * width/3000);
  if(mouseX >= 10 * width/3000 && mouseX <= textWidth("BACK") + 10 * width/3000&& mouseY <= 200 * height/2000 && mouseY >= 10 * height/2000){
    fill(255);
  }else{
    fill(161, 229, 76);
  }
  text("BACK", 10 * width/3000, 200 * height/2000);
}
public void mainGameconnect4(){
  background();
  ellipseMode(CORNER);
  stroke(32);
  restartButton();
  for(int row = 0; row < w; row++){
    for(int col = 0; col < h; col++){
      fill(255);
      rect(row * squareWidth, topScreen + col * squareHeight, squareWidth, squareHeight);
      if(connect4Board[col][row] > 0){
        if(getWinner() != -1){
          if(connect4Board[col][row]==1){
            fill(255, 0, 0);
          }
          if(connect4Board[col][row]==2){
            fill(255, 255, 0);
          }
        }else{
          fill(0);
        }
        noStroke();
        ellipse(row * squareWidth, topScreen + col * squareHeight, squareWidth, squareHeight);
        stroke(32);
      }
    }
  }   
  playerText();    
  letters();    
  if(getWinner() != 0){
    font = createFont("AA_typewriter.ttf",32);
    textFont(font);
    textSize(100 * width/3000);
    textAlign(LEFT);
    if(!tadaFile.isPlaying()){
      tadaFile.play();
    }
    fill(0);
    textSize(300);
    
    String winnerText = "Player " + getWinner() + " wins";
    
    text(winnerText, width/2 - textWidth(winnerText)/2, height/2);
  }
  
  buttons();
}

public int nextSpace(int x){
  if(x>=0){
    for(int y = h - 1; y >= 0; y--){
      if(connect4Board[y][x]==0){
        return y;
      }
    }
  }
  return -1;
}

public int getWinner(){
  //rows
  for(int x = 0; x <= w; x++){
    for(int y = 0; y <= h; y++){
      if(piece(y, x) != 0 && piece(y, x) == piece(y, x + 1) && piece(y, x) == piece(y, x + 2) && piece(y, x) == piece(y, x + 3)){
        return piece(y, x);
      }
    }
  }
  
  //columns
  for(int x = 0; x <= w; x++){
    for(int y = 0; y <= h; y++){
      if(piece(y, x) != 0 && piece(y, x) == piece(y + 1, x) && piece(y, x) == piece(y + 2, x) && piece(y, x) == piece(y + 3, x)){
        return piece(y, x);
      }
    }
  }
  
  //diagonals
  for(int x = 0; x <= w; x++){
    for(int y = 0; y <= h; y++){
      for(int d = -1; d<=1; d+=2){
        if(piece(y, x) != 0 && piece(y, x) == piece(y + 1*d, x + 1) && piece(y, x) == piece(y + 2*d, x + 2) && piece(y, x) == piece(y + 3*d, x + 3)){
          return piece(y, x);
        }
      }
    }
  }
  
  for(int x = 0; x <= w; x++){
    for(int y = 0; y <= h; y++){
      if(piece(y, x) == 0){
        return 0;
      }
    }
  }
  
  return -1;
}

public int piece(int y, int x){
  if(y < 0 || x < 0 || y >= h || x >= w){
    return 0;
  }else{
    return connect4Board[y][x];
  }
}

public void restartconnect4(){
  player = PApplet.parseInt(random(1,3));
  for(int x = 0; x < w; x++){
    for(int y = 0; y < h; y++){
      connect4Board[y][x] = 0;
    }
  }
  
  tadaFile = new SoundFile(this, "TaDa.wav");
  dropCoinFile = new SoundFile(this, "CoinDrop.wav");
}

public void playerText(){
  fill(0);
  textSize(50 * width/3000);  
  if(player == 1){
    text("Player 1: ", width/2 - textWidth(restart)/2 - 200 * width/3000 - textWidth("Player 1: Red chips"), 75 * height/2000);
    text("chips", width/2 - textWidth(restart)/2 - 200 * width/3000 - textWidth("Player 1: Red chips") + textWidth("Player 1: Red "), 75 * height/2000);
    fill(255, 0, 0);
    text("Red", width/2 - textWidth(restart)/2 - 200 * width/3000 - textWidth("Player 1: Red chips") + textWidth("Player 1: "), 75 * height/2000);
  }if(player == 2){
    text("Player 2: ", width/2 + textWidth(restart)/2 + 200 * width/3000, 75 * height/2000);
    text("chips", width/2 + textWidth(restart)/2 + 200 * width/3000 + textWidth("Player 2: Yellow "), 75 * height/2000);
    fill(255, 255, 0);
    text("Yellow", width/2 + textWidth(restart)/2 + 200 * width/3000 + textWidth("Player 2: "), 75 * height/2000);
  }
}

public void letters(){
  font = createFont("Courier", 32);
  textFont(font);
  fill(0);
  textSize(50 * width/3000);
  textAlign(CENTER);
  text("A", squareWidth/2, topScreen);
  text("B", squareWidth/2 + squareWidth, topScreen - 10 * height/2000);
  text("C", squareWidth/2 + squareWidth*2, topScreen - 10 * height/2000);
  text("D", squareWidth/2 + squareWidth*3, topScreen - 10 * height/2000);
  text("E", squareWidth/2 + squareWidth*4, topScreen - 10 * height/2000);
  text("F", squareWidth/2 + squareWidth*5, topScreen - 10 * height/2000);
  text("G", squareWidth/2 + squareWidth*6, topScreen - 10 * height/2000);
  text("H", squareWidth/2 + squareWidth*7, topScreen - 10 * height/2000);
  text("I", squareWidth/2 + squareWidth*8, topScreen - 10 * height/2000);
  text("J", squareWidth/2 + squareWidth*9, topScreen - 10 * height/2000);
  text("K", squareWidth/2 + squareWidth*10, topScreen - 10 * height/2000);
  text("L", squareWidth/2 + squareWidth*11, topScreen - 10 * height/2000);
}
public void menuconnect4() {
  clear();
  fill(0xff5e91fd);
  rect(0, 0, width, height);

  textAlign(LEFT);

  //main title
  font = createFont("BlockyLettersInverted.ttf", 32);
  textFont(font);
  textSize(400 * width/3000);
  fill(0);
  text(titleconnect4, width/2 - textWidth(titleconnect4)/2, height/2 + 100 * height/2000);

  //font
  font = createFont("AA_typewriter.ttf", 32);
  textFont(font);

  //play button
  textSize(100 * width/3000);
  if (mouseX>=width-400 * width/3000 && mouseX<=width-50 * width/3000 && mouseY>=height-400 * height/2000 && mouseY<=height-3 * height/2000) {
    play = loadImage("Play_Highlighted.png");
  } else {
    play = loadImage("Play.png");
  }
  fill(0);
  image(play, width-400 * width/3000, height-400 * height/2000, 350 * width/3000, 398 * height/2000);
  text("PLAY", width-375 * width/3000, height-180 * height/2000);

  textSize(200 * width/3000);
  if (mouseX >= 10 * width/3000 && mouseX <= textWidth("INSTRUCTIONS") + 10 * width/3000 && mouseY >= height-170 * height/2000 && mouseY <= height - 10 * height/2000) {
    fill(255);
  } else {
    fill(161, 229, 76);
  }
  text("INSTRUCTIONS", 10 * width/3000, height-10 * height/2000);

  buttons();
}

public void instructionsconnect4() {
  game2048 = false;
  gameconnect4 = false;

  clear();
  background(0xff5e91fd);

  font = createFont("BlockyLettersInverted.ttf", 32);
  textFont(font);
  textSize(250 * width/3000);

  textt(titleconnect4, width/2 - textWidth(titleconnect4)/2, 100 * height/2000, textWidth(titleconnect4 + " "), height, color(0), 250 * width/3000, LEFT);

  font = createFont("AA_typewriter.ttf", 32);
  textFont(font);
  textSize(250 * width/3000);

  buttons();

  String example3 = "Choose a\ncolumn between\nA and L";
  textt(example3, width/2 - textWidth(example3)/2, height/4, textWidth(example3 + ' '), height, color(0), 250 * width/3000, CENTER);
}
public void mainGame2048() {
  noStroke();
  background();
  restartButton();
  createBoard();
  createNumbers();
  buttons();
  score();

  //checks for game over
  if (life<1) { 
    gameOverScreen();
  }

  //checks for winner
  if (life==2048) {
    winnerScreen();
  }
}

public void createBoard(){
  //grid
  for (int row = 0; row < board.length; row++){
    for (int col = 0; col < board[row].length; col++) {
      rectt(screenX+(gap+squareSize)*col, screenY+(gap+squareSize)*row, squareSize, squareSize, 5, color(200));
    }
  }
}

public void createNumbers(){
  float grs=100;
  for (int row = 0; row < frames; row++){
    for (int col = 0; col < board[row].length; col++) {
      float xt = screenX + (gap + squareSize) * col;
      float yt = screenY + (gap + squareSize) * row; 
      float x = xt;
      float y = yt;
      float val = board[row][col];
      float tm = (frameCount-aS) * 1.0f / aL;
      
      if (frameCount - aS < aL && pp[row][col][0] > 0) {
        int py = screenY + (gap + squareSize) * pp[row][col][1];
        int px = screenX + (gap + squareSize) * pp[row][col][2];
        
        x = (x - px) * tm + px;
        y = (y - py) * tm + py;
        
        if (pp[row][col][0]>1) { 
          val=pp[row][col][0]; 
          textt("" + pp[row][col][0], xt, yt + squareSize/2 - 60, squareSize, squareSize, numberColour, 100, align);
          rectt(xt, yt, squareSize, squareSize, 5, color(255-log(val)/log(2)*130/11, log(val)/log(2)*130/11, log(val)/log(2)*255/11));
        }
      }
      if (frameCount - aS > aL || pp[row][col][0] >= 0) {
        if (pp[row][col][0] >= 2) { 
          float gr = abs(0.5f - tm) * 2;
          if (frameCount - aS > aL * 3){
            gr=1;
          }else{
            grs=gr; 
          }
          rectt(x - 2 * gr, y - 2 * gr, squareSize + 4 * gr, squareSize + 4 * gr, 5, color(255, 255, 0, 100));
        }else if (pp[row][col][0]==1){
          rectt(x - 2, y - 2, squareSize + 4, squareSize + 4, 5, color(255, 100));
        }
        if (val>0) {
          rectt(x, y, squareSize, squareSize, 5, color(255-log(val)/log(2)*130/11, log(val)/log(2)*130/11, log(val)/log(2)*255/11));
          textt("" + PApplet.parseInt(val), x, y + squareSize/2 - 60, squareSize, squareSize, numberColour, 100, align);
        }
      }
      
      if(life==1){
        if(board[row][col] == starter * pow(2,10)){
          life = 2048;
          if(!tadaFile.isPlaying()){
            tadaFile.play();
          }
        }
      }
    }
    if (grs>0){
      textt(""+score, 0, Height/2, Width, 200, color(255, 255, 0, 200), grs*40, align);
    }
  }
}

public void restart2048() {
  board = new int[4][4];
  spawn();
  score = 0;
  life = 1;
  Width = width;
  Height = height;
  
  popFile = new SoundFile(this, "Pop.wav");
}

public void spawn() {
  ArrayList<Integer> xs = new ArrayList<Integer>();
  ArrayList<Integer> ys = new ArrayList<Integer>();
  for (int row=0; row <= board.length-1; row++){
    for (int col=0; col<=board[row].length-1; col++){
      for (int k=0; k<1&&board[row][col]==0; k++){
        xs.add(col);
        ys.add(row);
      }
    }
  }
  int r=(PApplet.parseInt(random(xs.size())));
  int y=ys.get(r);
  int x=xs.get(r);
  int z = board[y][x]=random(-(pp[y][x][0] = -1)) < .9f ? starter : starter*2;
}

public void winnerScreen(){
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  
  textt("Winner Winner Chicken Dinner", 0, screenY + 150, screenX, height, color(0), 200, CENTER);
  textSize(100);
  if(mouseX >= width - (screenX/2) - textWidth(keepGoing)/2 - 50 && mouseX <= width - (screenX/2) + textWidth(keepGoing)/2 + 50 && mouseY >= height/2 - 62.5f - 25 && mouseY <= height/2 - 62.5f + 100){
    colour = 255;
  }else{
    colour = 0;
  }
  rectt(width - (screenX/2) - textWidth(keepGoing)/2 - 50, height/2 - 62.5f - 25, textWidth(keepGoing) + 100, 125, 10, color(colour));
  textt(keepGoing, width - (screenX/2) - textWidth(keepGoing)/2, height/2 - 62.5f, width, height, color(255 - colour), 100, LEFT);
}

public boolean gameOver2048() {
  int[] directionX = {1, -1, 0, 0};
  int[] directionY = {0, 0, 1, -1};
  int[] ppbk[][] = pp;
  boolean out = true;
  for (int i = 0; i < board.length; i++){
    if (go(directionY[i], directionX[i], false) != null){
      out = false;
    }
  }
  pp = ppbk;
  return out;
}

public void gameOverScreen(){
  textSize(300);
  textt("Game", screenX - 10 - textWidth("Game"), 800, screenX, height, color(0), 300, LEFT);
  textt("Over!", width - screenX + 10, 800, screenX, height, color(0), 300, LEFT);
}

public void background(){
  //background
  rectt(0, 0, width, height, 10, color(150));
}

public void score(){
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  textSize(150 * width/3000);
  textt("Score: ", 10 * width/3000, height - 150 * height/2000, width, height, color(0), 150 * width/3000, LEFT);
  textt(score + " points", textWidth("Score: ") + 10 * width/3000, height - 150 * height/2000, width, height, color(255), 150 * width/3000, LEFT);
}

public int[][] go(int directionY, int directionX, boolean updateScore) {
  pp = new int[4][4][3]; 
  boolean moved = false;
  int[][] boardCopy = new int[4][4];
  for (int row=0; row<=board.length-1; row++) {
    for (int col=0; col<= board[row].length-1; col++) {
      boardCopy[row][col]=board[row][col];
    }
  }

  if (directionX != 0 || directionY != 0) {  
    int direction = directionX != 0 ? directionX : directionY;
    for (int perpendicular = 0; perpendicular <= board.length - 1; perpendicular++) {
      for (int tangent = (direction > 0 ? board.length-2 : 1); tangent != (direction > 0 ? -1 : board.length); tangent = tangent - direction) {
        int y = directionX != 0 ? perpendicular : tangent;
        int x = directionX != 0 ? tangent : perpendicular;
        int targetY = y;
        int targetX = x; 
        if (boardCopy[y][x]==0){
          continue;
        }
        for (int i = (directionX != 0 ? x : y) + direction; i != (direction > 0 ? board.length : -1); i += direction) { 
          int r = directionX != 0 ? y : i, c = directionX != 0 ? i : x; 
          if (boardCopy[r][c]!=0 &&boardCopy[r][c]!=boardCopy[y][x]){
            break; 
          }if (directionX!=0){
            targetX=i; 
          }else{
            targetY=i;
          }            
        }
        if ( (directionX != 0 && targetX == x) || (directionY != 0 && targetY == y)){
          continue;
        }
        else if (boardCopy[targetY][targetX]==boardCopy[y][x]) {
          moved=true;
          pp[targetY][targetX][0]=boardCopy[targetY][targetX];
          if (updateScore){
            score+=(boardCopy[targetY][targetX]*=2);
            popFile.play();
          }
        } else if ((directionX!=0&&targetX!=x)||(directionY!=0&&targetY!=y)) { 
          pp[targetY][targetX][0]=1; 
          boardCopy[targetY][targetX]=boardCopy[y][x]; 
          moved=true;
        }
        if (moved) { 
          pp[targetY][targetX][1] = y; 
          pp[targetY][targetX][2] = x; 
          boardCopy[y][x] = 0;
        }
      }
    }
  }
  if (!moved){
    return null;
  }
  aS = frameCount; 
  return boardCopy;
}
public void menu2048(){
  clear();
  fill(0xff5e91fd);
  rect(0, 0, width, height);
  
  textAlign(LEFT);
  
  //main title
  font = createFont("BlockyLettersInverted.ttf",32);
  textFont(font);
  textSize(1000 * width/3000);
  fill(0);
  text(title2048, width/2 - textWidth(title2048)/2, height/2 + 300 * height/2000);
  
  //font
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  
  //play button
  textSize(100 * width/3000);
  if(mouseX>=width-400 * width/3000 && mouseX<=width-50 * width/3000 && mouseY>=height-400 * height/2000 && mouseY<=height-3 * height/2000){
    play = loadImage("Play_Highlighted.png");
  }else{
    play = loadImage("Play.png");
  }
  fill(0);
  image(play, width-400 * width/3000, height-400 * height/2000, 350 * width/3000, 398 * height/2000);
  text("PLAY", width-375 * width/3000, height-180 * height/2000);
  
  textSize(200 * width/3000);
  textAlign(LEFT);
  if(mouseX >= 10 * width/3000 && mouseX <= textWidth("INSTRUCTIONS") + 10 * width/3000 && mouseY >= height-170 * height/2000 && mouseY <= height - 10 * height/2000){
    fill(255);
  }else{
    fill(161, 229, 76);
  }
  text("INSTRUCTIONS", 10 * width/3000, height-10 * height/2000);
  
  buttons();
}

public void instructions2048(){
  game2048 = false;
  gameconnect4 = false;
  
  clear();
  background(0xff5e91fd);
  
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  
  textSize(150 * width/3000);
  textt(example, width/4 - textWidth(example)/2, 600 * height/2000, textWidth(example + " "), height, color(255, 255, 127.5f), 150 * width/3000, LEFT);
  rectt(width/32 - 50 * width/3000, height/2 - 250 * height/2000, (width/2)+100 * width/3000, (height/2)+100 * height/2000, 0, color(0));
  screenDesign = loadImage("Screen Design 2048.png");
  image(screenDesign, width/32, height/2 - 200 * height/2000, width/2, height/2);
  
  font = createFont("BlockyLettersInverted.ttf",32);
  textFont(font);
  textSize(250 * width/3000);
  
  textt(title2048, width/2 - textWidth(title2048)/2, 100 * height/2000, textWidth(title2048 + " "), height, color(0), 250 * width/3000, LEFT);
  
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  textSize(200 * width/3000);
  
  //controls
  int size = 200 * width/3000;
  int starter = width/2 - size*4;
  textSize(size);
  
  textt("UP arrow", width*3/4 - textWidth("UP arrow")/2, starter, textWidth("UP arrow "), height, color(0, 0, 0), size, CENTER);
  textt("DOWN arrow", width*3/4 - textWidth("DOWN arrow")/2, starter + size, textWidth("DOWN arrow "), height, color(0, 0, 0), size, CENTER);
  textt("LEFT arrow", width*3/4 - textWidth("LEFT arrow")/2, starter + size*2, textWidth("LEFT arrow "), height, color(0, 0, 0), size, CENTER);
  textt("RIGHT arrow", width*3/4 - textWidth("RIGHT arrow")/2, starter + size*3, textWidth("RIGHT arrow "), height, color(0, 0, 0), size, CENTER);
  
  buttons();
}
public void rectt(float x, float y, float w, float h, float r, int c) { 
  fill(c); 
  rect(x, y, w, h, r);
}

public void textt(String t, float x, float y, float w, float h, int c, float s, int align) {
  fill(c); 
  textAlign(align); 
  textSize(s); 
  text(t, x, y, w, h);
}
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


SoundFile popFile;
SoundFile doorFile;
SoundFile tadaFile;
SoundFile whooshFile;
SoundFile dropCoinFile;
SoundFile arcadePowerUpFile;
SoundFile arcadePowerDownFile;
public void arcade(){
  clear();
  noStroke();
  
  textAlign(CENTER);
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  
  arcadeRoom = loadImage("Arcade room.png");
  arcadeMachine2048 = loadImage("Arcade Machine 2048.png");
  arcadeMachineconnect4 = loadImage("Arcade Machine Connect 4.png");
  door = loadImage("Closed door.png");
  
  image(arcadeRoom, 0, 0, width, height);
  
  if(mouseX >= 100 * width/3000 && mouseX <= 700 * width/3000 && mouseY >= 475 * height/2000 && mouseY <= 1675 * height/2000){
    door = loadImage("Open door.png");
    image(door, 100 * width/3000, 435 * height/2000, 600 * width/3000, 1336.438f * height/2000);
  }else{
    door = loadImage("Closed door.png");
    image(door, 100 * width/3000, 475 * height/2000, 600 * width/3000, 1200 * height/2000);
  }
  
  image(arcadeMachine2048, 1000 * width/3000, 550 * height/2000, 600 * width/3000, 1200 * height/2000);
  if(mouseX >= 1000 * width/3000 && mouseX <= 1600 * width/3000 && mouseY >= 550 * height/2000 && mouseY <= 1750 * height/2000){
    fill(0xff5e91fd);
    rect(1080 * width/3000, 860 * height/2000, 440 * width/3000, 290 * height/2000);
    fill(255);
    textSize(100 * width/3000);
    text("PLAY", 1300 * width/3000, 1035 * height/2000);
  }else{
    fill(0);
    rect(1080 * width/3000, 860 * height/2000, 440 * width/3000, 290 * height/2000);
  }
  
  image(arcadeMachineconnect4, 2000 * width/3000, 550 * height/2000, 600 * width/3000, 1200 * height/2000);
  if(mouseX >= 2000 * width/3000 && mouseX <= 2600 * width/3000 && mouseY >= 550 * height/2000 && mouseY <= 1750 * height/2000){
    fill(0xff5e91fd);
    rect(2080 * width/3000, 860 * height/2000, 440 * width/3000, 290 * height/2000);
    fill(255);
    textSize(100 * width/3000);
    text("PLAY", 2300 * width/3000, 1035 * height/2000);
  }else{
    fill(0);
    rect(2080 * width/3000, 860 * height/2000, 440 * width/3000, 290 * height/2000);
  }
  
  font = createFont("Neon.ttf", 32);
  textFont(font);
  textSize(100 * width/3000);
  
  String welcome = "WELCOME";
  
  fill(0);
  
  rect(400 * width/3000 - textWidth(welcome)/2 - 25 * width/3000, 300 * height/2000, textWidth(welcome) + 50 * width/3000, 150 * height/2000, 75);
  
  fill(0xffB284BE);
  
  text(welcome, 400 * width/3000, 415 * height/2000);
}
public void keyPressed(){
  //2048
  if(game2048 == true && gameconnect4 == false){
    if (life == 1 || life == 2) {
      
      //moving up or down
      int directionY=keyCode==UP ? -1 : (keyCode==DOWN ? 1 : 0);
      
      //moving left or right
      int directionX=keyCode==LEFT ? -1 : (keyCode==RIGHT ? 1 : 0);
      
      //create a new board
      int[][] newBoard = go(directionY, directionX, true);
      if (newBoard != null) {
        board = newBoard;
        spawn();
      }
      
      if (gameOver2048()){
        life = 0;
      }
    }
  }
  
  //connect 4
  if(game2048 == false && gameconnect4 == true){
    int x = -1;
    int y = nextSpace(x);
    switch(key){
      case 'a':
        x = 0;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'b':
        x = 1;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'c':
        x = 2;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }        
        break;
      case 'd':
        x = 3;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'e':
        x = 4;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'f':
        x = 5;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'g':
        x = 6;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'h':
        x = 7;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'i':
        x = 8;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'j':
        x = 9;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'k':
        x = 10;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'l':
        x = 11;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'A':
        x = 0;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'B':
        x = 1;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'C':
        x = 2;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'D':
        x = 3;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'E':
        x = 4;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'F':
        x = 5;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'G':
        x = 6;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'H':
        x = 7;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'I':
        x = 8;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'J':
        x = 9;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'K':
        x = 10;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;
      case 'L':
        x = 11;
        y = nextSpace(x);
        if(nextSpace(x)!=-1){
          dropCoinFile.play();
        }
        break;        
    }
    if(getWinner()==0){
      if(y>=0){
        connect4Board[y][x] = player;
        if(player==1){
          player = 2;
        }else{
          player = 1;
        }
      }
    }
  }
}
public void mousePressed(){
  if(game2048 == false && gameconnect4 == false && gameMenu2048 == false && gameMenuconnect4 == false && instructionsButton2048 == false && instructionsButtonconnect4 == false){
    //arcade to 2048 menu
    if(mouseX >= 1000 * width/3000 && mouseX <= 1600 * width/3000 && mouseY >= 550 * height/2000 && mouseY <= 1750 * height/2000){
      arcadePowerUpFile.play();
      gameMenu2048 = true;
    }    
    //arcade to connect 4 menu
    if(mouseX >= 2000 * width/3000 && mouseX <= 2600 * width/3000 && mouseY >= 550 * height/2000 && mouseY <= 1750 * height/2000){
      arcadePowerUpFile.play();
      gameMenuconnect4 = true;
    }
    //quitting arcade
    if(mouseX >= 100 * width/3000 && mouseX <= 700 * width/3000 && mouseY >= 475 * height/2000 && mouseY <= 1675 * height/2000){
      gameMenu2048 = false;
      gameMenuconnect4 = false;
      doorFile.play();
      delay(1000);
      exit();
    }
  }
  
  if(gameMenu2048 == true && gameMenuconnect4 == false){
    textSize(1000 * width/3000);
    //2048 menu to 2048 game
    if(mouseX>=width-400 * width/3000 && mouseX<=width && mouseY>=height-400 * height/2000 && mouseY<=height){
      arcadePowerUpFile.play();
      game2048 = true;
    }
    
    //2048 menu to arcade
    if(mouseX >= 10 * width/3000 && mouseX <= textWidth("BACK") + 10 * width/3000 && mouseY <= 200 * height/2000 && mouseY >= 10 * height/2000){
      arcadePowerDownFile.play();
      gameMenu2048 = false;
    }
    
    //2048 menu to 2048 instructions
    textSize(200 * width/3000);
    if (mouseX >= 10 * width/3000 && mouseX <= textWidth("INSTRUCTIONS") + 10 * width/3000 && mouseY >= height-170 * height/2000 && mouseY <= height - 10 * height/2000) {
      whooshFile.play();
      instructionsButton2048 = true;
    }
  }
  
  if(gameMenu2048 == false && gameMenuconnect4 == true && gameconnect4 == false){
    textSize(400 * width/3000);
    //connect 4 menu to connect 4 game
    if(mouseX>=width-400 * width/3000 && mouseX<=width && mouseY>=height-400 * height/2000 && mouseY<=height){
      arcadePowerUpFile.play();
      gameconnect4 = true;
    }
    
    //connect 4 menu to arcade
    if(mouseX >= 10 * width/3000 && mouseX <= textWidth("BACK") + 10 * width/3000 && mouseY <= 200 * height/2000 && mouseY >= 10 * height/2000){
      arcadePowerDownFile.play();
      gameMenuconnect4 = false;
    }
    
    //connect 4 menu to connect 4 instructions
    textSize(200 * width/3000);
    if (mouseX >= 10 * width/3000 && mouseX <= textWidth("INSTRUCTIONS") + 10 * width/3000 && mouseY >= height-170 * height/2000 && mouseY <= height - 10 * height/2000) {
      whooshFile.play();
      instructionsButtonconnect4 = true;
    }
  }
  
  if(instructionsButton2048 == true && instructionsButtonconnect4 == false){
    //2048 instructions to 2048 menu
    if(mouseX >= 10 * width/3000 && mouseX <= textWidth("BACK") + 10 * width/3000 && mouseY <= 200 * height/2000 && mouseY >= 10 * height/2000){
      whooshFile.play();
      instructionsButton2048 = false;
      gameMenu2048 = true;
    }
  }
  
  if(instructionsButton2048 == false && instructionsButtonconnect4 == true){
    //connect 4 instructions to connect 4 menu
    if(mouseX >= 10 * width/3000 && mouseX <= 10+textWidth("BACK") + 10 * width/3000 && mouseY <= 200 * height/2000 && mouseY >= 10 * height/2000){
      whooshFile.play();
      instructionsButtonconnect4 = false;
      gameMenuconnect4 = true;
    }
  }
  
  if(game2048 == true && gameconnect4 == false){
    textSize(100 * width/3000);
    //restarting 2048 game
    if(mouseX >= width/2 - textWidth(restart)/2 - 50 * width/3000 && mouseX <= width/2 + textWidth(restart)/2 + 50 * width/3000 && mouseY >= 50 * height/2000 && mouseY <= 175 * height/2000){
      restart2048();
    }
    
    textSize(200 * width/3000);
    //2048 game to 2048 menu
    if(mouseX >= 10 * width/3000 && mouseX <= textWidth("BACK") + 10 * width/3000 && mouseY <= 200 * height/2000 && mouseY >= 10 * height/2000){
      arcadePowerDownFile.play();
      restart2048();
      game2048 = false;
      gameMenu2048 = true;
    }
    
    if(life==2048){
      if(mouseX >= width - (screenX/2) - textWidth(keepGoing)/2 - 50 * width/3000 && mouseX <= width - (screenX/2) + textWidth(keepGoing)/2 + 50 * width/3000 && mouseY >= height/2 - 87.5f * height/2000 && mouseY <= height/2 + 37.5f * height/2000){
        life = 2;
      }
    }
  }
  
  if(game2048 == false && gameconnect4 == true){
    textSize(100 * width/3000);
    //restarting connect 4 game
    if(mouseX >= width/2 - textWidth(restart)/2 - 50 * width/3000 && mouseX <= width/2 + textWidth(restart)/2 + 50 * width/3000 && mouseY >= 50 * height/2000 && mouseY <= 175 * height/2000){
      restartconnect4();
    }
    
    textSize(200 * width/3000);
    //connect 4 game to connect 4 menu
    if(mouseX >= 10 * width/3000 && mouseX <= textWidth("BACK") + 10 * width/3000 && mouseY <= 200 * height/2000 && mouseY >= 10 * height/2000){
      arcadePowerDownFile.play();
      restartconnect4();
      gameconnect4 = false;
      gameMenuconnect4 = true;
    }
    
    int x;
    if(mouseX >= width-400 * width/3000 && mouseX <= width-50 * width/3000 && mouseY >= height-400 * height/2000 && mouseY <= height-3 * height/2000){
      x = -1;
    }
    if(mouseY <= topScreen){
      x = -1;
    }
    if(mouseY >= topScreen && !(mouseX >= width-400 * width/3000 && mouseX <= width-50 * width/3000 && mouseY >= height-400 * height/2000 && mouseY <= height-3 * height/2000)){
      x = mouseX/squareWidth;
      if(nextSpace(x)!=-1){
        dropCoinFile.play();
      }
    }else{
      x = -1;
    }
    int y;
    y = nextSpace(x);
    
    if(getWinner()==0){
      if(y>=0){
        connect4Board[y][x] = player;
        if(player==1){
          player = 2;
        }else{
          player = 1;
        }
      }
    }
  }
  
  if(gameMenu2048 == true || gameMenuconnect4 == true || game2048 == true || gameconnect4 == true || instructionsButton2048 == true || instructionsButtonconnect4 == true){
    //quitting4 program
    if(mouseX >= width-textWidth("QUIT")-10 * width/3000 && mouseX <= width-10 * width/3000 && mouseY <= 170 * height/2000 && mouseY >= 10 * height/2000){
      game2048 = false;
      gameconnect4 = false;
      gameMenu2048 = false;
      gameMenuconnect4 = false;
      instructionsButton2048 = false;
      instructionsButtonconnect4 = false;
      doorFile.play();
      delay(1000);
      exit();
    }
  }
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Arcade" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
