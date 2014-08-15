package controllers;

import models.Poem;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Application extends Controller {

    public static Result index() {
        List<Poem> poems = Poem.find.all();
        Random random = new Random();
        Poem randomPoem = poems.get(random.nextInt(poems.size()));
        List<String> contentLines = Arrays.asList(randomPoem.content.split("\n"));
        return ok(index.render(""+poems.size(),
                randomPoem.title, randomPoem.author, contentLines));
    }

    public static Result readTang() {
        List<Poem> poems = Poem.find.where().eq("dynasty", "tang").findList();
        return ok(tang.render(poems));
    }

    public static Result poem(String author, String title) {
        Poem poem = Poem.find.where().eq("author", author).eq("title", title).findUnique();
        return ok(readpoem.render(poem));
    }

}
