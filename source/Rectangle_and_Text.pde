void rectt(float x, float y, float w, float h, float r, color c) { 
  fill(c); 
  rect(x, y, w, h, r);
}

void textt(String t, float x, float y, float w, float h, color c, float s, int align) {
  fill(c); 
  textAlign(align); 
  textSize(s); 
  text(t, x, y, w, h);
}
