package models


object Colors
{


    val colors = List(new Color("blue", "Bleu"),
                      new Color("green", "Vert"),
                      new Color("red", "Rouge"))


    def getAll = colors

}