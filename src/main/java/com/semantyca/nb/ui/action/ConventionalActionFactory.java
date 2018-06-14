package com.semantyca.nb.ui.action;


import com.semantyca.nb.ui.action.constants.ActionEffect;
import com.semantyca.nb.ui.action.constants.ActionIcon;
import com.semantyca.nb.ui.action.constants.ActionType;

public class ConventionalActionFactory {
    public Action addNew = new Action(ActionType.LINK).id("new_").caption("new_");
    public Action refreshVew = new Action(ActionType.RELOAD).caption(null).hint("refresh").id("refresh").icon(ActionIcon.REFRESH);
    public Action close = new Action(ActionType.CLOSE).caption("close").icon("fa fa-chevron-left").cls("btn-back");
    public Action saveAndClose = new Action(ActionType.SAVE_AND_CLOSE).caption("save_close").cls("btn-primary");
    public Action deleteDocument = new Action(ActionType.DELETE_DOCUMENT).caption("delete")
            .cls(ActionEffect.WARNING).targetSingle().confirm();

    public static class Approving {

        public static Action startApproving(String uri) {
            return new Action().id("startApproving").caption("start_approving").url(uri + "startApproving");
        }

        public static Action signApprovalBlock(String uri) {
            return new Action().id("acceptApprovalBlock").caption("sign").url(uri + "acceptApprovalBlock");
        }

        public static Action acceptApprovalBlock(String uri) {
            return new Action().id("acceptApprovalBlock").caption("accept").url(uri + "acceptApprovalBlock");
        }

        public static Action declineApprovalBlock(String uri) {
            return new Action().id("declineApprovalBlock").caption("decline").url(uri + "declineApprovalBlock");
        }
    }
}
