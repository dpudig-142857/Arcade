void menu2048(){
  clear();
  fill(#5e91fd);
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

void instructions2048(){
  game2048 = false;
  gameconnect4 = false;
  
  clear();
  background(#5e91fd);
  
  font = createFont("AA_typewriter.ttf",32);
  textFont(font);
  
  textSize(150 * width/3000);
  textt(example, width/4 - textWidth(example)/2, 600 * height/2000, textWidth(example + " "), height, color(255, 255, 127.5), 150 * width/3000, LEFT);
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
