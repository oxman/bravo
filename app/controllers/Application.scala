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
            single(
                "color" -> text
            )
        )

    def index = Action {
        val colors = Colors.getAll

        Ok(views.html.index(colors))
    }

    def result = Action { implicit request =>

        val colorSubmitted = colorForm.bindFromRequest.get
        val color = Colors.getAll((new Random).nextInt(3))

        val good =
            if (colorSubmitted == color.key) {
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