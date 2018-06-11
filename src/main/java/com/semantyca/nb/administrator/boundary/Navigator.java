package com.semantyca.nb.administrator.boundary;

import com.semantyca.nb.administrator.init.AdmModuleConst;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.ui.outline.Outline;
import com.semantyca.nb.ui.outline.OutlineEntry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.LinkedList;

@Path(AdmModuleConst.BASE_URL + "navigator")
public class Navigator {
    private static final String BASE_URL = AdmModuleConst.BASE_URL;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNav() {
        Outline co = new Outline("Server settings", "server");
        co.addEntry(new OutlineEntry("Server parameters", "", "fa fa-server", "server", BASE_URL + "server"));
        co.addEntry(new OutlineEntry("User", "", "fa fa-users", "users", BASE_URL + "users"));
        co.addEntry(new OutlineEntry("Modules", "", "fa fa-puzzle-piece", "modules", BASE_URL + "modules"));
        co.addEntry(new OutlineEntry("Languages", "", "fa fa-globe", "languages", BASE_URL + "languages"));
        co.addEntry(new OutlineEntry("Subscriptions", "", "fa fa-paper-plane-o", "subscriptions", BASE_URL + "subscriptions"));
        co.addEntry(new OutlineEntry("Lang strings", "", "fa fa-language", "sentences", BASE_URL + "sentences"));

        Outline lo = new Outline("Log", "log");
        lo.addEntry(new OutlineEntry("Server", "", "fa fa-file-text-o", "logs/server", BASE_URL + "logs/s/server"));
        lo.addEntry(new OutlineEntry("Web", "", "fa fa-file-text-o", "logs/web", BASE_URL + "logs/s/web"));
        lo.addEntry(new OutlineEntry("Messaging", "", "fa fa-file-text-o", "logs/message", BASE_URL + "logs/s/message"));
        lo.addEntry(new OutlineEntry("Scheduler", "", "fa fa-file-text-o", "logs/scheduler", BASE_URL + "logs/s/scheduler"));
        lo.addEntry(new OutlineEntry("Localization", "", "fa fa-file-text-o", "logs/localization", BASE_URL + "logs/s/localization"));
        lo.addEntry(new OutlineEntry("Report", "", "fa fa-file-text-o", "logs/report", BASE_URL + "logs/s/report"));

        Outline scho = new Outline("Scheduler", "schedulers");
        scho.addEntry(new OutlineEntry("Tasks", "", "fa fa-tasks", "scheduled-tasks", BASE_URL + "tasks"));
        scho.addEntry(new OutlineEntry("Queue", "", "fa fa-tasks", "queue", BASE_URL + "queue"));

        Collection<Outline> list = new LinkedList<>();
        list.add(co);
        list.add(lo);
        list.add(scho);

        Outcome outcome = new Outcome();
        outcome.addPayload("nav", list);

        return Response.ok(outcome).build();
    }
}
