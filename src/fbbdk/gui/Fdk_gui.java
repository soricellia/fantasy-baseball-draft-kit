/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fbbdk.data.Draft;
import fbbdk.data.DraftDataManager;
import fbbdk.data.DraftDataView;
import fbbdk.file.DraftFileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tony
 */
public class Fdk_gui implements DraftDataView{

    DraftFileManager dfm;
    DraftDataManager ddm;
    
    
    @Override
    public void reloadDraft(Draft draftToReload) {
        //TODO
    }
    
}
