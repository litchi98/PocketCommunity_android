package com.litchi.pocketcommunity.data.bean;

import java.util.Date;

public class Proposal {
    public static final int STATE_TO_BE_CONFIRMED = 0;
    public static final int STATE_TO_BE_PROCESSED = 1;
    public static final int STATE_PROCESSING = 2;
    public static final int STATE_FINISH = 3;

    private Integer id;

    private Integer proposerId;

    private Integer currentProcessorId;

    private Integer state;

    private String title;

    private String content;

    private Date proposeDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProposerId() {
        return proposerId;
    }

    public void setProposerId(Integer proposerId) {
        this.proposerId = proposerId;
    }

    public Integer getCurrentProcessorId() {
        return currentProcessorId;
    }

    public void setCurrentProcessorId(Integer currentProcessorId) {
        this.currentProcessorId = currentProcessorId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getProposeDate() {
        return proposeDate;
    }

    public void setProposeDate(Date proposeDate) {
        this.proposeDate = proposeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStateText() {
        switch (this.state){
            case STATE_TO_BE_PROCESSED:
                return "待处理";
            case STATE_PROCESSING:
                return "处理中";
            case STATE_TO_BE_CONFIRMED:
                return "待确认";
            case STATE_FINISH:
                return "已关闭";
            default:
                return "ERROR";
        }
    }
}
