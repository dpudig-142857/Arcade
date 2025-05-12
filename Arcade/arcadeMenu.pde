void arcade(){
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
    image(door, 100 * width/3000, 435 * height/2000, 600 * width/3000, 1336.438 * height/2000);
  }else{
    door = loadImage("Closed door.png");
    image(door, 100 * width/3000, 475 * height/2000, 600 * width/3000, 1200 * height/2000);
  }
  
  image(arcadeMachine2048, 1000 * width/3000, 550 * height/2000, 600 * width/3000, 1200 * height/2000);
  if(mouseX >= 1000 * width/3000 && mouseX <= 1600 * width/3000 && mouseY >= 550 * height/2000 && mouseY <= 1750 * height/2000){
    fill(#5e91fd);
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
    fill(#5e91fd);
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
  
  fill(#B284BE);
  
  text(welcome, 400 * width/3000, 415 * height/2000);
}
