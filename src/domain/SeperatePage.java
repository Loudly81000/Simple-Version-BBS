package domain;
import dao.PostDAO;
import dao.UserDAO;

import java.io.Serializable;

public class SeperatePage implements Serializable{

    public SeperatePage() {
    }

    private int pageNums;
    private int pageNow;
    private int allDataNums;
    final private int pageSize =3;
    PostDAO postDAO = new PostDAO();

    public SeperatePage(int pageNow, int allDataNums) {
        this.pageNums = postDAO.getPageNums();
        this.pageNow = pageNow;

        this.allDataNums = allDataNums;
    }

    public int getPageNums() {
        return pageNums;
    }

    public void setPageNums(int pageNums) {
        this.pageNums = pageNums;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getAllDataNums() {
        return allDataNums;
    }

    public void setAllDataNums(int allDataNums) {
        this.allDataNums = allDataNums;
    }

    public int getPageDataNums() {
        return pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    //select * from(select * from post_list order by post_id ) partOfUsers order by post_id ASC limit ?, ?;
    //need this sql statement to get comment of every page

}
