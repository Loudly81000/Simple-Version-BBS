package domain;
import dao.PostDAO;

import java.io.Serializable;
import java.util.ArrayList;

public class SeperatePage implements Serializable{

    private int pageCount;
    private int pageNow;
    final private int pageSize =3;

    public SeperatePage() {
    }

    public SeperatePage(int pageNow) {
        this.pageCount = PostDAO.getPageCount(pageSize);
        this.pageNow = pageNow;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    //select * from(select * from post_list order by post_id ) partOfUsers order by post_id ASC limit ?, ?;
    //need this sql statement to get comment of every page

    // page id index model
    public static ArrayList getPageIndexList(int pageCount, int pageNow, int pageSize){

        ArrayList <Integer> pageIndexList = new ArrayList<>();
        int pagePlus = pageNow +9;

        if(pageNow <= 10) {

            //page numbers(pageCount) <= 10
            if (pageCount <= 10) {
                //add page index to arraylist
                // if page index(now) >  page numbers, break
                for (int i = 1; i <= pageCount; i++) {
                    pageIndexList.add(i);
                }
            }

            //page numbers(pageCount) > 10
            if( pageCount > 10){
                //pageNow+9(pagePlus) < page numbers
                //for showing 10(or <10) page index one time
                //set index max = pagePlus , min = pageNow
                if(pagePlus < pageCount){
                    for(int i=pageNow; i<=pageNow+9 ;i++){
                        //although it is impossible to happen
                        //still set safety lock in case
                        if( pageNow > pageCount){
                            break;
                        }
                        pageIndexList.add(i);
                    }
                }

                if(pagePlus >= pageCount){
                    for(int i=pageCount-9;i<=pageCount;i++){
                        pageIndexList.add(i);
                    }
                }
            }

        }

        //now page index > 10
        if(pageNow > 10){
            //page numbers <= now page +10
            if(pageCount <= pagePlus){
                for(int i=pageCount-9;i<=pageCount;i++){
                    pageIndexList.add(i);
                }
            }

            if(pageCount > pagePlus){
                for(int i=pageNow;i<=pagePlus;i++){
                    pageIndexList.add(i);
                }
            }
        }

        return pageIndexList;
    }


}
