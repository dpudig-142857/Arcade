void setup(){
  fullScreen();
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

void draw(){
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
