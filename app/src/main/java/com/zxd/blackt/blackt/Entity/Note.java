package com.zxd.blackt.blackt.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhuangxd on 2017/5/17.
 */

@Entity
public class Note {
    @Id(autoincrement = true)//自增
    private Long nid;
    private String nname;
    private Date ndate;
    private String ncontent;
    @Generated(hash = 1917899488)
    public Note(Long nid, String nname, Date ndate, String ncontent) {
        this.nid = nid;
        this.nname = nname;
        this.ndate = ndate;
        this.ncontent = ncontent;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public Long getNid() {
        return this.nid;
    }
    public void setNid(Long nid) {
        this.nid = nid;
    }
    public String getNname() {
        return this.nname;
    }
    public void setNname(String nname) {
        this.nname = nname;
    }
    public Date getNdate() {
        return this.ndate;
    }
    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }
    public String getNcontent() {
        return this.ncontent;
    }
    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }
}
