package controllers;

import com.avaje.ebean.Ebean;
import play.data.Form;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.mvc.Result;

import javax.persistence.Entity;
import javax.persistence.Id;

import static play.data.Form.form;
import static play.mvc.Controller.session;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

import models.Users;

import views.html.*;

/**
 * Created by loretta on 15/08/14.
 */
public class Authentication {

    public static Result login() {
        return ok(login.render(form(Users.class)));
    }

    public static Result authenticate() {
        Form<Users> loginForm = form(Users.class).bindFromRequest();
        Users user = Ebean.find(Users.class)
                .where().eq("email", loginForm.get().email)
                .eq("password", loginForm.get().password)
                .findUnique();
        if (user == null) {
            return badRequest(login.render(form(Users.class)));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(routes.Application.index());
        }
    }

    public static Result register() {
        return ok(register.render(form(Users.class)));
    }

    public static Result addUser() {
        Form<Users> registerForm = form(Users.class).bindFromRequest();
        Users existingUser = Ebean.find(Users.class)
                .where().eq("email", registerForm.get().email).findUnique();
        if (existingUser != null) {
            return badRequest(register.render(form(Users.class)));
        } else {
            Ebean.save(registerForm.get());
            return redirect(routes.Authentication.login());
        }
    }


}
