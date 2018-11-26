package dao;

/**
 *
 * @author Paritosh
 */
public class Board {
    int boardID;
    String boardName;
    int projectLead;

    public Board(int boardID, String boardName, int projectLead) {
        this.boardID = boardID;
        this.boardName = boardName;
        this.projectLead = projectLead;
    }

    public int getBoardID() {
        return boardID;
    }

    public void setBoardID(int boardID) {
        this.boardID = boardID;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getProjectLead() {
        return projectLead;
    }

    public void setProjectLead(int projectLead) {
        this.projectLead = projectLead;
    }
  
}
