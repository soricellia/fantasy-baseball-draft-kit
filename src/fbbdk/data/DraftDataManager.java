/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import fbbdk.file.DraftFileManager;

/**
 *
 * @author Tony
 */
public class DraftDataManager {

    Draft draft;
    DraftDataView ddv;
    DraftFileManager fileManager;

    final static String DEFAULT_DRAFT_NAME = "New Draft";

    public DraftDataManager(DraftDataView initView) {
        this.ddv = initView;
        draft = new Draft();
    }

    public Draft getDraft() {
        return draft;
    }

    public void reset() {
        draft.getTeams().clear();
        draft.getAvailablePlayers().clear();
        draft.setDraftName(DEFAULT_DRAFT_NAME);
        draft.getPickOrder().clear();

        ddv.reloadDraft(draft);
    }
}
