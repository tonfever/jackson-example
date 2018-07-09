package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class ChecklistItem implements Serializable {
    private static final long serialVersionUID = -5081990188674716423L;

    private String checkListCd;
    private String checkListDesc;

    public String getCheckListCd() {
        return checkListCd;
    }

    public void setCheckListCd(String checkListCd) {
        this.checkListCd = checkListCd;
    }

    public String getCheckListDesc() {
        return checkListDesc;
    }

    public void setCheckListDesc(String checkListDesc) {
        this.checkListDesc = checkListDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChecklistItem that = (ChecklistItem) o;

        return checkListCd != null ? checkListCd.equals(that.checkListCd) : that.checkListCd == null;
    }

    @Override
    public int hashCode() {
        return checkListCd != null ? checkListCd.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ChecklistItem{" +
                "checkListCd='" + checkListCd + '\'' +
                ", checkListDesc='" + checkListDesc + '\'' +
                '}';
    }
}
