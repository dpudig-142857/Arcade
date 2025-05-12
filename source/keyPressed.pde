void keyPressed(){
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
