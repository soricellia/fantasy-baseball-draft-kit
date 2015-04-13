/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.file;

import fbbdk.data.Draft;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface provides an abstraction of what a file manager should do. Note
 * that file managers know how to read and write courses, instructors, and subjects,
 * but now how to export sites.
 * 
 * @author Tony
 */
public interface DraftFileManager {
    public void                 saveDraft(Draft courseToSave) throws IOException;
    public void                 loadDraft(Draft courseToLoad, String coursePath) throws IOException;
    public void                 savePlayers(List<Object> subjects, String filePath) throws IOException;
    public ArrayList<String>    loadPlayers(String filePath) throws IOException;
}
