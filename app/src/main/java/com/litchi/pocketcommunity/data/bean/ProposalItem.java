package com.litchi.pocketcommunity.data.bean;

import java.util.Date;

public class ProposalItem {
    public static final int TYPE_FOLLOW_UP = 0;
    public static final int TYPE_RETURN = 1;
    public static final int TYPE_SUBMIT = 2;
    public static final int TYPE_FINISH = 3;

    private Integer id;

    private Integer workOrderId;

    private Integer processorId;

    private String processorName;

    private Integer nextProcessorId;

    private String nextProcessorName;

    private Integer type;

    private String message;

    private Date dealDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Integer getProcessorId() {
        return processorId;
    }

    public void setProcessorId(Integer processorId) {
        this.processorId = processorId;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public Integer getNextProcessorId() {
        return nextProcessorId;
    }

    public void setNextProcessorId(Integer nextProcessorId) {
        this.nextProcessorId = nextProcessorId;
    }

    public String getNextProcessorName() {
        return nextProcessorName;
    }

    public void setNextProcessorName(String nextProcessorName) {
        this.nextProcessorName = nextProcessorName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getTypeText() {
        switch (this.type){
            case TYPE_FOLLOW_UP:
                return "跟进";
            case TYPE_RETURN:
                return "回退";
            case TYPE_SUBMIT:
                return "提交";
            case TYPE_FINISH:
                return "结束";
            default:
                return "";
        }
    }
}
