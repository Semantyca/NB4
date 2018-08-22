package com.semantyca.nb.ui.action;

import java.util.ArrayList;
import java.util.List;

public class ActionBar{

    public String caption;
    public String hint;
    private ArrayList<Action> actions = new ArrayList<>();



    public ActionBar addAction(Action action) {
        //action.setupCaption(session, appEnv.getVocabulary());
        actions.add(action);
        return this;
    }

    public ActionBar addAction(List<Action> actions) {
        for (Action action : actions) {
            addAction(action);
        }
        return this;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
}
