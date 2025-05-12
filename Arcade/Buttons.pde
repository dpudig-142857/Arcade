void restartButton(){
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

void buttons(){
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
