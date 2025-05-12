void menuconnect4() {
  clear();
  fill(#5e91fd);
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

void instructionsconnect4() {
  game2048 = false;
  gameconnect4 = false;

  clear();
  background(#5e91fd);

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
