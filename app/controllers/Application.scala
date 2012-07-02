package controllers

import scala.util.Random

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Colors

object Application extends Controller
{

    val colorForm = Form(
            tuple(
                "blue"  -> optional(text),
                "green" -> optional(text),
                "red"   -> optional(text)
            )
        )

    def index = Action {
        val colors = Colors.getAll

        Ok(views.html.index(colors))
    }

    def result = Action { implicit request =>

        val (blue, green, red) = colorForm.bindFromRequest.get
        val color = Colors.getAll((new Random).nextInt(3))

        val good =
            if (blue != None && color.key == "blue") {
                true
            } else if (green != None && color.key == "green") {
                true
            } else if (red != None && color.key == "red") {
                true
            } else {
                false
            }

        if (good) {
            Ok(views.html.good(color))
        } else {
            Ok(views.html.bad(color))
        }

    }

}