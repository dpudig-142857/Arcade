void mainGame2048() {
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

void createBoard(){
  //grid
  for (int row = 0; row < board.length; row++){
    for (int col = 0; col < board[row].length; col++) {
      rectt(screenX+(gap+squareSize)*col, screenY+(gap+squareSize)*row, squareSize, squareSize, 5, color(200));
    }
  }
}

void createNumbers(){
  float grs=100;
  for (int row = 0; row < frames; row++){
    for (int col = 0; col < board[row].length; col++) {
      float xt = screenX + (gap + squareSize) * col;
      float yt = screenY + (gap + squareSize) * row; 
      float x = xt;
      float y = yt;
      float val = board[row][col];
      float tm = (frameCount-aS) * 1.0 / aL;
      
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
          float gr = abs(0.5 - tm) * 2;
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
          textt("" + int(val), x, y + squareSize/2 - 60, squareSize, squareSize, numberColour, 100, align);
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

void restart2048() {
  board = new int[4][4];
  spawn();
  score = 0;
  life = 1;
  Width = width;
  Height = height;
  
  popFile = new SoundFile(this, "Pop.wav");
}

void spawn() {
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
  int r=(int(random(xs.size())));
  int y=ys.get(r);
  int x=xs.get(r);
  int z = board[y][x]=random(-(pp[y][x][0] = -1)) < .9 ? starter : starter*2;
}

void winnerScreen(){
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  
  textt("Winner Winner Chicken Dinner", 0, screenY + 150, screenX, height, color(0), 200, CENTER);
  textSize(100);
  if(mouseX >= width - (screenX/2) - textWidth(keepGoing)/2 - 50 && mouseX <= width - (screenX/2) + textWidth(keepGoing)/2 + 50 && mouseY >= height/2 - 62.5 - 25 && mouseY <= height/2 - 62.5 + 100){
    colour = 255;
  }else{
    colour = 0;
  }
  rectt(width - (screenX/2) - textWidth(keepGoing)/2 - 50, height/2 - 62.5 - 25, textWidth(keepGoing) + 100, 125, 10, color(colour));
  textt(keepGoing, width - (screenX/2) - textWidth(keepGoing)/2, height/2 - 62.5, width, height, color(255 - colour), 100, LEFT);
}

boolean gameOver2048() {
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

void gameOverScreen(){
  textSize(300);
  textt("Game", screenX - 10 - textWidth("Game"), 800, screenX, height, color(0), 300, LEFT);
  textt("Over!", width - screenX + 10, 800, screenX, height, color(0), 300, LEFT);
}

void background(){
  //background
  rectt(0, 0, width, height, 10, color(150));
}

void score(){
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  textSize(150 * width/3000);
  textt("Score: ", 10 * width/3000, height - 150 * height/2000, width, height, color(0), 150 * width/3000, LEFT);
  textt(score + " points", textWidth("Score: ") + 10 * width/3000, height - 150 * height/2000, width, height, color(255), 150 * width/3000, LEFT);
}

int[][] go(int directionY, int directionX, boolean updateScore) {
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
