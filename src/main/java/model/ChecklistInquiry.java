package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
public class ChecklistInquiry implements Serializable {
    private static final long serialVersionUID = 243822421350168034L;

    private long taskId;
    private String procCd;
    private String taskCd;
    private Set<ChecklistItem> listOfCheckList;

    public String getProcCd() {
        return procCd;
    }

    public void setProcCd(String procCd) {
        this.procCd = procCd;
    }

    public String getTaskCd() {
        return taskCd;
    }

    public void setTaskCd(String taskCd) {
        this.taskCd = taskCd;
    }

    public Set<ChecklistItem> getListOfCheckList() {
        return listOfCheckList;
    }

    public void setListOfCheckList(Set<ChecklistItem> listOfCheckList) {
        this.listOfCheckList = listOfCheckList;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public void addItem(ChecklistItem item) {
        if (null == listOfCheckList) {
            listOfCheckList = new HashSet<>();
        }
        listOfCheckList.add(item);
    }

    @Override
    public String toString() {
        return "ChecklistInquiry{" +
                "procCd='" + procCd + '\'' +
                ", taskCd='" + taskCd + '\'' +
                ", listOfCheckList=" + listOfCheckList +
                ", taskId=" + taskId +
                '}';
    }
}
