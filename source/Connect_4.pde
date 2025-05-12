void mainGameconnect4(){
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

int nextSpace(int x){
  if(x>=0){
    for(int y = h - 1; y >= 0; y--){
      if(connect4Board[y][x]==0){
        return y;
      }
    }
  }
  return -1;
}

int getWinner(){
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

int piece(int y, int x){
  if(y < 0 || x < 0 || y >= h || x >= w){
    return 0;
  }else{
    return connect4Board[y][x];
  }
}

void restartconnect4(){
  player = int(random(1,3));
  for(int x = 0; x < w; x++){
    for(int y = 0; y < h; y++){
      connect4Board[y][x] = 0;
    }
  }
  
  tadaFile = new SoundFile(this, "TaDa.wav");
  dropCoinFile = new SoundFile(this, "CoinDrop.wav");
}

void playerText(){
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

void letters(){
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
