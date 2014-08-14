package controllers;

import models.Poem;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;
import java.util.Random;

public class Application extends Controller {

    public static Result index() {
        List<Poem> poems = Poem.find.all();
        Random random = new Random();
        Poem randomPoem = poems.get(random.nextInt(poems.size()));
        return ok(index.render(""+poems.size(),
                randomPoem.title, randomPoem.author, randomPoem.content));
    }

}
