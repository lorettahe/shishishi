package controllers;

import models.Poem;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
        List<Poem> poems = Poem.find.all();
        return ok(index.render(""+poems.size()));
    }

}
