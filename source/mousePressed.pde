void mousePressed(){
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
      if(mouseX >= width - (screenX/2) - textWidth(keepGoing)/2 - 50 * width/3000 && mouseX <= width - (screenX/2) + textWidth(keepGoing)/2 + 50 * width/3000 && mouseY >= height/2 - 87.5 * height/2000 && mouseY <= height/2 + 37.5 * height/2000){
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
